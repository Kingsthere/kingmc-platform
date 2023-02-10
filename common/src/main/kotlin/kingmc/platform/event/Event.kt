package kingmc.platform.event

/**
 * Indicate that the class annotated with
 * [@Event][Event] is an event that ready to publish
 * or receive from listeners
 *
 *
 * The event classes could contain the parameters that
 * required to trigger this event, event class could is
 * a **data class**, for example:
 * ```
 * @Event
 * data class EventA(val param1: Int, val param2: String)
 * ```
 * An event could forward in a [Listener] so it
 * can listen when that event is sent by using
 * [Publisher] that the listener is subscribed
 * to
 *
 * @since 0.0.1
 * @author kingsthere
 * @see Publisher
 * @see Listener
 */
@MustBeDocumented
@Retention
@Target(AnnotationTarget.CLASS)
annotation class Event
