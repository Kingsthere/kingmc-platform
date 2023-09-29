package kingmc.platform

/**
 * Represent an object that holds a location
 *
 * @author kingsthere
 * @since 0.0.3
 */
interface Locatable : Locatable3D {
    /**
     * The location of this locatable object
     *
     * @since 0.0.3
     */
    override val location: Location
}