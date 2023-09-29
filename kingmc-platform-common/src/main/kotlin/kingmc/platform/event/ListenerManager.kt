package kingmc.platform.event

import kingmc.common.application.Isolated
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope

/**
 * The manager to manage listeners
 *
 * @author kingsthere
 * @since 0.0.3
 */
@Component
@Scope(BeanScope.SINGLETON)
@Isolated
interface ListenerManager {
    /**
     * Register a listener to this listener manager
     */
    fun registerListener(listener: Listener)

    /**
     * Unregister a listener
     *
     * @throws IllegalArgumentException if the [listener] is not registered in this listener manager
     */
    fun unregisterListener(listener: Listener)

    /**
     * Gets listeners registered in this listener manager
     */
    fun getRegisteredListeners(): Set<Listener>

    /**
     * Close this listener manager and release all listeners registered
     * by this listener manager
     */
    fun close()
}