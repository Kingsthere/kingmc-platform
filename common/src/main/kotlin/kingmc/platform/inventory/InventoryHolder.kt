package kingmc.platform.inventory

/**
 * A superinterface represents a holder that could hold an `Inventory`
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface InventoryHolder {
    /**
     * The inventory that this holder holding
     */
    val inventory: Inventory
}