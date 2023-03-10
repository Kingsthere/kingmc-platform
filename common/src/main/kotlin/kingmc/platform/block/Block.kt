package kingmc.platform.block

import kingmc.platform.Locatable
import kingmc.platform.MutableMaterialHolder

/**
 * Represents a block. This is a mutable object, and only one Block
 * may exist for any given location in a world. The state of the
 * block may change concurrently to your own handling of it; use
 * [Block.getState] to get a snapshot state of a block which will
 * not be modified.
 *
 * @since 0.0.4
 * @author kingsthere
 */
interface Block : Locatable, MutableMaterialHolder {
    /**
     * Gets a [BlockState] from current block
     */
    fun getState(): BlockState
}