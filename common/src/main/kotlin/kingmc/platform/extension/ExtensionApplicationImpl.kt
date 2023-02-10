package kingmc.platform.extension

import kingmc.common.application.ApplicationEnvironment
import kingmc.common.logging.LoggerCapableApplication
import kingmc.common.logging.LoggerManager
import kingmc.common.structure.Project
import kingmc.platform.Platform
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive

/**
 * A default implementation of [ExtensionApplication]
 *
 * @since 0.0.3
 * @author kingsthere
 * @see ExtensionApplication
 * @param context the context of this application
 * @param environment the environment to load this application
 * @param project the project that this application is load from
 */
class ExtensionApplicationImpl(
    override val platform: Platform,
    override val context: ExtensionContext,
    override val environment: ApplicationEnvironment,
    private val project: Project,
    override val loggers: LoggerManager
) : ExtensionApplication, LoggerCapableApplication {
    override val name: String
        get() = (project as ExtensionProject).extensions.first().id

    override fun dispose() {
        if (!environment.isActive) {
            environment.cancel("Application disposed")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExtensionApplicationImpl

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