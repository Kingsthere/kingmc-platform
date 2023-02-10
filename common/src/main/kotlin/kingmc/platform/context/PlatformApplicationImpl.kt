package kingmc.platform.context

import kingmc.common.application.ApplicationEnvironment
import kingmc.common.logging.LoggerCapableApplication
import kingmc.common.logging.LoggerManager
import kingmc.platform.Platform

/**
 * An application implement for using kingmc platform api
 *
 * @since 0.0.3
 * @author kingsthere
 */
open class PlatformApplicationImpl(platformOn: Platform, override val context: PlatformContext, override val environment: ApplicationEnvironment,
                                   override val loggers: LoggerManager
) : PlatformApplication<PlatformContext>, LoggerCapableApplication {
    override val platform = platformOn

    init {

    }

    override val name: String
        get() = "platform"

    override fun dispose() {
        // Dispose application logic
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

    override fun hashCode(): Int {
        var result = context.hashCode()
        result = 31 * result + environment.hashCode()
        return result
    }
}