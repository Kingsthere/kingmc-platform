package kingmc.platform.bukkit.item

import kingmc.common.application.Application
import kingmc.platform.item.AbstractItem
import kingmc.platform.item.ItemBuilder
import kingmc.platform.item.ItemStack
import kingmc.platform.item.itemFactory
import kingmc.platform.material.Material
import kingmc.platform.nbt.NBTCompound
import kingmc.platform.nbt.toMutableNBTCompound
import kingmc.util.key.Key

/**
 * A simple `AbstractItem` implementation
 *
 * @author kingsthere
 * @since 0.0.7
 */
open class SimpleBukkitItem(material: Material<*>, nbt: NBTCompound, key: Key) : AbstractItem(material, nbt, key) {
    /**
     * Convert this `Item` to a [ItemStack]
     *
     * @see ItemStack
     */
    override fun toItemStack(application: Application, amount: Int): ItemStack {
        return application.itemFactory.buildItemStackForItem(this, amount)
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