package kingmc.platform.command.model

import kingmc.common.application.Application
import kingmc.common.application.WithApplication
import kingmc.platform.command.CommandContext
import kingmc.platform.command.CommandResult
import kingmc.platform.command.parameter.CommandParameter

/**
 * The basic unit of a command tree to simply execute the
 * commands with arguments that is processed
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface Handler {
    /**
     * The name of this command
     */
    var name: String

    /**
     * The description of this command
     */
    var description: String?

    /**
     * The parameters of this command handler required
     */
    val parameters: MutableList<CommandParameter<*>>

    /**
     * The executor of this handler to run this
     * command, left to null to skip when running
     * this command
     *
     * @see invoke
     */
    var executor: CommandExecutor

    /**
     * The aliases of this handler
     */
    val aliases: MutableSet<String>

    /**
     * The application that registers this handler
     */
    val application: Application<*>

    /**
     * Invoke this handler
     */
    @WithApplication
    suspend operator fun invoke(commandContext: CommandContext): CommandResult
}