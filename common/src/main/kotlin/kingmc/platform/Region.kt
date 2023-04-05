package kingmc.platform

/**
 * Represents a block shaped region determined by two corner location
 *
 * @since 0.0.7
 * @author kingsthere
 */
data class Region(val first: Location3D, val second: Location3D)

/**
 * Create a region by x1, y1, z1, x2, y2, z2
 *
 * @return region created
 */
fun Region(x1: Int, y1: Int, z1: Int, x2: Int, y2: Int, z2: Int) : Region {
    return Region(Location3D(x1, y1, z1), Location3D(x2, y2, z2))
}