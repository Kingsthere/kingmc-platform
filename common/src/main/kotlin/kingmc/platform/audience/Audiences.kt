package kingmc.platform.audience

import com.google.common.base.Predicate
import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.text.Text
import kingmc.platform.audienceFactory
import kingmc.platform.entity.player.Player
import kingmc.util.KingMCDsl
import kingmc.util.text.TextDisplayable

/**
 * Gets an [Audience] refer all audiences (include players & console)
 *
 * @since 0.0.3
 * @author kingsthere
 */
@KingMCDsl
@WithApplication
fun all(): Audience =
    currentApplication().audienceFactory.all()

/**
 * Gets an [Audience] refer all players
 *
 * @since 0.0.3
 * @author kingsthere
 */
@KingMCDsl
@WithApplication
fun players(): Audience =
    currentApplication().audienceFactory.players()

/**
 * Gets an [Audience] refer all players
 *
 * @since 0.0.3
 * @author kingsthere
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