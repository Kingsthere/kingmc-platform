package kingmc.platform.facet.text

import kingmc.common.text.MiniMessage
import kingmc.common.text.Text
import kingmc.platform.util.TextSolver

/**
 * A [TextSolver] solving [Text] from mini messages
 *
 * @param miniMessage configured mini message to parse strings
 * @author kingsthere
 * @since 0.0.7
 */
open class MiniMessageTextSolver(val miniMessage: MiniMessage) : TextSolver {
    // private val _tagResolver = TagResolver.builder()
    //     .resolver(TagResolver.standard())
    //     .apply {
    //         // Solve tag from format context
    //         val formatContext = this@MiniMessageTextSolver.application.formatContext
    //         formatContext.forEach {
    //             if (it.name != null) {
    //                 tag(it.name!!) { _, _ ->
    //                     val value = it.value
    //                     if (value is TextDisplayable) {
    //                         Tag.inserting(value.asText())
    //                     } else {
    //                         Tag.inserting(Text.text(value.toString()))
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     .build()

    /**
     * Resolve from a [String] to a [Text]
     *
     * @since 0.0.3
     */
    override fun solve(string: String): Text {
        return miniMessage.deserialize(string)
    }

    /**
     * Encode a [Text] to [String] that this `TextSolver` supports to solve
     *
     * @since 0.0.3
     */
    override fun encode(text: Text): String {
        return miniMessage.serialize(text)
    }
}