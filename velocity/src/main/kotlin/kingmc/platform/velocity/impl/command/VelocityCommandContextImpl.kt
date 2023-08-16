package kingmc.platform.velocity.impl.command

import kingmc.platform.command.CommandContext
import kingmc.platform.command.CommandSender
import kingmc.platform.command.parameter.Parameters

/**
 * A simple implementation of [CommandContext]
 *
 * @since 0.0.5
 * @author kingsthere
 */
data class VelocityCommandContextImpl(
    override val invoker: CommandSender,
    override val parameters: Parameters,
    override val source: String
) : CommandContext
