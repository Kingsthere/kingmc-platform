package kingmc.platform.command

import kingmc.platform.command.model.Node

/**
 * The result of a command to return when an invoker
 * is invoked a command
 *
 * @author kingsthere
 * @since 0.0.3
 */
interface CommandResult {
    /**
     * Forward this command's invocation and parameter to the specified
     * command
     *
     * @since 0.0.3
     */
    val forwardTo: Node?

    /**
     * Convert this command result as a [Int]
     */
    fun asInt(): Int
}