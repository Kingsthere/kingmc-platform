package kingmc.platform.extension

import kingmc.platform.context.PlatformApplication
import kingmc.platform.context.PlatformContext

/**
 * A context serves for extensions
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface ExtensionContext : PlatformContext {
    /**
     * The extension application of this extension context
     */
    override var application: PlatformApplication

    /**
     * The extension definition that this context is loading
     */
    val extension: ExtensionDefinition
}