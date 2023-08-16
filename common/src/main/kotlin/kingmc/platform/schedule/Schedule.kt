package kingmc.platform.schedule

/**
 * An annotation to quickly schedule a repeating task
 *
 * @since 0.1.0
 * @author kingsthere
 */
@Retention
@Target(AnnotationTarget.FUNCTION)
annotation class Schedule(
    /**
     * Interval in milliseconds
     */
    val intervalInMills: Long,
)
