package kingmc.platform.inventory

/**
 * A superinterface represents a holder that could hold an `Inventory`
 *
 * @author kingsthere
 * @since 0.0.6
 */
interface InventoryHolder {
    /**
     * The inventory that this holder holding
     */
    val inventory: Inventory
}