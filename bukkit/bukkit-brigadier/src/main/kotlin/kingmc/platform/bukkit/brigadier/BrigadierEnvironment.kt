package kingmc.platform.bukkit.brigadier

import kingmc.common.context.annotation.Component
import kingmc.common.context.condition.ConditionalOnClass
import kingmc.common.environment.RuntimeDependency

/**
 * Shaded mojang brigadier api environment
 *
 * @since 0.0.5
 * @author kingsthere
 */
@RuntimeDependency(
    value = "com!.mojang:brigadier:1.0.18",
    relocate = ["com!.mojang!.brigadier", "kingmc.platform.bukkit.brigadier"],
    repository = "https://libraries.minecraft.net"
)
@Component("brigadierEnvironment")
@ConditionalOnClass("kingmc.platform.bukkit.brigadier.CommandDispatcher")
object BrigadierEnvironment