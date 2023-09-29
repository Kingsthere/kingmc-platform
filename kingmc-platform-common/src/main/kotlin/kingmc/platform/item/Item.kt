package kingmc.platform.item

import kingmc.platform.material.Material
import kingmc.platform.nbt.NBTCompound
import kingmc.platform.nbt.NBTCompoundHolder
import kingmc.util.Tagged
import kingmc.util.builder.Buildable
import com.google.errorprone.annotations.Immutable
import kingmc.common.application.Application
import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.util.key.Keyed
import kingmc.util.text.HoverEventDisplayable
import kingmc.util.text.TextDisplayable

/**
 * An interface represents any kind of item, similar to a read-only item stack template
 *
 * @author kingsthere
 * @since 0.0.1
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
    fun toItemStack(application: Application, amount: Int = 1): ItemStack

    // TODO enchantments support
    // val enchantments
}

/**
 * Convert this `Item` to a [ItemStack]
 *
 * @see ItemStack
 */
@WithApplication
fun Item.toItemStack(amount: Int = 1): ItemStack = toItemStack(currentApplication(), amount)