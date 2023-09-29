package kingmc.platform.velocity.command

import kingmc.platform.command.CommandSender

/**
 * Extended `CommandSender` exposed an interface to convert this into a `org.velocity.command.CommandSender`
 *
 * @author kingsthere
 * @since 0.0.7
 */
interface VelocityCommandSender : CommandSender {
    /**
     * Convert this velocity command sender to a `org.velocity.command.CommandSender`
     */
    fun toVelocityCommandSender(): _VelocityCommandSender
}