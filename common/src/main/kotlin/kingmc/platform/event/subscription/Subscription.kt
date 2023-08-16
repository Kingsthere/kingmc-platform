package kingmc.platform.event.subscription

import kingmc.common.application.Application
import kingmc.common.application.Isolated
import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.event.Publisher
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
sealed interface Subscription<TEvent : Any> : Comparable<Subscription<*>>, Closeable {
    /**
     * The source application that creates this subscription
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

/**
 * A `Subscription` that handles events blocking
 *
 * @param TEvent the type of event this subscription listening to
 * @since 0.0.9
 * @author kingsthere
 */
interface BlockingSubscription<TEvent : Any> : Subscription<TEvent> {
    /**
     * The [Function] to execute when this subscription receive events
     */
    val handler: @WithApplication (TEvent) -> Unit
}

/**
 * A `Subscription` that handles events suspend
 *
 * @param TEvent the type of event this subscription listening to
 * @since 0.0.9
 * @author kingsthere
 */
interface SuspendSubscription<TEvent : Any> : Subscription<TEvent> {
    /**
     * The `suspend [Function]` to execute when this subscription receive events
     */
    val handler: @WithApplication suspend (TEvent) -> Unit
}

/**
 * A shortcut to create a [BlockingSubscription]
 *
 * @return subscription created
 */
fun <TEvent : Any> BlockingSubscription(eventType: KClass<TEvent>,
                                handler: @WithApplication (TEvent) -> Unit,
                                priority: Byte = 0,
                                ignoreCancelled: Boolean = true,
                                publisher: Publisher,
                                application: Application = currentApplication()
): BlockingSubscription<TEvent> = BlockingSubscriptionImpl(
    eventType = eventType,
    handler = handler,
    priority = priority,
    ignoreCancelled = ignoreCancelled,
    publisher = publisher,
    application = application
)

/**
 * A shortcut to create a [SuspendSubscription]
 *
 * @return subscription created
 */
fun <TEvent : Any> SuspendSubscription(eventType: KClass<TEvent>,
                                handler: @WithApplication suspend (TEvent) -> Unit,
                                priority: Byte = 0,
                                ignoreCancelled: Boolean = true,
                                publisher: Publisher,
                                application: Application = currentApplication()
): SuspendSubscription<TEvent> = SuspendSubscriptionImpl(
    eventType = eventType,
    handler = handler,
    priority = priority,
    ignoreCancelled = ignoreCancelled,
    publisher = publisher,
    application = application
)