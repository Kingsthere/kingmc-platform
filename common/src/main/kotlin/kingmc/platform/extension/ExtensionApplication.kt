package kingmc.platform.extension

import kingmc.common.application.Application
import kingmc.platform.context.PlatformApplication

/**
 * An application for the extensions to use kingmc framework api in separated
 * environment by extensions
 *
 * @since 0.0.3
 * @author kingsthere
 * @see Application
 * @see ExtensionContext
 */
interface ExtensionApplication : PlatformApplication<ExtensionContext>