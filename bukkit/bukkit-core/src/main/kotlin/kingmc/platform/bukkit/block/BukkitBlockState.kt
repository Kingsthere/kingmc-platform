package kingmc.platform.bukkit.block

import kingmc.platform.Location
import kingmc.platform.Material
import kingmc.platform.block.Block
import kingmc.platform.block.BlockState
import kingmc.platform.bukkit.bukkitPlatform
import kingmc.platform.bukkit.fromBukkit
import kingmc.platform.bukkit.material.BukkitMaterialProvider
import kingmc.platform.materials

class BukkitBlockState(override val block: Block, private val _bukkitBlockState: OriginalBukkitBlockState) : BlockState {
    /**
     * The location of this locatable
     *
     * @since 0.0.3
     */
    override val location: Location
        get() = _bukkitBlockState.location.fromBukkit()

    /**
     * The material of this holder
     */
    override val material: Material
        get() = (bukkitPlatform.materials as BukkitMaterialProvider).getFromBukkit(_bukkitBlockState.type)
}