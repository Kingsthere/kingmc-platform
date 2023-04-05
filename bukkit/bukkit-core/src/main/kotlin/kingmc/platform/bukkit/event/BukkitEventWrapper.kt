package kingmc.platform.bukkit.event

import kotlin.reflect.KClass

/**
 * A wrapper for wrapping bukkit events to kingmc events
 */
interface BukkitEventWrapper<T : Any, R : Any> : (T) -> R {
    /**
     * The class of the event that this wrapper accepts
     */
    val type: KClass<T>
}