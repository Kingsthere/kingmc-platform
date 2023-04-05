package kingmc.platform.bukkit

import kingmc.common.application.application
import kingmc.common.application.currentApplication
import kingmc.common.context.annotation.Component
import kingmc.platform.Awake
import kingmc.platform.Server
import kingmc.platform.audience.Console
import kingmc.platform.bukkit.audience.adventure.AdventureBukkitConsole
import kingmc.platform.bukkit.audience.adventure.AdventureBukkitPlayer
import kingmc.platform.bukkit.audience.adventure.adventureBukkitAudiences
import kingmc.platform.bukkit.driver.bukkitPlugin
import kingmc.platform.bukkit.driver.bukkitServer
import kingmc.platform.bukkit.entity.player._BukkitPlayer
import kingmc.platform.entity.player.OfflinePlayer
import kingmc.platform.entity.player.Player
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.*

/**
 * Default bukkit platform [Server] implementation
 *
 * @since 0.0.7
 * @author kingsthere
 */
@Component
class AdventureBukkitServer : BukkitServer, _BukkitListener {
    val _onlinePlayers: MutableMap<UUID, Player> = mutableMapOf()
    val _onlinePlayersByUsername: MutableMap<String, Player> = mutableMapOf()

    @Awake(3)
    fun enable() {
        bukkitServer.pluginManager.registerEvents(this, bukkitPlugin)
    }

    @_BukkitEventHandler
    fun handlePlayerLogin(playerLogin: PlayerLoginEvent) {
        application {
            if (playerLogin.result != PlayerLoginEvent.Result.ALLOWED) {
                return@application
            }

            val player = playerLogin.player
            val ins = AdventureBukkitPlayer(player,  adventureBukkitAudiences.player(player), currentApplication())
            _onlinePlayers.put(player.uniqueId, ins)
            _onlinePlayersByUsername.put(player.name, ins)
        }
    }

    @_BukkitEventHandler
    fun handlePlayerLogin(playerJoin: PlayerJoinEvent) {
        application {
            val player = playerJoin.player
            val ins = AdventureBukkitPlayer(player,  adventureBukkitAudiences.player(player), currentApplication())
            _onlinePlayers.put(player.uniqueId, ins)
            _onlinePlayersByUsername.put(player.name, ins)
        }
    }

    @_BukkitEventHandler
    fun handlePlayerLeave(playerQuit: PlayerQuitEvent) {
        application {
            val player = playerQuit.player
            _onlinePlayers.remove(player.uniqueId)
            _onlinePlayersByUsername.remove(player.name)
        }
    }

    override val console: Console by lazy {
        AdventureBukkitConsole(adventureBukkitAudiences.console())
    }

    /**
     * Gets all players that online
     *
     * @return all players that online
     */
    override fun getOnlinePlayers(): Set<Player> {
        return _onlinePlayers.values.toSet()
    }

    /**
     * Gets every player that has ever played on this server
     */
    override fun getOfflinePlayers(): Array<OfflinePlayer> {
        TODO("Not yet implemented")
    }

    /**
     * Gets an online player by its username
     *
     * @param username the username of the player
     * @return player named [username], or `null` if the player named [username] isn't exists or offline
     */
    override fun getPlayer(username: String): Player? {
        return _onlinePlayersByUsername[username]
    }

    /**
     * Gets an online player by its uuid
     *
     * @param uuid the uuid of the player
     * @return player with [uuid], or `null` if the player with [uuid] isn't exists or offline
     */
    override fun getPlayer(uuid: UUID): Player? {
        return _onlinePlayers[uuid]
    }

    /**
     * Gets an offline player by its username
     *
     * @param username the username of the player
     * @return the offline player get
     */
    @Deprecated("Persistent storage of users should be by UUID as names are no longer unique past a single session")
    override fun getOfflinePlayer(username: String): OfflinePlayer? {
        TODO("Not yet implemented")
    }

    /**
     * Gets an offline player
     *
     * @param uuid the uuid of the player
     * @return the offline player get
     */
    override fun getOfflinePlayer(uuid: UUID): OfflinePlayer? {
        TODO("Not yet implemented")
    }

    override fun asBukkitServer(): _BukkitServer {
        return bukkitServer
    }

    override fun getPlayerForBukkit(bukkitPlayer: _BukkitPlayer): Player {
        return _onlinePlayers[bukkitPlayer.uniqueId]!!
    }
}