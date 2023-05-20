package kingmc.platform.bukkit.material

import kingmc.common.application.Application
import kingmc.platform.material.MaterialType
import kingmc.platform.materialProvider

/**
 * Convert this `MaterialType` to a [org.bukkit.Material]
 *
 * @receiver the [MaterialType] to convert
 */
val MaterialType<*>.bukkitMaterial
    get() = (this as BukkitMaterialType<*>).toBukkitMaterial()

val _cachedMaterial = HashMap<_BukkitMaterial, MaterialType<*>>()

/**
 * Convert this [org.bukkit.Material] as a `MaterialType`
 *
 * @receiver the [org.bukkit.Material] to convert
 * @since 0.0.8
 * @author kingsthere
 */
// @WithApplication use parameter [application] instead
fun _BukkitMaterial.asKingMC(application: Application): MaterialType<*> {
    return _cachedMaterial.computeIfAbsent(this) { (application.materialProvider as BukkitMaterialProvider).getTypeForBukkit(this) }
}