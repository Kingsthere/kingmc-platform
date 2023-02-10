package kingmc.platform.bukkit.audience

import kingmc.platform.World
import kingmc.platform.audience.Audience
import kingmc.platform.audience.ForwardingAudience
import kingmc.platform.audience.text.Text
import java.util.function.Predicate

class FilteredBukkitWorldAudiences(val world: World, private val filter: Predicate<Audience>) : ForwardingAudience {
    /**
     * Convert this object into a [Text]
     */
    override fun asText(): Text =
        world.displayName

    /**
     * Gets the audiences to forward to
     */
    override fun audiences(): Iterable<Audience> =
        world.audiences().filter { filter.test(it) }
}