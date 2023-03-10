package kingmc.platform.bukkit.nbtapi

import kingmc.common.context.annotation.Component
import kingmc.common.context.condition.ConditionalOnClass
import kingmc.common.environment.RuntimeDependency

/**
 * Shaded nbt api environment
 *
 * @since 0.0.5
 * @author kingsthere
 */
@RuntimeDependency(
    value = "de!.tr7zw:item-nbt-api:2.11.1",
    repository = "https://repo.codemc.org/repository/maven-public/",
    relocate = ["de!.tr7zw!.changeme!.nbtapi", "kingmc.platform.nbt"]
)
@Component("nbtApiEnvironment")
@ConditionalOnClass("kingmc.platform.nbt.NBT")
object NBTAPIEnvironment