package kingmc.platform

import kingmc.platform.util.ChunkPosition

operator fun Chunk.component1(): Int =
    x

operator fun Chunk.component2(): Int =
    z

operator fun Chunk.component3(): World =
    world

/**
 * Gets the [Chunk] at the given [Locatable]'s location
 *
 * @param locatable the locatable to get from
 * @return Chunk at the given coordinates
 */
fun World.getChunkAt(locatable: Locatable3D) =
    getChunkAt(locatable.location)

/**
 * Gets the [Chunk] at the given [Locatable]'s location
 *
 * @param locatable the locatable to get from
 * @return Block got at the given coordinates
 */
fun World.getBlockAt(locatable: Locatable3D) =
    getBlockAt(locatable.location)

/**
 * Gets the [Chunk] at the given [ChunkPosition]
 *
 * @param position `ChunkPosition` of the chunk
 * @return Chunk at the given location
 */
fun World.getChunkAt(position: ChunkPosition) = this.getChunkAt(position.x, position.z)