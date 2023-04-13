package kingmc.platform.inventory

/**
 * Interface to an anvil inventory
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface AnvilInventory : Inventory

/**
 * Interface to an mutable anvil inventory
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface MutableAnvilInventory : AnvilInventory, MutableInventory