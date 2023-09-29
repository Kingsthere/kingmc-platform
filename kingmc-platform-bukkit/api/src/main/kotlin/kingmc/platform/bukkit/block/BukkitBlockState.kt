package kingmc.platform.bukkit.block

import kingmc.platform.Location
import kingmc.platform.block.Block
import kingmc.platform.block.BlockState
import kingmc.platform.bukkit.asKingMC
import kingmc.platform.material.Material

class BukkitBlockState(override val block: Block, private val _bukkitBlockState: _BukkitBlockState) : BlockState {
    override fun update(force: Boolean, applyPhysics: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override val isPlaced: Boolean
        get() = TODO("Not yet implemented")

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