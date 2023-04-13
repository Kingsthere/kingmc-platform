package kingmc.platform.bukkit.impl.inventory

import kingmc.common.application.Application
import kingmc.common.text.Text
import kingmc.platform.bukkit.inventory.MutableBukkitInventory
import kingmc.platform.bukkit.inventory._BukkitInventory
import kingmc.platform.bukkit.item.BukkitItemStack
import kingmc.platform.bukkit.item.ItemStack
import kingmc.platform.inventory.InventorySlot
import kingmc.platform.inventory.InventorySlots
import kingmc.platform.inventory.UnsupportedInventorySlotException
import kingmc.platform.item.ItemStack

/**
 * Bukkit [MutableBukkitInventory] implementation
 *
 * @since 0.0.8
 * @author kingsthere
 */
open class MutableBukkitInventoryImpl(private val _bukkitInventory: _BukkitInventory, override val title: Text? = null, val application: Application) :
    MutableBukkitInventory {
    /**
     * Convert this `BukkitInventory` to a [org.bukkit.inventory.Inventory]
     */
    override fun toBukkitInventory(): _BukkitInventory = _bukkitInventory

    /**
     * Add an `ItemStack` into this inventory
     *
     * @return `true` if [itemStack] successfully added to this inventory, for example: if the inventory
     *  is fulled then you cannot add [itemStack]
     */
    override fun add(itemStack: ItemStack): Boolean {
        return _bukkitInventory.addItem(
            /* ...items = */ (itemStack as BukkitItemStack).toBukkitItemStack()
        ).isEmpty()
    }

    /**
     * Set an `ItemStack` into specifies [slot]
     */
    override fun set(slot: InventorySlot, itemStack: ItemStack) {
        _bukkitInventory.setItem(
            /* index = */ _slots[slot] ?: throw UnsupportedInventorySlotException("Unsupported inventory slot $slot"),
            /* item = */ (itemStack as BukkitItemStack).toBukkitItemStack()
        )
    }

    /**
     * Put an `ItemStack` into specifies [slotId]
     */
    override fun set(slotId: Int, itemStack: ItemStack) {
        _bukkitInventory.setItem(
            /* index = */ slotId,
            /* item = */ (itemStack as BukkitItemStack).toBukkitItemStack()
        )
    }

    /**
     * Remove an `ItemStack` that on specifies [slot]
     *
     * @return `true` if `ItemStack` successfully removed from this inventory
     */
    override fun remove(slot: InventorySlot) {
        _bukkitInventory.setItem(
            /* index = */ _slots[slot] ?: throw UnsupportedInventorySlotException("Unsupported inventory slot $slot"),
            /* item = */ (ItemStack.AIR as BukkitItemStack).toBukkitItemStack()
        )
    }

    /**
     * Remove an `ItemStack` that on specifies [slotId]
     *
     * @return `true` if `ItemStack` successfully removed from this inventory
     */
    override fun remove(slotId: Int) {
        _bukkitInventory.setItem(
            /* index = */ slotId,
            /* item = */ (ItemStack.AIR as BukkitItemStack).toBukkitItemStack()
        )
    }

    /**
     * Clear all `ItemStack`s inn this inventory
     */
    override fun clear() {
        _bukkitInventory.clear()
    }

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
    override fun iterator(): MutableIterator<ItemStack> {
        return slots.map { get(it) }.toMutableSet().iterator()
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