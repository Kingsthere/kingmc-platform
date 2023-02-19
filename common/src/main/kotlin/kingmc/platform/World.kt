package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.platform.audience.AudienceFactory
import kingmc.platform.audience.ForwardingAudience
import kingmc.platform.audience.particle.ParticleRecipient
import kingmc.platform.block.Block
import java.util.*

/**
 * A representation of **world**, **world**
 * is the forth dimension of minecraft, an
 * original minecraft server only have 3
 * dimension:
 *  + world
 *  + world_nether
 *  + world_the_end
 *
 *
 * You can create a custom dimension by
 * using other plugins
 *
 * @since 0.0.1
 * @author kingsthere
 */
interface World : ForwardingAudience, AudienceFactory, ParticleRecipient {
    /**
     * The uuid of this world
     */
    override val uuid: UUID

    /**
     * The name of this world, name
     * is the only identifier to identity
     * worlds
     *
     * @since 0.0.1
     */
    override val name: String

    /**
     * Gets the [Chunk] at the given coordinates
     *
     * @param x X-coordinate of the chunk
     * @param z Z-coordinate of the chunk
     * @return Chunk at the given coordinates
     */
    @WithApplication
    fun getChunkAt(x: Int, z: Int): Chunk

    /**
     * Gets the [Chunk] at the given [Location3D]
     *
     * @param location Location of the chunk
     * @return Chunk at the given location
     */
    @WithApplication
    fun getChunkAt(location: Location3D): Chunk

    /**
     * Gets the [Block] at the given [x], [y], [z]
     *
     * @param x X-coordinate of the block
     * @param z Z-coordinate of the block
     * @param y Y-coordinate of the chunk
     * @return Chunk at the given location
     */
    @WithApplication
    fun getBlockAt(x: Int, y: Int, z: Int): Block

    /**
     * Gets the [Block] at the given [Location3D]
     *
     * @param location Location of the block
     * @return Block at the given location
     */
    @WithApplication
    fun getBlockAt(location: Location3D): Block

    /**
     * Gets the minimum height of this world.
     *
     *
     * If the min height is 0, there are only blocks from y=0.
     *
     * @return Minimum height of the world
     */
    val minHeight: Int

    /**
     * Gets the maximum height of this world.
     *
     *
     * If the max height is 100, there are only blocks from y=0 to y=99.
     *
     * @return Maximum height of the world
     */
    val maxHeight: Int

    companion object
}