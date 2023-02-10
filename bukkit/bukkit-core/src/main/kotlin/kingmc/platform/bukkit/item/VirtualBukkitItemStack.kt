package kingmc.platform.bukkit.item

import de.tr7zw.changeme.nbtapi.NBTCompound
import de.tr7zw.changeme.nbtapi.NBTContainer
import kingmc.platform.Material
import kingmc.platform.audience.text.BinaryTagHolder
import kingmc.platform.audience.text.HoverEvent
import kingmc.platform.audience.text.Text
import kingmc.platform.item.Item
import kingmc.platform.item.ItemStack
import kingmc.platform.item.displayName
import net.kyori.adventure.key.Key

class VirtualBukkitItemStack(
    override var amount: Int,
    override var material: Material,
    override val nbt: NBTCompound = NBTContainer()
) : ItemStack {

    /**
     * The tags of this tagged object
     *
     * @since 0.0.1
     */
    override val tags: Set<String>
        get() = try {
            nbt.getStringList("Tags").toSet()
        } catch (e: IllegalArgumentException) {
            emptySet()
        }

    /**
     * Convert this object as a [HoverEvent]
     */
    override fun asHoverEvent(): HoverEvent<*> {
        return HoverEvent.showItem(Key.key(material.key.namespace(), material.key.value()), 1, BinaryTagHolder.binaryTagHolder(nbt.toString()))
    }

    /**
     * Convert this object into a [Text]
     */
    override fun asText(): Text =
        displayName ?: material.asText()

    /**
     * Merge the contents from [item] into this `ItemStack`
     *
     * @param item the item to apply into
     * @see Item
     */
    override fun merge(item: Item) {
        this.material = item.material
        this.nbt.mergeCompound(item.nbt)
    }

    /**
     * Convert this `ItemStack` to a [Item] to read contents of this `ItemStack` thread-safely
     *
     * @see Item
     */
    override fun toItem(): Item {
        return BukkitItem(material, nbt)
    }
}