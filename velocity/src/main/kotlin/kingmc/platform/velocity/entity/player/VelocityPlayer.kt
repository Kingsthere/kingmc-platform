package kingmc.platform.velocity.entity.player

import kingmc.platform.entity.player.Player
import kingmc.platform.velocity._VelocityPlayer
import kingmc.platform.velocity.command.VelocityCommandSender

/**
 * Extended `Player` exposed an interface to convert this into a `com.velocitypowered.api.proxy.Player`
 *
 * @since 0.0.9
 * @author kingsthere
 */
interface VelocityPlayer : Player, VelocityCommandSender {
    /**
     * Convert this velocity player to a [com.velocitypowered.api.proxy.Player]
     */
    fun toVelocityPlayer(): _VelocityPlayer
}