package kingmc.platform.inventory

/**
 * A superinterface indicates a viewer that could open an `Inventory`
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface InventoryViewer {
    /**
     * The inventory that opened by this inventory viewer
     */
    val openedInventory: InventoryView?

    /**
     * Open an inventory for this inventory viewer
     *
     * @param inventory the inventory to open
     * @return The newly opened inventory view
     */
    fun openInventory(inventory: Inventory): InventoryView

    /**
     * Force-closes the currently open inventory view for this player, if any
     */
    fun closeInventory()
}