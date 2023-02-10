package kingmc.platform.command

import kingmc.common.application.WithApplication
import kingmc.platform.command.parameter.Parameters

/**
 * A CommandHandler is the basic interface for accessing command api
 * in kingmc, CommandHandler provides tab-complete
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface CommandHandler {
    /**
     * Invoke this command handler
     *
     * @since 0.0.3
     */
    @WithApplication
    operator fun invoke(invocation: CommandContext, parameters: Parameters)

    /**
     * Invoke this command's tab completion
     *
     * @since 0.0.3
     */
    @WithApplication
    fun complete(invocation: CommandContext, parameters: Parameters): List<String>
}