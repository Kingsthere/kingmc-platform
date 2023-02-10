package kingmc.platform

/**
 * Represent a thing that has a mutable location, you can change
 * the location of this object
 *
 * @since 0.0.3
 * @author kingsthere
 * @see Location
 */
interface MutableLocatable : Locatable {
    /**
     * The [location] of this locatable
     *
     * @since 0.0.3
     */
    @set:Deprecated(
        message = "Use teleport() function instead",
        replaceWith = ReplaceWith("teleport(location)")
    )
    override var location: Location

    /**
     * Teleport this locatable to another [location]
     *
     * @since 0.0.3
     * @param location the location this locatable to teleport to
     * @see Location
     */
    fun teleport(location: Location)

    /**
     * Teleport [locatable] to another locatable location
     *
     * @since 0.0.3
     * @param locatable the locatable this locatable to teleport to
     * @see Location
     */
    fun teleport(locatable: Locatable)
}