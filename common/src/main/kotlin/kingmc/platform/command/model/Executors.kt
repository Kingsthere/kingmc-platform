package kingmc.platform.command.model

import kingmc.common.application.WithApplication
import kingmc.platform.command.CommandContext
import kingmc.platform.command.CommandResult
import kingmc.platform.command.success

fun interface CommandExecutor : @WithApplication (CommandContext) -> CommandResult {
    companion object {
        /**
         * An empty [CommandExecutor], always returns [success] as command result
         */
        val EMPTY = CommandExecutor { success() }
    }
}