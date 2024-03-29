package kingmc.platform.event

/**
 * A marker superinterface for events that could cancel
 *
 * @author kingsthere
 * @since 0.0.5
 */
interface Cancellable {
    /**
     * The variable refer the cancellation state of this event. A cancelled event
     * will not be executed in the server, but will still pass to other extensions
     */
    var cancelled: Boolean
}