package kingmc.platform.bukkit.brigadier

import kingmc.common.context.annotation.ConditionalOnClass
import kingmc.common.context.annotation.Configuration
import kingmc.common.environment.maven.MavenDependency

/**
 * Shaded mojang brigadier api environment
 *
 * @author kingsthere
 * @since 0.0.5
 */
@MavenDependency(
    groupId = "com.mojang",
    artifactId = "brigadier",
    version = "1.0.18",
    repository = "https://libraries.minecraft.net"
)
@Configuration
@ConditionalOnClass(["com.mojang.brigadier.CommandDispatcher"])
object BrigadierEnvironment