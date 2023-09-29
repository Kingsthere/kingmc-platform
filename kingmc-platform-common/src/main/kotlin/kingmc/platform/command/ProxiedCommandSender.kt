package kingmc.platform.command

/**
 * Represent a CommandSender that send commands proxied by other CommandSender
 *
 * @author kingsthere
 * @since 0.0.5
 */
interface ProxiedCommandSender : CommandSender {
    /**
     * The CommandSender which triggered this proxied command
     *
     * @return the caller which triggered the command
     */
    val caller: CommandSender

    /**
     * The CommandSender which is being used to call the command
     *
     * @return the caller which the command is being run as
     */
    val callee: CommandSender
}