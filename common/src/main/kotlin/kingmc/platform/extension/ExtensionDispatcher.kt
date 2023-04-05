package kingmc.platform.extension

import kingmc.platform.ExperimentalPlatformApi
import kingmc.util.Reloadable

/**
 * A dispatcher responsible for dispatching extensions, include to
 *  + load extensions
 *  + disable/enable extensions
 *  + reload extensions
 *
 * @since 0.0.7
 * @author kingsthere
 */
interface ExtensionDispatcher : Reloadable {
    /**
     * Gets all extensions that handled by this extension dispatcher
     *
     * @return extensions handled by this dispatcher
     */
    fun getExtensions(): List<ExtensionData>

    /**
     * Disable an extension
     *
     * @param extension extension to disable
     */
    fun disableExtension(extension: ExtensionData)

    /**
     * Reload all extensions handled by this extension dispatcher
     */
    @ExperimentalPlatformApi
    override fun reload()

    /**
     * Reload specified extension handled by this extension dispatcher
     *
     * @param extension the extension to reload
     */
    @ExperimentalPlatformApi
    fun reload(extension: ExtensionData)
}