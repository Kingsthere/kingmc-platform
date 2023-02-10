package kingmc.platform.event

import kingmc.common.application.WithApplication
import kotlin.reflect.KClass

/**
 * A [Set] stores many handlers
 * for the specified [Event]
 *
 * @since 0.0.1
 * @author kingsthere
 * @see Event
 * @see Subscribe
 */
typealias HandlerList = HashMap<KClass<*>, MutableCollection<EventHandler<Any>>>

/**
 * An event handler to handle an event when the event
 * is published from a Publisher
 */
interface EventHandler<T> : @WithApplication suspend (T) -> Unit, Comparable<EventHandler<*>> {
    /**
     * The priority of this event handler to handle the event
     */
    val priority: Byte

    /**
     * Whether to ignore cancelled events
     */
    val ignoreCancelled: Boolean

    override fun compareTo(other: EventHandler<*>): Int =
        this.priority - other.priority
}
