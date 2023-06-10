package kingmc.platform.velocity.impl.driver

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
import kingmc.common.text.Text
import kingmc.platform.ExperimentalPlatformApi
import kingmc.platform.Platforms
import kingmc.platform.context.*
import kingmc.platform.logging.infoColored
import kingmc.platform.util.loadConfigIntoProperties
import kingmc.platform.velocity.VelocityImplementation
import kingmc.platform.velocity.VelocityJavaPlugin
import kingmc.platform.velocity.driver.VelocityPlatformDriver
import kingmc.platform.velocity.impl.VelocityPlatform
import kingmc.platform.velocity.impl.extension.VelocityExtensionDispatcherImpl
import kingmc.util.Lifecycle
import kingmc.util.Version
import kingmc.util.format.PropertiesFormatContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import net.kyori.adventure.text.logger.slf4j.ComponentLogger
import org.apache.commons.io.FileUtils
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

const val FILENAME_CONFIG_PROPERTIES = "config.properties"
const val FILENAME_CONFIG_TOML = "config.toml"
const val FILENAME_CONFIG_YAML = "config.yml"

/**
 * `PlatformDriver` implementation for velocity
 *
 * @since 0.0.7
 * @author kingsthere
 */
@VelocityImplementation
open class VelocityPlatformDriverImpl(protected val _velocityJavaPlugin: VelocityJavaPlugin) : VelocityPlatformDriver {
    override val platform: VelocityPlatform
    private val _rawLogger: Slf4jLogger = ComponentLogger.logger("KingMC")
    override val logger: Slf4jLoggerWrapper = Slf4jLoggerWrapper(_rawLogger)
    override lateinit var properties: Properties
    override val dependencyDispatcher: DependencyDispatcher = DependencyDispatcher(
        root = File("${_velocityJavaPlugin.kingmcRoot}/libraries"),
        logger = logger
    )

    override val velocityPluginInstance: VelocityJavaPlugin = _velocityJavaPlugin
    override val pluginFile: File = _velocityJavaPlugin.file

    override val extensionDispatcher: VelocityExtensionDispatcherImpl = VelocityExtensionDispatcherImpl(this)

    override lateinit var application: PlatformApplication
    lateinit var contextInitializer: ContextInitializer
    lateinit var contextLifecycle: Lifecycle<Runnable>

    override val baseDirectory: File = _velocityJavaPlugin.kingmcRoot
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
        OpenAPI.supportClassLoader(_velocityJavaPlugin.classLoader)

        loadConfigurationEnvironment()
        loadProperties()
        platform = loadPlatform()
    }

    val formatContext = PropertiesFormatContext(properties)
    val mavenRepository = repository("{kingmc.environment.maven-repository}", formatContext)

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
                infoColored("""<grey>v: ${KingMC.VERSION} 2021 - 2023 ©""")
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
        val classLoader = _velocityJavaPlugin.classLoader
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
            dependency(groupId = "org.jetbrains.kotlin", artifactId = "kotlin-reflect", version = "{kingmc.environment.kotlin}", scope = DependencyScope.RUNTIME, formatContext),
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
        // launch {
        //     Adventure::class.loadDependenciesSuspend(dependencyDispatcher, formatContext)
        // }
    }
    protected open fun loadPlatform(): VelocityPlatform {
        val server = _velocityJavaPlugin.server
        val version = server.version
        return VelocityPlatform(Version.LATEST, version.name, version.vendor, version.version, this)
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
            _velocityJavaPlugin.server.shutdown(Text("Failed to load properties for kingmc framework ${e}"))
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
        _velocityJavaPlugin.tempFolder.deleteOnExit()
    }
}