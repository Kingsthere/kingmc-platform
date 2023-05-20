package kingmc.platform.material.block

import kingmc.platform.block.BlockFace

/**
 * Indicates a block that have a `BlockFace` turned to
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface Directional {
    /**
     * The direction that this block is facing in
     */
    var facing: BlockFace
}