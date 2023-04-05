package kingmc.platform.event

import kingmc.common.context.annotation.Component
import java.io.Closeable

/**
 * A `Publisher` is responsible for publishing specified events and
 * register [Subscription]s to handle them
 *
 * @since 0.0.7
 * @author kingsthere
 */
@Component
interface Publisher : Closeable {
    /**
     * Register a subscription this publisher
     *
     * Normally you use [Listener.subscribe] instead of just using this
     */
    fun subscribe(subscription: Subscription<Any>)

    /**
     * Unsubscribe a registered subscription
     *
     * Normally you use [Listener.unsubscribe] instead of just using this
     */
    fun unsubscribe(subscription: Subscription<Any>)

    /**
     * Close this publisher
     */
    override fun close()

    /**
     * Call an event and block current thread till every subscription proceed the event
     *
     * @param event the event to call
     * @return the event called
     */
    fun <TEvent : Any> callEvent(event: TEvent) : TEvent

    /**
     * Call an event suspend
     *
     * @param event the event to call
     * @return the event called
     */
    suspend fun <TEvent : Any> callEventSuspend(event: TEvent) : TEvent
}