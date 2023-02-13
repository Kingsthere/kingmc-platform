package kingmc.platform.event

import kotlin.reflect.KClass

open class DefaultRegisteredListener(
    override val publisher: Publisher,
    override val handlers: HandlerList,
    override val type: KClass<*>, override val instance: Any
) : ClassRegisteredListener, RegisteredListener {

    /**
     * Get the type of events that this listener is
     * needed to listen to
     *
     * @since 0.0.1
     */
    override fun supports(): Set<KClass<*>> =
        this.handlers.keys

    override fun toString(): String {
        return "DefaultRegisteredListener(publisher=$publisher, handlers=$handlers, type=$type)"
    }
}