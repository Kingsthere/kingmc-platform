package kingmc.platform

/**
 * A location implement represent a location relative from another
 * [Locatable], the relative location is mutable from
 * providing relative target ([Locatable])
 *
 * @since 0.0.5
 * @author kingsthere
 */
open class RelativeLocation(
    /**
     * The target locatable this location relative to
     */
    val target: Locatable,

    /**
     * The offset x position from [target]
     */
    val offsetX: Double = 0.0,

    /**
     * The offset y position from [target]
     */
    val offsetY: Double = 0.0,

    /**
     * The offset z position from [target]
     */
    val offsetZ: Double = 0.0,
): Location {
    /**
     * The exactly x position this location is
     * on
     *
     * @see Double
     * @since 0.0.1
     */
    final override val x: Double
        get() = target.x + offsetX

    /**
     * The exactly y position this location is
     * on
     *
     * @see Double
     * @since 0.0.1
     */
    final override val y: Double
        get() = target.y + offsetY

    /**
     * The exactly z position this location is
     * on
     *
     * @see Double
     * @since 0.0.1
     */
    final override val z: Double
        get() = target.z + offsetZ

    /**
     * The direction this location is on
     *
     * @since 0.0.1
     * @see Direction
     */
    final override val direction: Direction
        get() = target.direction

    /**
     * The world this location is on
     *
     * @since 0.0.1
     * @see getWorld
     */
    final override val world: World?
        get() = target.world

    /**
     * Adds the location by another.
     *
     * @param location The other location
     * @return the same location
     * @throws IllegalArgumentException for differing worlds
     * @see Vector
     */
    override fun plus(location: Location3D): Location {
        return RelativeLocation(target, offsetX + location.x, offsetY + location.y, offsetZ + location.z)
    }

    /**
     * Adds the location by a vector.
     *
     * @param vec Vector to use
     * @return the same location
     * @see Vector
     */
    override fun plus(vec: Vector): Location {
        return RelativeLocation(target, offsetX + vec.x, offsetY + vec.y, offsetZ + vec.z)
    }

    /**
     * Adds the location by another. Not world-aware.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return the same location
     * @see Vector
     */
    override fun plus(x: Double, y: Double, z: Double): Location {
        return RelativeLocation(target, offsetX + x, offsetY + y, offsetZ + z)
    }

    /**
     * Subtracts the location by a vector.
     *
     * @param vec The vector to use
     * @return the same location
     * @see Vector
     */
    override fun minus(vec: Vector): Location {
        return RelativeLocation(target, offsetX - vec.x, offsetY - vec.y, offsetZ - vec.z)
    }

    /**
     * Subtracts the location by another location.
     *
     * @param location The vector to use
     * @return the same location
     * @see Location
     */
    override fun minus(location: Location3D): Location {
        return RelativeLocation(target, offsetX - location.x, offsetY - location.y, offsetZ - location.z)
    }

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
    override fun minus(x: Double, y: Double, z: Double): Location {
        return RelativeLocation(target, offsetX - x, offsetY - y, offsetZ - z)
    }

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar. Not world-aware.
     *
     * @param m The factor
     * @return the same location
     * @see Vector
     */
    override fun times(m: Double): Location {
        return RelativeLocation(target, offsetX * m, offsetY * m, offsetZ * m)
    }

    /**
     * Convert current location to a [Vector] by current position
     *
     * @since 0.0.1
     * @author kingsthere
     */
    override fun toVector(): Vector {
        return target.location.plus(offsetX, offsetY, offsetZ).toVector()
    }

    override fun component4(): Direction? {
        return direction
    }

    override fun component5(): World? {
        return world
    }

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
    override fun length(): Double {
        return target.location.plus(offsetX, offsetY, offsetZ).length()
    }

    /**
     * Gets the magnitude of the location squared. Not world-aware and
     * orientation independent.
     *
     * @return the magnitude
     * @see Vector
     */
    override fun lengthSquared(): Double {
        return target.location.plus(offsetX, offsetY, offsetZ).lengthSquared()
    }

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
    override fun rangeTo(o: Location3D): Double {
        return target.location.plus(offsetX, offsetY, offsetZ).rangeTo(o)
    }

    /**
     * Get the squared distance between this location and another.
     *
     * @param o The other location
     * @return the distance
     * @throws IllegalArgumentException for differing worlds
     * @see Vector
     */
    override fun rangeSquared(o: Location3D): Double {
        return target.location.plus(offsetX, offsetY, offsetZ).rangeTo(o)
    }

    override fun component1(): Double {
        return x
    }

    override fun component2(): Double {
        return y
    }

    override fun component3(): Double {
        return z
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RelativeLocation) return false
        if (!super.equals(other)) return false

        if (direction != other.direction) return false
        if (world != other.world) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + direction.hashCode()
        result = 31 * result + (world?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "RelativeLocation(direction=$direction, world=$world) ${super.toString()}"
    }

    override fun clone(): Location {
        return world?.let { Location(x, y, z, direction, it) } ?: Location(x, y, z, direction)
    }
}