package kingmc.platform.bukkit

import kingmc.common.KingMC
import kingmc.common.OpenAPI
import kingmc.common.application.application
import kingmc.common.context.ContextDefiner
import kingmc.common.context.context
import kingmc.common.context.contextLifecycle
import kingmc.common.context.process.inheritProcessors
import kingmc.common.context.process.insertProcessBeanLifecycle
import kingmc.common.coroutine.*
import kingmc.common.environment.*
import kingmc.common.logging.*
import kingmc.common.structure.FileProject
import kingmc.common.structure.plugin.PluginClassLoader
import kingmc.platform.*
import kingmc.platform.audience.text.*
import kingmc.platform.bukkit.audience.adventure.Adventure
import kingmc.platform.bukkit.coroutine.AsyncBukkitCoroutineDispatcher
import kingmc.platform.bukkit.coroutine.SyncBukkitCoroutineDispatcher
import kingmc.platform.bukkit.paper.PaperPlatform
import kingmc.platform.bukkit.spigot.SpigotPlatform
import kingmc.platform.context.*
import kingmc.platform.extension.*
import kingmc.platform.logging.infoColored
import kingmc.util.Lifecycle
import kingmc.util.Reloadable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.runBlocking
import org.bukkit.plugin.java.JavaPlugin
import org.slf4j.Logger
import java.io.File
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.nio.file.Files
import java.security.AccessController
import java.security.PrivilegedAction
import java.util.regex.Pattern
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

/**
 * Bukkit plugin main class uses in kingmc
 *
 * @see JavaPlugin
 * @see bukkitPluginInstance
 * @since 0.0.3
 * @author kingsthere
 */
@Suppress("unused")
class BukkitPlugin(private val bukkitPluginInstance: BukkitJavaPlugin, private val startFrom: Long): Reloadable {
    private val _logger: Logger = ComponentLogger.logger("KingMC")
    var isEnvironmentLoaded: Boolean = false
    lateinit var platformContextInitializer: PlatformContextInitializer
    lateinit var syncMinecraftCoroutineDispatcher: SyncBukkitCoroutineDispatcher
    lateinit var asyncMinecraftCoroutineDispatcher: AsyncBukkitCoroutineDispatcher

    fun onLoad() {
        if (!isEnvironmentLoaded) {
            // Load dependencies for additional contents
            runBlocking(Dispatchers.IO) {
                DefaultEnv::class.loadDependenciesSuspend()
                Adventure::class.loadDependenciesSuspend()
                Class.forName("kingmc.platform.bukkit.nbtapi.NBTAPIEnvironment").kotlin.loadDependenciesSuspend()
                Class.forName("kingmc.platform.bukkit.brigadier.BrigadierEnvironment").kotlin.loadDependenciesSuspend()
                isEnvironmentLoaded = true
            }
        }

        /*
         Initialize the environments to load the kingmc
         framework
         */
        bukkitServerClass = bukkitPluginInstance.server.javaClass
        kingmc.platform.bukkit.bukkitPluginInstance = bukkitPluginInstance
        pluginFile = bukkitPluginInstance.file
        bukkitPluginDriver = this

        syncMinecraftCoroutineDispatcher = SyncBukkitCoroutineDispatcher(kingmc.platform.bukkit.bukkitPluginInstance)
        asyncMinecraftCoroutineDispatcher = AsyncBukkitCoroutineDispatcher(kingmc.platform.bukkit.bukkitPluginInstance)

        // Setup base dir of server
        baseDirectory = File("${bukkitPluginInstance.server.worldContainer}/kingmc")

        val platformContextSource = FileProject(pluginFile, bukkitPluginInstance.classLoader)

        // Load the platform
        val platform = loadPlatform()
        bukkitPlatform = platform

        val context = PlatformContextImpl("kingmc")
        platformContextInitializer = PlatformContextInitializer(context).apply {
            addSource(platformContextSource)
        }
        val environment = PlatformApplicationEnvironment(bukkitPluginInstance.classLoader, Dispatchers.Default)
        val loggers = slf4jLoggers(this._logger)
        val application: PlatformApplication<PlatformContext> = PlatformApplicationImpl(platform, context, environment, loggers)
        context.application = application
        Platforms.registerPlatform(application.platform, application.context)
        platform.context = application.context

        ContextDefiner.defineBeanClass(this::class.java, application.context)

        platformContextInitializer()
        context.insertProcessBeanLifecycle(5)
        application {
            loadExtensions(this@BukkitPlugin.contextLifecycle)
            // Load processor beans defined in the extension
            loadExtensionsProcessors()
            // Load extension's default resources that defined in jar
            loadExtensionDefaultResources()
        }

        application {
            // Log the current kingmc framework information
            info("")
            infoColored(""" _  ___             __  __  _____  """)
            infoColored("""| |/ (_)           |  \/  |/ ____| """)
            infoColored("""| ' / _ _ __   __ _| \  / | |      """)
            infoColored("""|  < | | '_ \ / _` | |\/| | |      """)
            infoColored("""| . \| | | | | (_| | |  | | |____  """)
            infoColored("""|_|\_\_|_| |_|\__, |_|  |_|\_____| """)
            infoColored("""               __/ |               """)
            infoColored("""              |___/                """)
            infoColored("""<grey>v: ${KingMC.VERSION} 2018 - 2022 ©""")
            info("")

            infoColored("<yellow>Booting up by driver ${this@BukkitPlugin::class}")
            infoColored("<grey>Booting up based on platform $platform (${System.currentTimeMillis() - startFrom}ms)")
        }

        /*
         Booting lifecycles
          + Lifecycle.const (stage 0)
          + Lifecycle.initialize (stage 1)
          + Lifecycle.load (stage 2)
         */
        contextLifecycle.jump(3)
    }

    private fun loadPlatform(): BukkitPlatform {
        val versionPattern = Pattern.compile("(?i)\\(MC: (\\d\\.\\d+\\.?\\d+?)?(?: Pre-Release )?(\\d)?\\)")
        val versionMatcher = versionPattern.matcher(Bukkit.getVersion())
        val minecraftVersion = if (versionMatcher.find()) {
            versionMatcher.group(1)
        } else {
            throw IllegalStateException("Unable to fetch minecraft version")
        }
        return try {
            Class.forName("com.destroystokyo.paper.PaperConfig")
            PaperPlatform(minecraftVersion)
        } catch (e: ClassNotFoundException) {
            try {
                Class.forName("org.spigotmc.SpigotConfig")
                SpigotPlatform(minecraftVersion)
            } catch (e1: ClassNotFoundException) {
                BukkitPlatform(minecraftVersion)
            }
        }
    }

    fun onEnable() {
        // Lifecycle.enable (stage 3)
        contextLifecycle.next()

    }

    fun onDisable() {
        // Lifecycle.disable (stage 4)
        contextLifecycle.next()
        platformContextInitializer.dispose()
        extensionContainer.forEach {
            it.contextInitializer.dispose()
        }
    }

    private fun loadExtensionsProcessors() {
        extensionContainer.forEach {
            it.context.inheritProcessors(this.context)
        }
    }

    private fun loadExtensionDefaultResources() {
        extensionContainer.forEach {
            val resourcesLoader = it.application.environment.classLoader
            application(it.application) {
                val jar: URL = it.project.file.toURI().toURL()
                val zip = ZipInputStream(jar.openStream())
                var ze: ZipEntry?
                val resourceNames: MutableList<String> = mutableListOf()
                while (zip.nextEntry.also { entry -> ze = entry } != null) {
                    val entryName: String = ze!!.name
                    if (entryName.startsWith("default/") && entryName.contains(".")) {
                        resourceNames.add(entryName)
                    }
                }
                resourceNames.forEach { res ->
                    loadExtensionDefaultResourcesFromFile(resourcesLoader.getResource(res)!!, ExtensionFile(res.substringAfter("default/")))
                }
            }
        }
    }

    private fun loadExtensionDefaultResourcesFromFile(resourceTemplate: URL, resourceInstance: File) {
        if (!resourceInstance.exists()) {
            info("Copying resources from $resourceTemplate")
            try {
                resourceTemplate.openStream().use { input ->
                    info(resourceInstance.toString())
                    if (!resourceInstance.parentFile.exists()) {
                        resourceInstance.parentFile.mkdirs()
                    }
                    Files.copy(input, resourceInstance.toPath())
                }
            } catch (e: IOException) {
                warn("Unable to load resource $resourceTemplate")
                e.printStackTrace()
            } catch (e: MalformedURLException) {
                warn("Unable to load resource $resourceTemplate")
                e.printStackTrace()
            }
        }
    }

    /**
     * Load extensions that added in kingmc/extension
     *
     * @since 0.0.3
     */
    private fun loadExtensions(contextLifecycle: Lifecycle<Runnable>) {
        val extensions = mutableListOf<LoadedExtension>()
        val extensionsDir = File("$baseDirectory/extensions")
        fun validExtension(extension: File): Boolean {
            // Check if the extension file is a valid
            // extension that available to install
            return extension.extension == "jar"
        }
        fun fetchExtensionClasses(extension: File): PluginClassLoader {
            infoColored("<grey>Loading extension from $extension...</grey>")
            val classLoader = AccessController.doPrivileged (PrivilegedAction {
                PluginClassLoader(
                    extension, OpenAPI.classLoader()!!
                )
            })
            classLoader.addToClassloaders()
            return classLoader
        }
        fun loadExtension(extension: File, classLoader: PluginClassLoader): List<LoadedExtension> {
            try {
                // Load the class loader for extension
                val extensionContextSource = ExtensionProject(extension, classLoader)
                // Refresh classes
                extensionContextSource.getClasses()

                val extensionLoggers = slf4jLoggers(ComponentLogger.logger(extensionContextSource.extensions.first().name))
                val extensionEnvironment = ExtensionEnvironment(classLoader, syncMinecraftCoroutineDispatcher)
                val extensionContext = ExtensionContextImpl(name = extensionContextSource.extensions.first().id)
                val extensionContextInitializer = ExtensionContextInitializer(extensionContext).apply {
                    addSource(extensionContextSource)
                    addParent(this@BukkitPlugin.context)
                }
                extensionContext.insertProcessBeanLifecycle(5)

                val application = ExtensionApplicationImpl(bukkitPlatform, extensionContext, extensionEnvironment, extensionContextSource, extensionLoggers)
                extensionContext.application = application

                val returns = mutableListOf<LoadedExtension>()
                extensionContextSource.extensions.forEach {
                    // Create data directory for extension
                    File("$extensionsDir/${it.id}").mkdir()
                    val loadedExtension = LoadedExtension(it, extensionContext, extensionContextInitializer, application, extensionContextSource)
                    returns.add(loadedExtension)
                    extensions.add(loadedExtension)
                }

                // Load extension lifecycle
                for (index in 0..4) {
                    contextLifecycle.insertPlan(index) {
                        extensionContext.lifecycle().next()
                    }
                }
                extensionContextSource.extensions.forEach {
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
            extensions.forEach { extension ->
                extension.contextInitializer()
            }
        }
        fun loadExtensionDependencies() {
            extensions.forEach { extension ->
                // Get extension by the dependency name
                for (dependency in extension.definition.dependencies) {
                    val dependencyExtension = extensions.find { it.definition.id == dependency.id }
                    if (!dependency.optional) {
                        dependencyExtension ?: throw ExtensionInitializeException("Unable to load extension ${extension.definition.id} cause the required dependencies of this plugin is not found")
                    }
                    dependencyExtension?.let {
                        extension.contextInitializer.addParent(it.context)
                    }
                }
            }
        }

        extensionContainer = DefaultExtensionContainer()
        val loadedExtensionClassLoaders = mutableMapOf<File, PluginClassLoader>()
        // Load extension classes
        extensionsDir.listFiles()?.forEach { file ->
            if (validExtension(file)) {
                loadedExtensionClassLoaders.put(file, fetchExtensionClasses(file))
            }
        }
        // Traverse the extensions in the extension
        // directory and load if the file is a valid extension
        // Make dirs if the direction is not exists
        val loadedExtensions = mutableListOf<LoadedExtension>()
        extensionsDir.listFiles()?.forEach {
            // Check if the file is a valid extension
            if (validExtension(it)) {
                try {
                    val loadingExtensions = loadExtension(extension = it, classLoader = loadedExtensionClassLoaders[it]
                        ?: throw IllegalStateException("Unable to load this extension, because the classloader of this extension is not loaded"))
                    loadingExtensions.forEach { ex ->
                        infoColored("<grey>Successfully load extension ${ex.definition.name}")
                    }
                    loadedExtensions.addAll(loadingExtensions)
                } catch (e: Exception) {
                    error("Unable to load extension from file $it, cause:")
                    e.printStackTrace()
                }
            }
        }
        loadExtensionDependencies()
        finishLoadExtensions()
        extensionContainer.addAll(loadedExtensions)
    }

    /**
     * Reload all extensions in kingmc
     */
    @ExperimentalPlatformApi
    override fun reload() {
        // Dispose loaded extensions
        extensionContainer.forEach { extension ->
            extension.application.dispose()
        }
        val reloadedLifecycle = Lifecycle.create<Runnable>()
        val extensions = mutableListOf<LoadedExtension>()
        val extensionsDir = File("$baseDirectory/extensions")
        fun validExtension(extension: File): Boolean {
            // Check if the extension file is a valid
            // extension that available to install
            return extension.extension == "jar"
        }
        fun fetchExtensionClasses(extension: File): PluginClassLoader {
            infoColored("<grey>Loading extension from $extension...</grey>")
            val classLoader = AccessController.doPrivileged (PrivilegedAction {
                PluginClassLoader(
                    extension, OpenAPI.classLoader()!!
                )
            })
            classLoader.addToClassloaders()
            return classLoader
        }
        fun loadExtension(extension: File, classLoader: PluginClassLoader): List<LoadedExtension> {
            try {
                // Load the class loader for extension
                val extensionContextSource = ExtensionProject(extension, classLoader)
                // Refresh classes
                extensionContextSource.getClasses()

                val extensionLoggers = slf4jLoggers(ComponentLogger.logger(extensionContextSource.extensions.first().name))
                val extensionEnvironment = ExtensionEnvironment(classLoader, syncMinecraftCoroutineDispatcher)
                val extensionContext = ExtensionContextImpl(name = extensionContextSource.extensions.first().id)
                val extensionContextInitializer = ExtensionContextInitializer(extensionContext).apply {
                    addSource(extensionContextSource)
                    addParent(this@BukkitPlugin.context)
                }
                extensionContext.insertProcessBeanLifecycle(5)

                val application = ExtensionApplicationImpl(bukkitPlatform, extensionContext, extensionEnvironment, extensionContextSource, extensionLoggers)
                extensionContext.application = application

                val returns = mutableListOf<LoadedExtension>()
                extensionContextSource.extensions.forEach {
                    // Create data directory for extension
                    File("$extensionsDir/${it.id}").mkdir()
                    val loadedExtension = LoadedExtension(it, extensionContext, extensionContextInitializer, application, extensionContextSource)
                    returns.add(loadedExtension)
                    extensions.add(loadedExtension)
                }

                // Load extension lifecycle
                for (index in 0..4) {
                    reloadedLifecycle.insertPlan(index) {
                        extensionContext.lifecycle().next()
                    }
                }
                extensionContextSource.extensions.forEach {
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
            extensions.forEach { extension ->
                extension.contextInitializer()
            }
        }
        fun loadExtensionDependencies() {
            extensions.forEach { extension ->
                // Get extension by the dependency name
                for (dependency in extension.definition.dependencies) {
                    val dependencyExtension = extensions.find { it.definition.id == dependency.id }
                    if (!dependency.optional) {
                        dependencyExtension ?: throw ExtensionInitializeException("Unable to load extension ${extension.definition.id} cause the required dependencies of this plugin is not found")
                    }
                    dependencyExtension?.let {
                        extension.contextInitializer.addParent(it.context)
                    }
                }
            }
        }

        extensionContainer = DefaultExtensionContainer()
        val loadedExtensionClassLoaders = mutableMapOf<File, PluginClassLoader>()
        // Load extension classes
        extensionsDir.listFiles()?.forEach { file ->
            if (validExtension(file)) {
                loadedExtensionClassLoaders.put(file, fetchExtensionClasses(file))
            }
        }
        // Traverse the extensions in the extension
        // directory and load if the file is a valid extension
        // Make dirs if the direction is not exists
        val loadedExtensions = mutableListOf<LoadedExtension>()
        extensionsDir.listFiles()?.forEach {
            // Check if the file is a valid extension
            if (validExtension(it)) {
                try {
                    val loadingExtensions = loadExtension(extension = it, classLoader = loadedExtensionClassLoaders[it]
                        ?: throw IllegalStateException("Unable to load this extension, because the classloader of this extension is not loaded"))
                    loadingExtensions.forEach { ex ->
                        infoColored("<grey>Successfully load extension ${ex.definition.name}")
                    }
                    loadedExtensions.addAll(loadingExtensions)
                } catch (e: Exception) {
                    error("Unable to load extension from file $it, cause:")
                    e.printStackTrace()
                }
            }
        }
        loadExtensionDependencies()
        finishLoadExtensions()
        extensionContainer.addAll(loadedExtensions)
        reloadedLifecycle.jump(3)
        reloadedLifecycle.next()
        contextLifecycle.insertPlan(4) { reloadedLifecycle.next() }
    }
}