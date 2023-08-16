package kingmc.platform.event

import kingmc.common.context.annotation.Component
import kingmc.platform.event.subscription.Subscription

/**
 * A `Publisher` is responsible for publishing specified events and
 * register [Subscription]s to handle them
 *
 * @since 0.0.7
 * @author kingsthere
 */
@Component
interface Publisher {
    /**
     * Register a subscription to this publisher
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
    fun close()

    /**
     * Fire an event and block current thread till every subscription proceed the event
     *
     * @param event the event to fire
     * @return the event fired
     */
    fun <TEvent : Any> fireEvent(event: TEvent) : TEvent

    /**
     * Fire an event in coroutine
     *
     * @param event the event to fire
     * @return the event fired
     */
    suspend fun <TEvent : Any> fireEventSuspend(event: TEvent) : TEvent
}