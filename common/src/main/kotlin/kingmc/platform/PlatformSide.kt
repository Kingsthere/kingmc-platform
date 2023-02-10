package kingmc.platform

/**
 * Marks an element is only available on platform
 *
 * @since 0.0.3
 * @author kingsthere
 */
@Retention
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
annotation class PlatformSide
