package kingmc.platform.bukkit.audience.adventure

import kingmc.platform.audience.ForwardingAudience
import kingmc.platform.audience.Player
import kingmc.platform.audience.Players
import kingmc.platform.audience.text.Text
import kingmc.platform.bukkit.Bukkit


/**
 * A [ForwardingAudience] for all online players
 *
 * @since 0.0.4
 * @author kingsthere
 */
object AllBukkitPlayer : Players {
    /**
     * Convert this object into a [Text]
     */
    override fun asText(): Text =
        Text.text("AllPlayers")

    /**
     * Gets the audiences.
     *
     * @return the audiences
     * @since 4.0.0
     */
    override fun audiences(): Iterable<Player> =
        Bukkit.getOnlinePlayers().map { AdventureBukkitAudienceFactory.player(it) }
}