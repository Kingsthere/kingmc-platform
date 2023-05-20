package kingmc.platform.material.block

/**
 * Indicated a Material that may carry or create a Redstone current, such as
 *  + (Stone/Wooden) Button
 *  +
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface Redstone {
    /**
     * Gets the current state of this Material, indicating if it's powered or
     * unpowered
     *
     * @return true if powered, otherwise false
     */
    val isPowered: Boolean
}