package kingmc.platform.event

import kingmc.common.application.*
import kingmc.common.context.annotation.Component
import kingmc.platform.publisher
import java.io.Closeable
import java.util.*
import kotlin.reflect.KClass

/**
 * Abstract class for create a listener to listen events
 *
 * @since 0.0.7
 * @author kingsthere
 */
@Isolated
@Component
abstract class Listener(publisher: Publisher? = null) : Closeable {
    init {
        this.application.addShutdownHook {
            this@Listener.close()
        }
    }

    /**
     * Available subscriptions created by this listener
     */
    protected val subscriptions: MutableSet<Subscription<Any>> = TreeSet(compareBy { it.priority })

    /**
     * The publisher to register this listener to
     */
    val publisher = publisher ?: this.application.publisher

    /**
     * Activate this listener and register it into [publisher]
     */
    fun activate() {
        this.subscriptions.forEach {
            publisher.subscribe(it)
        }
    }

    /**
     * Let this listener subscribe an event
     *
     * @param eventClass the class of the event type subscribing
     * @param callback the callback to call when events listened from publisher
     * @param priority the priority of this listener to handle the event to call the [callback]
     * @param ignoreCancelled whether to ignore cancelled events
     * @param publisher the publisher to subscribe the event
     * @return subscription created
     */
    @Suppress("UNCHECKED_CAST")
    @WithApplication
    fun <TEvent : Any> subscribe(eventClass: KClass<TEvent>,
                                 callback: @WithApplication suspend (TEvent) -> Unit,
                                 priority: Byte,
                                 ignoreCancelled: Boolean,
                                 publisher: Publisher,
                                 application: Application = currentApplication()
    ): Subscription<TEvent> {
        val subscription = LambdaSubscription(eventClass, callback, priority, ignoreCancelled, publisher, application)
        this.subscriptions.add(subscription as Subscription<Any>)
        return subscription
    }

    /**
     * Unsubscribe a [subscription] that subscribed from this listener
     *
     * @param subscription the subscription to unsubscribe
     * @throws IllegalArgumentException if the subscription is not owned by this listener
     */
    fun unsubscribe(subscription: Subscription<*>) {
        val subscriptionRemoval = requireNotNull(subscriptions.find { it == subscription }) { "Subscription $subscription is not owned by this listener" }
        this.subscriptions.remove(subscriptionRemoval)
        this.publisher.unsubscribe(subscriptionRemoval)
    }

    /**
     * Close this listener
     */
    override fun close() {
        this.subscriptions.forEach {
            this.publisher.unsubscribe(it)
        }
        this.subscriptions.clear()
    }
}

/**
 * Let this listener subscribe an event
 *
 * @param TEvent the type of event this subscribes to
 * @param callback the callback to call when events listened from publisher
 * @param priority the priority of this listener to handle the event to call the [callback]
 * @param ignoreCancelled whether to ignore cancelled events
 * @param publisher the publisher to subscribe the event
 * @receiver the listener used to subscribe the event
 * @return subscription created
 */
inline fun <reified TEvent : Any> Listener.subscribe(
    priority: Byte = 0,
    ignoreCancelled: Boolean = true,
    publisher: Publisher = this.publisher,
    application: Application = currentApplication(),
    noinline callback: @WithApplication suspend (TEvent) -> Unit) {
    this.subscribe(TEvent::class, callback, priority, ignoreCancelled, publisher, application)
}
