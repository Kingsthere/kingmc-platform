package kingmc.platform.bukkit.impl.command

import kingmc.common.context.annotation.Component
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.command.CommandContext
import kingmc.platform.command.CommandContextFactory
import kingmc.platform.command.CommandSender
import kingmc.platform.command.parameter.Parameters

/**
 * Default invocation factory implementation of kingmc command api for bukkit
 *
 * @since 0.0.3
 * @author kingsthere
 * @see CommandContextFactory
 */
@BukkitImplementation
@Component
object BukkitCommandContextFactory : CommandContextFactory {
    override fun create(commandSender: CommandSender, parameters: Parameters, source: String): CommandContext {
        return BukkitCommandContextImpl(commandSender, parameters, source)
    }
}