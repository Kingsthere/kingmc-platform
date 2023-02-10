package kingmc.platform.item

import de.tr7zw.changeme.nbtapi.NBTCompound
import kingmc.platform.Material
import kingmc.platform.audience.text.BinaryTagHolder
import kingmc.platform.audience.text.HoverEvent
import kingmc.platform.audience.text.Text
import net.kyori.adventure.key.Key

/**
 * An abstract implement of [Item]
 *
 * @since 0.0.5
 * @author kingsthere
 */
abstract class AbstractItemStack : ItemStack {
    override var material: Material
    override var nbt: NBTCompound

    constructor(material: Material, nbt: NBTCompound) {
        this.material = material
        this.nbt = nbt
    }

    constructor(item: ItemStack) {
        this.material = item.material
        this.nbt = item.nbt
    }

    /**
     * Indicates whether some other object is "equal to" this one. Implementations must fulfil the following
     * requirements:
     *
     * * Reflexive: for any non-null value `x`, `x.equals(x)` should return true.
     * * Symmetric: for any non-null values `x` and `y`, `x.equals(y)` should return true if and only if `y.equals(x)` returns true.
     * * Transitive:  for any non-null values `x`, `y`, and `z`, if `x.equals(y)` returns true and `y.equals(z)` returns true, then `x.equals(z)` should return true.
     * * Consistent:  for any non-null values `x` and `y`, multiple invocations of `x.equals(y)` consistently return true or consistently return false, provided no information used in `equals` comparisons on the objects is modified.
     * * Never equal to null: for any non-null value `x`, `x.equals(null)` should return false.
     *
     * Read more about [equality](https://kotlinlang.org/docs/reference/equality.html) in Kotlin.
     */
    override fun equals(other: Any?): Boolean {
        return nbt == other
    }

    /**
     * Returns a hash code value for the object.  The general contract of `hashCode` is:
     *
     * * Whenever it is invoked on the same object more than once, the `hashCode` method must consistently return the same integer, provided no information used in `equals` comparisons on the object is modified.
     * * If two objects are equal according to the `equals()` method, then calling the `hashCode` method on each of the two objects must produce the same integer result.
     */
    override fun hashCode(): Int {
        return nbt.hashCode()
    }

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

    override fun toString(): String {
        return "ItemStack(nbt=$nbt, material=$material, amount=$amount)"
    }
}