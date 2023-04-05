package kingmc.platform

/**
 * A superinterface indicating a location for `x, y, z`
 *
 * @since 0.0.1
 * @author kingsthere
 * @see Location
 */
interface Location3D : Cloneable {
    /**
     * The exactly x position this location is
     * on
     *
     * @see Double
     * @since 0.0.1
     */
    val x: Double

    /**
     * The exactly y position this location is
     * on
     *
     * @see Double
     * @since 0.0.1
     */
    val y: Double

    /**
     * The exactly z position this location is
     * on
     *
     * @see Double
     * @since 0.0.1
     */
    val z: Double

    /**
     * Adds the location by another.
     *
     * @param location The other location
     * @return the same location
     * @throws IllegalArgumentException for differing worlds
     * @see Vector
     */
    operator fun plus(location: Location3D): Location3D

    /**
     * Adds the location by a vector.
     *
     * @param vec Vector to use
     * @return the same location
     * @see Vector
     */
    operator fun plus(vec: Vector): Location3D

    /**
     * Adds the location by another. Not world-aware.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return the same location
     * @see Vector
     */
    fun plus(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0): Location3D

    /**
     * Subtracts the location by a vector.
     *
     * @param vec The vector to use
     * @return the same location
     * @see Vector
     */
    operator fun minus(vec: Vector): Location3D

    /**
     * Subtracts the location by another location.
     *
     * @param location The vector to use
     * @return the same location
     * @see Location
     */
    operator fun minus(location: Location3D): Location3D

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
    fun minus(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0): Location3D

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
    fun length(): Double

    /**
     * Gets the magnitude of the location squared. Not world-aware and
     * orientation independent.
     *
     * @return the magnitude
     * @see Vector
     */
    fun lengthSquared(): Double

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
    operator fun rangeTo(o: Location3D): Double

    /**
     * Get the squared distance between this location and another.
     *
     * @param o The other location
     * @return the distance
     * @throws IllegalArgumentException for differing worlds
     * @see Vector
     */
    fun rangeSquared(o: Location3D): Double

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar. Not world-aware.
     *
     * @param m The factor
     * @return the same location
     * @see Vector
     */
    operator fun times(m: Double): Location3D

    operator fun component1(): Double
    operator fun component2(): Double
    operator fun component3(): Double

    override fun clone(): Location3D
}