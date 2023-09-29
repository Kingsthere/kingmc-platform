package kingmc.platform.inventory

import kingmc.platform.item.ItemStack

/**
 * Interface to a player's 2x2 crafting inventory
 *
 * @author kingsthere
 * @since 0.0.6
 */
interface CraftingInventory : Inventory

/**
 * Interface to a mutable crafting inventory
 *
 * @author kingsthere
 * @since 0.0.6
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