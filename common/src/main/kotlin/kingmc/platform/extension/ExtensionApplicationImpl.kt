package kingmc.platform.extension

import kingmc.common.application.ApplicationEnvironment
import kingmc.common.application.properties
import kingmc.common.logging.LoggerCapableApplication
import kingmc.common.logging.LoggerManager
import kingmc.common.structure.ClassSource
import kingmc.platform.Platform
import kingmc.util.format.FormatArgument
import kingmc.util.format.FormatContext
import kingmc.util.format.ListFormatArguments
import kingmc.util.format.PropertiesFormatContext

/**
 * A default implementation of [ExtensionApplication]
 *
 * @param context the context of this application
 * @param environment the environment to load this application
 * @param project the project that this application is load from
 * @since 0.0.3
 * @author kingsthere
 * @see ExtensionApplication
 */
class ExtensionApplicationImpl(
    override val platform: Platform,
    override val context: ExtensionContext,
    override val environment: ApplicationEnvironment,
    private val project: ClassSource,
    override val loggers: LoggerManager
) : ExtensionApplication, LoggerCapableApplication {
    val shutdownHooks: MutableList<() -> Unit> = mutableListOf()

    override val name: String
        get() = (project as ExtensionClassSource).extensions.first().id

    override fun addShutdownHook(shutdownHook: () -> Unit) {
        this.shutdownHooks.add(shutdownHook)
    }

    override fun shutdown() {
        this.shutdownHooks.forEach {
            it.invoke()
        }
        context.close()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExtensionApplicationImpl

        if (context != other.context) return false
        if (environment != other.environment) return false

        return true
    }

    val _formatContext by lazy {
        PropertiesFormatContext(this.properties).with(ListFormatArguments(listOf(
            FormatArgument(context.extension.id, "extension.id"),
            FormatArgument(context.extension.displayName, "extension.name"),
            FormatArgument(context.extension.tag, "extension.tag"),
            FormatArgument(context.extension.description, "extension.description"),
        )) )
    }

    /**
     * Get the format context that this holder holding
     */
    override fun getFormatContext(): FormatContext {
        return _formatContext.with(context.getFormatContext())
    }

    override fun hashCode(): Int {
        var result = context.hashCode()
        result = 31 * result + environment.hashCode()
        return result
    }
}