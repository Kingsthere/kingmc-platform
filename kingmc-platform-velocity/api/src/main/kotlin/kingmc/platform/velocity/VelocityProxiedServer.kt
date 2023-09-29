package kingmc.platform.velocity

import kingmc.common.context.annotation.Component
import kingmc.platform.proxy.ProxiedServer

/**
 * A `Server` capable to convert into a [_VelocityProxiedServer] and capable to convert
 * velocity players to [kingmc.platform.entity.player.Player]
 *
 * @author kingsthere
 * @since 0.0.9
 */
@Component
interface VelocityProxiedServer : ProxiedServer {
    /**
     * Convert this server to a [_VelocityProxiedServer]
     */
    fun asVelocityProxiedServer(): _VelocityProxiedServer
}