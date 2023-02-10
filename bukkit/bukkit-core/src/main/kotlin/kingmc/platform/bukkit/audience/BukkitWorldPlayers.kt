package kingmc.platform.bukkit.audience

import kingmc.platform.World
import kingmc.platform.audience.Player
import kingmc.platform.audience.Players
import kingmc.platform.audience.text.Text

class BukkitWorldPlayers(val world: World) : Players {
    /**
     * Convert this object into a [Text]
     */
    override fun asText(): Text =
        world.displayName

    /**
     * Gets the audiences to forward to
     */
    override fun audiences(): Iterable<Player> =
        world.filterIsInstance<Player>()
}