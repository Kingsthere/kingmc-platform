package kingmc.platform.inventory

/**
 * Represents the state of what the audience that opened the inventory viewing, include
 *  + The base inventory of the audience
 *  + The extra inventory that the audience opening (such as a `ChestInventory`)
 *
 * @since 0.0.6
 * @author kingsthere
 * @see Inventory
 */
interface InventoryView {
    val baseInventory: Inventory
    val extraInventory: Inventory
}