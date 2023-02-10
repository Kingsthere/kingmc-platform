package kingmc.platform.item

import de.tr7zw.changeme.nbtapi.NBTCompound
import kingmc.platform.Material
import kingmc.platform.audience.text.HoverEventDisplayable
import kingmc.platform.audience.text.TextDisplayable
import kingmc.platform.nbt.NBTCompoundHolder
import kingmc.util.Tagged
import kingmc.util.builder.Buildable
import kingmc.util.errorprone.Immutable

/**
 * An interface represents any kind of item, this class is read-only and thread-safe
 *
 * @since 0.0.1
 * @author kingsthere
 */
@Immutable
interface Item : HoverEventDisplayable, TextDisplayable, NBTCompoundHolder, Tagged, Buildable<Item, ItemBuilder> {
    /**
     * Gets the `NBTCompound` to access information of this item stack
     */
    override val nbt: NBTCompound

    /**
     * The material of this item to display in client side
     *
     * @since 0.0.1
     * @see Material
     */
    val material: Material

    // TODO enchantments support
    // val enchantments
}