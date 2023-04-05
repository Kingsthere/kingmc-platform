package kingmc.platform.bukkit.audience.adventure

import kingmc.common.text.Text
import kingmc.platform.Server
import kingmc.platform.audience.ForwardingAudience
import kingmc.platform.entity.player.Player
import kingmc.platform.entity.player.Players
import java.util.function.Predicate


/**
 * A [ForwardingAudience] for all online players and console
 *
 * @since 0.0.4
 * @author kingsthere
 */
class FilteredAllBukkitPlayer(val _server: Server, private val filter: Predicate<Player>) : Players {
    /**
     * Convert this object into a [Text]
     */
    override fun asText(): Text =
        Text.text("All")

    /**
     * Gets the audiences.
     *
     * @return the audiences
     * @since 4.0.0
     */
    override fun audiences(): Iterable<Player> =
        _server.getOnlinePlayers().filter { filter.test(it) }
}