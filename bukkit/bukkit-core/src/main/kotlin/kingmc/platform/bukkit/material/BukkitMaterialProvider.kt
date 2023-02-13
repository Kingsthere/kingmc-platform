package kingmc.platform.bukkit.material

import kingmc.common.context.annotation.Component
import kingmc.platform.Material
import kingmc.platform.MaterialProvider
import kingmc.platform.PlatformImplementation

/**
 * A [MaterialProvider] implementation for bukkit
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component
@PlatformImplementation
object BukkitMaterialProvider : MaterialProvider {
    /**
     * Get a Material by the name
     *
     * @since 0.0.1
     * @param name the name of the Material
     */
    override fun getByName(name: String): Material {
        return BukkitMaterial(OriginalBukkitMaterial.getMaterial(name)!!)
    }

    /**
     * Get a `Material` from [OriginalBukkitMaterial]
     *
     * @since 0.0.5
     * @param originalBukkitMaterial the bukkit material to get from
     * @author kingsthere
     */
    fun getFromBukkit(originalBukkitMaterial: OriginalBukkitMaterial): Material {
        return BukkitMaterial(originalBukkitMaterial)
    }
}