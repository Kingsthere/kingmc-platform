package kingmc.platform.bukkit.impl.block

import kingmc.platform.bukkit.block.BukkitMaterialData
import kingmc.platform.bukkit.block._BukkitBlockData

open class BukkitBlockMaterialDataImpl(val _bukkitBlockData: _BukkitBlockData) : BukkitMaterialData {
    /**
     * Convert this `BukkitMaterialData` to a [org.bukkit.block.data.BlockData]
     */
    override fun toBukkitBlockData(): _BukkitBlockData = _bukkitBlockData
}