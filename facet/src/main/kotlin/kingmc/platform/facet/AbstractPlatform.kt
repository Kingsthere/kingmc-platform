package kingmc.platform.facet

import kingmc.platform.Platform

/**
 * A facet of [Platform]
 * 
 * @since 0.0.3
 * @author kingsthere
 */
abstract class AbstractPlatform(
    override val id: Array<String>
) : Platform {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractPlatform) return false

        return id.contentEquals(other.id)
    }

    override fun hashCode(): Int {
        return id.contentHashCode()
    }
}