package kingmc.platform

/**
 * Represent an object that follow the physical, the
 * object implement this interface contains
 * [Vector] and [Location]
 *
 * @since 0.0.1
 * @author kingsthere
 * @see Vector
 * @see Location
 */
interface Physical : Locatable {
    /**
     * The vector of this object
     *
     * @since 0.0.1
     * @see Vector
     */
    val vector: Vector
}