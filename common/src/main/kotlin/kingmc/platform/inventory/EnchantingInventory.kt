package kingmc.platform.inventory

import kingmc.platform.item.ItemStack

/**
 * Interface to an enchanting table inventory
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface EnchantingInventory : Inventory

/**
 * Interface to a mutable enchanting inventory
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface MutableEnchantingInventory : BrewerInventory, MutableInventory

/**
 * `InventorySlots.ENCHANTING_ITEM`
 */
val BrewerInventory.item: ItemStack by inventoryItemStack(InventorySlots.ENCHANTING_ITEM)

/**
 * `InventorySlots.ENCHANTING_ITEM`
 */
var MutableBrewerInventory.item: ItemStack by mutableInventoryItemStack(InventorySlots.ENCHANTING_ITEM)

/**
 * `InventorySlots.ENCHANTING_SECONDARY`
 */
val BrewerInventory.secondary: ItemStack by inventoryItemStack(InventorySlots.ENCHANTING_SECONDARY)

/**
 * `InventorySlots.ENCHANTING_SECONDARY`
 */
var MutableBrewerInventory.secondary: ItemStack by mutableInventoryItemStack(InventorySlots.ENCHANTING_SECONDARY)