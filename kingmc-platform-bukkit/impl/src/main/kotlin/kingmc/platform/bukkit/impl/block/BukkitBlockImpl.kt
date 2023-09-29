package kingmc.platform.bukkit.impl.block

import kingmc.common.application.Application
import kingmc.platform.Location
import kingmc.platform.block.Block
import kingmc.platform.block.BlockState
import kingmc.platform.bukkit.asKingMC
import kingmc.platform.bukkit.block.BukkitBlockState
import kingmc.platform.bukkit.block._BukkitBlock
import kingmc.platform.bukkit.block._BukkitChestBlock
import kingmc.platform.bukkit.impl.material.block.BukkitChestBlockImpl
import kingmc.platform.bukkit.material.asKingMC
import kingmc.platform.bukkit.material.bukkitMaterial
import kingmc.platform.material.Material
import kingmc.platform.material.MaterialType
import kingmc.platform.material.Materials

/**
 * Bukkit side [Block] implementation implemented by calling bukkit api
 *
 * @author kingsthere
 * @since 0.0.5
 */
open class BukkitBlockImpl(val _bukkitBlock: _BukkitBlock, override val application: Application) : Block {
    /**
     * The location of this locatable
     *
     * @since 0.0.3
     */
    override val location: Location = _bukkitBlock.location.asKingMC(application)

    /**
     * The material of this holder
     */
    override var material: Material<*>
        get() = obtainMaterialFor(_bukkitBlock.type.asKingMC(application))
        set(value) {
            _bukkitBlock.type = value.type.bukkitMaterial
        }

    override val materialType: MaterialType<*>
        get() = _bukkitBlock.type.asKingMC(application)

    @Suppress("UNCHECKED_CAST")
    fun obtainMaterialFor(materialType: MaterialType<*>): Material<*> {
        return when (val blockData = _bukkitBlock.blockData) {
            is _BukkitChestBlock -> {
                Material(MaterialType(Materials.CHEST), BukkitChestBlockImpl(blockData))
            }

            else -> {
                Material(materialType, BukkitBlockMaterialDataImpl(_bukkitBlock.blockData))
            }
        }
    }

    /**
     * Gets a [BlockState] from current block
     */
    override fun getState(): BlockState =
        BukkitBlockState(this, _bukkitBlock.state)
}