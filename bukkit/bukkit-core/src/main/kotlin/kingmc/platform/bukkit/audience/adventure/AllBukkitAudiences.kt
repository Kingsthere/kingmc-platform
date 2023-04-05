package kingmc.platform.bukkit.audience.adventure

import kingmc.common.text.Text
import kingmc.platform.Server
import kingmc.platform.audience.Audience
import kingmc.platform.audience.ForwardingAudience


/**
 * A [ForwardingAudience] for all online players and console
 *
 * @since 0.0.4
 * @author kingsthere
 */
class AllBukkitAudiences(private val _server: Server) : ForwardingAudience {
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
    override fun audiences(): Iterable<Audience> =
        _server.getOnlinePlayers() + _server.console
}