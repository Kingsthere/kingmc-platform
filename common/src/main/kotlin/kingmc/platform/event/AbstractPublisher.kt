package kingmc.platform.event

import kingmc.common.application.suspendApplication
import kotlinx.coroutines.runBlocking
import java.util.*

/**
 * An abstract implementation of [Publisher]
 */
abstract class AbstractPublisher : Publisher {
    protected val subscriptions: MutableSet<Subscription<Any>> = TreeSet(compareBy { it })

    /**
     * Register a subscription this publisher
     *
     * Normally you use [Listener.subscribe] instead of just using this
     */
    override fun subscribe(subscription: Subscription<Any>) {
        this.subscriptions.add(subscription)
    }

    /**
     * Unsubscribe a registered subscription
     *
     * Normally you use [Listener.unsubscribe] instead of just using this
     */
    override fun unsubscribe(subscription: Subscription<Any>) {
        this.subscriptions.remove(subscription)
    }

    /**
     * Close this publisher
     */
    override fun close() {
        this.subscriptions.clear()
    }

    /**
     * Call an event and block current thread till every subscription proceed the event
     *
     * @param event the event to call
     * @return the event called
     */
    override fun <TEvent : Any> callEvent(event: TEvent): TEvent {
        return runBlocking {
            callEventSuspend(event)
        }
    }

    /**
     * Call an event suspend
     *
     * @param event the event to call
     * @return the event called
     */
    override suspend fun <TEvent : Any> callEventSuspend(event: TEvent): TEvent {
        this@AbstractPublisher.subscriptions.forEach {
            if (it.ignoreCancelled) {
                if (!checkEventCancelled(it)) {
                    suspendApplication(it.application) {
                        it.invoke(event)
                    }
                }
            } else {
                suspendApplication(it.application) {
                    it.invoke(event)
                }
            }
        }
        return event
    }

    open fun checkEventCancelled(event: Any): Boolean {
        return if (event is Cancellable) {
            event.cancelled
        } else {
            false
        }
    }
}