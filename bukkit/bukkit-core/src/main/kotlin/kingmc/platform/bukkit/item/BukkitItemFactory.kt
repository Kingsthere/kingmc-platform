package kingmc.platform.bukkit.item

import kingmc.common.context.annotation.Component
import kingmc.platform.Material
import kingmc.platform.Platform
import kingmc.platform.bukkit.bukkitPlatform
import kingmc.platform.bukkit.material.BukkitMaterialProvider
import kingmc.platform.item.Item
import kingmc.platform.item.ItemBuilder
import kingmc.platform.item.ItemFactory
import kingmc.platform.item.ItemStack
import kingmc.platform.materials

/**
 * A `ItemFactory` for bukkit
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component
object BukkitItemFactory : ItemFactory {
    /**
     * Gets the platform of this
     */
    override val platform: Platform
        get() = bukkitPlatform

    /**
     * Build an item from a [ItemBuilder] and return
     *
     * @param builderAction the action to the builder
     * @return the item built
     */
    override fun buildItem(builderAction: ItemBuilder.() -> Unit): Item {
        return BukkitItemBuilder(material = null).apply(builderAction).build()
    }

    /**
     * Build an `ItemStack` for the given [item]
     *
     * @param item the item template to build `ItemStack` from
     * @param amount the amount of the item stack
     * @return the item stack built
     */
    override fun buildItemStackForItem(item: Item, amount: Int): ItemStack {
        return VirtualBukkitItemStack(amount, item.material, item.nbt)
    }

    /**
     * Create an `ItemStack` with basic properties
     *
     * @param material the material of the `ItemStack`
     * @param amount the amount of the `ItemStack`
     * @return the item stack create
     */
    override fun createItemStack(material: Material, amount: Int): ItemStack {
        return BukkitItemStack(amount, material)
    }

    /**
     * Create an [ItemStack] from bukkit `ItemStack`
     */
    fun createItemStackForBukkit(bukkitItemStack: OriginalBukkitItemStack): ItemStack {
        return BukkitItemStack(
            bukkitItemStack.amount,
            (platform.materials as BukkitMaterialProvider).getFromBukkit(bukkitItemStack.type),
            bukkitItemStack
        )
    }
}