package kingmc.platform.velocity

import kingmc.common.application.Application
import kingmc.common.application.currentApplication
import kingmc.common.context.annotation.Component
import kingmc.platform.entity.player.Player
import kingmc.platform.proxy.ProxiedServer
import kingmc.platform.proxy.ProxyServer
import kingmc.platform.server

/**
 * A `Server` capable to convert into a [_VelocityProxyServer] and capable to convert
 * velocity players to [kingmc.platform.entity.player.Player]
 *
 * @since 0.0.9
 * @author kingsthere
 */
@Component
interface VelocityProxyServer : ProxyServer {
    /**
     * Convert this server to a [_BukkitServer]
     */
    fun asVelocityProxiedServer(): _VelocityProxiedServer

    /**
     * Gets a `ProxiedServer` instance to specified [_VelocityProxiedServer]
     *
     * @param velocityProxiedServer velocity server
     * @return `ProxiedServer` to specified [_VelocityProxiedServer]
     */
    fun getProxiedServerForVelocity(velocityProxiedServer: _VelocityProxiedServer): ProxiedServer

    /**
     * Gets a [Player] for velocity player [velocityPlayer]
     *
     * @param velocityPlayer the bukkit player
     * @return player
     */
    fun getPlayerForVelocity(velocityPlayer: _VelocityPlayer): Player
}

/**
 * A shortcut to get a [Player] for velocity player [velocityPlayer]
 *
 * @param velocityPlayer the bukkit player
 * @return player
 */
fun getPlayerForVelocity(velocityPlayer: _VelocityPlayer): Player {
    return (currentApplication().server as VelocityProxyServer).getPlayerForVelocity(velocityPlayer)
}

/**
 * A shortcut to get a [Player] for the receiver velocity player
 */
fun _VelocityPlayer.asKingMC(application: Application): Player {
    return (application.server as VelocityProxyServer).getPlayerForVelocity(this)
}