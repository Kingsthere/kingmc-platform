package kingmc.platform.bukkit.nms.v1_20_1.impl.brigadier

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.tree.LiteralCommandNode
import kingmc.common.context.annotation.Autowired
import kingmc.common.context.annotation.Component
import kingmc.common.context.condition.ConditionalOnBean
import kingmc.platform.bukkit.brigadier.EnhancedCommandDispatcher
import kingmc.platform.command.CommandSender

@Component("brigadierCommandDispatcher_1_19_2")
@ConditionalOnBean(beanName = "brigadierNMS_1_19_2")
class EnhancedCommandDispatcher_1_19_2 : EnhancedCommandDispatcher() {
    @Autowired
    lateinit var brigadierNMS: BrigadierNMS_1_19_2

    override fun register(command: LiteralArgumentBuilder<CommandSender>): LiteralCommandNode<CommandSender> {
        return super.register(command)
    }
}