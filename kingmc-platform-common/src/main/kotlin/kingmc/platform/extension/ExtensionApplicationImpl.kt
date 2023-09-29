package kingmc.platform.extension

import kingmc.common.application.ApplicationEnvironment
import kingmc.common.application.ApplicationLocalMap
import kingmc.common.context.Context
import kingmc.common.logging.LoggerCapableApplication
import kingmc.common.logging.LoggerManager
import kingmc.platform.Platform
import kingmc.platform.context.PlatformApplicationImpl
import kingmc.util.ReloadableManager
import kingmc.util.format.FormatArgument
import kingmc.util.format.FormatContext

/**
 * A default implementation of [ExtensionApplication]
 *
 * @author kingsthere
 * @since 0.0.3
 * @see ExtensionApplication
 */
class ExtensionApplicationImpl(
    context: Context,
    environment: ApplicationEnvironment,
    platform: Platform,
    loggers: LoggerManager,
    val extension: ExtensionDefinition
) : PlatformApplicationImpl("extension_${extension.id}", context, environment, platform, loggers), ExtensionApplication, LoggerCapableApplication {
    private val shutdownHooks: MutableList<() -> Unit> = mutableListOf()
    override val applicationLocalMap: ApplicationLocalMap = ApplicationLocalMap()
    override val reloadableManager: ReloadableManager = ReloadableManager()

    override fun addShutdownHook(shutdownHook: () -> Unit) {
        this.shutdownHooks.add(shutdownHook)
    }

    override fun shutdown() {
        this.shutdownHooks.forEach { hook -> hook() }
        context.dispose()
    }

    /**
     * Reload properties for this application
     */
    override fun reloadProperties() {
        TODO("Reload properties")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExtensionApplicationImpl

        if (context != other.context) return false
        if (environment != other.environment) return false

        return true
    }

    /**
     * Extension default format arguments
     */
    private val _formatContext: FormatContext = FormatContext(listOf(
        FormatArgument(extension.id, "extension.id"),
        FormatArgument(extension.displayName, "extension.name"),
        FormatArgument(extension.version, "extension.tag"),
        FormatArgument(extension.description, "extension.description"),
    ))

    /**
     * Get the format context that this holder holding
     */
    override fun getFormatContext(): FormatContext {
        return super.getFormatContext().with(_formatContext)
    }

    override fun hashCode(): Int {
        var result = context.hashCode()
        result = 31 * result + environment.hashCode()
        return result
    }

    override fun toString(): String {
        return "ExtensionApplicationImpl(platform=$platform, context=$context, extension=$extension, name='$name')"
    }
}