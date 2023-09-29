package kingmc.platform.velocity.impl.driver

import com.electronwill.nightconfig.core.file.FileConfig
import com.electronwill.nightconfig.toml.TomlFormat
import com.electronwill.nightconfig.yaml.YamlFormat
import io.github.classgraph.ClassGraph
import kingmc.common.KingMC
import kingmc.common.application.lifecycle
import kingmc.common.application.withApplication
import kingmc.common.context.process.insertAfterProcessBeanLifecycle
import kingmc.common.context.process.insertProcessBeanLifecycle
import kingmc.common.environment.*
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
import kingmc.platform.ExperimentalPlatformApi
import kingmc.platform.Platforms
import kingmc.platform.context.*
import kingmc.platform.context.source.PlatformBeanSource
import kingmc.platform.context.source.PlatformBeanSourceImpl
import kingmc.platform.logging.infoColored
import kingmc.platform.util.loadConfigIntoProperties
import kingmc.platform.velocity.VelocityImplementation
import kingmc.platform.velocity.driver.VelocityPlatformDriver
import kingmc.platform.velocity.VelocityPlatform
import kingmc.platform.velocity.impl.VelocityJavaPlugin
import kingmc.platform.velocity.impl.extension.VelocityExtensionDispatcherImpl
import kingmc.util.InternalAPI
import kingmc.util.Version
import kingmc.util.format.FormatContext
import kingmc.util.lifecycle.Lifecycle
import kotlinx.coroutines.*
import net.kyori.adventure.text.logger.slf4j.ComponentLogger
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.util.*
import java.util.function.Consumer
import java.util.regex.Pattern
import kotlin.system.measureTimeMillis

val VERSION_REGEX = Pattern.compile("(?i)\\(MC: (\\d\\.\\d+\\.?\\d+?)?(?: Pre-Release )?(\\d)?\\)")!!

const val FILENAME_CONFIG_PROPERTIES = "config.properties"
const val FILENAME_CONFIG_TOML = "config.toml"
const val FILENAME_CONFIG_YAML = "config.yml"

/**
 * `PlatformDriver` implementation for velocity, it also supports spigot and paper
 *
 * @author kingsthere
 * @since 0.1.2
 */
@VelocityImplementation
open class VelocityPlatformDriverImpl(private val _velocityJavaPlugin: VelocityJavaPlugin) : VelocityPlatformDriver {
    override val platform: VelocityPlatform = loadPlatform()

    /**
     * The slf4j logger instance
     */
    private val _rawLogger: Slf4jLogger = ComponentLogger.logger("KingMC")

    /**
     * The [kingmc.common.logging.Logger] instance for this platform
     */
    override val logger: Slf4jLoggerWrapper = Slf4jLoggerWrapper(_rawLogger)

    /**
     * Class loader that loaded the velocity-side kingmc driver plugin
     */
    override val classLoader: ClassLoader get() = _velocityJavaPlugin.classLoader

    /**
     * Class appender used for dependency dispatcher
     */
    private val _classAppender: ClassAppender = ClassAppender(classLoader, logger)

    /**
     * `Properties` of this platform
     */
    override lateinit var properties: Properties

    /**
     * The bean source of this driver
     */
    override lateinit var beanSource: PlatformBeanSource

    /**
     * KingMC directory files
     */
    final override val baseDirectory: File = _velocityJavaPlugin.kingmcRoot
    private val extensionDirectory: File = File("$baseDirectory/extensions").apply {
        // Create directory if not exists
        if (!exists()) {
            mkdirs()
        }
    }
    private val driverDirectory: File = File("$baseDirectory/drivers").apply {
        // Create directory if not exists
        if (!exists()) {
            mkdirs()
        }
    }

    /**
     * The dependency dispatcher of this platform driver
     */
    override val dependencyDispatcher: DependencyDispatcher = DependencyDispatcher(
        root = File("${this.baseDirectory}/libraries"),
        logger = logger,
        classAppender = _classAppender
    )

    /**
     * The path of drivers
     */
    val driverPaths: MutableList<String> = LinkedList()


    override val velocityPluginInstance: VelocityJavaPlugin = _velocityJavaPlugin
    override val pluginFile: File = _velocityJavaPlugin.file

    override val extensionDispatcher: VelocityExtensionDispatcherImpl = VelocityExtensionDispatcherImpl(this)

    override lateinit var application: PlatformApplication
    private lateinit var lifecycle: Lifecycle

    private val mavenRepositoryDefault = Repository("https://repo1.maven.org/maven2/")

    /**
     * Initialize environment for configuration files
     */
    init {
        loadConfigurationEnvironment()
        loadProperties()
    }

    private val formatContext = FormatContext(properties)
    private val mavenRepository = repository("{kingmc.environment.maven-repository}", formatContext)
    private val extensionCoroutineDispatcher by lazy {
        Dispatchers.Default
    }

    override fun load() {
        val time = measureTimeMillis {
            disposeTempFilesOnExit()
            loadCoroutineEnvironment()
            runBlocking {
                loadFullEnvironmentSuspend()
            }
            application = loadPlatformApplication()
            lifecycle = application.lifecycle

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
                infoColored("""<grey>v: ${KingMC.VERSION} 2021 - 2023 ©""")
                info("")

                infoColored("<yellow>Booting up by driver $this")

                runBlocking(extensionCoroutineDispatcher) {
                    withApplication(this@VelocityPlatformDriverImpl.application) {
                        // Init extension dispatcher
                        extensionDispatcher.init()
                        info("Loading extensions from $extensionDirectory...")
                        extensionDispatcher.loadExtensionsFromDirectory(
                            directory = extensionDirectory,
                            lifecycle = this@VelocityPlatformDriverImpl.lifecycle
                        )
                    }
                }

            }
        }

        withApplication(this.application) {
            infoColored("<green>KingMC framework launched successfully on platform $platform in $time(ms)")
        }

        // Lifecycle.const (stage 0)
        lifecycle.next()
        // Lifecycle.initialize (stage 1)
        lifecycle.next()
        // Lifecycle.load (stage 2)
        lifecycle.next()
    }

    @OptIn(InternalAPI::class)
    override fun loadPlatformApplication(): PlatformApplication {
        val classLoader = _velocityJavaPlugin.classLoader
        this.beanSource = loadBeanSource()
        val environment = PlatformApplicationEnvironment(classLoader)
        val loggers = Slf4jLoggerManager(logger)

        // Create context
        val context = PlatformContextImpl(properties, "platform", beanSource, emptySet())

        // Create application instance
        val application: PlatformApplication = PlatformApplicationImpl("platform", context, environment, platform, loggers)
        context.application = application

        context.load()

        // Register platform
        Platforms.registerPlatform(application.platform, application.context)

        // Insert process lifecycle
        context.insertProcessBeanLifecycle(4)
        context.insertAfterProcessBeanLifecycle(4)

        return application
    }

    protected open fun loadBeanSource(): PlatformBeanSource {
        driverDirectory.listFiles()?.mapNotNull {
            if (it.extension != "jar") {
                return@mapNotNull null
            }

            logger.logInfo("Loading driver from $it")

            driverPaths.add(it.absolutePath)
            _classAppender.addPath(it.toPath())
        }

        val classGraph = ClassGraph()
            .overrideClassLoaders(classLoader)
            .ignoreParentClassLoaders()
            .enableAnnotationInfo()
            .enableMethodInfo()
            .enableClassInfo()
            .filterClasspathElements {
                it == pluginFile.absolutePath || driverPaths.contains(it)
            }
        return PlatformBeanSourceImpl(
            classGraph,
            classLoader,
            properties,
            platform,
            emptyList()
        ).also { it.load() }
    }

    protected open fun loadCoroutineEnvironment() {
        val kotlinReflect = dependencyDispatcher.installDependencyAsync(
            dependency(
                groupId = "org.jetbrains.kotlin",
                artifactId = "kotlin-reflect",
                version = "{kingmc.environment.kotlin}",
                scope = DependencyScope.RUNTIME,
                formatContext
            ),
            mavenRepository,
            emptySet(),
            DependencyScope.RUNTIME
        )
        val cglib = dependencyDispatcher.installDependencyAsync(
            Dependency("cglib", "cglib", "3.3.0", DependencyScope.RUNTIME),
            mavenRepository,
            emptySet()
        )
        // Join kotlin-reflect and cglib, because load dependencies from a class is required
        // to use kotlin reflection and cglib proxy
        cglib.get()
        kotlinReflect.get()

        // Kotlin coroutine dependency
        KotlinCoroutineEnvironment::class.loadDependencies(dependencyDispatcher, formatContext)
    }

    private fun loadConfigurationEnvironment() {
        val core = dependencyDispatcher.installDependencyAsync(
            Dependency("com.electronwill.night-config", "core", "3.6.5", DependencyScope.RUNTIME),
            mavenRepositoryDefault,
            emptySet()
        )
        val yaml = dependencyDispatcher.installDependencyAsync(
            Dependency("com.electronwill.night-config", "yaml", "3.6.5", DependencyScope.RUNTIME),
            mavenRepositoryDefault,
            emptySet()
        )
        val toml = dependencyDispatcher.installDependencyAsync(
            Dependency("com.electronwill.night-config", "toml", "3.6.5", DependencyScope.RUNTIME),
            mavenRepositoryDefault,
            emptySet()
        )
        core.get()
        yaml.get()
        toml.get()
    }

    protected open suspend fun loadFullEnvironmentSuspend() = withContext(Dispatchers.IO) {
        launch {
            KingMCEnvironment::class.loadDependenciesSuspend(dependencyDispatcher, formatContext)
        }
        launch {
            Class.forName("kingmc.platform.velocity.nbtapi.NBTAPIEnvironment").kotlin.loadDependenciesSuspend(
                dependencyDispatcher,
                formatContext
            )
        }
        launch {
            Class.forName("kingmc.platform.velocity.adventure.AdventureEnvironment").kotlin.loadDependenciesSuspend(
                dependencyDispatcher,
                formatContext
            )
        }
        launch {
            Class.forName("kingmc.platform.velocity.brigadier.BrigadierEnvironment").kotlin.loadDependenciesSuspend(
                dependencyDispatcher,
                formatContext
            )
        }
    }

    protected open fun loadPlatform(): VelocityPlatform {
        val server = velocityPluginInstance.server
        val version = server.version
        return VelocityPlatform(
            Version.LATEST,
            version.name,
            version.vendor,
            version.version,
            this
        )
    }

    @Throws(IOException::class)
    protected fun loadProperties() {
        val loadingProperties = Properties()

        try {
            // Load config with .properties format
            loadProperties(FILENAME_CONFIG_PROPERTIES) { properties ->
                try {
                    loadingProperties.load(properties)
                } catch (e: IOException) {
                    throw RuntimeException(e)
                }
            }
            // Load config with .toml format
            loadPropertiesFile(FILENAME_CONFIG_TOML) { file ->
                FileConfig.of(file, TomlFormat.instance()).use { fileConfig ->
                    fileConfig.load()
                    loadConfigIntoProperties(
                        fileConfig,
                        loadingProperties,
                    )
                }
            }
            // Load config with .yml/.yaml format
            loadPropertiesFile(FILENAME_CONFIG_YAML) { file ->
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
            _velocityJavaPlugin.server.shutdown()
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
        val externalFile = File(_velocityJavaPlugin.kingmcRoot, path)
        if (externalFile.exists()) {
            val externalInputStream: InputStream = FileInputStream(externalFile)
            externalInputStream.use { consumer.accept(externalInputStream) }
        }
    }

    @Throws(IOException::class)
    protected fun loadPropertiesFile(path: String, consumer: Consumer<File>) {
        // Jar builtin properties
        val builtinInputStream = this.javaClass.classLoader.getResourceAsStream(path)
        builtinInputStream?.use {
            // KingMC will create external config.xxx file instead
            // if (builtinInputStream != null) {
            //     val tempFile = File.createTempFile(path, ".tmp", _velocityJavaPlugin.tempFolder)
            //     FileUtils.copyInputStreamToFile(builtinInputStream, tempFile)
            //     consumer.accept(tempFile)
            // }

            val externalFile = File(_velocityJavaPlugin.kingmcRoot, path)
            if (externalFile.exists()) {
                consumer.accept(externalFile)
            } else {
                if (externalFile.createNewFile()) {
                    FileUtils.copyInputStreamToFile(builtinInputStream, externalFile)
                    consumer.accept(externalFile)
                }
            }
        }
    }

    override fun enable() {
        // Lifecycle.enable (stage 3)
        lifecycle.next()
    }

    override fun disable() {
        // Lifecycle.disable (stage 4)
        lifecycle.next()
        application.shutdown()
        extensionDispatcher.getExtensions().forEach { extension ->
            extension.application.shutdown()
        }
    }

    @ExperimentalPlatformApi
    override fun reload() {
        // Reload extensions
        extensionDispatcher.reload()
    }

    private fun disposeTempFilesOnExit() {
        _velocityJavaPlugin.tempFolder.deleteOnExit()
    }
}