package kingmc.platform.version

/**
 * Marks an element is only available since minecraft version
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Retention
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
annotation class SinceMinecraft(
    /**
     * Since minecraft version
     */
    val version: String
)
