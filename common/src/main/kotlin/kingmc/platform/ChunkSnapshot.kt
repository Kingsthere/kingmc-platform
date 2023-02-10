package kingmc.platform

/**
 * An immutable, thread-safe snapshot of chunk of blocks, you can read
 * a chunk quickly using this inference
 *
 * @since 0.0.5
 * @author kingsthere
 */
interface ChunkSnapshot {
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
    fun getBlockType(x: Int, y: Int, z: Int): Material
}