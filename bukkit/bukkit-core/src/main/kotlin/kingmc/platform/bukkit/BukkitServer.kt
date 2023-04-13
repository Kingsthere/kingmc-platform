package kingmc.platform.bukkit

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.context.annotation.Component
import kingmc.platform.Server
import kingmc.platform.bukkit.entity.player._BukkitPlayer
import kingmc.platform.entity.player.Player
import kingmc.platform.server

/**
 * A `Server` capable to convert into a [_BukkitServer] and capable to convert
 * bukkit players to [kingmc.platform.entity.player.Player]
 *
 * @since 0.0.7
 * @author kingsthere
 */
@Component
interface BukkitServer : Server {
    /**
     * Convert this server to a [_BukkitServer]
     */
    fun asBukkitServer(): _BukkitServer

    /**
     * Gets a [Player] for bukkit player [bukkitPlayer]
     *
     * @param bukkitPlayer the bukkit player
     * @return player
     */
    fun getPlayerForBukkit(bukkitPlayer: _BukkitPlayer): Player
}

/**
 * A shortcut to get a [Player] for bukkit player [bukkitPlayer]
 *
 * @param bukkitPlayer the bukkit player
 * @return player
 */
@WithApplication
fun getPlayerForBukkit(bukkitPlayer: _BukkitPlayer): Player {
    return (currentApplication().server as BukkitServer).getPlayerForBukkit(bukkitPlayer)
}