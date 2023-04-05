package kingmc.platform.context

import kingmc.common.application.ApplicationExposedContext
import kingmc.common.context.ApplicationContext

/**
 * Extended context used in kingmc platform api
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface PlatformContext : ApplicationContext, ApplicationExposedContext {
    /**
     * The extension application of this extension context
     */
    override var application: PlatformApplication
}