package kingmc.platform.item

import kingmc.common.application.Application
import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.text.Text
import kingmc.platform.Material
import kingmc.platform.nbt.*
import kingmc.util.key.Key
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Gets the [ItemFactory] of this Application
 *
 * @since 0.0.5
 * @author kingsthere
 */
val Application.itemFactory: ItemFactory
    get() = this.context.getBean(ItemFactory::class)

/**
 * Create an item using a [ItemBuilder] and return
 */
@OptIn(ExperimentalContracts::class)
@WithApplication
fun Item(key: Key, builderAction: @WithApplication ItemBuilder.() -> Unit): Item {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return currentApplication().itemFactory.buildItem(key, builderAction)
}

/**
 * Build an `ItemStack` for the given [item]
 */
@WithApplication
fun ItemStack(item: Item, amount: Int = 1): ItemStack {
    return currentApplication().itemFactory.buildItemStackForItem(item, amount)
}

/**
 * Build an `ItemStack` with basic properties
 */
@WithApplication
fun ItemStack(material: Material<*>, amount: Int = 1): ItemStack {
    return currentApplication().itemFactory.createItemStack(material, amount)
}

/**
 * Set item display name
 */
fun ItemBuilder.displayName(name: Text): ItemBuilder {
    this.nbt.getOrCreateCompound("display").setTextForJson("Name", name)
    return this
}

/**
 * Set item display lore
 */
fun ItemBuilder.displayLore(lore: List<Text>): ItemBuilder {
    this.nbt.getOrCreateCompound("display").setTextListForJson("Lore", lore)
    return this
}

/**
 * Set item unbreakable
 */
fun ItemBuilder.unbreakable(unbreakable: Boolean = true): ItemBuilder {
    this.nbt.setBoolean("Unbreakable", unbreakable)
    return this
}

/**
 * Set item durability
 */
fun ItemBuilder.durability(durability: Short): ItemBuilder {
    this.nbt.setShort("Damage", durability)
    return this
}

/**
 * The item flags of this item, A ItemFlag can hide some Attributes
 * from ItemStacks when players hover their mouse on the item
 *
 * @since 0.0.1
 */
fun ItemBuilder.itemFlags(vararg itemFlag: ItemFlag): ItemBuilder {
    this.nbt.setInt("HideFlags", convertItemFlagsToInt(itemFlag.toSet()))
    return this
}

/**
 * The displaying name of this item, display
 * when player holding this item or the title
 * of the lore when players hover their mouse
 * on the item in inventory, returns `null` for the default
 * name of the material
 *
 * @since 0.0.1
 */
val Item.displayName: Text?
    get() {
        return nbt.getCompound("display")?.getTextForJson("Name")
    }

/**
 * The displaying lore of this item, display when players
 * hover their mouse on the item in inventory, like a
 * sorted list
 *
 * @see List
 * @since 0.0.1
 */
val Item.displayLore: List<Text>
    get() {
        return nbt.getCompound("display")?.getTextListForLegacy("Name") ?: emptyList()
    }

/**
 * Is this item is unbreakable, if this item is unbreakable
 * then the duration of this item will not lose when
 * this item use
 *
 * @since 0.0.1
 */
val Item.unbreakable: Boolean
    get() = nbt.getBoolean("Unbreakable") ?: false

/**
 * The durability of this item, it displays as a bar
 * with color from red to green in the bottom of
 * item, some items don't show that bar
 *
 * @since 0.0.1
 */
val Item.durability: Short
    get() = nbt.getShort("Damage") ?: 0

/**
 * The item flags of this item, A ItemFlag can hide some Attributes
 * from ItemStacks when players hover their mouse on the item
 *
 * @since 0.0.1
 */
val Item.itemFlags: Set<ItemFlag>
    get() {
        val hideFlags = nbt.getInt("HideFlags") ?: return emptySet()
        return obtainItemFlagsFromInt(hideFlags)
    }

/**
 * The displaying name of this item, display
 * when player holding this item or the title
 * of the lore when players hover their mouse
 * on the item in inventory, returns `null` for the default
 * name of the material
 *
 * @since 0.0.1
 */
var ItemStack.displayName: Text?
    get() {
        return nbt.getCompound("display")?.getTextForJson("Name")
    }
    set(value) {
        if (value == null) {
            nbt.getOrCreateCompound("display").remove("Name")
        } else {
            nbt.getOrCreateCompound("display").setTextForJson("Name", value)
        }
    }

/**
 * The displaying lore of this item, display when players
 * hover their mouse on the item in inventory, like a
 * sorted list
 *
 * @see List
 * @since 0.0.1
 */
var ItemStack.displayLore: List<Text>
    get() {
        return nbt.getCompound("display")?.getTextListForLegacy("Lore") ?: emptyList()
    }
    set(value) {
        if (value.isEmpty()) {
            nbt.getOrCreateCompound("display").remove("Lore")
        } else {
            nbt.getOrCreateCompound("display").setTextListForJson("Lore", value)
        }
    }

/**
 * Is this item is unbreakable, if this item is unbreakable
 * then the duration of this item will not lose when
 * this item use
 *
 * @since 0.0.1
 */
var ItemStack.unbreakable: Boolean
    get() = nbt.getBoolean("Unbreakable") ?: false
    set(value) {
        nbt.setBoolean("Unbreakable", value)
    }

/**
 * The durability of this item, it displays as a bar
 * with color from red to green in the bottom of
 * item, some items don't show that bar
 *
 * @since 0.0.1
 */
var ItemStack.durability: Short
    get() = nbt.getShort("Damage") ?: 0
    set(value) {
        nbt.setShort("Damage", value)
    }

/**
 * The item flags of this item, A ItemFlag can hide some Attributes
 * from ItemStacks when players hover their mouse on the item
 *
 * @since 0.0.1
 */
var ItemStack.itemFlags: Set<ItemFlag>
    get() = nbt.getInt("HideFlags")?.let { obtainItemFlagsFromInt(it) } ?: emptySet()
    set(value) {
        nbt.setInt("HideFlags", convertItemFlagsToInt(value))
    }
