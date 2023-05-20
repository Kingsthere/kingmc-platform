package kingmc.platform.context

import kingmc.common.application.ApplicationEnvironment
import kingmc.common.application.properties
import kingmc.common.logging.LoggerCapableApplication
import kingmc.common.logging.LoggerManager
import kingmc.platform.Platform
import kingmc.util.format.FormatContext
import kingmc.util.format.PropertiesFormatContext

/**
 * An application implement for using kingmc platform api
 *
 * @since 0.0.3
 * @author kingsthere
 */
open class PlatformApplicationImpl(override val platform: Platform,
                                   override val context: PlatformContext,
                                   override val environment: ApplicationEnvironment,
                                   override val loggers: LoggerManager
) : PlatformApplication, LoggerCapableApplication {
    /**
     * Shutdown hooks to be executed when application [shutdown]
     */
    val shutdownHooks: MutableList<() -> Unit> = mutableListOf()

    override val name: String
        get() = "platform"

    override fun addShutdownHook(shutdownHook: () -> Unit) {
        this.shutdownHooks.add(shutdownHook)
    }

    override fun shutdown() {
        this.shutdownHooks.forEach { hook -> hook() }
        context.close()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlatformApplicationImpl

        if (context != other.context) return false
        if (environment != other.environment) return false

        return true
    }

    val propertiesFormatContext by lazy {
        PropertiesFormatContext(this.properties)
    }

    private val _formatContext by lazy {
        propertiesFormatContext.with(context.getFormatContext())
    }

    /**
     * Get the format context that this holder holding
     */
    override fun getFormatContext(): FormatContext {
        return _formatContext
    }

    override fun hashCode(): Int {
        var result = context.hashCode()
        result = 31 * result + environment.hashCode()
        return result
    }
}