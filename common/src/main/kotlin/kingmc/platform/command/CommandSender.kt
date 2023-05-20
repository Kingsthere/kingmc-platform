package kingmc.platform.command

import kingmc.platform.audience.Audience
import kingmc.platform.permission.Permissible

/**
 * Represent a command sender that capable for send commands
 *
 * @since 0.0.3
 * @author kingsthere
 * @see Audience
 */
interface CommandSender : Audience, Permissible {
    /**
     * To let this command sender send a chat message
     *
     * @param message the chat message to send
     */
    fun chat(message: String)
}

/**
 * To let this command sender sends a command
 *
 * @param command the command to send
 */
fun CommandSender.command(command: String) {
    this.chat("/$command")
}