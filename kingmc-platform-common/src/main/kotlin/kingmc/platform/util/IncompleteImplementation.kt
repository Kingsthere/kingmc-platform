package kingmc.platform.util

/**
 * Mark details for an **incomplete implementation** of the platform api
 *
 * @author kingsthere
 * @since 0.0.8
 */
@Retention
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.VALUE_PARAMETER
)
annotation class IncompleteImplementation(
    /**
     * A value describe why this implementation is incomplete
     */
    val value: String
)
