package kingmc.platform.block

/**
 * Indicated a block that could open by players, such as
 *  + Door
 *  + Fence Gate
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface Openable {
    /**
     * `true` If the door is open.
     */
    var isOpen: Boolean
}