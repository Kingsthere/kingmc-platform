package kingmc.platform

import kotlin.math.cos
import kotlin.math.sin

/**
 * A Direction, describe using **pitch** to **yaw**, direction
 * is a part of component in [Location]
 *
 * @since 0.0.1
 * @author kingsthere
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
        val DEFAULT: Direction = Direction(0F, 0F)
    }
}