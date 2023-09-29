package kingmc.platform.context

import kingmc.common.application.ApplicationExposedContext
import kingmc.common.context.ApplicationContext
import kingmc.platform.Platform

/**
 * Extended context used in kingmc platform api
 *
 * @author kingsthere
 * @since 0.0.3
 */
interface PlatformContext : ApplicationContext, ApplicationExposedContext {
    /**
     * The extension application of this extension context
     */
    override var application: PlatformApplication

    /**
     * The platform of this context
     */
    val platform: Platform
}