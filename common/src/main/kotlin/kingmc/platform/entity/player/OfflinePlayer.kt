package kingmc.platform.entity.player

import java.util.*

/**
 * Represent an offline player, defined extra information of a player could
 * hold when it is not online
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface OfflinePlayer {
    /**
     * The name of this entity
     */
    val name: String

    /**
     * The uuid of this entity
     */
    val uuid: UUID

    /**
     * Checks if this player is currently online
     */
    val isOnline: Boolean

    /**
     * Gets the first date and time that this player was witnessed on this
     * server
     *
     *
     * If the player has never played before, this will return 0. Otherwise,
     * it will be the amount of milliseconds since midnight, January 1, 1970,
     * UTC
     *
     * @return Date of first log-in for this player, or 0
     */
    val firstPlayed: Long

    /**
     * Gets the last date and time that this player was witnessed on this
     * server
     *
     *
     * If the player has never played before, this will return 0. Otherwise,
     * it will be the amount of milliseconds since midnight, January 1, 1970,
     * UTC
     *
     * @return Date of last log-in for this player, or 0
     */
    val lastPlayed: Long

    /**
     * Checks if this player has played on this server before
     *
     * @return True if the player has played before, otherwise false
     */
    fun hasPlayedBefore(): Boolean
}