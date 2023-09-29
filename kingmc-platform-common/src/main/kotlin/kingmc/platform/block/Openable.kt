package kingmc.platform.block

/**
 * Indicated a block that could open by players, such as
 *  + Door
 *  + Fence Gate
 *
 * @author kingsthere
 * @since 0.0.8
 */
interface Openable {
    /**
     * `true` If the door is open.
     */
    var isOpen: Boolean
}