package kingmc.platform.audience.text

import kingmc.common.context.annotation.Component
import kingmc.util.builder.Buildable

/**
 * A superinterface represent a resolver to resolve/deserialize
 * [text] from special string
 *
 * @since 0.0.3
 * @author kingsthere
 */
@Component
interface TextResolver {
    /**
     * Resolve from a [String] to a [text]
     *
     * @since 0.0.3
     */
    fun resolve(string: String): Text

    /**
     * Restore a [text] to [String]
     *
     * @since 0.0.3
     */
    fun restore(text: Text): String

    /**
     * Builder to build the resolvers that to resolve
     * texts
     *
     * @since 0.0.3
     * @author kingsthere
     */
    interface Builder : Buildable.Builder<TextResolver> {
        override fun build(): TextResolver
    }
}