package kingmc.platform.bukkit

import kingmc.platform.World
import kingmc.platform.bukkit._BukkitWorld

/**
 * A `World` capable for convert it to a [org.bukkit.World]
 *
 * @author kingsthere
 * @since 0.0.7
 */
@BukkitImplementation
interface BukkitWorld : World {
    /**
     * Convert it to a bukkit world
     */
    fun toBukkitWorld(): _BukkitWorld
}