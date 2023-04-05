package kingmc.platform.context

import kingmc.common.application.Application
import kingmc.common.application.FormatCapableApplication
import kingmc.platform.Platform

/**
 * Extended [Application] exposed the platform where this application running
 *
 * @since 0.0.5
 * @author kingsthere
 */
interface PlatformApplication : FormatCapableApplication, Application {
    /**
     * The platform of this application
     */
    val platform: Platform
}