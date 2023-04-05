package kingmc.platform.bukkit.impl.extension

import kingmc.common.OpenAPI
import kingmc.common.application.WithApplication
import kingmc.common.application.application
import kingmc.common.context.process.insertProcessBeanLifecycle
import kingmc.common.logging.slf4j.Slf4jLoggerManager
import kingmc.common.logging.slf4j.Slf4jLoggerWrapper
import kingmc.common.structure.classloader.ExtensionClassLoader
import kingmc.platform.ExperimentalPlatformApi
import kingmc.platform.bukkit.impl.driver.BukkitPlatformDriverImpl
import kingmc.platform.extension.*
import kingmc.platform.logging.infoColored
import kingmc.util.Lifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import net.kyori.adventure.text.logger.slf4j.ComponentLogger
import java.io.File

/**
 * Bukkit [ExtensionDispatcher] implementation
 *
 * @since 0.0.8
 * @author kingsthere
 */
class BukkitExtensionDispatcherImpl(val driver: BukkitPlatformDriverImpl) : AbstractExtensionDispatcher(), ExtensionDispatcher {
    lateinit var extensionSourceDirectory: File

    @WithApplication
    fun loadExtensionsFromDirectory(directory: File, lifecycle: Lifecycle<Runnable>) {
        extensionSourceDirectory = directory
        val loadingExtensionData = mutableListOf<ExtensionData>()
        fun validExtension(extension: File): Boolean {
            // Check if the extension file is a valid
            // extension that available to install
            return extension.extension == "jar"
        }
        fun fetchExtensionClasses(extension: File): ExtensionClassLoader {
            infoColored("<grey>Loading extension from $extension...</grey>")
            val classLoader = ExtensionClassLoader(extension, OpenAPI.classLoader()!!)
            classLoader.addToClassloaders()
            return classLoader
        }
        fun loadExtension(extension: File, classLoader: ExtensionClassLoader): List<ExtensionData> {
            try {
                // Load the class loader for extension
                val source = ExtensionClassSource(extension, classLoader)

                runBlocking(Dispatchers.IO) {
                    source.loadDependencies(driver.dependencyDispatcher, setOf(driver.mavenRepository))
                }

                // Refresh classes
                source.load()
                source.getClasses()

                val extensionLoggers = Slf4jLoggerManager(Slf4jLoggerWrapper(ComponentLogger.logger(source.extensions.first().name)))
                val extensionEnvironment = ExtensionEnvironment(classLoader)
                val extensionDefinition = source.extensions.first()
                val extensionContext = ExtensionContextImpl(name = extensionDefinition.id, extension = extensionDefinition)
                val extensionContextInitializer = ExtensionContextInitializer(extensionContext).apply {
                    addSource(source)
                    addParent(driver.application.context)
                }
                extensionContext.insertProcessBeanLifecycle(5)

                val application = ExtensionApplicationImpl(driver.platform, extensionContext, extensionEnvironment, source, extensionLoggers, driver.properties)
                extensionContext.application = application

                val returns = mutableListOf<ExtensionData>()
                source.extensions.forEach {
                    // Create data directory for extension
                    File("$directory/${it.id}").mkdir()
                    val extensionData = ExtensionData(it, extensionContext, extensionContextInitializer, application, source)
                    returns.add(extensionData)
                    loadingExtensionData.add(extensionData)
                }

                // Load extension lifecycle
                for (index in 0..4) {
                    lifecycle.insertPlan(index) {
                        extensionContext.lifecycle().next()
                    }
                }
                source.extensions.forEach {
                    application(application) {
                        infoColored("<gradient:aqua:light_purple>Extension loaded ${it.name}(${it.id}) v. ${it.tag} by ${it.description.contributors.joinToString(", ")}</gradient>")
                        if (it.description.website != "https://www.example.com/") {
                            infoColored("<dark_grey>Check the website of this extension <blue>${it.description.website}</blue> for more information")
                        }
                    }
                }
                return returns
            } catch (e: Exception) {
                throw ExtensionInitializeException("Unable to load extension from file $extension", e)
            }
        }
        fun finishLoadExtensions() {
            loadingExtensionData.forEach { extension ->
                extension.contextInitializer()
            }
        }
        fun loadExtensionDependencies() {
            loadingExtensionData.forEach { extension ->
                // Get extension by the dependency name
                for (dependency in extension.definition.dependencies) {
                    val dependencyExtension = loadingExtensionData.find { it.definition.id == dependency.id }
                    if (!dependency.optional) {
                        dependencyExtension ?: throw ExtensionInitializeException("Unable to load extension ${extension.definition.id} cause the required dependencies of this plugin is not found")
                    }
                    dependencyExtension?.let {
                        extension.contextInitializer.addParent(it.context)
                    }
                }
            }
        }

        val loadedExtensionClassLoaders = mutableMapOf<File, ExtensionClassLoader>()
        // Load extension classes
        directory.listFiles()?.forEach { file ->
            if (validExtension(file)) {
                loadedExtensionClassLoaders.put(file, fetchExtensionClasses(file))
            }
        }
        // Traverse the extensions in the extension
        // directory and load if the file is a valid extension
        // Make dirs if the direction is not exists
        val loadedExtensionData = mutableListOf<ExtensionData>()
        directory.listFiles()?.forEach {
            // Check if the file is a valid extension
            if (validExtension(it)) {
                try {
                    val loadingExtensions = loadExtension(extension = it, classLoader = loadedExtensionClassLoaders[it]
                        ?: throw IllegalStateException("Unable to load this extension, because the classloader of this extension is not loaded"))
                    loadingExtensions.forEach { ex ->
                        infoColored("<grey>Successfully load extension ${ex.definition.name}")
                    }
                    loadedExtensionData.addAll(loadingExtensions)
                } catch (e: Exception) {
                    kingmc.common.logging.error("Unable to load extension from file $it, cause:")
                    e.printStackTrace()
                }
            }
        }
        loadExtensionDependencies()
        finishLoadExtensions()
        this.loadedExtensions.addAll(loadedExtensionData)
    }

    override fun disableExtension(extension: ExtensionData) {
        extension.context.lifecycle().next()
        extension.contextInitializer.dispose()
        extension.application.shutdown()
        super.disableExtension(extension)
    }

    fun disableExtensions() {
        this.loadedExtensions.toList().forEach {
            disableExtension(it)
        }
    }

    @ExperimentalPlatformApi
    override fun reload() {
        disableExtensions()
        val reloadedLifecycle = Lifecycle.create<Runnable>()
        loadExtensionsFromDirectory(extensionSourceDirectory, reloadedLifecycle)
        reloadedLifecycle.jump(4)
    }

    @ExperimentalPlatformApi
    override fun reload(extension: ExtensionData) {
        TODO("Not yet implemented")
    }
}