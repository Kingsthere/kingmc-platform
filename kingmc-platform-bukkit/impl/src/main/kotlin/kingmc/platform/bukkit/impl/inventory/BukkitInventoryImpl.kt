package kingmc.platform.bukkit.impl.inventory

import kingmc.common.application.Application
import kingmc.common.text.Text
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.inventory.BukkitInventory
import kingmc.platform.bukkit.inventory._BukkitInventory
import kingmc.platform.bukkit.item.ItemStack
import kingmc.platform.inventory.InventorySlot
import kingmc.platform.inventory.InventorySlots
import kingmc.platform.inventory.UnsupportedInventorySlotException
import kingmc.platform.item.ItemStack

/**
 * Bukkit [BukkitInventory] implementation
 *
 * @author kingsthere
 * @since 0.0.8
 */
@BukkitImplementation
open class BukkitInventoryImpl(private val _bukkitInventory: _BukkitInventory, override val title: Text? = null, val application: Application) : BukkitInventory {
    /**
     * Convert this `BukkitInventory` to a [org.bukkit.inventory.Inventory]
     */
    override fun toBukkitInventory(): _BukkitInventory = _bukkitInventory

    /**
     * The size of this inventory, for the slots of this `Inventory` that
     * contains a non-air material
     */
    override val size: Int
        get() = _bukkitInventory.size

    private val _slots = inventorySlots[size / 9]!!

    /**
     * All slots that this `Inventory` supports
     */
    override val slots: Set<InventorySlot> = _slots.keys

    /**
     * Gets an `ItemStack` from this inventory by [slot]
     *
     * @param slot the slot of the `ItemStack` to get
     * @throws UnsupportedInventorySlotException if the [slot] is not supported
     * @return the `ItemStack` got
     */
    override fun get(slot: InventorySlot): ItemStack {
        val bukkitItemStack = _bukkitInventory.getItem(
            _slots[slot] ?: throw UnsupportedInventorySlotException("Unsupported inventory slot $slot")
        )
        return bukkitItemStack?.let { ItemStack(it) } ?: ItemStack.AIR
    }

    /**
     * Gets an `ItemStack` from this inventory by integer [slotId]
     *
     * @param slotId the integer id of the slot of `ItemStack` to get
     * @throws UnsupportedInventorySlotException if the slot with [slotId] is not supported
     * @return the ItemStack got
     */
    override fun get(slotId: Int): ItemStack {
        val bukkitItemStack = _bukkitInventory.getItem(slotId)
        return bukkitItemStack?.let { ItemStack(it) } ?: ItemStack.AIR
    }

    /**
     * Check if the specifies [slot] is supported in this `Inventory`, means you can
     * use this slot to get contents in this `Inventory` using such as [get] ...
     *
     * @param slot the slot to check if this inventory supports
     */
    override fun isSlotSupported(slot: InventorySlot) = slot in slots

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<ItemStack> {
        return slots.map { get(it) }.iterator()
    }

    companion object Slots {
        val inventorySlots: Map<Int, Map<InventorySlot, Int>> get() {
            return buildMap {
                // For each possible size to an inventory
                repeat(6) { size ->
                    put(size + 1, buildMap {
                        // For each row (1-6 rows)
                        repeat(size + 1) { row ->
                            // For each column (9 columns)
                            repeat(9) { column ->
                                val slotNumber = (row * 9) + column
                                put(InventorySlots.valueOf("CONTAINER_$slotNumber"), slotNumber)
                            }
                        }
                    })
                }
            }
        }
    }
}