package kingmc.platform.event

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.publisher

/**
 * A shortcut to get the publisher of current application
 */
@get:WithApplication
val publisher: Publisher
    get() = currentApplication().publisher

/**
 * A shortcut to fire the receiver event and block current thread till every subscription
 * proceed the event
 *
 * @receiver event to fire
 * @return the event fired
 */
fun <TEvent : Any> TEvent.fire(): TEvent {
    return fireEvent(this)
}

/**
 * A shortcut to fire the receiver event in coroutine
 *
 * @receiver event to fire
 * @return the event fired
 */
suspend fun <TEvent : Any> TEvent.fireSuspend(): TEvent {
    return fireEventSuspend(this)
}

/**
 * A shortcut to fire an event and block current thread till every subscription proceed the event
 *
 * @param event the event to fire
 * @return the event fired
 */
@WithApplication
fun <TEvent : Any> fireEvent(event: TEvent): TEvent = publisher.fireEvent(event)

/**
 * A shortcut to fire an event in coroutine
 *
 * @param event the event to fire
 * @return the event fired
 */
@WithApplication
suspend fun <TEvent : Any> fireEventSuspend(event: TEvent): TEvent = publisher.fireEventSuspend(event)