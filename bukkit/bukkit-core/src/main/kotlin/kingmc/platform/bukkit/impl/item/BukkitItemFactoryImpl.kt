package kingmc.platform.bukkit.impl.item

import kingmc.common.context.annotation.Autowired
import kingmc.common.context.annotation.Component
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.item.BukkitItemBuilder
import kingmc.platform.bukkit.item.BukkitItemFactory
import kingmc.platform.bukkit.item._BukkitItemStack
import kingmc.platform.bukkit.material.BukkitMaterialProvider
import kingmc.platform.bukkit.material.BukkitMaterialType
import kingmc.platform.bukkit.material._BukkitMaterial
import kingmc.platform.bukkit.nbt.BukkitNBTFactory
import kingmc.platform.item.Item
import kingmc.platform.item.ItemBuilder
import kingmc.platform.item.ItemStack
import kingmc.platform.material.Material
import kingmc.platform.material.MaterialType
import kingmc.platform.nbt.merge
import kingmc.util.key.Key

/**
 * Bukkit `ItemFactory` implementation
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component
@BukkitImplementation
object BukkitItemFactoryImpl : BukkitItemFactory {
    @Autowired
    lateinit var nbtFactory: BukkitNBTFactory

    @Autowired
    lateinit var materialProvider: BukkitMaterialProvider

    /**
     * Create an `ItemStack` for [org.bukkit.inventory.ItemStack]
     *
     * @param itemStack [org.bukkit.inventory.ItemStack]
     * @return the item stack created
     */
    override fun createItemStackForBukkit(itemStack: _BukkitItemStack): ItemStack {
        // TODO Material data
        return BukkitItemStackImpl(
            amount = itemStack.amount,
            material = Material(materialProvider.getTypeForBukkit(itemStack.type) as MaterialType<Unit>, Unit),
            _bukkitItemStack = itemStack
        )
    }

    /**
     * A constant value for an `Air ItemStack`
     */
    override val AIR: ItemStack by lazy {
        createItemStackForBukkit(_BukkitItemStack(_BukkitMaterial.AIR) )
    }

    /**
     * Build an item from a [ItemBuilder] and return
     *
     * @param key the key of the item
     * @param builderAction the action to the builder
     * @return the item built
     */
    override fun buildItem(key: Key, builderAction: ItemBuilder.() -> Unit): Item {
        return BukkitItemBuilder(
            nbt = nbtFactory.createMutableNBTCompound(),
            material = null,
            key = key
        ).apply(builderAction).build()
    }

    /**
     * Build an `ItemStack` for the given [item]
     *
     * @param item the item template to build `ItemStack` from
     * @param amount the amount of the item stack
     * @return the item stack built
     */
    override fun buildItemStackForItem(item: Item, amount: Int): ItemStack {
        val material = item.material
        return BukkitItemStackImpl(
            amount = amount,
            material = material,
            _bukkitItemStack = _BukkitItemStack((material.type as BukkitMaterialType).toBukkitMaterial())
        ).apply {
            this.nbt.merge(item.nbt)
        }
    }

    /**
     * Create an `ItemStack` with basic properties
     *
     * @param material the material of the `ItemStack`
     * @param amount the amount of the `ItemStack`
     * @return the item stack create
     */
    override fun createItemStack(material: Material<*>, amount: Int): ItemStack {
        return BukkitItemStackImpl(
            amount = amount,
            material = material,
            _bukkitItemStack = _BukkitItemStack((material.type as BukkitMaterialType).toBukkitMaterial())
        )
    }
}