package kingmc.platform.item

import kingmc.common.application.currentApplication
import kingmc.common.text.HoverEventDisplayable
import kingmc.platform.material.Material
import kingmc.platform.material.MutableMaterialHolder
import kingmc.platform.nbt.MutableNBTCompound
import kingmc.platform.nbt.MutableNBTCompoundHolder
import kingmc.util.Tagged
import kingmc.util.text.TextDisplayable

/**
 * An interface represents an item stack, this interface is mutable
 *
 * @since 0.0.1
 * @author kingsthere
 */
interface ItemStack : MutableMaterialHolder, HoverEventDisplayable, TextDisplayable, MutableNBTCompoundHolder, Tagged, Cloneable {
    /**
     * Gets the `NBTCompound` to access information of this item stack
     */
    override val nbt: MutableNBTCompound

    /**
     * The material of this item to display in client side
     *
     * @see MaterialType
     */
    override var material: Material<*>

    /**
     * The amounts of this `ItemStack`
     */
    var amount: Int

    /**
     * Convert this `ItemStack` to a [Item]
     *
     * @see Item
     */
    fun toItem(): Item

    /**
     * Merge the contents from [item] into this `ItemStack`
     *
     * @param item the item to apply into
     * @see Item
     */
    fun merge(item: Item)

    companion object Items {
        val AIR get() = currentApplication().itemFactory.AIR
    }
}