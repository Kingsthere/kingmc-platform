package kingmc.platform.event

import kotlin.reflect.KClass

/**
 * A registered listener that is registered from a
 * class
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface ClassRegisteredListener : RegisteredListener {
    /**
     * The class of this listener
     */
    val type: KClass<*>
}