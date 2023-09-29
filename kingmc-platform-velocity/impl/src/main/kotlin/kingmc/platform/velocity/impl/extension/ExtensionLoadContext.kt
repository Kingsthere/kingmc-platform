package kingmc.platform.velocity.impl.extension

import kingmc.common.application.WithApplication
import kingmc.common.application.lifecycle
import kingmc.common.context.process.processBeans
import kingmc.common.context.source.ClassGraphBeanSource
import kingmc.common.logging.slf4j.Slf4jLoggerManager
import kingmc.common.logging.slf4j.Slf4jLoggerWrapper
import kingmc.common.logging.trace
import kingmc.platform.extension.*
import kingmc.platform.velocity.driver.VelocityPlatformDriver
import kingmc.util.InternalAPI
import kingmc.util.lifecycle.Execution
import net.kyori.adventure.text.logger.slf4j.ComponentLogger

/**
 * Represents the context while loading extensions
 *
 * @author kingsthere
 * @since 0.1.2
 */
class ExtensionLoadContext(
    val driver: VelocityPlatformDriver,
    // Extracted extension bean sources by id
    private val extracted: Map<String, ExtensionBeanSource>
) {
    private val loadedExtensions: MutableMap<ExtensionBeanSource, ExtensionInstance> = HashMap()

    /**
     * Try to load the `ExtensionInstance` for the given extension bean source, this
     * loaded the context & application for the given extension bean source
     *
     * @param source the extension bean source to load extension instance for
     * @return `ExtensionInstance` loaded for the given source
     */
    @OptIn(InternalAPI::class)
    @WithApplication
    fun loadExtensionInstance(source: ExtensionBeanSource): ExtensionInstance {
        require(source is ClassGraphBeanSource) { "Invalid bean source $source" }

        // Default extension logger (by extension name)
        val extensionLoggers =
            Slf4jLoggerManager(Slf4jLoggerWrapper(ComponentLogger.logger(source.extension.displayName)))
        // Extension environment
        val extensionEnvironment = ExtensionEnvironment(source.classLoader)
        // Extension definition
        val extensionDefinition = source.extension

        trace("Preparing context for $source")
        // Extension context
        val extensionContext = ExtensionContextImpl(
            properties = driver.properties,
            name = extensionDefinition.id,
            beanSource = source,
            parents = buildSet {
                add(driver.context)
                extensionDefinition.dependencies.forEach {
                    val dependentExtensionSource =
                        checkNotNull(extracted[it.id]) { "No such dependent extension with id ${it.id}" }
                    add(getOrLoadExtensionInstance(dependentExtensionSource).context)
                }
            },
            extension = extensionDefinition
        )

        extensionContext.load()

        // Schedule execution to launch extension lifecycle when platform lifecycle changes
        (0..5).forEach { index ->
            extensionContext.getLifecycle().scheduleExecution(index, Execution(0, "launch_extension_lifecycle") {
                try {
                    extensionContext.processBeans(index)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            })
        }

        trace("Preparing application instance for $source")
        val extensionApplication = ExtensionApplicationImpl(
            extensionContext,
            extensionEnvironment,
            driver.platform,
            extensionLoggers,
            extensionDefinition
        )
        // Set application instance
        extensionContext.application = extensionApplication

        // Schedule execution to launch extension lifecycle when platform lifecycle changes
        for (index in 0..5) {
            driver.application.lifecycle.scheduleExecution(index) {
                extensionContext.getLifecycle().next()
            }
        }
        trace("Successfully create extension instance for $source")
        return ExtensionInstance(
            definition = extensionDefinition,
            context = extensionContext,
            application = extensionApplication,
            source = source
        )
    }

    /**
     * Gets the `ExtensionInstance` for the given extension bean source, if the `ExtensionInstance` for
     * the given extension is not loaded, this method will load the extension instance for that bean source
     *
     * This method returns the old extension instance if the extension instance for the given extension
     * bean source is already loaded in this context
     *
     * @param extensionBeanSource the extension bean source to get or load `ExtensionInstance` for
     * @return The `ExtensionInstance` found or loaded
     */
    @WithApplication
    fun getOrLoadExtensionInstance(extensionBeanSource: ExtensionBeanSource): ExtensionInstance {
        return loadedExtensions.computeIfAbsent(extensionBeanSource, ::loadExtensionInstance)
    }
}