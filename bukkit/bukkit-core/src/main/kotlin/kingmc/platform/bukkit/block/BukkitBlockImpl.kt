package kingmc.platform.bukkit.block

import kingmc.common.application.Application
import kingmc.platform.Location
import kingmc.platform.Material
import kingmc.platform.MaterialType
import kingmc.platform.block.Block
import kingmc.platform.block.BlockState
import kingmc.platform.bukkit.asKingMC
import kingmc.platform.bukkit.material.asBukkit
import kingmc.platform.bukkit.material.asKingMC

/**
 * Bukkit [Block] implementation
 *
 * @since 0.0.5
 * @author kingsthere
 */
open class BukkitBlockImpl(val _bukkitBlock: _BukkitBlock, override val application: Application) : Block {
    /**
     * The location of this locatable
     *
     * @since 0.0.3
     */
    override val location: Location by lazy {
        _bukkitBlock.location.asKingMC(application)
    }

    /**
     * The material of this holder
     */
    override var material: Material<*>
        get() = Material(_bukkitBlock.type.asKingMC(application) as MaterialType<Unit>, Unit)
        set(value) {
            _bukkitBlock.type = value.type.asBukkit()
        }

    /**
     * Gets a [BlockState] from current block
     */
    override fun getState(): BlockState =
        BukkitBlockState(this, _bukkitBlock.state)
}