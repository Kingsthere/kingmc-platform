package kingmc.platform.inventory

import kingmc.platform.item.ItemStack
import kotlin.properties.Delegates

/**
 * Interface to a crafting inventory
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
val CraftingInventory.result: ItemStack by Delegates.inventoryItemStack(InventorySlots.CRAFTING_RESULT)

/**
 * `InventorySlots.CRAFTING_RESULT`
 */
var MutableCraftingInventory.result: ItemStack by Delegates.mutableInventoryItemStack(InventorySlots.CRAFTING_RESULT)