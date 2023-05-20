package kingmc.platform.block

/**
 * Indicate a chest-like block that could animate state to open or close
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface Lidded {
    /**
     * Sets the block's animated state to open and prevents it from being closed
     * until [close] is called.
     */
    fun open()

    /**
     * Sets the block's animated state to closed even if a player is currently
     * viewing this block.
     */
    fun close()
}