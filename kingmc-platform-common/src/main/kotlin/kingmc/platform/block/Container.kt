package kingmc.platform.block

import kingmc.platform.inventory.Inventory
import kingmc.platform.inventory.InventoryHolder
import kingmc.platform.inventory.MutableInventory
import kingmc.platform.util.Nameable

/**
 * Represents a captured state of a container block, such as
 *  + Chest
 *  + Ender Chest
 *  + Shulker Box
 *
 * @author kingsthere
 * @since 0.0.8
 */
interface Container : BlockState, InventoryHolder, Lockable, Nameable {
    /**
     * The inventory to this container
     */
    override val inventory: MutableInventory

    /**
     * Gets a read-only snapshot inventory
     */
    val snapshotInventory: Inventory
}