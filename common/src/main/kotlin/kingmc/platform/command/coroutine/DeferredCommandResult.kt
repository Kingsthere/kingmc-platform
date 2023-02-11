package kingmc.platform.command.coroutine

import kingmc.platform.command.CommandResult
import kingmc.platform.command.model.Node
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

/**
 * A command result describe a command resulting in coroutine
 *
 *
 * This kind of result exposed a [Deferred] to fetch the result
 *
 * @since 0.0.3
 * @author kingsthere
 */
open class DeferredCommandResult(val coroutineScope: CoroutineScope, val deferred: Deferred<CommandResult>) : CommandResult {
    /**
     * Forward this command's invocation and parameter to the specified
     * command
     *
     * @since 0.0.3
     */
    override val forwardTo: Node
        get() = throw IllegalStateException("This command result is deferred, check \"deferred\" to get the command result correctly")

    override fun asInt(): Int {
        throw IllegalStateException("This command result is deferred, check \"DeferredCommandResult.deferred\" to get the command result correctly")
    }
}