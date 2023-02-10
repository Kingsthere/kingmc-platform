package kingmc.platform.event

import kotlin.reflect.KClass

/**
 * Indicating a listener that is already registered in a
 * publisher
 *
 * @since 0.0.1
 * @author kingsthere
 */
interface RegisteredListener {
    /**
     * The handler list of this listener
     *
     * @since 0.0.1
     */
    val handlers: HandlerList

    /**
     * The publisher current listener is injected to
     *
     * @since 0.0.1
     */
    val publisher: Publisher

    /**
     * Get the type of events that this listener is
     * needed to listen to
     *
     * @since 0.0.1
     */
    fun supports(): Set<KClass<*>>
}