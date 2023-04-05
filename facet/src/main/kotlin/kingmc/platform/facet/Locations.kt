package kingmc.platform.facet

import kingmc.platform.*
import kotlin.math.sqrt

/**
 * The common implementation of [Location]
 *
  * @since 0.0.3
 * @author kingsthere
 */
data class FacetLocation(
    override val x: Double,
    override val y: Double,
    override val z: Double,
    override val direction: Direction,
    override val world: World?,
) : Location {
    /**
     * Adds the location by another.
     *
     * @param location The other location
     * @return the same location
     * @throws IllegalArgumentException for differing worlds
     * @see Vector
     */
    override fun plus(location: Location3D): Location =
        FacetLocation(
            x = this.x + location.x,
            y = this.y + location.y,
            z = this.z + location.z,
            direction = this.direction,
            world = this.world,
        )

    /**
     * Adds the location by a vector.
     *
     * @param vec Vector to use
     * @return the same location
     * @see Vector
     */
    override fun plus(vec: Vector): Location =
        FacetLocation(
            x = this.x + vec.x,
            y = this.y + vec.y,
            z = this.z + vec.z,
            direction = this.direction,
            world = this.world,
        )

    /**
     * Adds the location by another. Not world-aware.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return the same location
     * @see Vector
     */
    override fun plus(x: Double, y: Double, z: Double): Location  =
        FacetLocation(
            x = this.x + x,
            y = this.y + y,
            z = this.z + z,
            direction = this.direction,
            world = this.world,
        )

    /**
     * Subtracts the location by a vector.
     *
     * @param vec The vector to use
     * @return the same location
     * @see Vector
     */
    override fun minus(vec: Vector): Location  =
        FacetLocation(
            x = this.x - vec.x,
            y = this.y - vec.y,
            z = this.z - vec.z,
            direction = this.direction,
            world = this.world,
        )

    /**
     * Subtracts the location by another location.
     *
     * @param location The vector to use
     * @return the same location
     * @see Location
     */
    override fun minus(location: Location3D): Location  =
        FacetLocation(
            x = this.x - location.x,
            y = this.y - location.y,
            z = this.z - location.z,
            direction = this.direction,
            world = this.world,
        )

    /**
     * Subtracts the location by another. Not world-aware and
     * orientation independent.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return the same location
     * @see Vector
     */
    override fun minus(x: Double, y: Double, z: Double): Location =
        FacetLocation(
            x = this.x - x,
            y = this.y - y,
            z = this.z - z,
            direction = this.direction,
            world = this.world,
        )

    /**
     * Gets the magnitude of the location, defined as sqrt(x^2+y^2+z^2). The
     * value of this method is not cached and uses a costly square-root
     * function, so do not repeatedly call this method to get the location's
     * magnitude. NaN will be returned if the inner result of the sqrt()
     * function overflows, which will be caused if the length is too long. Not
     * world-aware and orientation independent.
     *
     * @return the magnitude
     * @see Vector
     */
    override fun length(): Double =
        sqrt(NumberConversions.square(x) + NumberConversions.square(y) + NumberConversions.square(z))

    /**
     * Gets the magnitude of the location squared. Not world-aware and
     * orientation independent.
     *
     * @return the magnitude
     * @see Vector
     */
    override fun lengthSquared(): Double =
        NumberConversions.square(x) + NumberConversions.square(y) + NumberConversions.square(z)

    /**
     * Get the distance between this location and another. The value of this
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
    override fun rangeTo(o: Location3D): Double =
        sqrt(rangeSquared(o))

    /**
     * Get the squared distance between this location and another.
     *
     * @param o The other location
     * @return the distance
     * @throws IllegalArgumentException for differing worlds
     * @see Vector
     */
    override fun rangeSquared(o: Location3D): Double {
        if (o is Location) {
            require(o.world === world) { "Cannot measure distance between " + world?.name + " and " + o.world?.name }
        }

        return NumberConversions.square(x - o.x) + NumberConversions.square(y - o.y) + NumberConversions.square(z - o.z)
    }

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar. Not world-aware.
     *
     * @param m The factor
     * @return the same location
     * @see Vector
     */
    override fun times(m: Double): Location =
        FacetLocation(
            x = this.x * m,
            y = this.y * m,
            z = this.z * m,
            direction = this.direction,
            world = this.world,
        )

    /**
     * Convert current location to a [Vector] by current position
     *
     * @since 0.0.1
     * @author kingsthere
     */
    override fun toVector(): Vector {
        return direction.toVector()
    }

    override fun clone(): Location {
        return FacetLocation(x, y, z, direction, world)
    }
}

/**
 * A common implementation of [Location3D]
 */
data class FacetLocation3D(
    override val x: Double,
    override val y: Double,
    override val z: Double,
) : Location3D {
    /**
     * Adds the location by another.
     *
     * @param location The other location
     * @return the same location
     * @throws IllegalArgumentException for differing worlds
     * @see Vector
     */
    override fun plus(location: Location3D): Location3D =
        FacetLocation3D(
            x = x + location.x,
            y = y + location.y,
            z = z + location.z
        )

    /**
     * Adds the location by a vector.
     *
     * @param vec Vector to use
     * @return the same location
     * @see Vector
     */
    override fun plus(vec: Vector): Location3D =
        FacetLocation3D(
            x = x + vec.x,
            y = y + vec.y,
            z = z + vec.z
        )

    /**
     * Adds the location by another. Not world-aware.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return the same location
     * @see Vector
     */
    override fun plus(x: Double, y: Double, z: Double): Location3D =
        FacetLocation3D(
            x = this.x + x,
            y = this.y + y,
            z = this.z + z,
        )

    /**
     * Subtracts the location by a vector.
     *
     * @param vec The vector to use
     * @return the same location
     * @see Vector
     */
    override fun minus(vec: Vector): Location3D =
        FacetLocation3D(
            x = x - vec.x,
            y = y - vec.y,
            z = z - vec.z
        )

    /**
     * Subtracts the location by another location.
     *
     * @param location The vector to use
     * @return the same location
     * @see Location
     */
    override fun minus(location: Location3D): Location3D =
        FacetLocation3D(
            x = x - location.x,
            y = y - location.y,
            z = z - location.z
        )

    /**
     * Subtracts the location by another. Not world-aware and
     * orientation independent.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return the same location
     * @see Vector
     */
    override fun minus(x: Double, y: Double, z: Double): Location3D =
        FacetLocation3D(
            x = this.x + x,
            y = this.y + y,
            z = this.z + z
        )

    /**
     * Gets the magnitude of the location, defined as sqrt(x^2+y^2+z^2). The
     * value of this method is not cached and uses a costly square-root
     * function, so do not repeatedly call this method to get the location's
     * magnitude. NaN will be returned if the inner result of the sqrt()
     * function overflows, which will be caused if the length is too long. Not
     * world-aware and orientation independent.
     *
     * @return the magnitude
     * @see Vector
     */
    override fun length(): Double =
        sqrt(NumberConversions.square(x) + NumberConversions.square(y) + NumberConversions.square(z))

    /**
     * Gets the magnitude of the location squared. Not world-aware and
     * orientation independent.
     *
     * @return the magnitude
     * @see Vector
     */
    override fun lengthSquared(): Double =
        NumberConversions.square(x) + NumberConversions.square(y) + NumberConversions.square(z)


    /**
     * Get the distance between this location and another. The value of this
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
    override fun rangeTo(o: Location3D): Double =
        sqrt(rangeSquared(o))

    /**
     * Get the squared distance between this location and another.
     *
     * @param o The other location
     * @return the distance
     * @throws IllegalArgumentException for differing worlds
     * @see Vector
     */
    override fun rangeSquared(o: Location3D): Double {
        return NumberConversions.square(x - o.x) + NumberConversions.square(y - o.y) + NumberConversions.square(z - o.z)
    }

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar. Not world-aware.
     *
     * @param m The factor
     * @return the same location
     * @see Vector
     */
    override fun times(m: Double) =
        FacetLocation3D(
            x = this.x * m,
            y = this.y * m,
            z = this.z * m,
        )

    override fun clone(): Location3D {
        return FacetLocation3D(x, y, z)
    }
}

/**
 * Common implementation of [LocationProvider]
 *
 * @since 0.0.3
 * @author kingsthere
 */
@FacetImplementation
open class FacetLocationProvider : LocationProvider {

    /**
     * Create a location by the position
     *
     * @since 0.0.1
     */
    override fun createLocation(x: Double, y: Double, z: Double): Location =
        createLocation(x, y, z, Direction.DEFAULT)

    /**
     * Create a location by the position
     * and direction
     *
     * @see Direction
     * @since 0.0.1
     */
    override fun createLocation(x: Double, y: Double, z: Double, dir: Direction): Location =
        FacetLocation(x, y, z, dir, null)

    /**
     * Create a location by the approximate position
     * and direction, world
     *
     * @see Direction
     * @see World
     * @since 0.0.1
     */
    override fun createLocation(x: Double, y: Double, z: Double, dir: Direction, world: World): Location =
        FacetLocation(x, y, z, dir, world)

    /**
     * Create a location by the approximate position
     * and world
     *
     * @see World
     * @since 0.0.1
     */
    override fun createLocation(x: Double, y: Double, z: Double, world: World): Location =
        createLocation(x, y, z, Direction.DEFAULT, world)

    /**
     * Create a location3D with `x, y, z`
     *
     * @see Location3D
     * @since 0.0.1
     * @return the location created
     */
    override fun createLocation3D(x: Double, y: Double, z: Double): Location3D =
        FacetLocation3D(x, y, z)

}