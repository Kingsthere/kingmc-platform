package kingmc.platform.bukkit.adventure.impl

import kingmc.common.application.application
import kingmc.common.application.withApplication
import kingmc.common.context.annotation.Autowired
import kingmc.common.context.annotation.Component
import kingmc.platform.audience.Console
import kingmc.platform.bukkit.BukkitServer
import kingmc.platform.bukkit._BukkitServer
import kingmc.platform.bukkit.adventure.AdventureEnvironment
import kingmc.platform.bukkit.adventure.impl.audience.AdventureBukkitConsoleImpl
import kingmc.platform.bukkit.adventure.impl.audience.AdventureOnlineBukkitPlayerImpl
import kingmc.platform.bukkit.entity.player._BukkitCommandSender
import kingmc.platform.bukkit.entity.player._BukkitOfflinePlayer
import kingmc.platform.bukkit.entity.player._BukkitPlayer
import kingmc.platform.bukkit.impl.entity.player.BukkitOfflinePlayerImpl
import kingmc.platform.command.CommandSender
import kingmc.platform.entity.player.OfflinePlayer
import kingmc.platform.entity.player.Player
import kingmc.platform.messaging.OutputMessage
import java.util.*

/**
 * A bukkit side `Server` implementation, it is implemented by calling bukkit api & adventure api as a fallback
 * solution for servers that have the bukkit api but cannot use the nms implementation
 *
 * @author kingsthere
 * @since 0.1.0
 */
@Component
open class AdventureBukkitServerImpl : BukkitServer {
    /**
     * A `WeakHashMap` caches `Player` instances for [org.bukkit.entity.Player]
     */
    protected val players: MutableMap<_BukkitPlayer, Player> = WeakHashMap()

    /**
     * A `WeakHashMap` caches `Player` instances for [org.bukkit.OfflinePlayer]
     */
    protected val offlinePlayers: MutableMap<_BukkitOfflinePlayer, OfflinePlayer> = WeakHashMap()

    /**
     * [org.bukkit.Server] instance
     */
    protected val bukkitServer = withApplication { kingmc.platform.bukkit.driver.bukkitServer }

    /**
     * [org.bukkit.plugin.Plugin] instance
     */
    protected val bukkitPlugin = withApplication { kingmc.platform.bukkit.driver.bukkitPlugin }

    @Autowired
    lateinit var adventure: AdventureEnvironment

    override val console: Console by lazy {
        AdventureBukkitConsoleImpl(adventure.audienceProvider().console())
    }

    /**
     * Gets all players that online
     *
     * @return all players that online
     */
    override fun getOnlinePlayers(): Set<Player> {
        return bukkitServer.onlinePlayers.map { getPlayerForBukkit(it) }.toSet()
    }

    /**
     * Gets every player that has ever played on this server
     */
    override fun getOfflinePlayers(): List<OfflinePlayer> {
        return bukkitServer.offlinePlayers.map { getOfflinePlayerForBukkit(it) }
    }

    /**
     * Gets an online player by its username
     *
     * @param username the username of the player
     * @return player named [username], or `null` if the player named [username] isn't exists or offline
     */
    override fun getPlayer(username: String): Player? {
        return bukkitServer.getPlayerExact(username)?.let { player -> getPlayerForBukkit(player) }
    }

    /**
     * Gets an online player by its uuid
     *
     * @param uuid the uuid of the player
     * @return player with [uuid], or `null` if the player with [uuid] isn't exists or offline
     */
    override fun getPlayer(uuid: UUID): Player? {
        return bukkitServer.getPlayer(uuid)?.let { player -> getPlayerForBukkit(player) }
    }

    /**
     * Gets an offline player by its username
     *
     * @param username the username of the player
     * @return the offline player get
     */
    @Deprecated("Persistent storage of users should be by UUID as names are no longer unique past a single session",
        ReplaceWith("getOfflinePlayerForBukkit(bukkitServer.getOfflinePlayer(username))")
    )
    override fun getOfflinePlayer(username: String): OfflinePlayer {
        return getOfflinePlayerForBukkit(bukkitServer.getOfflinePlayer(username))
    }

    /**
     * Gets an offline player
     *
     * @param uuid the uuid of the player
     * @return the offline player get
     */
    override fun getOfflinePlayer(uuid: UUID): OfflinePlayer {
        return getOfflinePlayerForBukkit(bukkitServer.getOfflinePlayer(uuid))
    }

    /**
     * Sends this recipient a Plugin Message on the specified outgoing
     * channel
     *
     *
     * The message may not be larger than 32766
     * bytes, and the plugin must be registered to send messages on the
     * specified channel
     *
     * @param channel The channel to send this message on
     * @param message The raw message to send
     */
    override fun sendPluginMessage(channel: String, message: OutputMessage) {
        bukkitServer.sendPluginMessage(bukkitPlugin, channel, message.toByteArray())
    }

    /**
     * Gets a set containing all the Plugin Channels that this client is
     * listening on
     *
     * @return Set containing all the channels that this client may accept
     */
    override val listeningPluginChannels: Set<String>
        get() = bukkitServer.listeningPluginChannels

    override fun asBukkitServer(): _BukkitServer {
        return bukkitServer
    }

    override fun getPlayerForBukkit(bukkitPlayer: _BukkitPlayer): Player {
        return players.computeIfAbsent(bukkitPlayer) {
            createPlayerForBukkit(bukkitPlayer)
        }
    }

    protected open fun createPlayerForBukkit(bukkitPlayer: _BukkitPlayer): Player =
        AdventureOnlineBukkitPlayerImpl(bukkitPlayer, adventure.audienceProvider().player(bukkitPlayer),
            application
        )

    override fun getCommandSenderForBukkit(bukkitCommandSender: _BukkitCommandSender): CommandSender {
        TODO("Not yet implemented")
    }

    override fun getOfflinePlayerForBukkit(bukkitOfflinePlayer: _BukkitOfflinePlayer): OfflinePlayer {
        return offlinePlayers.computeIfAbsent(bukkitOfflinePlayer) {
            BukkitOfflinePlayerImpl(it)
        }
    }
}