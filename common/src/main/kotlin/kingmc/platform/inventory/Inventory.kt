package kingmc.platform.inventory

import kingmc.common.text.Text
import kingmc.platform.item.ItemStack

/**
 * A superinterface represents an inventory in minecraft
 *
 *
 * NOTE: [iterator] function returns a `Iterator` contains non-air material
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface Inventory : Iterable<ItemStack> {
    /**
     * The size of this inventory, for the slots of this `Inventory` that
     * contains a non-air material
     */
    val size: Int

    /**
     * All slots that this `Inventory` supports
     */
    val slots: List<InventorySlot>

    /**
     * The custom title of this inventory
     */
    val title: Text?

    /**
     * Gets an `ItemStack` from this inventory by [slot]
     *
     * @param slot the slot of the `ItemStack` to get
     * @throws UnsupportedInventorySlotException if the [slot] is not supported
     * @return the `ItemStack` got
     */
    operator fun get(slot: InventorySlot): ItemStack

    /**
     * Gets an `ItemStack` from this inventory by integer [slotId]
     *
     * @param slotId the integer id of the slot of `ItemStack` to get
     * @throws UnsupportedInventorySlotException if the slot with [slotId] is not supported
     * @return the ItemStack got
     */
    operator fun get(slotId: Int): ItemStack

    /**
     * Check if the specifies [slot] is supported in this `Inventory`, means you can
     * use this slot to get contents in this `Inventory` using such as [get] ...
     *
     * @param slot the slot to check if this inventory supports
     */
    fun isSlotSupported(slot: InventorySlot)
}