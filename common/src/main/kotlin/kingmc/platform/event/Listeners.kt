package kingmc.platform.event

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.listeners
import kotlin.reflect.KClass

/**
 * Builder reference for building a listener
 *
 * @since 0.0.3
 * @author kingsthere
 */
open class ListenerScope<E : Any>(
    /**
     * The target event type this listener is listening to
     *
     * @since 0.0.3
     */
    var target: KClass<E>?,

    /**
     * The handler of this listener scope
     *
     * @since 0.0.3
     */
    var handler: (suspend (E) -> Unit)?,

    /**
     * The priority of this listener to handle the
     * event
     *
     * @since 0.0.3
     */
    var priority: Byte) {
}

/**
 * Set the handler of current listener scope
 *
 * @since 0.0.3
 */
fun <E : Any> ListenerScope<E>.handler(consumer: suspend (E) -> Unit): ListenerScope<E> {
    this.handler = consumer
    return this
}

/**
 * Configure the listener manager in current application
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun listeners(block: @WithApplication ListenerManager.() -> Unit) {
    currentApplication().listeners.block()
}

inline fun <reified E : Any> ListenerManager.register(configure: ListenerScope<E>.() -> Unit): RegisteredListener {
    val listener = ListenerScope(E::class, {  }, 0)
    listener.configure()
    return register(E::class, listener)
}

inline fun <reified E : Any> ListenerManager.register(listener: ListenerScope<E>): RegisteredListener =
    register(E::class, listener)

@WithApplication
fun <E : Any> ListenerManager.register(type: KClass<E>, listener: ListenerScope<E>): RegisteredListener =
    publisher.register(type, listener.handler!!, listener.priority)