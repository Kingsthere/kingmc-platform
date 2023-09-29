package kingmc.platform.extension

/**
 * An abstract implementation of [ExtensionDispatcher]
 *
 * @author kingsthere
 * @since 0.0.7
 */
abstract class AbstractExtensionDispatcher : ExtensionDispatcher {
    protected open val dispatchedExtensions = mutableListOf<ExtensionInstance>()

    override fun getExtensions(): List<ExtensionInstance> = dispatchedExtensions

    override fun disableExtension(extension: ExtensionInstance) {
        dispatchedExtensions.remove(extension)
    }
}