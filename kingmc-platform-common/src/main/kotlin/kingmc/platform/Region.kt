package kingmc.platform

import java.util.ArrayList
import kotlin.math.abs

/**
 * Represents a block shaped region determined by two corner location
 *
 * @author kingsthere
 * @since 0.0.7
 */
data class Region(val first: Location3D, val second: Location3D) : Iterable<Location3D> {
    private val coveredLocations by lazy {
        val size = abs((first.blockX - second.blockX) * (first.blockY - second.blockY) * (first.blockZ - second.blockZ))
        return@lazy ArrayList<Location3D>(size).apply {
            for (x in first.blockX..second.blockX) {
                for (y in first.blockY..second.blockY) {
                    for (z in first.blockZ..second.blockZ) {
                        add(Location3D(x, y, z))
                    }
                }
            }
        }
    }

    override fun iterator(): Iterator<Location3D> = coveredLocations.iterator()

}

/**
 * Create a region by x1, y1, z1, x2, y2, z2
 *
 * @return region created
 */
fun Region(x1: Int, y1: Int, z1: Int, x2: Int, y2: Int, z2: Int) : Region {
    return Region(Location3D(x1, y1, z1), Location3D(x2, y2, z2))
}