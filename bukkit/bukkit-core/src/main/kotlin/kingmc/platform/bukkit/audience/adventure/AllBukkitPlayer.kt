package kingmc.platform.bukkit.audience.adventure

import kingmc.common.text.Text
import kingmc.platform.Server
import kingmc.platform.audience.ForwardingAudience
import kingmc.platform.entity.player.Player
import kingmc.platform.entity.player.Players


/**
 * A [ForwardingAudience] for all online players
 *
 * @since 0.0.4
 * @author kingsthere
 */
class AllBukkitPlayer(private val _server: Server) : Players {
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
        _server.getOnlinePlayers()
}