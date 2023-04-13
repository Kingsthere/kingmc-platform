package kingmc.platform.inventory

/**
 * Interface to a combined inventory of a Double Chest
 */
interface DoubleChestInventory : Inventory {
    /**
     * The left side inventory
     */
    val leftSide: Inventory

    /**
     * The right side inventory
     */
    val rightSide: Inventory
}