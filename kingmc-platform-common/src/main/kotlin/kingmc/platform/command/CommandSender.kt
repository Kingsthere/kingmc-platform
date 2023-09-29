package kingmc.platform.command

import kingmc.platform.audience.Audience
import kingmc.platform.permission.Permissible

/**
 * Represent a command sender that capable for send commands
 *
 * @author kingsthere
 * @since 0.0.3
 * @see Audience
 */
interface CommandSender : Audience, Permissible {
    /**
     * To let this command sender send a chat message
     *
     * @param message the chat message to send
     */
    fun chat(message: String) {
        throw UnsupportedOperationException("This command sender does not allowed to send a chat message")
    }

    /**
     * To let this command sender sends a command, **do not** add a extra slash in front
     * of the command
     *
     * For example:
     * `test abc 123`
     *
     * @param command the command to send
     */
    fun command(command: String)
}