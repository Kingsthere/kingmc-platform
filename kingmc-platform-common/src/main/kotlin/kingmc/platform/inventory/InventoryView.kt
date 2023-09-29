package kingmc.platform.inventory

/**
 * Represents the state of what the audience that opened the inventory viewing, include
 *  + The base inventory of the audience
 *  + The extra inventory that the audience opening (such as a `ChestInventory`)
 *
 * @author kingsthere
 * @since 0.0.6
 * @see Inventory
 */
interface InventoryView {
    /**
     * The holder that opened this inventory view
     */
    val holder: InventoryHolder

    /**
     * The base inventory holded by the [holder]
     */
    val baseInventory: Inventory

    /**
     * The extra inventory opening
     */
    val extraInventory: Inventory

    /**
     * The title of this inventory view
     */
    var title: String

    /**
     * Close this inventory view
     */
    fun close()
}