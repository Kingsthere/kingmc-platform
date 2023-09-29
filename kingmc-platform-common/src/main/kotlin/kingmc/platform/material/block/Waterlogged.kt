package kingmc.platform.material.block

/**
 * 'waterlogged' denotes whether this block has fluid in it such as
 *  + Slab
 *  + Stairs
 *  + Doors
 *  + Chests
 *
 * @author kingsthere
 * @since 0.0.8
 */
interface Waterlogged {
    /**
     * `true` if the value of the 'waterlogged' property.
     */
    var isWaterlogged: Boolean
}