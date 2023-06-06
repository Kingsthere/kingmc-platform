package kingmc.platform.velocity.entity.player

import kingmc.common.application.Application
import kingmc.platform.proxy.entity.player.ProxiedPlayer
import kingmc.platform.server
import kingmc.platform.velocity.VelocityProxyServer
import kingmc.platform.velocity._VelocityPlayer
import kingmc.platform.velocity.command.VelocityCommandSender

/**
 * Extended `Player` exposed an interface to convert this into a `com.velocitypowered.api.proxy.Player`
 *
 * @since 0.0.9
 * @author kingsthere
 */
interface VelocityPlayer : ProxiedPlayer, VelocityCommandSender {
    /**
     * Convert this velocity player to a [com.velocitypowered.api.proxy.Player]
     */
    fun toVelocityPlayer(): _VelocityPlayer
}

/**
 * Convert this [com.velocitypowered.api.proxy.Player] to kingmc `Player`
 *
 * @receiver velocity player to convert to
 * @return `Player` instance converted
 */
fun _VelocityPlayer.asKingMC(application: Application) = (application.server as VelocityProxyServer).getPlayerForVelocity(this)