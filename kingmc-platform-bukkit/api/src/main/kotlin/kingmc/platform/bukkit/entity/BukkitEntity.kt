package kingmc.platform.bukkit.entity

import kingmc.platform.bukkit.command.BukkitCommandSender
import kingmc.platform.bukkit.entity.player._BukkitCommandSender
import kingmc.platform.entity.Entity

/**
 * Extended `Entity` exposed an interface to convert this into a `org.bukkit.entity.Entity`
 *
 * @author kingsthere
 * @since 0.0.7
 */
interface BukkitEntity : Entity, BukkitCommandSender {
    /**
     * Convert this bukkit entity to a `org.bukkit.entity.Entity`
     */
    fun toBukkitEntity(): _BukkitEntity

    override fun toBukkitCommandSender(): _BukkitCommandSender {
        return toBukkitEntity()
    }
}