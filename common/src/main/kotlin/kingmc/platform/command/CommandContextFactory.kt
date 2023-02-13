package kingmc.platform.command

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.context.annotation.Component
import kingmc.platform.PlatformExposed
import kingmc.platform.audience.CommandSender
import kingmc.platform.command.parameter.Parameters
import kingmc.platform.invocations

/**
 * Cast a command sender to [CommandContext]
 *
 * @since 0.0.1
 * @see CommandSender
 * @see CommandContext
 */
@WithApplication
fun CommandContext(commandSender: CommandSender, parameters: Parameters, source: String): CommandContext {
    return currentApplication().invocations.create(
        commandSender = commandSender,
        parameters = parameters,
        source = source
    )
}

/**
 * Represent a factory to provide the invocations
 * to provide/cast the command senders to [CommandContext]
 *
 * @since 0.0.1
 * @author kingsthere
 * @see CommandContext
 */
@Component
interface CommandContextFactory : PlatformExposed {
    /**
     * Create a command sender from [CommandSender]
     *
     * @since 0.0.1
     * @param commandSender the command sender that create this command context
     * @param parameters deserialized parameters
     * @param source the source string
     * @see CommandSender
     * @see CommandContext
     */
    fun create(commandSender: CommandSender, parameters: Parameters, source: String): CommandContext
}