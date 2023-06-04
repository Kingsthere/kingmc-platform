package kingmc.platform.facet.util

import kingmc.common.text.Text
import kingmc.platform.facet.Facet
import kingmc.platform.facet.FacetImplementation
import kingmc.platform.facet.invoke
import kingmc.platform.util.TextSolver

/**
 * A facet `TextSolver` implementation
 *
 * @since 0.0.9
 * @author kingsthere
 */
@FacetImplementation
interface FacetTextSolver : TextSolver {
    val solve: Facet<(String) -> Text, Text>
    val encode: Facet<(Text) -> String, String>

    override fun solve(string: String): Text = solve.invoke(string)

    override fun encode(text: Text): String = encode.invoke(text)
}