package kingmc.platform.bukkit.adventure.impl.audience

import kingmc.platform.Server
import kingmc.platform.audience.Audience
import kingmc.platform.audience.ForwardingAudience
import kingmc.platform.console
import java.util.function.Predicate


/**
 * A [ForwardingAudience] for all online players and console
 *
 * @author kingsthere
 * @since 0.0.4
 */
class FilteredAllBukkitAudiences(private val _server: Server, private val filter: Predicate<Audience>) : ForwardingAudience {

    /**
     * Gets the audiences.
     *
     * @return the audiences
     * @since 4.0.0
     */
    override fun audiences(): Iterable<Audience> =
        (_server.getOnlinePlayers() + console).filter { filter.test(it) }
}