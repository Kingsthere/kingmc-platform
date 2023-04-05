package kingmc.platform.facet

import kingmc.platform.LocationProvider
import kingmc.platform.Platform
import kingmc.platform.VectorProvider

/**
 * A facet of [Platform]
 * 
 * @since 0.0.3
 * @author kingsthere
 */
abstract class AbstractPlatform(
    override val id: Array<String>
) : Platform {
    override val locations: LocationProvider = FacetLocationProvider()
    override val vectors: VectorProvider = FacetVectorProvider()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractPlatform) return false

        if (!id.contentEquals(other.id)) return false

        return true
    }

    override fun hashCode(): Int {
        return id.contentHashCode()
    }
}