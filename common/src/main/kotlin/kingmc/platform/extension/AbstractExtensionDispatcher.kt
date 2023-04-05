package kingmc.platform.extension

/**
 * An abstract implementation of [ExtensionDispatcher]
 *
 * @since 0.0.7
 * @author kingsthere
 */
abstract class AbstractExtensionDispatcher : ExtensionDispatcher {
    protected val loadedExtensions = mutableListOf<ExtensionData>()

    override fun getExtensions(): List<ExtensionData> = loadedExtensions

    override fun disableExtension(extension: ExtensionData) {
        loadedExtensions.remove(extension)
    }
}