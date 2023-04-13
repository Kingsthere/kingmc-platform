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
     * @return `true` if [itemStack] successfully added to this inventory
     */
    fun add(itemStack: ItemStack): Boolean

    /**
     * Set an `ItemStack` into specifies [slot]
     */
    operator fun set(slot: InventorySlot, itemStack: ItemStack)

    /**
     * Set an `ItemStack` into specifies [slotId]
     */
    operator fun set(slotId: Int, itemStack: ItemStack)

    /**
     * Remove an `ItemStack` that on specifies [slot]
     *
     * @return `true` if `ItemStack` successfully removed from this inventory
     */
    fun remove(slot: InventorySlot)

    /**
     * Remove an `ItemStack` that on specifies [slotId]
     *
     * @return `true` if `ItemStack` successfully removed from this inventory
     */
    fun remove(slotId: Int)

    /**
     * Clear all `ItemStack`s inn this inventory
     */
    fun clear()
}
