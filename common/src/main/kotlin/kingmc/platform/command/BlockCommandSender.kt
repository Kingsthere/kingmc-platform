package kingmc.platform.command

import kingmc.platform.block.Block

/**
 * Represent a command block in minecraft
 * server
 *
 * @since 0.0.3
 * @author kingsthere
 * @see CommandSender
 */
interface BlockCommandSender : CommandSender {
    /**
     * Gets the [Block] instance that this command sender represents
     */
    fun getBlock(): Block
}