package kingmc.platform.inventory

/**
 * Interface to an anvil inventory
 *
 * @author kingsthere
 * @since 0.0.6
 */
interface AnvilInventory : Inventory {
    /**
     * Get the name to be applied to the repaired item. An empty string denotes
     * the default item name
     */
    val renameText: String
}

/**
 * Interface to an mutable anvil inventory
 *
 * @author kingsthere
 * @since 0.0.6
 */
interface MutableAnvilInventory : AnvilInventory, MutableInventory