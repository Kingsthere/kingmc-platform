package kingmc.platform.block

import kingmc.platform.Locatable
import kingmc.platform.MaterialHolder

/**
 * Represents a captured state of a block, which will not updated automatically.
 *
 * @since 0.0.5
 * @author kingsthere
 */
interface BlockState : Locatable, MaterialHolder {
    /**
     * Gets the [Block] this state is representing
     */
    val block: Block
}