package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.platform.material.Material
import kingmc.platform.material.MaterialType

/**
 * Interface represents a snapshot of a chunk
 *
 * `ChunkSnapshot` is an immutable, thread-safe snapshot of chunk of blocks
 *
 * @author kingsthere
 * @since 0.0.5
 */
interface ChunkSnapshot : Iterable<Material<*>> {
    /**
     * The x-coordinate of this chunk
     */
    val x: Int

    /**
     * The z-coordinate of this chunk
     */
    val z: Int

    /**
     * The world name that this chunk is load from
     */
    val worldName: String

    /**
     * Get block type for block at corresponding coordinate in the chunk
     *
     * @param x 0-15
     * @param y world minHeight (inclusive) - world maxHeight (exclusive)
     * @param z 0-15
     * @return block material type
     */
    @WithApplication
    fun getBlockType(x: Int, y: Int, z: Int): MaterialType<*>

    /**
     * Get material for block at corresponding coordinate in the chunk
     *
     * @param x 0-15
     * @param y world minHeight (inclusive) - world maxHeight (exclusive)
     * @param z 0-15
     * @return block material type
     */
    @WithApplication
    fun getMaterial(x: Int, y: Int, z: Int): Material<*>

    /**
     * Get all block's type in this chunk snapshot
     *
     * @return block material types
     */
    fun getAllMaterialTypes(): List<MaterialType<*>>
}