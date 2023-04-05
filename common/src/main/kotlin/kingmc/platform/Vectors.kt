package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.common.application.application
import kingmc.common.application.currentApplication

/**
 * Create a location by the exact position
 *
 * @since 0.0.1
 */
@WithApplication
fun Vector(x: Int, y: Int, z: Int): Vector {
    return currentApplication().application.platform.vectors.create(x, y, z)
}

/**
 * Create a location by the exact position
 *
 * @since 0.0.1
 */
@WithApplication
fun Vector(x: Double, y: Double, z: Double): Vector {
    return currentApplication().application.platform.vectors.create(x, y, z)
}

/**
 * The location provider of current server's: Location
 * platform implement
 *
 * @since 0.0.1
 */
@Deprecated(
    message = "Use Platform.vectors instead for using platform api in current application's platform",
    replaceWith = ReplaceWith("platform.vectors", "kingmc.platform.Platform")
)
lateinit var vectorProvider: VectorProvider

/**
 * A provider to provide the instances of [Vector]
 * implemented by the current platform installed
 *
 * @since 0.0.1
 * @author kingsthere
 */
interface VectorProvider {
    /**
     * Create a vector by the approximate position
     *
     * @since 0.0.1
     */
    fun create(x: Int, y: Int, z: Int): Vector

    /**
     * Create a vector by the exact position
     *
     * @since 0.0.1
     */
    fun create(x: Double, y: Double, z: Double): Vector
}