package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.platform.impl.FacetVector


/**
 * Create a vector with the given exact position
 *
 * @since 0.0.1
 */
fun Vector(x: Double, y: Double, z: Double): Vector {
    return FacetVector(x, y, z)
}
/**
 * Create a vector with the given position
 *
 * @since 0.0.1
 */
fun Vector(x: Number, y: Number, z: Number): Vector {
    return FacetVector(x.toDouble(), y.toDouble(), z.toDouble())
}