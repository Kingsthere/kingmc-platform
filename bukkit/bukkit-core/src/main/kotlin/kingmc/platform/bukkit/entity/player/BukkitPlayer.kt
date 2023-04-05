package kingmc.platform.bukkit.entity.player

import kingmc.platform.bukkit.audience.BukkitCommandSender
import kingmc.platform.entity.player.Player

/**
 * Extended `Player` exposed an interface to convert this into a `org.bukkit.Player`
 *
 * @since 0.0.7
 * @author kingsthere
 */
interface BukkitPlayer : Player, BukkitCommandSender {
    /**
     * Convert this bukkit player to a bukkit player
     */
    fun toBukkitPlayer(): _BukkitPlayer

    override fun toBukkitCommandSender(): _BukkitCommandSender {
        return toBukkitPlayer()
    }
}