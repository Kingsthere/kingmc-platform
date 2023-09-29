package kingmc.platform.bukkit.block

import kingmc.platform.block.Block

/**
 * Gets this block as a [org.bukkit.block.Block]
 */
val Block.bukkitBlock: _BukkitBlock
    get() = (this as BukkitBlock).toBukkitBlock()


/**
 * Gets this block's material as a [org.bukkit.block.data.BlockData]
 */
val Block.bukkitBlockData: _BukkitBlockData
    get() = (this.material.data as BukkitMaterialData).toBukkitBlockData()
