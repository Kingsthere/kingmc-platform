package kingmc.platform

import kingmc.common.application.Application
import kingmc.common.application.Isolated
import kingmc.platform.audience.ForwardingAudience
import kingmc.platform.audience.particle.ParticleRecipient
import kingmc.platform.block.Block
import kingmc.platform.entity.EntityProvider
import kingmc.util.key.Key
import kingmc.util.key.Keyed
import kotlinx.coroutines.Deferred
import java.util.*

/**
 * A representation of **world**, **world** is the forth dimension of minecraft, an
 * original minecraft server only have 3 dimension:
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
@Isolated
interface World : EntityProvider, ForwardingAudience, ParticleRecipient, Keyed {
    /**
     * The uuid of this world
     */
    val uuid: UUID

    /**
     * The name of this world
     */
    val name: String

    /**
     * The key identifier of this world
     *
     * World identifiers were introduced in Minecraft 1.16.
     * On older game instances, worlds will be assigned the Key `minecraft:<world name>`
     */
    override val key: Key

    /**
     * The application of this world
     */
    val application: Application

    /**
     * Gets the [Chunk] at the given coordinates
     *
     * @param x X-coordinate of the chunk
     * @param z Z-coordinate of the chunk
     * @return Chunk at the given coordinates
     */
    fun getChunkAt(x: Int, z: Int): Chunk

    /**
     * Gets the [Chunk] at the given [Location3D]
     *
     * @param location Location of the chunk
     * @return Chunk at the given location
     */
    fun getChunkAt(location: Location3D): Chunk

    /**
     * Gets the [Chunk] at the given coordinates, loading it asynchronously if needed.
     *
     * @param x X-coordinate of the chunk
     * @param z Z-coordinate of the chunk
     * @return Chunk at the given coordinates
     */
    fun getChunkAtAsync(x: Int, z: Int): Deferred<Chunk>

    /**
     * Gets the [Chunk] at the given [Location3D]
     *
     * @param location Location of the chunk
     * @return Chunk at the given location
     */
    fun getChunkAtAsync(location: Location3D): Deferred<Chunk>

    /**
     * Gets the [Block] at the given [x], [y], [z]
     *
     * @param x X-coordinate of the block
     * @param z Z-coordinate of the block
     * @param y Y-coordinate of the chunk
     * @return Chunk at the given location
     */
    fun getBlockAt(x: Int, y: Int, z: Int): Block

    /**
     * Gets the [Block] at the given [Location3D]
     *
     * @param location Location of the block
     * @return Block at the given location
     */
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

    /**
     * The relative in-game time of this world
     */
    var time: Long

    /**
     * The full in-game time of this world
     */
    var fullTime: Long

    companion object
}