package kingmc.platform.nbt

/**
 * A superinterface representing an mutable NMS compound
 *
 * @author kingsthere
 * @since 0.0.8
 */
interface MutableNBTCompound : NBTCompound {
    /**
     * Gets all entries in this nbt compound
     */
    override fun getEntries(): List<MutableNBTEntry<*>>

    /**
     * Remove an entry in this nbt compound
     */
    fun remove(key: String)

    /**
     * @return This `NBTCompound` as printable NBT-Json
     */
    override fun toString(): String

    /**
     * Gets an entry from this nbt compound by it's [key]
     */
    override fun get(key: String): MutableNBTEntry<*>?

    /**
     * Represents an mutable entry of a [MutableNBTCompound]
     */
    interface MutableNBTEntry<TValue : Any> : NBTCompound.NBTEntry<TValue> {
        /**
         * The value of this `NBTEntry`
         */
        override var value: TValue
    }
}

/**
 * Add an entry to this nbt compound, entry will be replaced if entry with same [NBTCompound.NBTEntry.key] exists in this
 * compound
 */
fun MutableNBTCompound.add(entry: MutableNBTCompound.MutableNBTEntry<*>) {
    entry.merge(this)
}