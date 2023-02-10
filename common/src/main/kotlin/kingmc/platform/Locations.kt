package kingmc.platform

/**
 * Get the distance between this locatable location and another. The value of this
 * method is not cached and uses a costly square-root function, so do not
 * repeatedly call this method to get the location's magnitude. NaN will
 * be returned if the inner result of the sqrt() function overflows, which
 * will be caused if the distance is too long.
 *
 * @param o The other location
 * @return the distance
 * @throws IllegalArgumentException for differing worlds
 * @see Vector
 */
operator fun Locatable.rangeTo(o: Location): Double {
    return this.location..o
}

/**
 * Get the squared distance between this locatable location and another.
 *
 * @param o The other location
 * @return the distance
 * @throws IllegalArgumentException for differing worlds
 * @see Vector
 */
fun Locatable.rangeSquared(o: Location): Double {
    return this.location.rangeSquared(o)
}

/**
 * Get the distance between this locatable location and another. The value of this
 * method is not cached and uses a costly square-root function, so do not
 * repeatedly call this method to get the location's magnitude. NaN will
 * be returned if the inner result of the sqrt() function overflows, which
 * will be caused if the distance is too long.
 *
 * @param o The other location
 * @return the distance
 * @throws IllegalArgumentException for differing worlds
 * @see Vector
 */
operator fun Locatable.rangeTo(o: Locatable): Double {
    return this.location..o.location
}

/**
 * Get the squared distance between this locatable location and another.
 *
 * @param o The other location
 * @return the distance
 * @throws IllegalArgumentException for differing worlds
 * @see Vector
 */
fun Locatable.rangeSquared(o: Locatable): Double {
    return this.location..o.location
}

/**
 * The x position of it's location
 *
 * @see Locatable
 * @see Location
 */
val Locatable.x
    get() = this.location.x

/**
 * The x position of it's location
 *
 * @see Locatable
 * @see Location
 */
val Locatable.y
    get() = this.location.y

/**
 * The x position of it's location
 *
 * @see Locatable
 * @see Location
 */
val Locatable.z
    get() = this.location.z

/**
 * The x position of it's location
 *
 * @see Locatable
 * @see Location
 */
val Locatable.direction
    get() = this.location.direction

/**
 * The x position of it's location
 *
 * @see Locatable
 * @see Location
 */
val Locatable.world
    get() = this.location.world

/**
 * Gets the [Chunk] at the represented location
 *
 * @since 0.0.5
 */
fun Location.getChunk(): Chunk {
    return world?.getChunkAt(this)
        ?: throw IllegalStateException("Could not get chunk because world is null")
}

/**
 * Gets the [Chunk] at the represented location
 *
 * @since 0.0.5
 */
fun Locatable.getChunk(): Chunk {
    return world?.getChunkAt(this.location)
        ?: throw IllegalStateException("Could not get chunk because world is null")
}

/**
 * Gets the floored value of the X component, indicating the block that this location is contained with.
 *
 * @since 0.0.1
 */
val Location.blockX: Int
    get() = locToBlock(x)

/**
 * Gets the floored value of the X component, indicating the block that this location is contained with.
 *
 * @since 0.0.1
 */
val Location.blockY: Int
    get() = locToBlock(y)

/**
 * Gets the floored value of the X component, indicating the block that this location is contained with.
 *
 * @since 0.0.1
 */
val Location.blockZ: Int
    get() = locToBlock(z)
/**
 * Gets the floored value of the X component, indicating the block that this location is contained with.
 *
 * @since 0.0.1
 */
val Locatable.blockX: Int
    get() = locToBlock(x)

/**
 * Gets the floored value of the X component, indicating the block that this location is contained with.
 *
 * @since 0.0.1
 */
val Locatable.blockY: Int
    get() = locToBlock(y)

/**
 * Gets the floored value of the X component, indicating the block that this location is contained with.
 *
 * @since 0.0.1
 */
val Locatable.blockZ: Int
    get() = locToBlock(z)

/**
 * Safely converts a double (location coordinate) to an int (block
 * coordinate)
 *
 * @param loc Precise coordinate
 * @return Block coordinate
 */
fun locToBlock(loc: Double): Int {
    return NumberConversions.floor(loc)
}