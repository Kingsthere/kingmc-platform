package kingmc.platform.context

import kingmc.common.application.Application
import kingmc.common.context.Context
import kingmc.platform.Platform

/**
 * Extended [Application] exposed the platform where this application running
 *
 * @since 0.0.5
 * @author kingsthere
 */
interface PlatformApplication<TContext : Context> : Application<TContext> {
    /**
     * The platform of this application
     */
    val platform: Platform
}