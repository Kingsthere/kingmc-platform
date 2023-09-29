package kingmc.platform

/**
 * An interface representation of vector, a vector means the movement of an object, like the
 * speed, the vector store the movements as their speed & dimension, for example:
 *  + Vector{x=1,y=0,z=0}
 *  + Vector{x=-1,y=0,z=0}
 *
 *  @since 0.0.1
 *  @author kingsthere
 */
interface Vector : Cloneable {
    /**
     * The x position movement of this
     * vector is moving
     *
     * @see Double
     * @since 0.0.1
     */
    val x: Double

    /**
     * The y position movement of this
     * vector is moving
     *
     * @see Double
     * @since 0.0.1
     */
    val y: Double

    /**
     * The z position movement of this
     * vector is moving
     *
     * @see Double
     * @since 0.0.1
     */
    val z: Double

    /**
     * Adds a vector to this one
     *
     * @param vec The other vector
     * @return the same vector
     */
    operator fun plus(vec: Vector): Vector

    /**
     * Subtracts a vector from this one.
     *
     * @param vec The other vector
     * @return the same vector
     */
    operator fun minus(vec: Vector): Vector

    /**
     * Multiplies the vector by another.
     *
     * @param vec The other vector
     * @return the same vector
     */
    operator fun times(vec: Vector): Vector

    /**
     * Divides the vector by another.
     *
     * @param vec The other vector
     * @return the same vector
     */
    operator fun div(vec: Vector): Vector

    /**
     * Gets the magnitude of the vector, defined as sqrt(x^2+y^2+z^2). The
     * value of this method is not cached and uses a costly square-root
     * function, so do not repeatedly call this method to get the vector's
     * magnitude. NaN will be returned if the inner result of the sqrt()
     * function overflows, which will be caused if the length is too long.
     *
     * @return the magnitude
     */
    fun length(): Double

    /**
     * Gets the magnitude of the vector squared.
     *
     * @return the magnitude
     */
    fun lengthSquared(): Double

    /**
     * Get the distance between this vector and another. The value of this
     * method is not cached and uses a costly square-root function, so do not
     * repeatedly call this method to get the vector's magnitude. NaN will be
     * returned if the inner result of the sqrt() function overflows, which
     * will be caused if the distance is too long.
     *
     * @param o The other vector
     * @return the distance
     */
    fun distance(o: Vector): Double

    /**
     * Get the squared distance between this vector and another.
     *
     * @param o The other vector
     * @return the distance
     */
    fun distanceSquared(o: Vector): Double

    /**
     * Gets the angle between this vector and another in radians.
     *
     * @param other The other vector
     * @return angle in radians
     */
    fun angle(other: Vector): Float

    /**
     * Sets this vector to the midpoint between this vector and another.
     *
     * @param other The other vector
     * @return this same vector (now a midpoint)
     */
    fun midpoint(other: Vector): Vector

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m The factor
     * @return the same vector
     */
    fun multiply(m: Int): Vector

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m The factor
     * @return the same vector
     */
    fun multiply(m: Double): Vector

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m The factor
     * @return the same vector
     */
    fun multiply(m: Float): Vector

    /**
     * Calculates the dot product of this vector with another. The dot product
     * is defined as x1*x2+y1*y2+z1*z2. The returned value is a scalar.
     *
     * @param other The other vector
     * @return dot product
     */
    fun dot(other: Vector): Double

    /**
     * Calculates the cross product of this vector with another. The cross
     * product is defined as:
     *
     *  * x = y1 * z2 - y2 * z1
     *  * y = z1 * x2 - z2 * x1
     *  * z = x1 * y2 - x2 * y1
     *
     *
     * @param o The other vector
     * @return the same vector
     */
    fun crossProduct(o: Vector): Vector

    /**
     * Converts this vector to a unit vector (a vector with length of 1).
     *
     * @return the same vector
     */
    fun normalize(): Vector

    /**
     * Zero this vector's components.
     *
     * @return the same vector
     */
    fun zero(): Vector

    /*
     Data class functions declaration
     */
    operator fun component1(): Double
    operator fun component2(): Double
    operator fun component3(): Double

    // Companion object for extensions
    companion object
}