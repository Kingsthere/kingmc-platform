package kingmc.platform.bukkit.impl.driver

import com.electronwill.nightconfig.core.Config
import com.electronwill.nightconfig.core.file.FileConfig
import com.electronwill.nightconfig.toml.TomlFormat
import com.electronwill.nightconfig.yaml.YamlFormat
import kingmc.common.KingMC
import kingmc.common.OpenAPI
import kingmc.common.application.withApplication
import kingmc.common.context.LifecycleContext
import kingmc.common.context.initializer.ContextInitializer
import kingmc.common.context.process.insertProcessBeanLifecycle
import kingmc.common.environment.KingMCEnvironment
import kingmc.common.environment.KotlinCoroutineEnvironment
import kingmc.common.environment.loadDependencies
import kingmc.common.environment.loadDependenciesSuspend
import kingmc.common.environment.maven.DependencyDispatcher
import kingmc.common.environment.maven.DependencyScope
import kingmc.common.environment.maven.model.Dependency
import kingmc.common.environment.maven.model.Repository
import kingmc.common.environment.maven.model.dependency
import kingmc.common.environment.maven.model.repository
import kingmc.common.logging.info
import kingmc.common.logging.slf4j.Slf4jLogger
import kingmc.common.logging.slf4j.Slf4jLoggerManager
import kingmc.common.logging.slf4j.Slf4jLoggerWrapper
import kingmc.common.structure.JarFileClassSource
import kingmc.common.structure.classloader.ExtensionClassLoader
import kingmc.platform.BukkitJavaPlugin
import kingmc.platform.ExperimentalPlatformApi
import kingmc.platform.Platforms
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit._BukkitServer
import kingmc.platform.bukkit.adventure.Adventure
import kingmc.platform.bukkit.driver.BukkitPlatformDriver
import kingmc.platform.bukkit.impl.BukkitPlatform
import kingmc.platform.bukkit.impl.extension.BukkitExtensionDispatcherImpl
import kingmc.platform.bukkit.impl.paper.PaperImplementation
import kingmc.platform.bukkit.impl.paper.PaperPlatform
import kingmc.platform.bukkit.impl.spigot.SpigotImplementation
import kingmc.platform.bukkit.impl.spigot.SpigotPlatform
import kingmc.platform.bukkit.paperlib.PaperLib
import kingmc.platform.context.*
import kingmc.platform.logging.infoColored
import kingmc.util.Lifecycle
import kingmc.util.Version
import kingmc.util.format.PropertiesFormatContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import net.kyori.adventure.text.logger.slf4j.ComponentLogger
import org.apache.commons.io.FileUtils
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.util.*
import java.util.function.Consumer
import java.util.regex.Pattern
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

val VERSION_REGEX = Pattern.compile("(?i)\\(MC: (\\d\\.\\d+\\.?\\d+?)?(?: Pre-Release )?(\\d)?\\)")!!

/**
 * `PlatformDriver` implementation for bukkit
 *
 * @since 0.0.7
 * @author kingsthere
 */
@BukkitImplementation
@SpigotImplementation
@PaperImplementation
open class BukkitPlatformDriverImpl(protected val _bukkitJavaPlugin: BukkitJavaPlugin) : BukkitPlatformDriver {
    override val platform: BukkitPlatform
    private val _rawLogger: Slf4jLogger = ComponentLogger.logger("KingMC")
    override val logger: Slf4jLoggerWrapper = Slf4jLoggerWrapper(_rawLogger)
    override lateinit var properties: Properties
    override val dependencyDispatcher: DependencyDispatcher = DependencyDispatcher(
        root = File("${_bukkitJavaPlugin.kingmcRoot}/libraries"),
        logger = logger
    )

    override val bukkitPluginInstance: JavaPlugin = _bukkitJavaPlugin
    override val bukkitServerClass: Class<out _BukkitServer> = _bukkitJavaPlugin.server::class.java
    override val pluginFile: File = _bukkitJavaPlugin.file

    override val extensionDispatcher: BukkitExtensionDispatcherImpl = BukkitExtensionDispatcherImpl(this)

    override lateinit var application: PlatformApplication
    lateinit var contextInitializer: ContextInitializer
    lateinit var contextLifecycle: Lifecycle<Runnable>

    override val baseDirectory: File = _bukkitJavaPlugin.kingmcRoot
    val extensionDirectory: File = File("$baseDirectory/extensions").apply {
        if (!exists()) {
            mkdirs()
        }
    }
    val driverDirectory: File = File("$baseDirectory/drivers").apply {
        if (!exists()) {
            mkdirs()
        }
    }

    init {
        OpenAPI.supportClassLoader(_bukkitJavaPlugin.classLoader)

        loadConfigurationEnvironment()
        loadProperties()
        platform = loadPlatform()
        println(properties)
    }

    val formatContext = PropertiesFormatContext(properties)
    val mavenRepository = repository("{ kingmc.environment.maven-repository }", formatContext)

    @OptIn(ExperimentalTime::class)
    override fun load() {
        val time = measureTime {
            disposeTempFilesOnExit()
            loadCoroutineEnvironment()
            runBlocking {
                loadFullEnvironmentSuspend()
            }
            application = loadPlatformApplication()
            contextLifecycle = (application.context as LifecycleContext).lifecycle()

            withApplication(this.application) {
                // Log the current kingmc framework information
                info("")
                infoColored("""<gradient:green:aqua> _  ___             __  __  _____  """)
                infoColored("""<gradient:green:aqua>| |/ (_)           |  \/  |/ ____| """)
                infoColored("""<gradient:green:aqua>| ' / _ _ __   __ _| \  / | |      """)
                infoColored("""<gradient:green:aqua>|  < | | '_ \ / _` | |\/| | |      """)
                infoColored("""<gradient:green:aqua>| . \| | | | | (_| | |  | | |____  """)
                infoColored("""<gradient:green:aqua>|_|\_\_|_| |_|\__, |_|  |_|\_____| """)
                infoColored("""<gradient:green:aqua>               __/ |               """)
                infoColored("""<gradient:green:aqua>              |___/                """)
                infoColored("""<grey>v: ${KingMC.VERSION} 2018 - 2023 ©""")
                info("")

                infoColored("<yellow>Booting up by driver $this")

                runBlocking {
                    info("Loading extensions from $extensionDirectory...")
                    extensionDispatcher.loadExtensionsFromDirectory(extensionDirectory, contextLifecycle)
                }

            }
        }

        withApplication(this.application) {
            infoColored("<green>KingMC framework launched successfully on platform $platform in $time")
        }

        // Lifecycle.const (stage 0)
        // Lifecycle.initialize (stage 1)
        // Lifecycle.load (stage 2)
        contextLifecycle.jump(3)
    }

    override fun loadPlatformApplication(): PlatformApplication {
        val classLoader = _bukkitJavaPlugin.classLoader
        val source = JarFileClassSource(pluginFile, classLoader)
        source.load()
        val environment = PlatformApplicationEnvironment(classLoader)
        val loggers = Slf4jLoggerManager(logger)

        val context = PlatformContextImpl(properties, "kingmc")
        contextInitializer = PlatformContextInitializer(context).apply {
            addSource(source)
            driverDirectory.listFiles()?.forEach {
                if (it.extension == "jar") {
                    _rawLogger.info("Loading driver from $it")
                    val driverClassLoader = ExtensionClassLoader(it, OpenAPI.classLoader()!!)
                    driverClassLoader.addToClassloaders()
                    addSource(JarFileClassSource(it, driverClassLoader).apply { load() })
                }
            }
        }

        val application: PlatformApplication = PlatformApplicationImpl(platform, context, environment, loggers)
        context.application = application

        Platforms.registerPlatform(application.platform, application.context)

        contextInitializer.invoke()

        context.insertProcessBeanLifecycle(5)

        return application
    }

    protected open fun loadCoroutineEnvironment() {
        dependencyDispatcher.installDependency(
            dependency(groupId = "org.jetbrains.kotlin", artifactId = "kotlin-reflect", version = "{ kingmc.environment.kotlin }", scope = DependencyScope.RUNTIME, formatContext),
            setOf(mavenRepository),
            emptySet(),
            DependencyScope.RUNTIME
        )
        dependencyDispatcher.installDependency(
            Dependency("cglib", "cglib", "3.3.0", DependencyScope.RUNTIME),
            setOf(mavenRepository),
            emptySet()
        )
        // Kotlin coroutine dependency
        KotlinCoroutineEnvironment::class.loadDependencies(dependencyDispatcher, formatContext)
    }

    protected open fun loadConfigurationEnvironment() {
        dependencyDispatcher.installDependency(
            Dependency("com.electronwill.night-config", "core", "3.6.5", DependencyScope.RUNTIME),
            setOf(Repository("https://maven.aliyun.com/repository/public/")),
            emptySet()
        )
        dependencyDispatcher.installDependency(
            Dependency("com.electronwill.night-config", "yaml", "3.6.5", DependencyScope.RUNTIME),
            setOf(Repository("https://maven.aliyun.com/repository/public/")),
            emptySet()
        )
        dependencyDispatcher.installDependency(
            Dependency("com.electronwill.night-config", "toml", "3.6.5", DependencyScope.RUNTIME),
            setOf(Repository("https://maven.aliyun.com/repository/public/")),
            emptySet()
        )
    }
    protected open suspend fun loadFullEnvironmentSuspend() = withContext(Dispatchers.IO) {
        launch {
            KingMCEnvironment::class.loadDependenciesSuspend(dependencyDispatcher, formatContext)
        }
        launch {
            Adventure::class.loadDependenciesSuspend(dependencyDispatcher, formatContext)
        }
        launch {
            PaperLib::class.loadDependenciesSuspend(dependencyDispatcher, formatContext)
        }
        launch {
            Class.forName("kingmc.platform.bukkit.nbtapi.NBTAPIEnvironment").kotlin.loadDependenciesSuspend(dependencyDispatcher, formatContext)
        }
        launch {
            Class.forName("kingmc.platform.bukkit.brigadier.BrigadierEnvironment").kotlin.loadDependenciesSuspend(dependencyDispatcher, formatContext)
        }
    }
    protected open fun loadPlatform(): BukkitPlatform {
        // Fetch minecraft version use regex
        val versionMatcher = checkNotNull(VERSION_REGEX.matcher(Bukkit.getVersion())) { "Unable to fetch minecraft version" }
        versionMatcher.find()
        val minecraftVersion = Version(checkNotNull(versionMatcher.group(1)) { "Unable to fetch minecraft version" })
        // Instantiate `Platform` instance
        return try {
            Class.forName("com.destroystokyo.paper.PaperConfig")
            PaperPlatform(minecraftVersion, this)
        } catch (e1: ClassNotFoundException) {
            try {
                Class.forName("org.spigotmc.SpigotConfig")
                SpigotPlatform(minecraftVersion, this)
            } catch (e2: ClassNotFoundException) {
                BukkitPlatform(minecraftVersion, this)
            }
        }
    }
    @Throws(IOException::class)
    protected fun loadProperties() {
        val loadingProperties = Properties()

        try {
            // Load config with .properties format
            loadProperties("config.properties") { properties ->
                try {
                    loadingProperties.load(properties)
                } catch (e: IOException) {
                    throw RuntimeException(e)
                }
            }
            // Load config with .toml format
            loadPropertiesFile("config.toml") { file ->
                FileConfig.of(file, TomlFormat.instance()).use { fileConfig ->
                    fileConfig.load()
                    loadConfigIntoProperties(
                        fileConfig,
                        loadingProperties,
                    )
                }
            }
            // Load config with .yml/.yaml format
            loadPropertiesFile("config.yml") { file ->
                FileConfig.of(file, YamlFormat.defaultInstance()).use { fileConfig ->
                    fileConfig.load()
                    loadConfigIntoProperties(
                        fileConfig,
                        loadingProperties
                    )
                }
            }
            loadPropertiesFile("config.yaml") { file ->
                FileConfig.of(file, YamlFormat.defaultInstance()).use { fileConfig ->
                    fileConfig.load()
                    loadConfigIntoProperties(
                        fileConfig,
                        loadingProperties
                    )
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Bukkit.shutdown()
        }
        this.properties = loadingProperties
    }

    @Throws(IOException::class)
    protected fun loadProperties(path: String, consumer: Consumer<InputStream>) {
        // Jar builtin properties
        val builtinInputStream = this.javaClass.classLoader.getResourceAsStream(path)
        builtinInputStream.use {
            if (builtinInputStream != null) {
                consumer.accept(builtinInputStream)
            }
        }
        // External properties
        val externalFile = File(_bukkitJavaPlugin.kingmcRoot, path)
        if (externalFile.exists()) {
            val externalInputStream: InputStream = FileInputStream(externalFile)
            externalInputStream.use { consumer.accept(externalInputStream) }
        }
    }
    @Throws(IOException::class)
    protected fun loadPropertiesFile(path: String, consumer: Consumer<File>) {
        // Jar builtin properties
        val builtinInputStream = this.javaClass.classLoader.getResourceAsStream(path)
        builtinInputStream.use {
            if (builtinInputStream != null) {
                val tempFile = File.createTempFile(path, ".tmp", _bukkitJavaPlugin.tempFolder)
                FileUtils.copyInputStreamToFile(builtinInputStream, tempFile)
                consumer.accept(tempFile)
            }
        }
        // External properties
        val externalFile = File(_bukkitJavaPlugin.kingmcRoot, path)
        if (externalFile.exists()) {
            val externalInputStream: InputStream = FileInputStream(externalFile)
            externalInputStream.use {
                val tempFile = File.createTempFile(path, ".tmp", _bukkitJavaPlugin.tempFolder)
                FileUtils.copyInputStreamToFile(externalInputStream, tempFile)
                consumer.accept(tempFile)
            }
        }
    }
    protected fun loadConfigIntoProperties(config: Config, properties: Properties, path: String = "") {
        for (entry in config.entrySet()) {
            val value = entry.getValue<Any>()
            if (value is Config) {
                loadConfigIntoProperties(value, properties, path + "${entry.key}.")
            } else {
                properties[path + entry.key] = entry.getValue()
            }
        }
    }

    override fun enable() {
        // Lifecycle.enable (stage 3)
        contextLifecycle.next()
    }

    override fun disable() {
        // Lifecycle.disable (stage 4)
        contextLifecycle.next()
        contextInitializer.dispose()
        application.shutdown()
        extensionDispatcher.getExtensions().forEach { extension ->
            extension.contextInitializer.dispose()
            extension.application.shutdown()
        }
    }

    @ExperimentalPlatformApi
    override fun reload() {
        // Reload extensions
        extensionDispatcher.reload()
    }

    protected fun disposeTempFilesOnExit() {
        _bukkitJavaPlugin.tempFolder.deleteOnExit()
    }
}