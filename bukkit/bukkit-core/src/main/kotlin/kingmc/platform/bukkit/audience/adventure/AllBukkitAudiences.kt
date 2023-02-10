package kingmc.platform.bukkit.audience.adventure

import kingmc.platform.audience.Audience
import kingmc.platform.audience.ForwardingAudience
import kingmc.platform.audience.text.Text
import kingmc.platform.bukkit.Bukkit


/**
 * A [ForwardingAudience] for all online players and console
 *
 * @since 0.0.4
 * @author kingsthere
 */
object AllBukkitAudiences : ForwardingAudience {
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
        Bukkit.getOnlinePlayers().map { BukkitAudiences.player(it) } + BukkitAudiences.console()
}