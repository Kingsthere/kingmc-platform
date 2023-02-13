package kingmc.platform.common

import kingmc.platform.*
import kotlin.math.acos
import kotlin.math.sqrt

/**
 * The common implementation of [Vector]
 */
data class VectorImpl(
    override val x: Double,
    override val y: Double,
    override val z: Double,
) : Vector {
    /**
     * Adds a vector to this one
     *
     * @param vec The other vector
     * @return the same vector
     */
    override fun plus(vec: Vector): Vector =
        VectorImpl(
            x = this.x + vec.x,
            y = this.y + vec.y,
            z = this.z + vec.z
        )

    /**
     * Subtracts a vector from this one.
     *
     * @param vec The other vector
     * @return the same vector
     */
    override fun minus(vec: Vector): Vector =
        VectorImpl(
            x = this.x - vec.x,
            y = this.y - vec.y,
            z = this.z - vec.z
        )

    /**
     * Multiplies the vector by another.
     *
     * @param vec The other vector
     * @return the same vector
     */
    override fun times(vec: Vector): Vector =
        VectorImpl(
            x = this.x * vec.x,
            y = this.y * vec.y,
            z = this.z * vec.z
        )

    /**
     * Divides the vector by another.
     *
     * @param vec The other vector
     * @return the same vector
     */
    override fun div(vec: Vector): Vector =
        VectorImpl(
            x = this.x / vec.x,
            y = this.y / vec.y,
            z = this.z / vec.z
        )

    /**
     * Gets the magnitude of the vector, defined as sqrt(x^2+y^2+z^2). The
     * value of this method is not cached and uses a costly square-root
     * function, so do not repeatedly call this method to get the vector's
     * magnitude. NaN will be returned if the inner result of the sqrt()
     * function overflows, which will be caused if the length is too long.
     *
     * @return the magnitude
     */
    override fun length(): Double =
        sqrt(
            NumberConversions.square(x) + NumberConversions.square(y) + NumberConversions.square(z)
        )

    /**
     * Gets the magnitude of the vector squared.
     *
     * @return the magnitude
     */
    override fun lengthSquared(): Double =
        NumberConversions.square(x) + NumberConversions.square(y) + NumberConversions.square(z)

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
    override fun distance(o: Vector): Double =
        sqrt(
            NumberConversions.square(x - o.x) + NumberConversions.square(y - o.y) + NumberConversions.square(
                z - o.z
            )
        )

    /**
     * Get the squared distance between this vector and another.
     *
     * @param o The other vector
     * @return the distance
     */
    override fun distanceSquared(o: Vector): Double =
        NumberConversions.square(x - o.x) + NumberConversions.square(y - o.y) + NumberConversions.square(z - o.z)

    /**
     * Gets the angle between this vector and another in radians.
     *
     * @param other The other vector
     * @return angle in radians
     */
    override fun angle(other: Vector): Float {
        val dot: Double = constrainToRange(dot(other) / (length() * other.length()), -1.0, 1.0)
        return acos(dot).toFloat()
    }

    @Suppress("SameParameterValue", "SameParameterValue")
    private fun constrainToRange(value: Double, min: Double, max: Double): Double {
        if (min <= max) {
            return value.coerceAtLeast(min).coerceAtMost(max)
        }
        throw IllegalArgumentException("min ($min) must be less than or equal to max ($max)")

    }

    /**
     * Sets this vector to the midpoint between this vector and another.
     *
     * @param other The other vector
     * @return this same vector (now a midpoint)
     */
    override fun midpoint(other: Vector): Vector =
        VectorImpl(
            x = this.x + other.x / 2,
            y = this.y + other.y / 2,
            z = this.z + other.z / 2
        )

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m The factor
     * @return the same vector
     */
    override fun multiply(m: Int): Vector =
        VectorImpl(
            x = this.x * m,
            y = this.y * m,
            z = this.z * m
        )

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m The factor
     * @return the same vector
     */
    override fun multiply(m: Double): Vector =
        VectorImpl(
            x = this.x * m,
            y = this.y * m,
            z = this.z * m
        )

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m The factor
     * @return the same vector
     */
    override fun multiply(m: Float): Vector =
        VectorImpl(
            x = this.x * m,
            y = this.y * m,
            z = this.z * m
        )

    /**
     * Calculates the dot product of this vector with another. The dot product
     * is defined as x1*x2+y1*y2+z1*z2. The returned value is a scalar.
     *
     * @param other The other vector
     * @return dot product
     */
    override fun dot(other: Vector): Double =
        x * other.x + y * other.y + z * other.z

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
    override fun crossProduct(o: Vector): Vector {

        val newX = y * o.z - o.y * z
        val newY = z * o.x - o.z * x
        val newZ = x * o.y - o.x * y

        return VectorImpl(newX, newY, newZ)
    }

    /**
     * Converts this vector to a unit vector (a vector with length of 1).
     *
     * @return the same vector
     */
    override fun normalize(): Vector {
        val length = length()

        return VectorImpl(
            x = this.x / length,
            y = this.y / length,
            z = this.z / length
        )
    }

    /**
     * Zero this vector's components.
     *
     * @return the same vector
     */
    override fun zero(): Vector =
        VectorImpl(0.0, 0.0, 0.0)
}

/**
 * Common implementation of [VectorProvider]
 *
 * @since 0.0.3
 * @author kingsthere
 */
@PlatformImplementation
open class CommonVectorProvider(override val platform: Platform) : VectorProvider {
    /**
     * Create a vector by the approximate position
     *
     * @since 0.0.1
     */
    override fun of(x: Int, y: Int, z: Int): Vector =
        VectorImpl(x.toDouble(), y.toDouble(), z.toDouble())

    /**
     * Create a vector by the exact position
     *
     * @since 0.0.1
     */
    override fun of(x: Double, y: Double, z: Double): Vector =
        VectorImpl(x, y, z)

}