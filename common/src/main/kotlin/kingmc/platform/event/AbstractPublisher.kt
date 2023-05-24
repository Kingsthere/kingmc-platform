package kingmc.platform.event

import kingmc.common.application.withApplication
import kingmc.common.application.withApplicationSuspend
import kingmc.platform.event.subscription.BlockingSubscription
import kingmc.platform.event.subscription.Subscription
import kingmc.platform.event.subscription.SuspendSubscription
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.reflect.KClass

/**
 * An abstract implementation of [Publisher]
 */
abstract class AbstractPublisher : Publisher {
    protected val subscriptions: MutableMap<KClass<*>, MutableSet<Subscription<Any>>> = HashMap()

    /**
     * Register a subscription this publisher
     *
     * Normally you use [Listener.subscribe] instead of just using this
     */
    override fun subscribe(subscription: Subscription<Any>) {
        this.subscriptions.computeIfAbsent(subscription.eventType) { TreeSet() }.add(subscription)
    }

    /**
     * Unsubscribe a registered subscription
     *
     * Normally you use [Listener.unsubscribe] instead of just using this
     */
    override fun unsubscribe(subscription: Subscription<Any>) {
        this.subscriptions[subscription.eventType]?.remove(subscription)
    }

    /**
     * Close this publisher
     */
    override fun close() {
        this.subscriptions.clear()
    }

    /**
     * Call an event and block current thread till every subscription proceed the event, it
     * uses [runBlocking] to start coroutine so [SuspendSubscription] can handle events
     *
     * @param event the event to call
     * @return the event called
     */
    override fun <TEvent : Any> callEvent(event: TEvent): TEvent {
        this@AbstractPublisher.subscriptions[event::class]?.forEach {
            if (it.ignoreCancelled) {
                if (!checkEventCancelled(it)) {
                    if (it is BlockingSubscription<Any>) {
                        withApplication(it.application) {
                            it.handler.invoke(event)
                        }
                    }
                    if (it is SuspendSubscription<Any>) {
                        runBlocking {
                            withApplicationSuspend(it.application) {
                                it.handler.invoke(event)
                            }
                        }
                    }
                }
            } else {
                if (it is BlockingSubscription<Any>) {
                    withApplication(it.application) {
                        it.handler.invoke(event)
                    }
                }
                if (it is SuspendSubscription<Any>) {
                    runBlocking {
                        withApplicationSuspend(it.application) {
                            it.handler.invoke(event)
                        }
                    }
                }
            }
        }
        return event
    }

    /**
     * Call an event suspend
     *
     * @param event the event to call
     * @return the event called
     */
    override suspend fun <TEvent : Any> callEventSuspend(event: TEvent): TEvent {
        this@AbstractPublisher.subscriptions[event::class]?.forEach {
            if (it.ignoreCancelled) {
                if (!checkEventCancelled(it)) {
                    if (it is BlockingSubscription<Any>) {
                        withApplication(it.application) {
                            it.handler.invoke(event)
                        }
                    }
                    if (it is SuspendSubscription<Any>) {
                        withApplicationSuspend(it.application) {
                            it.handler.invoke(event)
                        }
                    }
                }
            } else {
                if (it is BlockingSubscription<Any>) {
                    withApplication(it.application) {
                        it.handler.invoke(event)
                    }
                }
                if (it is SuspendSubscription<Any>) {
                    withApplicationSuspend(it.application) {
                        it.handler.invoke(event)
                    }
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