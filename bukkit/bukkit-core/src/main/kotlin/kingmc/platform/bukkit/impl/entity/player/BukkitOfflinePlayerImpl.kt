package kingmc.platform.bukkit.impl.entity.player

import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.entity.player.BukkitOfflinePlayer
import kingmc.platform.bukkit.entity.player._BukkitOfflinePlayer
import java.util.*

/**
 * A bukkit side implementation of [BukkitOfflinePlayer]
 *
 * @since 0.1.0
 * @author kingsthere
 */
@BukkitImplementation
open class BukkitOfflinePlayerImpl(private val _bukkitOfflinePlayer: _BukkitOfflinePlayer) : BukkitOfflinePlayer {
    override fun toBukkitPlayer(): _BukkitOfflinePlayer =
        _bukkitOfflinePlayer

    override val name: String?
        get() = _bukkitOfflinePlayer.name
    override val uuid: UUID
        get() = _bukkitOfflinePlayer.uniqueId
    override val isOnline: Boolean
        get() = _bukkitOfflinePlayer.isOnline
    override val firstPlayed: Long
        get() = _bukkitOfflinePlayer.firstPlayed
    override val lastPlayed: Long
        get() = _bukkitOfflinePlayer.lastPlayed

    override fun hasPlayedBefore(): Boolean {
        return _bukkitOfflinePlayer.hasPlayedBefore()
    }
}