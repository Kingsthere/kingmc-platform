package kingmc.platform.command.model

import kingmc.common.application.Application
import kingmc.platform.command.CommandContext
import kingmc.platform.command.CommandResult
import kingmc.platform.command.parameter.CommandParameter

/**
 * A default implementation of [Handler]
 *
 * @author kingsthere
 * @since 0.0.3
 */
open class BlockingHandler(
    override var name: String,
    override var description: String? = null,
    override val parameters: MutableList<CommandParameter<*>> = mutableListOf(),
    override val aliases: MutableSet<String> = mutableSetOf(),
    override var executor: CommandContext.() -> CommandResult = COMMAND_EXECUTOR_EMPTY, override val application: Application
) : Handler {

    override fun invoke(commandContext: CommandContext): CommandResult {
        return this@BlockingHandler.executor.invoke(commandContext)
    }

    override fun toString(): String {
        return "<$name>"
    }
}