package kingmc.platform.velocity.impl.extension

import io.github.classgraph.ClassGraph
import kingmc.common.application.*
import kingmc.common.context.ContextDefiner
import kingmc.common.logging.error
import kingmc.platform.ExperimentalPlatformApi
import kingmc.platform.velocity.impl.driver.VelocityPlatformDriverImpl
import kingmc.platform.extension.*
import kingmc.platform.logging.infoColored
import kingmc.util.lifecycle.Lifecycle
import kotlinx.coroutines.*
import java.io.File
import java.util.*

/**
 * Velocity [ExtensionDispatcher] implementation
 *
 * @author kingsthere
 * @since 0.1.2
 */
class VelocityExtensionDispatcherImpl(val driver: VelocityPlatformDriverImpl) : AbstractExtensionDispatcher(), ExtensionDispatcher {
    private lateinit var extensionSourceDirectory: File
    private lateinit var extractedExtensions: Set<ExtensionBeanSource>
    override lateinit var dispatchedExtensions: MutableList<ExtensionInstance>

    fun init() {
        ContextDefiner.getOrCreateBeanClassInstanceContexts(this::class.java).put(
            System.identityHashCode(this),
            driver.context
        )
    }

    /**
     * Validate if the given file is valid extension file
     *
     * A valid extension is the jar file that stores an available extension for this extension
     * dispatcher to install, an extension file must
     *  + Not a directory
     *  + Ends with the file extension ".jar"
     *
     * @return `true` if the following file is a valid extension file
     */
    private fun validateExtension(extension: File): Boolean {
        // Check if the extension file is a valid
        // extension that available to install
        return extension.isFile && extension.extension == "jar"
    }

    /**
     * Try to recognize an extension from the given extension(jar) file
     *
     * @param extensionFile file to recognize the extension from
     * @return the extension bean source to that extension jar file
     */
    @WithApplication
    private fun recognizeExtensionFromJarFile(extensionFile: File): ExtensionBeanSource? {
        infoColored("<grey>Trying to recognize extension from $extensionFile...</grey>")

        return try {
            val classLoader = ExtensionClassLoader(arrayOf(extensionFile.toURI().toURL()), driver.classLoader)
            // Create class graph to read jar file
            val classGraph = ClassGraph()
                .overrideClassLoaders(classLoader)
                // .ignoreParentClassLoaders()
                .filterClasspathElements {
                    it == driver.pluginFile.absolutePath
                            || driver.driverPaths.contains(it)
                            || it == extensionFile.absolutePath
                }
                .enableAnnotationInfo()
                .enableMethodInfo()
                .enableClassInfo()

            // Create `ExtensionBeanSource` instance and return
            val beanSource = ExtensionBeanSourceImpl(
                classGraph = classGraph,
                classLoader = classLoader,
                properties = application.properties,
                platform = driver.platform,
                parent = listOf(driver.beanSource),
                preparedBeanDefinition = emptyList(),
                dependencyDispatcher = driver.dependencyDispatcher
            )
            beanSource
        } catch (e: ClassNotFoundException) {
            error(msg = "Unable to load extension (Is it up to date or missing dependencies?)", throwable = e)
            null
        } catch (e: Exception) {
            error(msg = "Unable to recognize extension for file $extensionFile", throwable = e)
            null
        }
    }

    /**
     * Solve the order for the given extension bean sources
     *
     * @return a list with sorted extensions
     */
    private fun solveExtensionDependencyOrder(extensions: Set<ExtensionBeanSource>): List<ExtensionBeanSource> {
        val result = LinkedList<ExtensionBeanSource>()
        extensions.forEach { extension ->
            val dependencyIndex = result.indexOfFirst {
                extension.extension.dependencies.any { dependency -> dependency.id == it.extension.id }
            }
            if (dependencyIndex == -1) {
                result.addLast(extension)
            } else {
                result.add(dependencyIndex, extension)
            }
        }
        return result
    }

    /**
     * Load all extensions from the given directory
     *
     * @param directory the directory to load extensions for
     */
    @WithApplication
    suspend fun loadExtensionsFromDirectory(directory: File, lifecycle: Lifecycle) = coroutineScope {
        extensionSourceDirectory = directory
        val application = currentApplication()
        val loadedExtensions = mutableListOf<ExtensionInstance>()

        fun finishLoadExtensionRecurves(extensions: List<ExtensionBeanSource>) {
            // Create the `ExtensionLoadContext` instance to load the given extensions
            val context = ExtensionLoadContext(driver, lifecycle, buildMap {
                extensions.forEach { extensionBeanSource ->
                    put(extensionBeanSource.extension.id, extensionBeanSource)
                }
            })

            // Load extension bean sources in the given order
            extensions.forEach { extensionBeanSource ->
                // Load extension instance
                val extensionInstance = context.getOrLoadExtensionInstance(extensionBeanSource)
                val definition = extensionInstance.definition

                infoColored(StringBuilder().apply {
                    append("<gradient:aqua:light_purple>Extension ${definition.displayName}(${definition.id}) v. ${definition.version}")
                    if (definition.description.contributors.isNotEmpty()) {
                        append(" by ${definition.description.contributors.joinToString(", ")}")
                    }
                    append(" has been loaded successfully</gradient>")
                }.toString())
                if (definition.description.website != "https://www.example.com/") {
                    infoColored("<dark_grey>Check the website of this extensionBeanSource <blue>${definition.description.website}</blue> for more information")
                }
                loadedExtensions.add(extensionInstance)
            }
        }

        // Load extension classes
        extractedExtensions = HashSet<ExtensionBeanSource>().apply {
            (directory.listFiles()?.map { file ->
                async {
                    if (validateExtension(file)) {
                        return@async withApplication(application) { recognizeExtensionFromJarFile(file) }
                    }
                    null
                }
            } ?: emptyList()).forEach {
                it.await()?.let { extensionBeanSource -> add(extensionBeanSource) }
            }
        }

        // Solve extension dependencies
        extractedExtensions.forEach { source ->
            try {
                val extension = source.extension
                val dependencies = extension.dependencies
                // Load extension dependencies
                if (dependencies.isEmpty()) {
                    return@forEach
                }
                dependencies.forEach { dependency ->
                    // Try to find the dependent extension
                    val dependentExtension = extractedExtensions.find { it.extension.id == dependency.id }
                        ?: throw RuntimeException("Could not find dependent extension for $source (required: $dependency)")
                    source.addParent(dependentExtension)
                }
            } catch (e: Exception) {
                error("Failed to load extension from $source", e)
                return@forEach // Skip the current extension if an exception was thrown
            }
        }

        // Load extracted extension bean sources
        extractedExtensions.forEach { source ->
            source.load()
        }

        // Load extension maven dependencies
        withContext(Dispatchers.IO) {
            extractedExtensions.forEach { source ->
                launch {
                    source.loadMavenDependencies()
                }
            }
        }

        withApplication(application) {
            finishLoadExtensionRecurves(solveExtensionDependencyOrder(extractedExtensions))
        }
        dispatchedExtensions = loadedExtensions
    }

    @WithApplication
    override fun disableExtension(extension: ExtensionInstance) {
        infoColored("<grey>Disabling extension ${extension.definition.id}(${extension.definition.displayName})...")
        extension.context.getLifecycle().next()
        extension.application.shutdown()
        super.disableExtension(extension)
    }

    @WithApplication
    @Synchronized
    fun disableExtensions() {
        val disablingExtensions = this.dispatchedExtensions.toList()
        disablingExtensions.forEach {
            disableExtension(it)
        }
    }

    @ExperimentalPlatformApi
    @Synchronized
    override fun reload() {
        withApplication(driver.application) {
            disableExtensions()
            val reloadLifecycle = Lifecycle()
            runBlocking(Dispatchers.Default) {
                withApplication(driver.application) {
                    loadExtensionsFromDirectory(extensionSourceDirectory, reloadLifecycle)
                    repeat(4) {
                        reloadLifecycle.next()
                    }
                }
            }
        }
    }
}