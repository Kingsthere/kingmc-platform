package kingmc.platform.bukkit.brigadier

import kingmc.common.context.annotation.Configuration
import kingmc.common.context.condition.ConditionalOnClass
import kingmc.common.environment.maven.MavenDependency

/**
 * Shaded mojang brigadier api environment
 *
 * @since 0.0.5
 * @author kingsthere
 */
@MavenDependency(
    groupId = "com.mojang",
    artifactId = "brigadier",
    version = "1.0.18",
    repository = "https://libraries.minecraft.net"
)
@Configuration
@ConditionalOnClass("com.mojang.brigadier.CommandDispatcher")
object BrigadierEnvironment