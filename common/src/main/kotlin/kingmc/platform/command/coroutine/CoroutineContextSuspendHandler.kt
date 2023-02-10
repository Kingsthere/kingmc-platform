package kingmc.platform.command.coroutine

import kingmc.common.application.Application
import kingmc.platform.command.model.CommandExecutor
import kingmc.platform.command.model.Handler
import kingmc.platform.command.parameter.CommandParameter
import kotlin.coroutines.CoroutineContext

/**
 * The subclass implemented from [Handler] serves to handle the command
 * in specifies coroutines, it will create a coroutine per this command runs
 *
 * @since 0.0.3
 * @author kingsthere
 */
open class CoroutineContextSuspendHandler(
    override var coroutineContext: CoroutineContext? = null,
    override var name: String = "undefine",
    override var description: String? = null,
    override val parameters: MutableList<CommandParameter<*>> = mutableListOf(),
    override val aliases: MutableSet<String> = mutableSetOf(),
    override var executor: CommandExecutor = CommandExecutor.EMPTY, override val application: Application<*>
) : SuspendedHandler()