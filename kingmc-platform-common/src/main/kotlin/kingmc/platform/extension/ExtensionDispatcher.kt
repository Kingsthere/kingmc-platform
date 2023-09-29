package kingmc.platform.extension

import kingmc.platform.ExperimentalPlatformApi
import kingmc.util.Reloadable

/**
 * A dispatcher responsible for dispatching extensions, include to
 *  + load extensions
 *  + disable/enable extensions
 *  + reload extensions
 *
 * @author kingsthere
 * @since 0.0.7
 */
interface ExtensionDispatcher : Reloadable {
    /**
     * Gets all extensions that handled by this extension dispatcher
     *
     * @return extensions handled by this dispatcher
     */
    fun getExtensions(): List<ExtensionInstance>

    /**
     * Disable an extension
     *
     * @param extension extension to disable
     */
    fun disableExtension(extension: ExtensionInstance)

    /**
     * Reload all extensions handled by this extension dispatcher
     */
    @ExperimentalPlatformApi
    override fun reload()
}