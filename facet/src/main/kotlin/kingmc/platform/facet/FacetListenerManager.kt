package kingmc.platform.facet

import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.platform.Releasable
import kingmc.platform.event.Listener
import kingmc.platform.event.ListenerManager

/**
 * Facet implementation of [ListenerManager]
 *
 * @since 0.0.7
 * @author kingsthere
 */
@Component
@Scope(BeanScope.SINGLETON)
@FacetImplementation
abstract class FacetListenerManager : ListenerManager, Releasable {
    protected val _registeredListeners: MutableSet<Listener> = mutableSetOf()

    /**
     * Close this listener manager
     */
    override fun release() {
        close()
    }

    /**
     * Register a listener to this listener manager
     */
    override fun registerListener(listener: Listener) {
        _registeredListeners.add(listener)
        listener.activate()
    }

    /**
     * Unregister a listener
     *
     * @throws IllegalArgumentException if the [listener] is not registered in this listener manager
     */
    override fun unregisterListener(listener: Listener) {
        _registeredListeners.remove(listener)
        listener.close()
    }

    /**
     * Gets listeners registered in this listener manager
     */
    override fun getRegisteredListeners(): Set<Listener> {
        return _registeredListeners
    }

    /**
     * Close this listener manager
     */
    override fun close() {
        _registeredListeners.forEach {
            it.close()
        }
    }
}