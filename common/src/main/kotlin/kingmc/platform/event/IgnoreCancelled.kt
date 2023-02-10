package kingmc.platform.event

/**
 * Add this to an event handler, if the event is cancelled by default that
 * event handler won't work, add this annotation to event handler so the event
 * handler can proceed cancelled events
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Retention
@Target(AnnotationTarget.FUNCTION)
annotation class IgnoreCancelled
