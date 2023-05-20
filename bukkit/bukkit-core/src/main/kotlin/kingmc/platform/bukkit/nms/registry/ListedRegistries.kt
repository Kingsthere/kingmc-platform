package kingmc.platform.bukkit.nms.registry

import kingmc.platform.World
import kingmc.platform.entity.EntityType
import kingmc.platform.material.MaterialType
import kingmc.platform.material.block.BlockMaterialType

/**
 * An enum listed known registries
 */
sealed class ListedRegistries<T> {
    object BLOCK : ListedRegistries<BlockMaterialType<*>>()
    object ITEM : ListedRegistries<MaterialType<*>>()
    object ENTITY : ListedRegistries<EntityType>()
    object WORLD : ListedRegistries<World>()
    companion object {
        fun values(): Array<ListedRegistries<*>> {
            return arrayOf(BLOCK, ITEM)
        }

        fun valueOf(value: String): ListedRegistries<*> {
            return when (value) {
                "BLOCK" -> BLOCK
                "ITEM" -> ITEM
                else -> throw IllegalArgumentException("No object kingmc.platform.bukkit.nms.RegistryNMS.Registries.$value")
            }
        }
    }
}