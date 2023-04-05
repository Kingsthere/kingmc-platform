package kingmc.platform.command

import kingmc.platform.command.parameter.Parameters

/**
 * A data class for invocation to invoke
 * the command executors, invoke a command executors
 * need:
 *  + Invocation
 *  + Parameters
 *
 * @since 0.0.1
 * @author kingsthere
 */
interface CommandContext {
    /**
     * The invoker is invoking the command, in
     * bukkit it represents `CommandSender`
     *
     * @since 0.0.1
     */
    val invoker: CommandSender

    /**
     * The deserialize parameters
     *
     * @since 0.0.5
     * @see Parameters
     */
    val parameters: Parameters

    /**
     * The source string deserialize context from
     *
     * @since 0.0.5
     * @see Parameters
     */
    val source: String
}