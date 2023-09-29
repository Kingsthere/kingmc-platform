package kingmc.platform

/**
 * Represent an object that holds a mutable location
 *
 * @author kingsthere
 * @since 0.0.3
 */
interface MutableLocatable : Locatable {
    /**
     * The [location] of this locatable object
     *
     * @since 0.0.3
     */
    @set:Deprecated(
        message = "Use teleport() function instead",
        replaceWith = ReplaceWith("teleport(location)")
    )
    override var location: Location

    /**
     * Teleport this locatable object to the given [location]
     *
     * @param location the location this locatable to teleport to
     * @since 0.0.3
     */
    fun teleport(location: Location)

    /**
     * Teleport [locatable] to the given locatable location
     *
     * @param locatable the locatable this locatable to teleport to
     * @since 0.0.3
     */
    fun teleport(locatable: Locatable) {
        this.teleport(locatable.location)
    }
}