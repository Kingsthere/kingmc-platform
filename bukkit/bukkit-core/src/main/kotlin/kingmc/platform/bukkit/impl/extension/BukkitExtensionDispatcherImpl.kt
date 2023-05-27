package kingmc.platform.bukkit.impl.extension

import kingmc.common.OpenAPI
import kingmc.common.application.*
import kingmc.common.context.process.afterProcess
import kingmc.common.context.process.processBeans
import kingmc.common.logging.error
import kingmc.common.logging.slf4j.Slf4jLoggerManager
import kingmc.common.logging.slf4j.Slf4jLoggerWrapper
import kingmc.common.logging.warn
import kingmc.common.structure.classloader.ExtensionClassLoader
import kingmc.platform.ExperimentalPlatformApi
import kingmc.platform.bukkit.impl.driver.BukkitPlatformDriverImpl
import kingmc.platform.extension.*
import kingmc.platform.logging.infoColored
import kingmc.util.Lifecycle
import kotlinx.coroutines.*
import net.kyori.adventure.text.logger.slf4j.ComponentLogger
import java.io.File
import java.util.function.Consumer

/**
 * Bukkit [ExtensionDispatcher] implementation
 *
 * @since 0.0.8
 * @author kingsthere
 */
class BukkitExtensionDispatcherImpl(val driver: BukkitPlatformDriverImpl) : AbstractExtensionDispatcher(), ExtensionDispatcher {
    lateinit var extensionSourceDirectory: File
    lateinit var extractedExtensions: List<ExtensionClassSource>
    override lateinit var dispatchedExtensions: MutableList<ExtensionData>

    /**
     * Validate an extension file is valid for this `ExtensionDispatcher` to load
     */
    fun validateExtension(extension: File): Boolean {
        // Check if the extension file is a valid
        // extension that available to install
        return extension.extension == "jar"
    }

    /**
     * Try to extract extensions from a file
     *
     * @param extensionFile file to extract extensions from
     * @return the class loader to load classes for extension extracted from the file
     */
    fun recognizeExtensionFromJarFile(extensionFile: File): ExtensionClassSource? {
        infoColored("<grey>Recognizing extension(s) from $extensionFile...</grey>")

        return try {
            val classSource = ExtensionClassSource(
                file = extensionFile,
                extensionClassLoader = ExtensionClassLoader(extensionFile, OpenAPI.classLoader()!!).apply {
                    addToClassloaders()
                },
                parentFormatContext = driver.formatContext,
                properties = application.properties
            )
            classSource
        } catch (e: ClassNotFoundException) {
            error(msg = "Unable to load extension (Is it up to date or missing dependencies?)", throwable = e)
            null
        } catch (e: Exception) {
            error(msg = "Unable to load extension", throwable = e)
            null
        }
    }

    /**
     * Load maven dependencies for [extractedExtensions]
     */
    suspend fun loadExtensionMavenDependencies() = withContext(Dispatchers.IO) {
        extractedExtensions.forEach { extensionSource ->
            launch {
                extensionSource.loadMavenDependencies(driver.dependencyDispatcher, setOf(driver.mavenRepository))
            }
        }
    }

    fun loadExtension(source: ExtensionClassSource, lifecycle: Lifecycle<Runnable>): List<ExtensionData> {
        try {
            val extensionLoggers = Slf4jLoggerManager(Slf4jLoggerWrapper(ComponentLogger.logger(source.extensions.first().displayName)))
            val extensionEnvironment = ExtensionEnvironment(source.classLoader)
            val extensionDefinition = source.extensions.first()
            val extensionContext = ExtensionContextImpl(properties = driver.properties, name = extensionDefinition.id, extension = extensionDefinition)
            val extensionContextInitializer = ExtensionContextInitializer(extensionContext).apply {
                addSource(source)
                addParent(driver.application.context)
            }

            (0..5).forEach { index ->
                lifecycle.insertPlan(index) {
                    try {
                        extensionContext.processBeans(index)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            (0..5).forEach { index ->
                lifecycle.insertPlan(index) {
                    try {
                        extensionContext.afterProcess(index)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            val application = ExtensionApplicationImpl(driver.platform, extensionContext, extensionEnvironment, source, extensionLoggers)
            extensionContext.application = application

            val returns = mutableListOf<ExtensionData>()
            source.extensions.forEach {
                // Create data directory for extension
                File("$extensionSourceDirectory/${it.id}").mkdir()
                val extensionData = ExtensionData(it, extensionContext, extensionContextInitializer, application, source)
                returns.add(extensionData)
            }

            // Load extension lifecycle
            for (index in 0..4) {
                lifecycle.insertPlan(index) {
                    extensionContext.lifecycle().next()
                }
            }
            source.extensions.forEach {
                withApplication(application) {
                    infoColored(StringBuilder().apply {
                        append("<gradient:aqua:light_purple>Extension ${it.displayName}(${it.id}) v. ${it.tag}")
                        if (it.description.contributors.isNotEmpty()) {
                            append(" by ${it.description.contributors.joinToString(", ")}")
                        }
                        append(" has been loaded successfully</gradient>")
                    }.toString())
                    if (it.description.website != "https://www.example.com/") {
                        infoColored("<dark_grey>Check the website of this extension <blue>${it.description.website}</blue> for more information")
                    }
                }
            }
            return returns
        } catch (e: Exception) {
            error(msg = "Unable to load recognized extensions from $source", throwable = e)
            return emptyList()
        }
    }

    fun solveExtensionDependencies(extensions: List<ExtensionData>): ExtensionLoadingRoutes {
        return buildList {
            val firsts = mutableListOf<ExtensionLoadingRoute>()
            val callbacks = mutableMapOf<ExtensionData, Consumer<ExtensionLoadingRoute>>()

            fun findExtensionLoadingRoute(extension: ExtensionData, parent: ExtensionLoadingRoute): ExtensionLoadingRoute? {
                if (parent.extension == extension) {
                    return parent
                }
                for (nextRoute in parent.next) {
                    findExtensionLoadingRoute(extension, nextRoute)?.let {
                        return it
                    }
                }
                return null
            }

            fun findExtensionLoadingRoute(extension: ExtensionData): ExtensionLoadingRoute? {
                firsts.forEach {
                    findExtensionLoadingRoute(extension, it)?.let { found ->
                        return found
                    }
                }
                return null
            }

            fun findOrCallbackExtensionLoadingRoute(extension: ExtensionData, callback: (ExtensionLoadingRoute) -> Unit) {
                findExtensionLoadingRoute(extension)?.let {
                    callback.invoke(it)
                    return
                }
                callbacks.put(extension, callback)
            }

            extensions.forEach { extension ->
                // Get extension by the dependency name
                val dependencies = extension.definition.dependencies
                if (dependencies.isNotEmpty()) {
                    for (dependency in extension.definition.dependencies) {
                        val dependencyExtension = extensions.find { it.definition.id == dependency.id }
                        if (!dependency.optional) {
                            dependencyExtension ?: warn("Disable extension ${extension.definition.id} because the required dependencies of this plugin is missing (${dependency.id})")
                        }
                        dependencyExtension?.let {
                            findOrCallbackExtensionLoadingRoute(it) { parentRoute ->
                                val newedRoute = ExtensionLoadingRoute(extension, mutableListOf())
                                parentRoute.next.add(newedRoute)
                                callbacks.remove(extension)?.accept(newedRoute)
                                extension.contextInitializer.addParent(it.context)
                            }

                        }
                    }
                } else {
                    val new = ExtensionLoadingRoute(extension, mutableListOf())
                    firsts.add(new)
                    callbacks.remove(extension)?.accept(new)
                }
            }
            addAll(firsts)
        }
    }

    @WithApplication
    suspend fun loadExtensionsFromDirectory(directory: File, lifecycle: Lifecycle<Runnable>) = coroutineScope {
        val application = currentApplication()
        extensionSourceDirectory = directory
        val loadingExtensions = mutableListOf<ExtensionData>()

        fun finishLoadExtensionRecurves(extensionLoadingRoute: ExtensionLoadingRoute) {
            extensionLoadingRoute.extension.contextInitializer()
            extensionLoadingRoute.next.forEach {
                finishLoadExtensionRecurves(it)
            }
        }

        fun finishLoadExtensionsRecurves(extensionLoadingRoutes: ExtensionLoadingRoutes) {
            extensionLoadingRoutes.forEach {
                finishLoadExtensionRecurves(it)
            }
        }

        // Load extension classes
        val scope = CoroutineScope(Dispatchers.IO)
        extractedExtensions = (directory.listFiles()?.map { file ->
            scope.async {
                return@async withApplicationSuspend(application) {
                    if (validateExtension(file)) {
                        try {
                            recognizeExtensionFromJarFile(file)?.let { source ->
                                source.loadExtensionDefinitions()
                                source.loadProperties()
                                source.loadFormatContext()
                                return@withApplicationSuspend source
                            }
                        } catch (e: Exception) {
                            error(msg = "Unable to recognize extension for file $file", throwable = e)
                        }
                    }
                    null
                }
            }
        } ?: emptyList()).mapNotNull { it.await() }

        loadExtensionMavenDependencies()

        // Traverse the extensions in the extension
        // directory and load if the file is a valid extension
        // Make dirs if the direction is not exists
        extractedExtensions.map {
            launch {
                withApplicationSuspend(application) {
                    try {
                        val extension = loadExtension(it, lifecycle)
                        extension.forEach { ex ->
                            infoColored("<grey>Successfully load extension ${ex.definition.displayName} from ${ex.source}")
                        }
                        loadingExtensions.addAll(extension)
                    } catch (e: Exception) {
                        error(msg = "Unable to load extension from $it", throwable = e)
                    }
                }
            }
        }.joinAll()

        finishLoadExtensionsRecurves(solveExtensionDependencies(loadingExtensions))
        dispatchedExtensions = loadingExtensions
    }

    @WithApplication
    override fun disableExtension(extension: ExtensionData) {
        infoColored("<grey>Disabling extension ${extension.definition.id}(${extension.definition.displayName})...")
        extension.context.lifecycle().next()
        extension.contextInitializer.dispose()
        extension.application.shutdown()
        val classLoader = extension.source.extensionClassLoader
        classLoader.close()
        super.disableExtension(extension)
    }

    @WithApplication
    fun disableExtensions() {
        val disablingExtensions = this.dispatchedExtensions.toList()
        disablingExtensions.forEach {
            disableExtension(it)
        }
    }

    @WithApplication
    @ExperimentalPlatformApi
    override fun reload() {
        disableExtensions()
        val reloadLifecycle = Lifecycle.create<Runnable>()
        runBlocking {
            loadExtensionsFromDirectory(extensionSourceDirectory, reloadLifecycle)
            reloadLifecycle.jump(4)
        }
    }

    @WithApplication
    @ExperimentalPlatformApi
    override fun reload(extension: ExtensionData) {
        disableExtension(extension)
        val reloadLifecycle = Lifecycle.create<Runnable>()
        loadExtension(extension.source, reloadLifecycle)
        reloadLifecycle.jump(4)
    }
}