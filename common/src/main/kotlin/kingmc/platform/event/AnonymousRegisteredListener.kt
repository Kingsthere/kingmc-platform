package kingmc.platform.event

import kotlin.reflect.KClass

class AnonymousRegisteredListener(override val handlers: HandlerList, override val publisher: Publisher) : RegisteredListener {
    /**
     * Get the type of events that this listener is
     * needed to listen to
     *
     * @since 0.0.1
     */
    override fun supports(): Set<KClass<*>> =
        this.handlers.keys
}