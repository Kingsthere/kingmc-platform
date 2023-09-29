package kingmc.platform

import kotlin.math.cos
import kotlin.math.sin

/**
 * Data class describe a direction of an object
 *
 * @author kingsthere
 * @since 0.0.1
 */
data class Direction(
    /**
     * The pitch of this direction
     *
     * @since 0.0.1
     */
    val pitch: Float,

    /**
     * The yaw of this direction
     *
     * @since 0.0.1
     */
    val yaw: Float
) {
    /**
     * Convert this Direction to a Vector
     */
    fun toVector(): Vector {
        val rotX: Double = this.yaw.toDouble()
        val rotY: Double = this.pitch.toDouble()

        val y = -sin(Math.toRadians(rotY))
        val xz = cos(Math.toRadians(rotY))
        val x = -xz * sin(Math.toRadians(rotX))
        val z = xz * cos(Math.toRadians(rotX))

        return Vector(x, y, z)
    }

    companion object {
        /**
         * A `Direction` instance with pitch 0F yaw 0F
         */
        val DEFAULT: Direction = Direction(0F, 0F)
    }
}