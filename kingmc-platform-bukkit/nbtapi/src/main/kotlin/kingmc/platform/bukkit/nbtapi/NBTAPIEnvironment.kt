package kingmc.platform.bukkit.nbtapi

import kingmc.common.environment.ExperimentalEnvironmentApi
import kingmc.common.environment.maven.MavenDependency
import kingmc.common.environment.maven.relocate.Relocate

/**
 * Shaded nbt api environment
 *
 * @author kingsthere
 * @since 0.0.5
 */
@OptIn(ExperimentalEnvironmentApi::class)
@MavenDependency(
    groupId = "de!.tr7zw",
    artifactId = "item-nbt-api",
    version = "2.11.2",
    repository = "https://repo.codemc.org/repository/maven-public/"
)
@Relocate(
    pattern = "de!.tr7zw!.changeme!.nbtapi",
    relocatedPattern = "kingmc.platform.bukkit.nbtapi"
)
object NBTAPIEnvironment