package kingmc.platform.command.coroutine

import kingmc.platform.command.CommandContext
import kingmc.platform.command.CommandResult
import kingmc.platform.command.model.CommandExecutor
import kingmc.platform.command.model.Handler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

/**
 * The subclass implemented from [Handler] serves to handle the command
 * with custom coroutine contexts
 *
 * @since 0.0.3
 * @author kingsthere
 */
abstract class SuspendedHandler : Handler {
    /**
     * The coroutine context to run this handler
     *
     * @since 0.0.3
     */
    abstract var coroutineContext: CoroutineContext?

    override suspend fun invoke(commandContext: CommandContext): CommandResult {
        val context = coroutineContext
        requireNotNull(context) { "You must specify a coroutine context to call suspended handlers" }
        val coroutineScope = CoroutineScope(context)

        return DeferredCommandResult(coroutineScope, coroutineScope.async {
            return@async this@SuspendedHandler.executor.invoke(commandContext)
        })
    }

    abstract override var executor: CommandExecutor
}