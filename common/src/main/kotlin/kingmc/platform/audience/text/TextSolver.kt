package kingmc.platform.audience.text

import kingmc.common.context.annotation.Component
import kingmc.util.builder.Buildable

/**
 * A superinterface responsible for solve(deserializing)
 * [Text] from special strings, such as `MiniMessages`
 *
 * @since 0.0.3
 * @author kingsthere
 * @sample MiniMessageTextSolver
 */
@Component
interface TextSolver {
    /**
     * Resolve from a [String] to a [text]
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

    /**
     * Builder to build a configured text solver
     *
     * @since 0.0.3
     * @author kingsthere
     */
    interface Builder : Buildable.Builder<TextSolver> {
        override fun build(): TextSolver
    }
}