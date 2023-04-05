package kingmc.platform.bukkit

import kingmc.platform.World

/**
 * A `World` capable for convert it to a [org.bukkit.World]
 *
 * @since 0.0.7
 * @author kingsthere
 */
@BukkitImplementation
interface BukkitWorld : World {
    /**
     * Convert it to a bukkit world
     */
    fun toBukkitWorld(): _BukkitWorld
}