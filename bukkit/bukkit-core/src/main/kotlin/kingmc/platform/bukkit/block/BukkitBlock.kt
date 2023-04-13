package kingmc.platform.bukkit.block

import kingmc.platform.block.Block

/**
 * A `Block` capable to convert to a [org.bukkit.block.Block]
 *
 * @since 0.0.5
 * @author kingsthere
 */
interface BukkitBlock : Block {
    /**
     * Convert this block to a [org.bukkit.block.Block]
     */
    fun toBukkitBlock(): _BukkitBlock
}