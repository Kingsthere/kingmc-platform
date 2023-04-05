package kingmc.platform

/**
 * A location3d implement represent a location relative from another
 * [Locatable3D], the relative location is mutable from
 * providing relative target ([Locatable3D])
 *
 * @since 0.0.5
 * @author kingsthere
 */
open class RelativeLocation3D(
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
): Location3D {
    /**
     * The exactly x position this location is
     * on
     *
     * @see Double
     * @since 0.0.1
     */
    override val x: Double
        get() = target.x + offsetX

    /**
     * The exactly y position this location is
     * on
     *
     * @see Double
     * @since 0.0.1
     */
    override val y: Double
        get() = target.y + offsetY

    /**
     * The exactly z position this location is
     * on
     *
     * @see Double
     * @since 0.0.1
     */
    override val z: Double
        get() = target.z + offsetZ

    /**
     * Adds the location by another.
     *
     * @param location The other location
     * @return the same location
     * @throws IllegalArgumentException for differing worlds
     * @see Vector
     */
    override fun plus(location: Location3D): Location3D {
        return RelativeLocation3D(target, offsetX + location.x, offsetY + location.y, offsetZ + location.z)
    }

    /**
     * Adds the location by a vector.
     *
     * @param vec Vector to use
     * @return the same location
     * @see Vector
     */
    override fun plus(vec: Vector): Location3D {
        return RelativeLocation3D(target, offsetX + vec.x, offsetY + vec.y, offsetZ + vec.z)
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
    override fun plus(x: Double, y: Double, z: Double): Location3D {
        return target.location.plus(offsetX, offsetY, offsetZ).plus(x, y, z)
    }

    /**
     * Subtracts the location by a vector.
     *
     * @param vec The vector to use
     * @return the same location
     * @see Vector
     */
    override fun minus(vec: Vector): Location {
        return target.location.plus(offsetX, offsetY, offsetZ).minus(vec)
    }

    /**
     * Subtracts the location by another location.
     *
     * @param location The vector to use
     * @return the same location
     * @see Location
     */
    override fun minus(location: Location3D): Location {
        return target.location.plus(offsetX, offsetY, offsetZ).minus(location)
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
        return target.location.plus(offsetX, offsetY, offsetZ).plus(x, y, z)
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
        return target.location.plus(offsetX, offsetY, offsetZ).rangeSquared(o)
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
        return target.location.plus(offsetX, offsetY, offsetZ).times(m)
    }

    override fun component1(): Double = x

    override fun component2(): Double = y

    override fun component3(): Double = z
    override fun clone(): Location3D {
        return Location3D(x, y, z)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RelativeLocation3D) return false

        if (target != other.target) return false
        if (offsetX != other.offsetX) return false
        if (offsetY != other.offsetY) return false
        if (offsetZ != other.offsetZ) return false

        return true
    }

    override fun hashCode(): Int {
        var result = target.hashCode()
        result = 31 * result + offsetX.hashCode()
        result = 31 * result + offsetY.hashCode()
        result = 31 * result + offsetZ.hashCode()
        return result
    }

    override fun toString(): String {
        return "RelativeLocation3D(target=$target, offsetX=$offsetX, offsetY=$offsetY, offsetZ=$offsetZ)"
    }

}