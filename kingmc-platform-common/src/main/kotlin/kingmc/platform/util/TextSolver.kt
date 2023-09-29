package kingmc.platform.util

import kingmc.common.context.annotation.Component
import kingmc.common.text.Text

/**
 * A superinterface responsible for solve(deserializing)
 * [Text] from special strings, such as `MiniMessages`
 *
 * @author kingsthere
 * @since 0.0.3
 */
@Component
interface TextSolver {
    /**
     * Resolve from a [String] to a text
     *
     * @since 0.0.3
     */
    fun solve(string: String): Text

    /**
     * Encode a [Text] to [String] that this `TextSolver` supports to solve
     *
     * @since 0.0.3
     */
    fun encode(text: Text): String
}