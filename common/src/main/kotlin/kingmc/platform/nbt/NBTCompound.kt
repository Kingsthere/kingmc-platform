package kingmc.platform.nbt

import kingmc.util.InternalAPI

/**
 * A superinterface representing an immutable NMS compound
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface NBTCompound {
    /**
     * The name of this nbt compound
     */
    val name: String

    /**
     * The parent of this nbt compound
     */
    val parent: NBTCompound?

    /**
     * The size of entries in this nbt compound
     */
    val size: Int

    /**
     * Gets all entries in this nbt compound
     */
    fun getEntries(): List<NBTEntry<*>>

    /**
     * Gets an entry from this nbt compound by it's [key]
     */
    operator fun get(key: String): NBTEntry<*>?

    /**
     * Check if this nbt compound contains `NBTEntry` with key specified
     */
    operator fun contains(key: String): Boolean

    /**
     * @return This `NBTCompound` as printable NBT-Json
     */
    override fun toString(): String

    /**
     * Represents an entry of a [NBTCompound]
     */
    interface NBTEntry<TValue : Any> : Cloneable {
        /**
         * The key of this nbt entry
         */
        val key: String

        /**
         * The type for the value of this `NBTEntry`
         */
        val type: NBTType<*>

        /**
         * The value of this `NBTEntry`
         */
        val value: TValue

        /**
         * The source `NBTCompound` that create this entry
         */
        @set:InternalAPI
        var source: NBTCompound

        /**
         * Merge this nbt entry to another nbt compound
         */
        fun merge(nbtCompound: NBTCompound)

        override fun clone(): NBTEntry<TValue>
    }
}