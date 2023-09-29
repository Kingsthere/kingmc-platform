package kingmc.platform.bukkit.adventure.impl.audience

import kingmc.platform.Server
import kingmc.platform.audience.ForwardingAudience
import kingmc.platform.entity.player.Player
import kingmc.platform.entity.player.Players
import kingmc.platform.permission.Permissible


/**
 * A [ForwardingAudience] for all online players
 *
 * @author kingsthere
 * @since 0.0.4
 */
class AllBukkitPlayer(private val _server: Server) : Players, Permissible by Permissible.ALWAYS {

    /**
     * Gets the audiences.
     *
     * @return the audiences
     * @since 4.0.0
     */
    override fun audiences(): Iterable<Player> =
        _server.getOnlinePlayers()
}