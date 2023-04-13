package kingmc.platform.inventory

import kingmc.platform.item.ItemStack
import kotlin.properties.Delegates

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
val FurnaceInventory.result: ItemStack by Delegates.inventoryItemStack(InventorySlots.FURNACE_RESULT)

/**
 * `InventorySlots.FURNACE_SMELTING`
 */
val FurnaceInventory.smelting: ItemStack by Delegates.inventoryItemStack(InventorySlots.FURNACE_SMELTING)

/**
 * `InventorySlots.FURNACE_FUEL`
 */
val FurnaceInventory.fuel: ItemStack by Delegates.inventoryItemStack(InventorySlots.FURNACE_FUEL)

/**
 * `InventorySlots.FURNACE_RESULT`
 */
var MutableFurnaceInventory.result: ItemStack by Delegates.mutableInventoryItemStack(InventorySlots.FURNACE_RESULT)

/**
 * `InventorySlots.FURNACE_SMELTING`
 */
val MutableFurnaceInventory.smelting: ItemStack by Delegates.mutableInventoryItemStack(InventorySlots.FURNACE_SMELTING)

/**
 * `InventorySlots.FURNACE_FUEL`
 */
val MutableFurnaceInventory.fuel: ItemStack by Delegates.mutableInventoryItemStack(InventorySlots.FURNACE_FUEL)