package kingmc.platform.bukkit.material

import kingmc.common.application.Application
import kingmc.platform.MaterialType
import kingmc.platform.materialProvider

/**
 * Convert this `MaterialType` to a [org.bukkit.Material]
 *
 * @receiver the [MaterialType] to convert
 */
fun MaterialType<*>.asBukkit() = (this as BukkitMaterialType<*>).toBukkitMaterial()

/**
 * Convert this [org.bukkit.Material] as a `MaterialType`
 *
 * @receiver the [org.bukkit.Material] to convert
 * @since 0.0.8
 * @author kingsthere
 */
// @WithApplication use parameter [application] instead
fun _BukkitMaterial.asKingMC(application: Application): MaterialType<*> {
    return (application.materialProvider as BukkitMaterialProvider).getTypeForBukkit(this)
}