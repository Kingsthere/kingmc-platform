package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication

/**
 * Create a location by the position
 *
 * @since 0.0.1
 */
@WithApplication
fun Location(x: Number, y: Number, z: Number): Location {
    return currentApplication().platform.locations.createLocation(x.toDouble(), y.toDouble(), z.toDouble())
}

/**
 * Create a location by the position
 * and direction
 *
 * @see Direction
 * @since 0.0.1
 */
@WithApplication
fun Location(x: Number, y: Number, z: Number, dir: Direction): Location {
    return currentApplication().platform.locations.createLocation(x.toDouble(), y.toDouble(), z.toDouble(), dir)
}

/**
 * Create a location by the position
 * and world
 *
 * @see Direction
 * @since 0.0.1
 */
@WithApplication
fun Location(x: Number, y: Number, z: Number, world: World): Location {
    return currentApplication().platform.locations.createLocation(x.toDouble(), y.toDouble(), z.toDouble(), world)
}

/**
 * Create a location by the position
 * and world
 *
 * @see Direction
 * @since 0.0.1
 */
@WithApplication
fun Location(x: Number, y: Number, z: Number, dir: Direction, world: World): Location {
    return currentApplication().platform.locations.createLocation(x.toDouble(), y.toDouble(), z.toDouble(), dir, world)
}

/**
 * Create a location3d
 *
 * @since 0.0.5
 * @see Location3D
 */
@WithApplication
fun Location3D(x: Number, y: Number, z: Number): Location3D {
    return currentApplication().platform.locations.createLocation3D(x.toDouble(), y.toDouble(), z.toDouble())
}

/**
 * Create a location by the approximate position
 * and direction, world
 *
 * @see Direction
 * @see World
 * @since 0.0.1
 */
@WithApplication
fun Location(x: Double, y: Double, z: Double, dir: Direction, world: World): Location {
    return currentApplication().platform.locations.createLocation(x, y, z, dir, world)
}

/**
 * Create a location by the approximate position
 * and world
 *
 * @see World
 * @since 0.0.1
 */
@WithApplication
fun Location(x: Double, y: Double, z: Double, world: World): Location {
    return currentApplication().platform.locations.createLocation(x, y, z, world)
}

/**
 * A provider to provide the instances of [Location]: Location
 * implemented by the current platform installed
 *
 * @since 0.0.1
 * @author kingsthere
 */
interface LocationProvider : PlatformExposed {
    /**
     * Create a location with `x, y, z`
     *
     * @since 0.0.1
     * @return the location created
     */
    fun createLocation(x: Double, y: Double, z: Double): Location

    /**
     * Create a location with `x, y, z, direction`
     *
     * @see Direction
     * @since 0.0.1
     * @return the location created
     */
    fun createLocation(x: Double, y: Double, z: Double, dir: Direction): Location

    /**
     * Create a location with `x, y, z, direction, world`
     *
     * @see Direction
     * @see World
     * @since 0.0.1
     * @return the location created
     */
    fun createLocation(x: Double, y: Double, z: Double, dir: Direction, world: World): Location

    /**
     * Create a location with `x, y, z, world`
     *
     * @see World
     * @since 0.0.1
     * @return the location created
     */
    fun createLocation(x: Double, y: Double, z: Double, world: World): Location

    /**
     * Create a location3D with `x, y, z`
     *
     * @see Location3D
     * @since 0.0.1
     * @return the location created
     */
    fun createLocation3D(x: Double, y: Double, z: Double): Location3D
}