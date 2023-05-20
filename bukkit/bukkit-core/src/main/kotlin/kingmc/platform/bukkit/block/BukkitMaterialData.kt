package kingmc.platform.bukkit.block

/**
 * A data for material exposed an interface to convert this material data
 * to a [org.bukkit.block.data.BlockData]
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface BukkitMaterialData {
    /**
     * Convert this `BukkitMaterialData` to a [org.bukkit.block.data.BlockData]
     */
    fun toBukkitBlockData(): _BukkitBlockData
}