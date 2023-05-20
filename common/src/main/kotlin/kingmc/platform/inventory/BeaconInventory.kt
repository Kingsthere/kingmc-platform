package kingmc.platform.inventory

import kingmc.platform.item.ItemStack

/**
 * Interface to an `Beacon`
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface BeaconInventory : Inventory

/**
 * Interface to an mutable anvil inventory
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface MutableBeaconInventory : BeaconInventory, MutableInventory

/**
 * `InventorySlots.BEACON`
 */
val BeaconInventory.beacon: ItemStack by inventoryItemStack(InventorySlots.BEACON)

/**
 * `InventorySlots.BEACON`
 */
var MutableBeaconInventory.beacon: ItemStack by mutableInventoryItemStack(InventorySlots.BEACON)