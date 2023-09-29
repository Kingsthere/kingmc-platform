package kingmc.platform.context

import kingmc.common.application.AbstractApplication
import kingmc.common.application.ApplicationEnvironment
import kingmc.common.application.properties
import kingmc.common.context.Context
import kingmc.common.logging.LoggerCapableApplication
import kingmc.common.logging.LoggerManager
import kingmc.platform.Platform
import kingmc.util.format.FormatArgument
import kingmc.util.format.FormatContext

/**
 * An application implement for using kingmc platform api
 *
 * @author kingsthere
 * @since 0.0.3
 */
open class PlatformApplicationImpl(
    name: String,
    context: Context,
    environment: ApplicationEnvironment,
    final override val platform: Platform,
    override val loggers: LoggerManager
) : AbstractApplication(name, context, environment), LoggerCapableApplication, PlatformApplication {
    private var _formatContext: FormatContext? = null
        get() {
            return if (field == null) {
                val value = FormatContext(this.properties).with(
                    FormatContext(listOf(
                        FormatArgument(platform.id, "platform.id"),
                        FormatArgument(platform.minecraftVersion, "platform.minecraft_version"),
                    ))
                )
                field = value
                value
            } else {
                field
            }
        }

    /**
     * Get the format context that this holder holding
     */
    override fun getFormatContext(): FormatContext {
        return _formatContext!!.with(context.getFormatContext())
    }

    override fun toString(): String {
        return "PlatformApplicationImpl(platform=$platform, loggers=$loggers, name='$name')"
    }
}