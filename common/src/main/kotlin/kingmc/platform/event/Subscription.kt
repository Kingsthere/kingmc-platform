package kingmc.platform.event

import kingmc.common.application.Application
import kingmc.common.application.Isolated
import kingmc.common.application.WithApplication
import java.io.Closeable
import kotlin.reflect.KClass

/**
 * A class describe the subscription between a listener and a publisher and
 * the callback when listened events from publisher, and few more details includes:
 *  + Priority to listen to event
 *  + Ignore cancelled events
 *
 * @param TEvent the type of event this subscription listening to
 * @since 0.0.7
 * @author kingsthere
 */
@Isolated
interface Subscription<TEvent : Any> : @WithApplication suspend (TEvent) -> Unit, Comparable<Subscription<*>>, Closeable {
    /**
     * The source application that involves this subscription
     */
    val application: Application

    /**
     * The publisher that this subscription is listening to
     */
    val publisher: Publisher

    /**
     * The type of the event to specifies the events this subscription will handle
     */
    val eventType: KClass<TEvent>

    /**
     * The priority of this event handler to handle the event
     */
    val priority: Byte

    /**
     * Whether to ignore cancelled events, `false` is you wish to listen
     * cancelled events
     */
    val ignoreCancelled: Boolean

    /**
     * Close this subscription
     */
    @Suppress("UNCHECKED_CAST")
    override fun close() {
        this.publisher.unsubscribe(this as Subscription<Any>)
    }

    override fun compareTo(other: Subscription<*>): Int =
        this.priority - other.priority
}
