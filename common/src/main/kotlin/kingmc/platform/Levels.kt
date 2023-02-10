package kingmc.platform

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
fun World.getChunkAt(locatable: Locatable) =
    getChunkAt(locatable.location)

/**
 * Gets the [Chunk] at the given [Locatable]'s location
 *
 * @param locatable the locatable to get from
 * @return Block got at the given coordinates
 */
fun World.getBlockAt(locatable: Locatable) =
    getBlockAt(locatable.location)