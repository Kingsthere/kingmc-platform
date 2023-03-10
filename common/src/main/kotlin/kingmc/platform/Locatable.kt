package kingmc.platform

/**
 * Represent a thing that has a location
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface Locatable : Locatable3D {
    /**
     * The location of this locatable
     *
     * @since 0.0.3
     */
    override val location: Location
}