package kingmc.platform.extension

/**
 * An abstract implementation of [ExtensionDispatcher]
 *
 * @since 0.0.7
 * @author kingsthere
 */
abstract class AbstractExtensionDispatcher : ExtensionDispatcher {
    protected open val dispatchedExtensions = mutableListOf<ExtensionData>()

    override fun getExtensions(): List<ExtensionData> = dispatchedExtensions

    override fun disableExtension(extension: ExtensionData) {
        dispatchedExtensions.remove(extension)
    }
}