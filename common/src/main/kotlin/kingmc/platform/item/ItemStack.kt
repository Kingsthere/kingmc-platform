package kingmc.platform.item

import de.tr7zw.changeme.nbtapi.NBTCompound
import kingmc.platform.Material
import kingmc.platform.audience.text.HoverEventDisplayable
import kingmc.platform.audience.text.TextDisplayable
import kingmc.platform.nbt.NBTCompoundHolder
import kingmc.util.Tagged

/**
 * An interface represents an item stack, this interface is mutable
 *
 * @since 0.0.1
 * @author kingsthere
 */
interface ItemStack : HoverEventDisplayable, TextDisplayable, NBTCompoundHolder, Tagged, Cloneable {
    /**
     * Gets the `NBTCompound` to access information of this item stack
     */
    override val nbt: NBTCompound

    /**
     * The material of this item to display in client side
     *
     * @see Material
     */
    var material: Material

    /**
     * The amounts of this `ItemStack`
     */
    var amount: Int

    /**
     * Convert this `ItemStack` to a [Item] to read contents of this `ItemStack` thread-safely
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
}