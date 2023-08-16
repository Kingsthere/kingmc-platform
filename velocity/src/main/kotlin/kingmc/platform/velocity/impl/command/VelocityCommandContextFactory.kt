package kingmc.platform.velocity.impl.command

import kingmc.common.context.annotation.Component
import kingmc.platform.command.CommandContext
import kingmc.platform.command.CommandContextFactory
import kingmc.platform.command.CommandSender
import kingmc.platform.command.parameter.Parameters
import kingmc.platform.velocity.VelocityImplementation

/**
 * Default invocation factory implementation of kingmc command api for bukkit
 *
 * @since 0.0.3
 * @author kingsthere
 * @see CommandContextFactory
 */
@VelocityImplementation
@Component
object VelocityCommandContextFactory : CommandContextFactory {
    override fun create(commandSender: CommandSender, parameters: Parameters, source: String): CommandContext {
        return VelocityCommandContextImpl(commandSender, parameters, source)
    }
}