package kingmc.platform.material

/**
 * Indicates a material that can be colored
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface Colorable {
    /**
     * The color of this object to the specified DyeColor
     */
    var color: DyeColor?
}