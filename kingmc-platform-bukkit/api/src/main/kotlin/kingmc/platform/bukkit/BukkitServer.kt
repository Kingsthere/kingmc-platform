package kingmc.platform.bukkit

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.context.annotation.Component
import kingmc.platform.Server
import kingmc.platform.bukkit.entity.player._BukkitCommandSender
import kingmc.platform.bukkit.entity.player._BukkitOfflinePlayer
import kingmc.platform.bukkit.entity.player._BukkitPlayer
import kingmc.platform.command.CommandSender
import kingmc.platform.entity.player.OfflinePlayer
import kingmc.platform.entity.player.Player
import kingmc.platform.server

/**
 * A `Server` capable to convert into a [_BukkitServer] and capable to convert
 * bukkit players to [kingmc.platform.entity.player.Player]
 *
 * @author kingsthere
 * @since 0.0.7
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

    /**
     * Gets a [CommandSender] for bukkit command sender [bukkitCommandSender]
     *
     * @param bukkitCommandSender the bukkit command sender
     * @return player
     */
    fun getCommandSenderForBukkit(bukkitCommandSender: _BukkitCommandSender): CommandSender

    /**
     * Gets a [OfflinePlayer] for bukkit offline player [bukkitOfflinePlayer]
     *
     * @param bukkitOfflinePlayer the bukkit offline player
     * @return player
     */
    fun getOfflinePlayerForBukkit(bukkitOfflinePlayer: _BukkitOfflinePlayer): OfflinePlayer
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