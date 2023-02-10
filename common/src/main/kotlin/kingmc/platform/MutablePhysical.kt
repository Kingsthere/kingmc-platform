package kingmc.platform

/**
 * Represent an object that follow the physical, the
 * object implement this interface contains
 * [Vector] and [Location], and the physical properties in this
 * object is mutable
 *
 * @since 0.0.1
 * @author kingsthere
 * @see Vector
 * @see Location
 */
interface MutablePhysical : Physical, MutableLocatable {
    /**
     * The vector of this object
     *
     * @since 0.0.1
     * @see Vector
     */
    override var vector: Vector
}