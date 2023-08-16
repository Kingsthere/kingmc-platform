package kingmc.platform.velocity.impl.extension

import kingmc.common.OpenAPI
import kingmc.common.application.*
import kingmc.common.context.ContextDefiner
import kingmc.common.context.process.afterProcess
import kingmc.common.context.process.processBeans
import kingmc.common.logging.error
import kingmc.common.logging.slf4j.Slf4jLoggerManager
import kingmc.common.logging.slf4j.Slf4jLoggerWrapper
import kingmc.common.logging.warn
import kingmc.common.structure.classloader.ExtensionClassLoader
import kingmc.platform.ExperimentalPlatformApi
import kingmc.platform.extension.*
import kingmc.platform.logging.infoColored
import kingmc.platform.velocity.impl.driver.VelocityPlatformDriverImpl
import kingmc.util.Lifecycle
import kotlinx.coroutines.*
import net.kyori.adventure.text.logger.slf4j.ComponentLogger
import java.io.File

/**
 * Velocity [ExtensionDispatcher] implementation
 *
 * @since 0.0.9
 * @author kingsthere
 */
class VelocityExtensionDispatcherImpl(val driver: VelocityPlatformDriverImpl) : AbstractExtensionDispatcher(), ExtensionDispatcher {
    lateinit var extensionSourceDirectory: File
    lateinit var extractedExtensions: List<ExtensionClassSource>
    override lateinit var dispatchedExtensions: MutableList<ExtensionData>

    fun init() {
        ContextDefiner.getOrCreateBeanClassInstanceContexts(this::class.java).put(System.identityHashCode(this), driver.context)
    }

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
                extensionContext.getLifecycle().insertPlan(index) {
                    try {
                        extensionContext.processBeans(index)
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
            for (index in 0..5) {
                lifecycle.insertPlan(index) {
                    extensionContext.getLifecycle().next()
                }
            }
            return returns
        } catch (e: Exception) {
            error(msg = "Unable to load recognized extensions from $source", throwable = e)
            return emptyList()
        }
    }

    fun solveExtensionDependencies(extensions: List<ExtensionData>): ExtensionLoadingRoute {
        val first = RootExtensionLoadingRoute(ArrayList())
        val extensionLoadingCallbacks = mutableMapOf<ExtensionData, MutableList<(ExtensionLoadingNode) -> Unit>>()

        fun addAndCallbackLoadingNode(node: ExtensionLoadingNode, parent: ExtensionLoadingRoute = first) {
            if (extensionLoadingCallbacks.containsKey(node.extension)) {
                extensionLoadingCallbacks.remove(node.extension)?.let { callbacks ->
                    callbacks.forEach { callback ->
                        callback(node)
                    }
                }
            }
            parent.next.add(node)
        }

        fun findOrAddCallback(extension: ExtensionData, parent: ExtensionLoadingRoute = first, declareCallback: Boolean = false, callback: (ExtensionLoadingNode) -> Unit) {
            parent.next.forEach { node ->
                if (node.extension == extension) {
                    callback(node)
                    return
                }
                findOrAddCallback(extension, node, callback = callback)
            }
            if (declareCallback) {
                extensionLoadingCallbacks.computeIfAbsent(extension) { ArrayList() }.add(callback)
            }
        }

        extensions.forEach { extension ->
            val dependencies = extension.definition.dependencies
            if (dependencies.isNotEmpty()) {
                dependencies.forEach { dependency ->
                    val dependencyExtension = extensions.find { it.definition.id == dependency.id }
                    if (!dependency.optional) {
                        dependencyExtension ?: warn("Unloaded extension ${extension.definition.id} because the required dependencies of this plugin is missing (${dependency.id})")
                    }
                    dependencyExtension?.let {
                        findOrAddCallback(it, declareCallback = true) { parent ->
                            val node = ExtensionLoadingNode(extension, mutableListOf())
                            addAndCallbackLoadingNode(node, parent)
                            extension.contextInitializer.addParent(it.context)
                        }
                    }
                }
            } else {
                val node = ExtensionLoadingNode(extension, ArrayList())
                addAndCallbackLoadingNode(node, first)
            }
        }
        return first
    }

    @WithApplication
    suspend fun loadExtensionsFromDirectory(directory: File, lifecycle: Lifecycle<Runnable>) = coroutineScope {
        val application = currentApplication()
        extensionSourceDirectory = directory
        val loadingExtensions = mutableListOf<ExtensionData>()

        fun finishLoadExtensionRecurves(extensionLoadingRoute: ExtensionLoadingRoute) {
            extensionLoadingRoute.forEach { extension ->
                val definition = extension.definition
                infoColored(StringBuilder().apply {
                    append("<gradient:aqua:light_purple>Extension ${definition.displayName}(${definition.id}) v. ${definition.tag}")
                    if (definition.description.contributors.isNotEmpty()) {
                        append(" by ${definition.description.contributors.joinToString(", ")}")
                    }
                    append(" has been loaded successfully</gradient>")
                }.toString())
                if (definition.description.website != "https://www.example.com/") {
                    infoColored("<dark_grey>Check the website of this extension <blue>${definition.description.website}</blue> for more information")
                }
                extension.contextInitializer()
            }
        }

        // Load extension classes
        extractedExtensions = (directory.listFiles()?.mapNotNull { file ->
            if (validateExtension(file)) {
                try {
                    return@mapNotNull recognizeExtensionFromJarFile(file)?.let { source ->
                        source.scanByClassGraph()
                        source.loadExtensionDefinitions()
                        source.loadProperties()
                        source.loadFormatContext()
                        source
                    }
                } catch (e: Exception) {
                    error(msg = "Unable to recognize extension for file $file", throwable = e)
                }
            }
            null
        } ?: emptyList())

        // Load extension classloader's dependencies
        extractedExtensions.forEach { source ->
            source.extensions.forEach { extension ->
                if (extension.dependencies.isNotEmpty()) {
                    extension.dependencies.forEach { dependency ->
                        extractedExtensions.find { dependencySource ->
                            dependencySource.extensions.any { dependencyExtension -> dependencyExtension.id == dependency.id }
                        }?.let { dependencySource ->
                            source.extensionClassLoader.addDependency(dependencySource.extensionClassLoader)
                        }
                    }
                }
            }
        }

        loadExtensionMavenDependencies()

        extractedExtensions.forEach { source ->
            source.load()
        }

        // Traverse the extensions in the extension
        // directory and load if the file is a valid extension
        // Make dirs if the direction is not exists
        extractedExtensions.map {
            launch {
                withApplication(application) {
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

        loadingExtensions.forEach { extension ->
            (0..5).forEach { index ->
                extension.context.getLifecycle().insertPlan(index) {
                    try {
                        extension.context.afterProcess(index)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

        finishLoadExtensionRecurves(solveExtensionDependencies(loadingExtensions))
        dispatchedExtensions = loadingExtensions
    }

    @WithApplication
    override fun disableExtension(extension: ExtensionData) {
        infoColored("<grey>Disabling extension ${extension.definition.id}(${extension.definition.displayName})...")
        extension.context.getLifecycle().next()
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