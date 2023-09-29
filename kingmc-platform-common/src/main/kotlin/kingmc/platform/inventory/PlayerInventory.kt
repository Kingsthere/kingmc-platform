package kingmc.platform.inventory

import kingmc.platform.item.ItemStack

/**
 * Interface to a inventory of a player
 *
 * @author kingsthere
 * @since 0.0.6
 */
interface PlayerInventory : Inventory

/**
 * Interface to a mutable inventory of a player
 *
 * @author kingsthere
 * @since 0.0.6
 */
interface MutablePlayerInventory : PlayerInventory, MutableInventory

/**
 * `InventorySlots.ARMOR_HELMET`
 */
val PlayerInventory.helmet: ItemStack by inventoryItemStack(InventorySlots.ARMOR_HEAD)

/**
 * `InventorySlots.ARMOR_CHEST`
 */
val PlayerInventory.chest: ItemStack by inventoryItemStack(InventorySlots.ARMOR_CHEST)

/**
 * `InventorySlots.ARMOR_LEGS`
 */
val PlayerInventory.legs: ItemStack by inventoryItemStack(InventorySlots.ARMOR_LEGS)

/**
 * `InventorySlots.ARMOR_FEET`
 */
val PlayerInventory.feet: ItemStack by inventoryItemStack(InventorySlots.ARMOR_FEET)

/**
 * `InventorySlots.WEAPON_MAINHAND`
 */
val PlayerInventory.mainhand: ItemStack by inventoryItemStack(InventorySlots.WEAPON_MAINHAND)

/**
 * `InventorySlots.WEAPON_OFFHAND`
 */
val PlayerInventory.offhand: ItemStack by inventoryItemStack(InventorySlots.WEAPON_OFFHAND)

/**
 * `InventorySlots.ARMOR_HELMET`
 */
var MutablePlayerInventory.helmet: ItemStack by mutableInventoryItemStack(InventorySlots.ARMOR_HEAD)

/**
 * `InventorySlots.ARMOR_CHEST`
 */
var MutablePlayerInventory.chest: ItemStack by mutableInventoryItemStack(InventorySlots.ARMOR_CHEST)

/**
 * `InventorySlots.ARMOR_LEGS`
 */
var MutablePlayerInventory.legs: ItemStack by mutableInventoryItemStack(InventorySlots.ARMOR_LEGS)

/**
 * `InventorySlots.ARMOR_FEET`
 */
var MutablePlayerInventory.feet: ItemStack by mutableInventoryItemStack(InventorySlots.ARMOR_FEET)

/**
 * `InventorySlots.WEAPON_MAINHAND`
 */
var MutablePlayerInventory.mainhand: ItemStack by mutableInventoryItemStack(InventorySlots.WEAPON_MAINHAND)

/**
 * `InventorySlots.WEAPON_OFFHAND`
 */
var MutablePlayerInventory.offhand: ItemStack by mutableInventoryItemStack(InventorySlots.WEAPON_OFFHAND)