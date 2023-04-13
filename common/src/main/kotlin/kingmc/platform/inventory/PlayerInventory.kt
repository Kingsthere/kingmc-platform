package kingmc.platform.inventory

import kingmc.platform.item.ItemStack
import kotlin.properties.Delegates

/**
 * Interface to a inventory of a player
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface PlayerInventory : Inventory

/**
 * Interface to a mutable inventory of a player
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface MutablePlayerInventory : PlayerInventory, MutableInventory

/**
 * `InventorySlots.ARMOR_HELMET`
 */
val PlayerInventory.helmet: ItemStack by Delegates.inventoryItemStack(InventorySlots.ARMOR_HEAD)

/**
 * `InventorySlots.ARMOR_CHEST`
 */
val PlayerInventory.chest: ItemStack by Delegates.inventoryItemStack(InventorySlots.ARMOR_CHEST)

/**
 * `InventorySlots.ARMOR_LEGS`
 */
val PlayerInventory.legs: ItemStack by Delegates.inventoryItemStack(InventorySlots.ARMOR_LEGS)

/**
 * `InventorySlots.ARMOR_FEET`
 */
val PlayerInventory.feet: ItemStack by Delegates.inventoryItemStack(InventorySlots.ARMOR_FEET)

/**
 * `InventorySlots.WEAPON_MAINHAND`
 */
val PlayerInventory.mainhand: ItemStack by Delegates.inventoryItemStack(InventorySlots.WEAPON_MAINHAND)

/**
 * `InventorySlots.WEAPON_OFFHAND`
 */
val PlayerInventory.offhand: ItemStack by Delegates.inventoryItemStack(InventorySlots.WEAPON_OFFHAND)

/**
 * `InventorySlots.ARMOR_HELMET`
 */
var MutablePlayerInventory.helmet: ItemStack by Delegates.mutableInventoryItemStack(InventorySlots.ARMOR_HEAD)

/**
 * `InventorySlots.ARMOR_CHEST`
 */
var MutablePlayerInventory.chest: ItemStack by Delegates.mutableInventoryItemStack(InventorySlots.ARMOR_CHEST)

/**
 * `InventorySlots.ARMOR_LEGS`
 */
var MutablePlayerInventory.legs: ItemStack by Delegates.mutableInventoryItemStack(InventorySlots.ARMOR_LEGS)

/**
 * `InventorySlots.ARMOR_FEET`
 */
var MutablePlayerInventory.feet: ItemStack by Delegates.mutableInventoryItemStack(InventorySlots.ARMOR_FEET)

/**
 * `InventorySlots.WEAPON_MAINHAND`
 */
var MutablePlayerInventory.mainhand: ItemStack by Delegates.mutableInventoryItemStack(InventorySlots.WEAPON_MAINHAND)

/**
 * `InventorySlots.WEAPON_OFFHAND`
 */
var MutablePlayerInventory.offhand: ItemStack by Delegates.mutableInventoryItemStack(InventorySlots.WEAPON_OFFHAND)