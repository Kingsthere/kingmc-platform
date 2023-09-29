package kingmc.platform.schedule

/**
 * An annotation schedule a repeating task at [kingmc.util.lifecycle.Lifecycles.ACTIVE]
 *
 * @author kingsthere
 * @since 0.1.0
 */
@Retention
@Target(AnnotationTarget.FUNCTION)
annotation class Schedule(
    /**
     * Interval in milliseconds
     */
    val intervalInMills: Long,
)
