package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.context.annotation.Component
import kingmc.platform.audience.Audience
import kingmc.platform.audience.Console
import kingmc.platform.entity.player.OfflinePlayer
import kingmc.platform.entity.player.Player
import java.util.*

/**
 * Represents a server
 *
 * @since 0.0.7
 * @author kingsthere
 */
@Component
interface Server {
    /**
     * The console of this server
     */
    val console: Console

    /**
     * Gets all players that online
     *
     * @return all players that online
     */
    fun getOnlinePlayers(): Set<Player>

    /**
     * Gets every player that has ever played on this server
     */
    fun getOfflinePlayers(): Array<OfflinePlayer>

    /**
     * Gets an online player by its username
     *
     * @param username the username of the player
     * @return player named [username], or `null` if the player named [username] isn't exists or offline
     */
    fun getPlayer(username: String): Player?

    /**
     * Gets an online player by its uuid
     *
     * @param uuid the uuid of the player
     * @return player with [uuid], or `null` if the player with [uuid] isn't exists or offline
     */
    fun getPlayer(uuid: UUID): Player?

    /**
     * Gets an offline player by its username
     *
     * @param username the username of the player
     * @return the offline player get
     */
    @Deprecated("Persistent storage of users should be by UUID as names are no longer unique past a single session")
    fun getOfflinePlayer(username: String): OfflinePlayer?

    /**
     * Gets an offline player
     *
     * @param uuid the uuid of the player
     * @return the offline player get
     */
    fun getOfflinePlayer(uuid: UUID): OfflinePlayer?
}

/**
 * A shortcut to get online players on the server
 */
@get:WithApplication
val onlinePlayers: Set<Player>
    get() = currentApplication().server.getOnlinePlayers()


/**
 * A shortcut to get offline players on the server
 */
@get:WithApplication
val offlinePlayers: Array<OfflinePlayer>
    get() = currentApplication().server.getOfflinePlayers()

/**
 * Get the console of current server application from
 * current audience factory
 *
 * @since 0.0.3
 * @author kingsthere
 */
@get:WithApplication
val console: Audience
    get() = currentApplication().server.console

/**
 * A shortcut to get an online player by its uuid
 *
 * @param uuid the uuid of the player
 * @return player with [uuid], or `null` if the player with [uuid] isn't exists or offline
 */
fun getPlayer(uuid: UUID): Player? = currentApplication().server.getPlayer(uuid)

/**
 * A shortcut to get an online player by its username
 *
 * @param username the username of the player
 * @return player named [username], or `null` if the player named [username] isn't exists or offline
 */
fun getPlayer(username: String): Player? = currentApplication().server.getPlayer(username)