
package kingmc.platform.audience

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.audience.text.Text
import kingmc.platform.audiences
import kingmc.platform.util.TextDisplayable
import java.util.*
import java.util.function.Predicate

/**
 * Create and return a new audience identifier that identity
 * to a player
 *
 * @since 0.0.3
 */
fun player(): PlayerIdentifier =
    PlayerIdentifier(null, null)

/**
 * Get a player from factory provided from current application's platform
 * is loading this plugin
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun playerWith(playerIdentifier: PlayerIdentifier) =
    currentApplication().audiences.audience(playerIdentifier)


/**
 * Get a player from factory by the identifier building
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun playerWith(playerIdentifier: PlayerIdentifier.() -> PlayerIdentifier): Player? {
    return playerWith(player().playerIdentifier())
}

fun PlayerIdentifier.named(name: String) =
    this.withName(name)

/**
 * Gets a [Players] refer all online players
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun players(): Players =
    currentApplication().audiences.players()

/**
 * Gets a player named [name]
 *
 * @since 0.0.5
 * @author kingsthere
 */
@WithApplication
fun playerNamed(name: String) =
    currentApplication().audiences.player(name)

/**
 * Gets a player with uuid [uuid]
 *
 * @since 0.0.5
 * @author kingsthere
 */
@WithApplication
fun playerWithUUID(uuid: UUID) =
    currentApplication().audiences.player(uuid)

/**
 * Gets an [Audience] refer all audiences (include players & console)
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun all(): Audience =
    currentApplication().audiences.all()

/**
 * Gets an [Audience] refer all filtered audiences (include players & console)
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun all(predicate: Predicate<Audience>): Audience =
    currentApplication().audiences.all(predicate)

/**
 * Gets all online audiences, includes online players and console
 *
 * @since 0.0.5
 * @author kingsthere
 */
@WithApplication
fun onlineAudiences(): List<Audience> =
    currentApplication().audiences.toList()

/**
 * Get all players from the player factory from current
 * application's audience factory
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun onlinePlayers(): List<Player> =
    currentApplication().audiences.filterIsInstance<Player>()

/**
 * Get all players name from the player factory from
 * current application's audience factory
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun onlinePlayerNames(): List<String> =
    onlinePlayers()
        .map { it.name }

/**
 * Get the console of current server application from
 * current audience factory
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun console(): Console =
    currentApplication().audiences.console()

/**
 * Get a player by name from current audience factory
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun Player(name: String) =
    currentApplication().audiences.player(name)

/**
 * Get a player by uuid from current audience factory
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun Player(uuid: UUID) =
    currentApplication().audiences.player(uuid)

/**
 * Broadcast a text to all players that defined in this audience
 * factory
 *
 * @since 0.0.4
 */
@WithApplication
fun AudienceFactory.broadcast(text: Text) {
    all().text(text)
}

/**
 * Broadcast a text obtained from text displayable specified to all players that
 * defined in this audience factory
 *
 * @since 0.0.4
 */
@WithApplication
fun AudienceFactory.broadcast(obj: TextDisplayable) {
    all().text(obj.asText())
}