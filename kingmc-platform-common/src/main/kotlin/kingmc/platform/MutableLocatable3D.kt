package kingmc.platform

/**
 * Represent an object that holds a mutable location3D
 *
 * @author kingsthere
 * @since 0.0.3
 */
interface MutableLocatable3D : Locatable3D {
    /**
     * The [location] of this locatable
     *
     * @since 0.0.3
     */
    @set:Deprecated(
        message = "Use teleport() function instead",
        replaceWith = ReplaceWith("teleport(location)")
    )
    override var location: Location3D

    /**
     * Teleport this locatable to another [location]
     *
     * @since 0.0.3
     * @param location the location this locatable to teleport to
     * @see Location
     */
    fun teleport(location: Location3D)

    /**
     * Teleport [locatable] to another locatable location
     *
     * @since 0.0.3
     * @param locatable the locatable this locatable to teleport to
     * @see Location
     */
    fun teleport(locatable: Locatable3D) {
        this.teleport(locatable.location)
    }
}