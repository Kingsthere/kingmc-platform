package kingmc.platform.command.model

import kingmc.common.application.WithApplication
import kingmc.platform.command.CommandContext
import kingmc.platform.command.CommandResult
import kingmc.platform.command.failed

fun interface CommandExecutor : @WithApplication (CommandContext) -> CommandResult {
    companion object {
        /**
         * An empty [CommandExecutor], always returns [failed] as command result
         */
        val EMPTY = CommandExecutor { failed() }
    }
}