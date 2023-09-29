package kingmc.platform.extension

import kingmc.common.application.Application
import kingmc.common.application.FormatCapableApplication
import kingmc.platform.context.PlatformApplication

/**
 * An application for the extensions to use kingmc framework api in separated
 * environment by extensions
 *
 * @author kingsthere
 * @since 0.0.3
 * @see Application
 * @see ExtensionContext
 */
interface ExtensionApplication : FormatCapableApplication, PlatformApplication {
    /**
     * Reload properties for this application
     */
    fun reloadProperties()
}