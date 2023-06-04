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

    val registerListener = Facet<Listener, Unit> { listener ->
        _registeredListeners.add(listener)
        listener.activate()
    }

    /**
     * Register a listener to this listener manager
     */
    @FacetAvailable
    final override fun registerListener(listener: Listener) = registerListener.invoke(listener)

    val unregisterListener = Facet<Listener, Unit> { listener ->
        _registeredListeners.remove(listener)
        listener.close()
    }

    /**
     * Unregister a listener
     *
     * @throws IllegalArgumentException if the [listener] is not registered in this listener manager
     */
    @FacetAvailable
    final override fun unregisterListener(listener: Listener) = unregisterListener.invoke(listener)

    val getRegisteredListener = Facet<Set<Listener>> {
        return@Facet _registeredListeners
    }

    /**
     * Gets listeners registered in this listener manager
     */
    @FacetAvailable
    override fun getRegisteredListeners(): Set<Listener> = getRegisteredListener.invoke()

    val close = Facet<Unit> {
        _registeredListeners.forEach {
            it.close()
        }
    }

    /**
     * Close this listener manager
     */
    @FacetAvailable
    override fun close() = close.invoke()
}