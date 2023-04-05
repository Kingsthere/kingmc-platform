package kingmc.platform.util

import kingmc.platform.MaterialType
import kingmc.platform.material.BlockMaterialType
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Check if the type of this material is a block that could be placed
 *
 * @receiver the material to check
 * @since 0.0.6
 * @author kingsthere
 * @return `true` if this material represents a block
 */
@OptIn(ExperimentalContracts::class)
fun MaterialType<*>.isBlock(): Boolean {
    contract {
        returns(true) implies (this@isBlock is BlockMaterialType<*>)
    }
    return this is BlockMaterialType<*>
}