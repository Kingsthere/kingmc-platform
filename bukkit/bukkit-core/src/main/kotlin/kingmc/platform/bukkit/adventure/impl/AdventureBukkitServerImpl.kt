package kingmc.platform.bukkit.adventure.impl

import kingmc.common.application.application
import kingmc.common.context.annotation.Autowired
import kingmc.common.context.annotation.Component
import kingmc.platform.audience.Console
import kingmc.platform.bukkit.BukkitServer
import kingmc.platform.bukkit._BukkitServer
import kingmc.platform.bukkit.adventure.Adventure
import kingmc.platform.bukkit.adventure.impl.audience.AdventureBukkitConsoleImpl
import kingmc.platform.bukkit.adventure.impl.audience.AdventureOnlineBukkitPlayerImpl
import kingmc.platform.bukkit.driver.bukkitPlugin
import kingmc.platform.bukkit.driver.bukkitServer
import kingmc.platform.bukkit.entity.player._BukkitOfflinePlayer
import kingmc.platform.bukkit.entity.player._BukkitPlayer
import kingmc.platform.bukkit.impl.entity.player.BukkitOfflinePlayerImpl
import kingmc.platform.entity.player.OfflinePlayer
import kingmc.platform.entity.player.Player
import kingmc.platform.messaging.OutputMessage
import java.util.*

/**
 * A bukkit `Server` implementation
 *
 * @since 0.0.7
 * @author kingsthere
 */
@Component
class AdventureBukkitServerImpl : BukkitServer {
    /**
     * A `WeakHashMap` cache `Player` instances for [org.bukkit.entity.Player]
     */
    val _players: MutableMap<_BukkitPlayer, Player> = WeakHashMap()

    /**
     * A `WeakHashMap` cache `Player` instances for [org.bukkit.OfflinePlayer]
     */
    val _offlinePlayers: MutableMap<_BukkitOfflinePlayer, OfflinePlayer> = WeakHashMap()

    /**
     * [org.bukkit.Server] instance
     */
    val _bukkitServer = application { bukkitServer }

    /**
     * [org.bukkit.plugin.Plugin] instance
     */
    val _bukkitPlugin = application { bukkitPlugin }

    @Autowired
    lateinit var adventure: Adventure

    override val console: Console by lazy {
        AdventureBukkitConsoleImpl(adventure.getAudienceProvider().console())
    }

    /**
     * Gets all players that online
     *
     * @return all players that online
     */
    override fun getOnlinePlayers(): Set<Player> {
        return _bukkitServer.onlinePlayers.map { getPlayerForBukkit(it) }.toSet()
    }

    /**
     * Gets every player that has ever played on this server
     */
    override fun getOfflinePlayers(): List<OfflinePlayer> {
        return _bukkitServer.offlinePlayers.map { getOfflinePlayerForBukkit(it) }
    }

    /**
     * Gets an online player by its username
     *
     * @param username the username of the player
     * @return player named [username], or `null` if the player named [username] isn't exists or offline
     */
    override fun getPlayer(username: String): Player? {
        return _bukkitServer.getPlayerExact(username)?.let { player -> getPlayerForBukkit(player) }
    }

    /**
     * Gets an online player by its uuid
     *
     * @param uuid the uuid of the player
     * @return player with [uuid], or `null` if the player with [uuid] isn't exists or offline
     */
    override fun getPlayer(uuid: UUID): Player? {
        return _bukkitServer.getPlayer(uuid)?.let { player -> getPlayerForBukkit(player) }
    }

    /**
     * Gets an offline player by its username
     *
     * @param username the username of the player
     * @return the offline player get
     */
    @Deprecated("Persistent storage of users should be by UUID as names are no longer unique past a single session")
    override fun getOfflinePlayer(username: String): OfflinePlayer {
        return getOfflinePlayerForBukkit(_bukkitServer.getOfflinePlayer(username))
    }

    /**
     * Gets an offline player
     *
     * @param uuid the uuid of the player
     * @return the offline player get
     */
    override fun getOfflinePlayer(uuid: UUID): OfflinePlayer {
        return getOfflinePlayerForBukkit(_bukkitServer.getOfflinePlayer(uuid))
    }

    /**
     * Sends this recipient a Plugin Message on the specified outgoing
     * channel.
     *
     *
     * The message may not be larger than 32766
     * bytes, and the plugin must be registered to send messages on the
     * specified channel.
     *
     * @param channel The channel to send this message on.
     * @param message The raw message to send.
     */
    override fun sendPluginMessage(channel: String, message: OutputMessage) {
        _bukkitServer.sendPluginMessage(_bukkitPlugin, channel, message.toByteArray())
    }

    /**
     * Gets a set containing all the Plugin Channels that this client is
     * listening on.
     *
     * @return Set containing all the channels that this client may accept.
     */
    override val listeningPluginChannels: Set<String>
        get() = _bukkitServer.listeningPluginChannels

    override fun asBukkitServer(): _BukkitServer {
        return _bukkitServer
    }

    override fun getPlayerForBukkit(bukkitPlayer: _BukkitPlayer): Player {
        return _players.computeIfAbsent(bukkitPlayer) {
            AdventureOnlineBukkitPlayerImpl(it, adventure.getAudienceProvider().player(it), application)
        }
    }

    override fun getOfflinePlayerForBukkit(bukkitOfflinePlayer: _BukkitOfflinePlayer): OfflinePlayer {
        return _offlinePlayers.computeIfAbsent(bukkitOfflinePlayer) {
            BukkitOfflinePlayerImpl(it)
        }
    }
}