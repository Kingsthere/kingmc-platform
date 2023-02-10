package kingmc.platform.bukkit.command

import kingmc.common.context.annotation.Component
import kingmc.platform.Platform
import kingmc.platform.PlatformImplementation
import kingmc.platform.audience.CommandSender
import kingmc.platform.bukkit.bukkitPlatform
import kingmc.platform.command.CommandContext
import kingmc.platform.command.CommandContextFactory
import kingmc.platform.command.parameter.Parameters

/**
 * Default invocation factory implementation of kingmc command api for bukkit
 *
 * @since 0.0.3
 * @author kingsthere
 * @see CommandContextFactory
 */
@PlatformImplementation
@Component("bukkitCommandContextFactory")
object BukkitCommandContextFactory : CommandContextFactory {
    /**
     * Gets the platform of this
     */
    override val platform: Platform
        get() = bukkitPlatform

    override fun create(commandSender: CommandSender, parameters: Parameters, source: String): CommandContext {
        return CommandContextImpl(commandSender, parameters, source)
    }
}