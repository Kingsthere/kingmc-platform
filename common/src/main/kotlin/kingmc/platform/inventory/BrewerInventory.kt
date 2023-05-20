package kingmc.platform.inventory

import kingmc.platform.item.ItemStack

/**
 * Interface to an brewer inventory
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface BrewerInventory : Inventory

/**
 * Interface to a mutable brewer inventory
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface MutableBrewerInventory : BrewerInventory, MutableInventory

/**
 * `InventorySlots.BREWER_INGREDIENT`
 */
val BrewerInventory.ingredient: ItemStack by inventoryItemStack(InventorySlots.BREWER_INGREDIENT)

/**
 * `InventorySlots.BREWER_INGREDIENT`
 */
var MutableBrewerInventory.ingredient: ItemStack by mutableInventoryItemStack(InventorySlots.ENCHANTING_SECONDARY)

/**
 * `InventorySlots.BREWER_FUEL`
 */
val BrewerInventory.fuel: ItemStack by inventoryItemStack(InventorySlots.FURNACE_FUEL)

/**
 * `InventorySlots.BREWER_FUEL`
 */
var MutableBrewerInventory.fuel: ItemStack by mutableInventoryItemStack(InventorySlots.BREWER_FUEL)