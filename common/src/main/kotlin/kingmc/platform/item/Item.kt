package kingmc.platform.item

import kingmc.common.text.HoverEventDisplayable
import kingmc.platform.Material
import kingmc.platform.nbt.NBTCompound
import kingmc.platform.nbt.NBTCompoundHolder
import kingmc.util.Tagged
import kingmc.util.builder.Buildable
import kingmc.util.errorprone.Immutable
import kingmc.util.key.Keyed
import kingmc.util.text.TextDisplayable

/**
 * An interface represents any kind of item, similar to a read-only item stack template
 *
 * @since 0.0.1
 * @author kingsthere
 */
@Immutable
interface Item : Keyed, HoverEventDisplayable, TextDisplayable, NBTCompoundHolder, Tagged, Buildable<Item, ItemBuilder> {
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
    val material: Material<*>

    /**
     * Convert this `Item` to a [ItemStack]
     *
     * @see ItemStack
     */
    fun toItemStack(amount: Int = 1): ItemStack

    // TODO enchantments support
    // val enchantments
}