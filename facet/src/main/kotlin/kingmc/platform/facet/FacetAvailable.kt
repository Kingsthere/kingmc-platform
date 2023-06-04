package kingmc.platform.facet

/**
 * A marker annotation that indicates an element that implements by [Facet]
 *
 * @since 0.0.9
 * @author kingsthere
 */
@Target(AnnotationTarget.FUNCTION)
@Retention
annotation class FacetAvailable(
    /**
     * The expression to [Facet] instance value, or empty if the expression
     * to [Facet] is same as the name of where this annotation added
     */
    val facetValue: String = ""
)
