package kingmc.platform.bukkit.brigadier

import com.mojang.brigadier.CommandDispatcher
import kingmc.common.context.annotation.Component
import kingmc.platform.command.CommandSender

/**
 * A [CommandDispatcher] enhanced by kingmc
 * allows to get command source stack typed [CommandSender] when proceed commands
 *
 * @author kingsthere
 * @since 0.0.5
 */
@Component("brigadierCommandDispatcher")
abstract class EnhancedCommandDispatcher : CommandDispatcher<CommandSender>()