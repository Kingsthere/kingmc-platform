package kingmc.platform.bukkit.block

import kingmc.platform.Location
import kingmc.platform.Material
import kingmc.platform.block.Block
import kingmc.platform.block.BlockState
import kingmc.platform.bukkit.asKingMC

class BukkitBlockState(override val block: Block, private val _bukkitBlockState: _BukkitBlockState) : BlockState {
    /**
     * The location of this locatable
     *
     * @since 0.0.3
     */
    override val location: Location
        get() = _bukkitBlockState.location.asKingMC(block.application)

    /**
     * The material of this holder
     */
    override val material: Material<*>
        get() = TODO()
}