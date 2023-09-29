package kingmc.platform.extension

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication

/**
 * The extension dispatcher for this platform
 *
 * @author kingsthere
 * @since 0.0.4
 */
lateinit var extensionDispatcher: ExtensionDispatcher

@WithApplication
fun reloadExtensionProperties() {
    val extensionApp = currentApplication() as ExtensionApplicationImpl
}