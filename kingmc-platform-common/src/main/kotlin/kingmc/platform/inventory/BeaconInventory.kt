package kingmc.platform.inventory

import kingmc.platform.item.ItemStack

/**
 * Interface to an `Beacon`
 *
 * @author kingsthere
 * @since 0.0.6
 */
interface BeaconInventory : Inventory

/**
 * Interface to an mutable anvil inventory
 *
 * @author kingsthere
 * @since 0.0.6
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