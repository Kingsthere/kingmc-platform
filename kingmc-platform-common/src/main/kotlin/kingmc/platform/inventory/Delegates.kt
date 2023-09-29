package kingmc.platform.inventory

import kingmc.platform.item.ItemStack
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Create a Delegate property for specified [slot]
 */
fun inventoryItemStack(slot: InventorySlot) = InventorySlotDelegate(slot)

/**
 * Create a Delegate property for specified [slot]
 */
fun mutableInventoryItemStack(slot: InventorySlot) = MutableInventorySlotDelegate(slot)

class InventorySlotDelegate(val slot: InventorySlot) : ReadOnlyProperty<Inventory, ItemStack> {
    /**
     * Returns the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @return the property value.
     */
    override fun getValue(thisRef: Inventory, property: KProperty<*>): ItemStack {
        return thisRef[slot]
    }
}

class MutableInventorySlotDelegate(val slot: InventorySlot) : ReadWriteProperty<MutableInventory, ItemStack> {
    /**
     * Returns the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @return the property value.
     */
    override fun getValue(thisRef: MutableInventory, property: KProperty<*>): ItemStack {
        return thisRef[slot]
    }

    /**
     * Sets the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @param value the value to set.
     */
    override fun setValue(thisRef: MutableInventory, property: KProperty<*>, value: ItemStack) {
        thisRef[slot] = value
    }
}