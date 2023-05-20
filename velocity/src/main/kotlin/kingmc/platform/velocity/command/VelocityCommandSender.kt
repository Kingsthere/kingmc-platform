package kingmc.platform.velocity.command

import kingmc.platform.command.CommandSender

/**
 * Extended `CommandSender` exposed an interface to convert this into a `org.bukkit.command.CommandSender`
 *
 * @since 0.0.7
 * @author kingsthere
 */
interface VelocityCommandSender : CommandSender {
    /**
     * Convert this bukkit command sender to a `org.bukkit.command.CommandSender`
     */
    fun toVelocityCommandSender(): _VelocityCommandSender
}