package kingmc.platform.command.model

import kingmc.platform.command.CommandContext
import kingmc.platform.command.CommandResult
import kingmc.platform.command.parameter.CommandParameter
import kingmc.platform.command.success

typealias CommandExecutor = CommandContext.() -> CommandResult

/**
 * An empty command executor, always returns [success] as command result
 */
val COMMAND_EXECUTOR_EMPTY: CommandContext.() -> CommandResult = { success() }

/**
 * Get the value of specified [parameter]
 *
 * @receiver command context to get parameter from
 * @param parameter the command parameter to get the value of
 * @return command parameter's value got
 */
fun <TValue : Any> CommandContext.getParameter(parameter: CommandParameter<TValue>): TValue? {
    return parameters[parameter]
}