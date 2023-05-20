package kingmc.platform.bukkit.entity.player

import kingmc.platform.entity.player.OfflinePlayer

/**
 * Extended `OfflinePlayer` exposed an interface to convert this into a `org.bukkit.OfflinePlayer`
 *
 * @since 0.0.7
 * @author kingsthere
 */
interface BukkitOfflinePlayer : OfflinePlayer {
    /**
     * Convert this bukkit offline player to a `org.bukkit.OfflinePlayer`
     */
    fun toBukkitPlayer(): _BukkitOfflinePlayer
}