package kingmc.platform.command.model

import kingmc.common.application.Application
import kingmc.platform.command.CommandContext
import kingmc.platform.command.CommandResult
import kingmc.platform.command.parameter.CommandParameter
import kotlinx.coroutines.runBlocking

/**
 * A default implementation of [Handler]
 *
 * @since 0.0.3
 * @author kingsthere
 */
open class BlockingHandler internal constructor(
    override var name: String,
    override var description: String? = null,
    override val parameters: MutableList<CommandParameter<*>> = mutableListOf(),
    override val aliases: MutableSet<String> = mutableSetOf(),
    override var executor: CommandExecutor = CommandExecutor.EMPTY, override val application: Application<*>
) : Handler {

    override fun invoke(commandContext: CommandContext): CommandResult {
        return runBlocking {
            return@runBlocking this@BlockingHandler.executor.invoke(commandContext)
        }
    }
}