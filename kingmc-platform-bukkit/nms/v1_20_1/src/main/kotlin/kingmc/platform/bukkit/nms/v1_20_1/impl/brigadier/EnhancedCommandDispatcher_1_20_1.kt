package kingmc.platform.bukkit.nms.v1_20_1.impl.brigadier

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.tree.LiteralCommandNode
import kingmc.common.context.annotation.Autowired
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.ConditionalOnBean
import kingmc.platform.bukkit.brigadier.EnhancedCommandDispatcher
import kingmc.platform.command.CommandSender

@Component("brigadierCommandDispatcher_1_20_1")
@ConditionalOnBean([BrigadierNMS_1_20_1::class])
class EnhancedCommandDispatcher_1_20_1 : EnhancedCommandDispatcher() {
    @Autowired
    lateinit var brigadierNMS: BrigadierNMS_1_20_1

    override fun register(command: LiteralArgumentBuilder<CommandSender>): LiteralCommandNode<CommandSender> {
        return super.register(command)
    }
}