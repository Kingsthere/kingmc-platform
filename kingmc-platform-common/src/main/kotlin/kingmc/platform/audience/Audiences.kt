package kingmc.platform.audience

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.text.Text
import kingmc.platform.audienceFactory
import kingmc.platform.entity.player.Player
import kingmc.util.KingMCDsl
import kingmc.util.text.TextDisplayable
import java.util.function.Predicate

/**
 * Gets an [Audience] refer all audiences (include players & console)
 *
 * @author kingsthere
 * @since 0.0.3
 */
@KingMCDsl
@WithApplication
fun all(): Audience =
    currentApplication().audienceFactory.all()

/**
 * Gets an [Audience] refer all players
 *
 * @author kingsthere
 * @since 0.0.3
 */
@KingMCDsl
@WithApplication
fun players(): Audience =
    currentApplication().audienceFactory.players()

/**
 * Gets an [Audience] refer all players
 *
 * @author kingsthere
 * @since 0.0.3
 */
@KingMCDsl
@WithApplication
fun players(predicate: Predicate<Player>): Audience =
    currentApplication().audienceFactory.players(predicate)

/**
 * Broadcast a text to all players that defined in this audience
 * factory
 *
 * @since 0.0.4
 */
fun AudienceFactory.broadcast(text: Text) {
    all().sendText(text)
}

/**
 * Broadcast a text obtained from text displayable specified to all players that
 * defined in this audience factory
 *
 * @since 0.0.4
 */
fun AudienceFactory.broadcast(obj: TextDisplayable) {
    all().sendText(obj.asText())
}

/**
 * Broadcast a text to all players
 *
 * @since 0.0.8
 */
@WithApplication
fun broadcast(text: Text) {
    all().sendText(text)
}

/**
 * Broadcast a text obtained from text displayable specified to all players
 *
 * @since 0.0.8
 */
@WithApplication
fun broadcast(obj: TextDisplayable) {
    all().sendText(obj.asText())
}