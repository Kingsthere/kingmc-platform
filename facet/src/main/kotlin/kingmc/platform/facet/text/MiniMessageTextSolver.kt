package kingmc.platform.facet.text

import kingmc.common.text.MiniMessage
import kingmc.common.text.Text
import kingmc.platform.util.TextSolver

/**
 * A [TextSolver] solving [Text] from mini messages
 *
 * @param miniMessage configured mini message to parse strings
 * @since 0.0.7
 * @author kingsthere
 */
open class MiniMessageTextSolver(val miniMessage: MiniMessage) : TextSolver {
    /**
     * Resolve from a [String] to a [text]
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