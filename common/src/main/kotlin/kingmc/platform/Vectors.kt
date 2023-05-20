package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.platform.impl.FacetVector


/**
 * Create a location by the exact position
 *
 * @since 0.0.1
 */
@WithApplication
fun Vector(x: Double, y: Double, z: Double): Vector {
    return FacetVector(x, y, z)
}
/**
 * Create a location by the exact position
 *
 * @since 0.0.1
 */
@WithApplication
fun Vector(x: Number, y: Number, z: Number): Vector {
    return FacetVector(x.toDouble(), y.toDouble(), z.toDouble())
}