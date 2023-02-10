package kingmc.platform.bukkit.brigadier

import com.mojang.brigadier.CommandDispatcher
import kingmc.common.context.annotation.Component
import kingmc.platform.audience.CommandSender

/**
 * A [CommandDispatcher] enhanced by kingmc
 * allows to get command source stack typed [CommandSender] when proceed commands
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component("brigadierCommandDispatcher")
abstract class EnhancedCommandDispatcher : CommandDispatcher<CommandSender>()