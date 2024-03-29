package kingmc.platform

import kingmc.platform.impl.FacetLocation
import kingmc.platform.impl.FacetLocation3D

/**
 * A shortcut to create a `Location` instance by the given approximate position
 *
 * @since 0.1.3
 */
fun Location(x: Double, y: Double, z: Double): Location {
    return Location(x, y, z, Direction.DEFAULT, null)
}

/**
 * A shortcut to create a `Location` instance by the given approximate position and world
 *
 * @since 0.1.3
 */
fun Location(x: Double, y: Double, z: Double, world: World?): Location {
    return Location(x, y, z, Direction.DEFAULT, world)
}

/**
 * A shortcut to create a `Location` instance by the given approximate position and direction
 *
 * @since 0.1.3
 */
fun Location(x: Double, y: Double, z: Double, direction: Direction): Location {
    return Location(x, y, z, direction, null)
}

/**
 * A shortcut to create a `Location` instance by the given approximate position, direction and world
 *
 * @since 0.1.3
 */
fun Location(x: Double, y: Double, z: Double, dir: Direction, world: World?): Location {
    return FacetLocation(x, y, z, dir, world)
}

/**
 * A shortcut to create a `Location` instance by the given approximate position
 *
 * @since 0.1.3
 */
fun Location(x: Number, y: Number, z: Number): Location {
    return Location(x, y, z, Direction.DEFAULT)
}

/**
 * A shortcut to create a `Location` instance by the given position and direction
 *
 * @since 0.1.3
 */
fun Location(x: Number, y: Number, z: Number, dir: Direction): Location {
    return Location(x.toDouble(), y.toDouble(), z.toDouble(), dir, null)
}

/**
 * A shortcut to create a `Location` instance by the given position and world
 *
 * @since 0.1.3
 */
fun Location(x: Number, y: Number, z: Number, world: World?): Location {
    return Location(x, y, z, Direction.DEFAULT, world)
}

/**
 * A shortcut to create a `Location` instance by the given approximate position, direction and world
 *
 * @since 0.1.3
 */
fun Location(x: Number, y: Number, z: Number, dir: Direction, world: World?): Location {
    return FacetLocation(x.toDouble(), y.toDouble(), z.toDouble(), dir, world)
}

/**
 * A shortcut to create a `Location3D` instance by the given approximate position
 *
 * @since 0.1.3
 */
fun Location3D(x: Double, y: Double, z: Double): Location3D {
    return FacetLocation3D(x, y, z)
}

/**
 * A shortcut to create a `Location3D` instance by the given position
 *
 * @since 0.1.3
 */
fun Location3D(x: Number, y: Number, z: Number): Location3D {
    return FacetLocation3D(x.toDouble(), y.toDouble(), z.toDouble())
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
operator fun Locatable3D.rangeTo(o: Location): Double {
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
fun Locatable3D.rangeSquared(o: Location): Double {
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
operator fun Locatable3D.rangeTo(o: Locatable): Double {
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
fun Locatable3D.rangeSquared(o: Locatable): Double {
    return this.location..o.location
}

/**
 * The x position of it's location
 *
 * @see Locatable
 * @see Location
 */
val Locatable3D.x
    get() = this.location.x

/**
 * The y position of it's location
 *
 * @see Locatable
 * @see Location
 */
val Locatable3D.y
    get() = this.location.y

/**
 * The z position of it's location
 *
 * @see Locatable
 * @see Location
 */
val Locatable3D.z
    get() = this.location.z

/**
 * The direction of it's location
 *
 * @see Locatable
 * @see Location
 */
val Locatable.direction
    get() = this.location.direction

/**
 * The world of it's location
 *
 * @see Locatable
 * @see Location
 */
val Locatable.world
    get() = this.location.world

/**
 * Create a relative location from this locatable
 *
 * @author kingsthere
 * @since 0.0.7
 */
fun Locatable.offset(offsetX: Double, offsetY: Double, offsetZ: Double): RelativeLocation {
    return RelativeLocation(this, offsetX, offsetY, offsetZ)
}

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
val Location3D.blockX: Int
    get() = locToBlock(x)

/**
 * Gets the floored value of the X component, indicating the block that this location is contained with.
 *
 * @since 0.0.1
 */
val Location3D.blockY: Int
    get() = locToBlock(y)

/**
 * Gets the floored value of the X component, indicating the block that this location is contained with.
 *
 * @since 0.0.1
 */
val Location3D.blockZ: Int
    get() = locToBlock(z)
/**
 * Gets the floored value of the X component, indicating the block that this location is contained with.
 *
 * @since 0.0.1
 */
val Locatable3D.blockX: Int
    get() = locToBlock(x)

/**
 * Gets the floored value of the X component, indicating the block that this location is contained with.
 *
 * @since 0.0.1
 */
val Locatable3D.blockY: Int
    get() = locToBlock(y)

/**
 * Gets the floored value of the X component, indicating the block that this location is contained with.
 *
 * @since 0.0.1
 */
val Locatable3D.blockZ: Int
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