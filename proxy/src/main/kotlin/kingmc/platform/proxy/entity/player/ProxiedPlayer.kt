package kingmc.platform.proxy.entity.player

import kingmc.platform.entity.player.Player
import kingmc.platform.proxy.ConnectionRequest
import kingmc.platform.proxy.ProxiedServer

/**
 * Represent an online minecraft player on a proxy-like server such as
 *  + BungeeCord
 *  + Velocity
 *
 * @since 0.0.9
 * @author kingsthere
 */
interface ProxiedPlayer : Player {
    /**
     * The `ProxiedServer` that the player is currently connected to
     */
    val currentServer: ProxiedServer

    /**
     * Try to connect this player to the [target]
     *
     * @param target target server to connect to
     * @return connection request created to connect the player to target server
     */
    fun tryConnect(target: ProxiedServer): ConnectionRequest
}