package kingmc.platform

/**
 * A representation of [Location] that represent
 * the location only have three-dimensional, the
 * 3D location don't have the properties [world]
 * present
 *
 * @since 0.0.1
 * @author kingsthere
 */
abstract class Location3D(
    override val x: Double,
    override val y: Double,
    override val z: Double) : Location {

    // World default set to null
    override val world: World? = null
}