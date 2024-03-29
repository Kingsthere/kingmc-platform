package kingmc.platform.bukkit

import kingmc.platform.World
import kingmc.platform.WorldFactory

/**
 * A `WorldFactory` capable to convert [org.bukkit.World] to
 * [getWorld]
 *
 * @author kingsthere
 * @since 0.0.7
 */
@BukkitImplementation
interface BukkitWorldFactory : WorldFactory {
    /**
     * Gets a world from a [org.bukkit.World]
     */
    fun getWorldForBukkit(bukkitWorld: _BukkitWorld): World
}
