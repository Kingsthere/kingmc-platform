package kingmc.platform.inventory

import kingmc.platform.item.ItemStack

/**
 * Interface to a player's 2x2 crafting inventory
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface CraftingInventory : Inventory

/**
 * Interface to a mutable crafting inventory
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface MutableCraftingInventory : CraftingInventory, MutableInventory

/**
 * `InventorySlots.CRAFTING_RESULT`
 */
val CraftingInventory.result: ItemStack by inventoryItemStack(InventorySlots.CRAFTING_RESULT)

/**
 * `InventorySlots.CRAFTING_RESULT`
 */
var MutableCraftingInventory.result: ItemStack by mutableInventoryItemStack(InventorySlots.CRAFTING_RESULT)