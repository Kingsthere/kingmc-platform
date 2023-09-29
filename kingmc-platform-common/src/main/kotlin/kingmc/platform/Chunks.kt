package kingmc.platform

import kingmc.platform.material.MaterialType


/**
 * Get block type for block at corresponding coordinate in the chunk
 *
 * @param location the location of the block
 * @return block material type
 */
fun ChunkSnapshot.getBlockType(location: Location): MaterialType<*> =
    this.getBlockType(location.blockX, location.blockY, location.blockZ)

/**
 * Get block type for block at corresponding coordinate in the chunk
 *
 * @param locatable the location of the block
 * @return block material type
 */
fun ChunkSnapshot.getBlockType(locatable: Locatable): MaterialType<*> =
    this.getBlockType(locatable.blockX, locatable.blockY, locatable.blockZ)