package kingmc.platform.extension

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication

/**
 * The extension dispatcher for this platform
 *
 * @since 0.0.4
 * @author kingsthere
 */
lateinit var extensionDispatcher: ExtensionDispatcher

@WithApplication
fun reloadExtensionProperties() {
    val extensionApp = currentApplication() as ExtensionApplicationImpl
}