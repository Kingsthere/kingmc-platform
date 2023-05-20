package kingmc.platform.block

import kingmc.platform.inventory.Inventory

/**
 * Represents a captured state of a chest
 */
interface Chest : Container, Lidded {
    /**
     * The inventory of the block represented by this block state
     *
     * If the block was changed to a different type in the meantime, the returned inventory might no longer be valid
     *
     * If this block state is not placed this will return the captured inventory snapshot instead
     */
    val blockInventory: Inventory
}