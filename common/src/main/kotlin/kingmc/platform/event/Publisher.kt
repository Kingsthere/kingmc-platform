package kingmc.platform.event

import kingmc.common.context.annotation.Component
import kingmc.util.SubclassSingleton
import kotlin.reflect.KClass

/**
 * Represent a publisher that could publish many events
 * to [Listener] that registered to subscribe this publisher
 *
 *
 * A publisher should not instantiate **more than once**, the
 * publishers could receive the events from others and forward
 * them to the [Listener]
 *
 * @since 0.0.1
 * @author kingsthere
 */
@Component
@SubclassSingleton
interface Publisher {
    /**
     * Register a listener into this publisher
     * so when this publisher receives events
     * it will forward to the registered listeners
     *
     * @since 0.0.1
     * @see Listener
     */
    fun register(listener: Any): RegisteredListener

    /**
     * Register a lambda listener to this publisher
     *
     * @since 0.0.1
     */
    fun <T : Any> register(event: KClass<out T>, listener: suspend (T) -> Unit, priority: Byte = 0, ignoreCancelled: Boolean = false): RegisteredListener

    /**
     * To remove a registered listener from this publisher
     *
     * @since 0.0.1
     * @see RegisteredListener
     */
    fun cancel(listener: RegisteredListener)

    /**
     * To remove a registered listener by the instance
     *
     * @since 0.0.1
     * @see Listener
     */
    fun cancel(listener: Any)

    /**
     * Clear all event listener registered in this publisher
     *
     * @since 0.0.4
     */
    fun clear()

    /**
     * Call an event to this publisher so this
     * publisher will forward the event called
     * to listeners
     *
     * @see Listener
     * @since 0.0.1
     * @return the event
     */
    fun <T : Any> callEvent(event: T) : T

    /**
     * Call an event suspend to this publisher so this
     * publisher will forward the event called
     * to listeners
     *
     * @see Listener
     * @since 0.0.1
     * @return the event
     */
    suspend fun <T : Any> callEventSuspend(event: T) : T
}