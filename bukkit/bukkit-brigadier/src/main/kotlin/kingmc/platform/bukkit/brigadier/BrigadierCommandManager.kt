package kingmc.platform.bukkit.brigadier

import kingmc.common.context.annotation.Component
import kingmc.platform.command.CommandManager

/**
 * An interface extended [CommandManager], indicating subclasses is
 * compatible with brigadier api
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component("brigadierCommandManager")
interface BrigadierCommandManager : CommandManager