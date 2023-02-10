package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication

/**
 * Create a location by the approximate position
 *
 * @since 0.0.1
 */
@WithApplication
fun Location(x: Int, y: Int, z: Int): Location {
    return currentApplication().platform.locations.of(x, y, z)
}

/**
 * Create a location by the approximate position
 * and direction
 *
 * @see Direction
 * @since 0.0.1
 */
@WithApplication
fun Location(x: Int, y: Int, z: Int, dir: Direction): Location {
    return currentApplication().platform.locations.of(x, y, z, dir)
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
fun Location(x: Int, y: Int, z: Int, dir: Direction, world: World): Location {
    return currentApplication().platform.locations.of(x, y, z, dir, world)
}

/**
 * Create a location by the approximate position
 * and world
 *
 * @see World
 * @since 0.0.1
 */
@WithApplication
fun Location(x: Int, y: Int, z: Int, world: World): Location {
    return currentApplication().platform.locations.of(x, y, z, world)
}

/**
 * Create a location by the position
 *
 * @since 0.0.1
 */
@WithApplication
fun Location(x: Double, y: Double, z: Double): Location {
    return currentApplication().platform.locations.of(x, y, z)
}

/**
 * Create a location by the position
 * and direction
 *
 * @see Direction
 * @since 0.0.1
 */
@WithApplication
fun Location(x: Double, y: Double, z: Double, dir: Direction): Location {
    return currentApplication().platform.locations.of(x, y, z, dir)
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
    return currentApplication().platform.locations.of(x, y, z, dir, world)
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
    return currentApplication().platform.locations.of(x, y, z, world)
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
     * Create a location by the approximate position
     *
     * @since 0.0.1
     */
    fun of(x: Int, y: Int, z: Int): Location

    /**
     * Create a location by the approximate position
     * and direction
     *
     * @see Direction
     * @since 0.0.1
     */
    fun of(x: Int, y: Int, z: Int, dir: Direction): Location

    /**
     * Create a location by the approximate position
     * and direction, world
     *
     * @see Direction
     * @see World
     * @since 0.0.1
     */
    fun of(x: Int, y: Int, z: Int, dir: Direction, world: World): Location

    /**
     * Create a location by the approximate position
     * and world
     *
     * @see World
     * @since 0.0.1
     */
    fun of(x: Int, y: Int, z: Int, world: World): Location

    /**
     * Create a location by the position
     *
     * @since 0.0.1
     */
    fun of(x: Double, y: Double, z: Double): Location

    /**
     * Create a location by the position
     * and direction
     *
     * @see Direction
     * @since 0.0.1
     */
    fun of(x: Double, y: Double, z: Double, dir: Direction): Location

    /**
     * Create a location by the approximate position
     * and direction, world
     *
     * @see Direction
     * @see World
     * @since 0.0.1
     */
    fun of(x: Double, y: Double, z: Double, dir: Direction, world: World): Location

    /**
     * Create a location by the approximate position
     * and world
     *
     * @see World
     * @since 0.0.1
     */
    fun of(x: Double, y: Double, z: Double, world: World): Location
}