package kingmc.platform.inventory

import kingmc.platform.item.ItemStack

/**
 * Interface to a inventory of a Furnace
 */
interface FurnaceInventory : Inventory

/**
 * Interface to a mutable inventory of a Furnace
 */
interface MutableFurnaceInventory : FurnaceInventory, MutableInventory

/**
 * `InventorySlots.FURNACE_RESULT`
 */
val FurnaceInventory.result: ItemStack by inventoryItemStack(InventorySlots.FURNACE_RESULT)

/**
 * `InventorySlots.FURNACE_SMELTING`
 */
val FurnaceInventory.smelting: ItemStack by inventoryItemStack(InventorySlots.FURNACE_SMELTING)

/**
 * `InventorySlots.FURNACE_FUEL`
 */
val FurnaceInventory.fuel: ItemStack by inventoryItemStack(InventorySlots.FURNACE_FUEL)

/**
 * `InventorySlots.FURNACE_RESULT`
 */
var MutableFurnaceInventory.result: ItemStack by mutableInventoryItemStack(InventorySlots.FURNACE_RESULT)

/**
 * `InventorySlots.FURNACE_SMELTING`
 */
val MutableFurnaceInventory.smelting: ItemStack by mutableInventoryItemStack(InventorySlots.FURNACE_SMELTING)

/**
 * `InventorySlots.FURNACE_FUEL`
 */
val MutableFurnaceInventory.fuel: ItemStack by mutableInventoryItemStack(InventorySlots.FURNACE_FUEL)