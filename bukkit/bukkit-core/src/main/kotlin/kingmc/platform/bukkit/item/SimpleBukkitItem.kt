package kingmc.platform.bukkit.item

import kingmc.platform.Material
import kingmc.platform.bukkit.impl.item.BukkitItemFactoryImpl
import kingmc.platform.item.AbstractItem
import kingmc.platform.item.ItemBuilder
import kingmc.platform.item.ItemStack
import kingmc.platform.nbt.NBTCompound
import kingmc.platform.nbt.toMutableNBTCompound
import kingmc.util.key.Key

/**
 * A simple `AbstractItem` implementation
 *
 * @since 0.0.7
 * @author kingsthere
 */
open class SimpleBukkitItem(material: Material<*>, nbt: NBTCompound, key: Key) : AbstractItem(material, nbt, key) {
    /**
     * Convert this `Item` to a [ItemStack]
     *
     * @see ItemStack
     */
    override fun toItemStack(amount: Int): ItemStack {
        return BukkitItemFactoryImpl.buildItemStackForItem(this, amount)
    }

    /**
     * Create a builder from this thing.
     *
     * @return a builder
     * @since 0.0.1
     */
    override fun toBuilder(): ItemBuilder {
        return BukkitItemBuilder(nbt.toMutableNBTCompound(), material, key)
    }
}