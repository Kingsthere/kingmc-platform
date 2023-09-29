package kingmc.platform.bukkit.impl.material.block

import kingmc.platform.block.BlockFace
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.block._BukkitBlockFace
import kingmc.platform.bukkit.block._BukkitChestBlock
import kingmc.platform.bukkit.block._BukkitChestBlockType
import kingmc.platform.material.block.ChestBlock

@BukkitImplementation
class BukkitChestBlockImpl(val bukkitBlock: _BukkitChestBlock) : ChestBlock {
    /**
     * Tne type of this chest
     */
    override var type: ChestBlock.Type
        get() = ChestBlock.Type.valueOf(bukkitBlock.type.name)
        set(value) {
            bukkitBlock.type = _BukkitChestBlockType.valueOf(value.name)
        }

    /**
     * The direction that this block is facing in
     */
    override var facing: BlockFace
        get() = BlockFace.valueOf(bukkitBlock.facing.name)
        set(value) {
            bukkitBlock.facing = _BukkitBlockFace.valueOf(value.name)
        }

    /**
     * `true` if the value of the 'waterlogged' property.
     */
    override var isWaterlogged: Boolean
        get() = bukkitBlock.isWaterlogged
        set(value) {
            bukkitBlock.isWaterlogged = value
        }
}