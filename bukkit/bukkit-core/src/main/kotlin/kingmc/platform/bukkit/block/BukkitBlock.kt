package kingmc.platform.bukkit.block

import kingmc.platform.Location
import kingmc.platform.Material
import kingmc.platform.block.Block
import kingmc.platform.block.BlockState
import kingmc.platform.bukkit.bukkitPlatform
import kingmc.platform.bukkit.fromBukkit
import kingmc.platform.bukkit.material.BukkitMaterial
import kingmc.platform.bukkit.material.BukkitMaterialProvider
import kingmc.platform.materials

/**
 * An [Block] implementation in bukkit
 *
 * @since 0.0.5
 * @author kingsthere
 */
class BukkitBlock(val _bukkitBlock: OriginalBukkitBlock) : Block {
    /**
     * The location of this locatable
     *
     * @since 0.0.3
     */
    override val location: Location by lazy {
        _bukkitBlock.location.fromBukkit()
    }

    /**
     * The material of this holder
     */
    override var material: Material
        get() = (bukkitPlatform.materials as BukkitMaterialProvider).getFromBukkit(_bukkitBlock.type)
        set(value) {_bukkitBlock.type = (value as BukkitMaterial).originalBukkitMaterial}

    /**
     * Gets a [BlockState] from current block
     */
    override fun getState(): BlockState =
        BukkitBlockState(this, _bukkitBlock.state)
}