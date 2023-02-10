package kingmc.platform.extension

/**
 * A container described all extensions that is loaded in current platform
 *
 * @since 0.0.4
 * @author kingsthere
 */
interface ExtensionContainer : MutableList<LoadedExtension>

/**
 * A default implementation of [ExtensionContext]
 *
 * @see ExtensionContext
 * @since 0.0.4
 * @author kingsthere
 */
class DefaultExtensionContainer(private val values: MutableList<LoadedExtension> = mutableListOf()) :
    ExtensionContainer, MutableList<LoadedExtension> by values