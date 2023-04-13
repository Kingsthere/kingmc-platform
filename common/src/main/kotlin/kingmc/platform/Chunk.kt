package kingmc.platform

import kingmc.common.application.Application
import kingmc.platform.block.Block

/**
 * Chunk is the basic unit to describe the blocks in a minecraft
 * server, Normally it represents a 16x16 blocks zone in minecraft
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface Chunk : Iterable<Block> {
    /**
     * The x-coordinate of this chunk
     */
    val x: Int

    /**
     * The z-coordinate of this chunk
     */
    val z: Int

    /**
     * The world that this chunk is load from
     */
    val world: World

    /**
     * The application of this chunk
     */
    val application: Application

    /**
     * Gets a block from this chunk
     *
     * @param x 0-15
     * @param y world minHeight (inclusive) - world maxHeight (exclusive)
     * @param z 0-15
     * @return the Block
     */
    operator fun get(x: Int, y: Int, z: Int): Block

    /**
     * Capture a [ChunkSnapshot] for accessing this chunk
     *
     * @return the chunk snapshot captured
     */
    fun getChunkSnapshot(): ChunkSnapshot
}