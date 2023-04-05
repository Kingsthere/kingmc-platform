package kingmc.platform.inventory

import kingmc.platform.item.ItemStack

/**
 * Extended [Inventory] and make it mutable
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface MutableInventory : Inventory, MutableIterable<ItemStack> {
    /**
     * Add an `ItemStack` into this inventory
     *
     * @return `true` if [itemStack] successfully added to this inventory, for example: if the inventory
     *  is fulled then you cannot add [itemStack]
     */
    fun add(itemStack: ItemStack): Boolean

    /**
     * Put an `ItemStack` into specifies [slot]
     */
    fun put(slot: InventorySlot, itemStack: ItemStack)

    /**
     * Put an `ItemStack` into specifies [slotId]
     */
    fun put(slotId: Int, itemStack: ItemStack)

    /**
     * Clear all `ItemStack`s inn this inventory
     */
    fun clear()
}
