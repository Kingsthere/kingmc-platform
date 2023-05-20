package kingmc.platform.nbt

import kingmc.common.context.annotation.Component

/**
 * A superinterface responsible to create `NBTCompound`(s)
 *
 * @since 0.0.8
 * @author kingsthere
 */
@Component("nbtFactory")
interface NBTFactory {
    /**
     * Create a new nbt compound
     *
     * @return the `NBTCompound` created
     */
    fun createNBTCompound(): NBTCompound

    /**
     * Create and configure a nbt compound
     *
     * @param block the block to configure the `NBTCompound` created
     * @return the `NBTCompound` created
     */
    fun createNBTCompound(block: MutableNBTCompound.() -> Unit): NBTCompound

    /**
     * Create a new **mutable** nbt compound
     *
     * @return the `NBTCompound` created
     */
    fun createMutableNBTCompound(): MutableNBTCompound

    /**
     * Create and configure a **mutable** nbt compound
     *
     * @param block the block to configure the `NBTCompound` created
     * @return the `NBTCompound` created
     */
    fun createMutableNBTCompound(block: MutableNBTCompound.() -> Unit): MutableNBTCompound

    /**
     * Create a new nbt entry
     *
     * @param TValue the type of the value [NBTCompound.NBTEntry] stores
     * @param source the source `NBTCompound` created this entry
     * @param key the key of the entry
     * @param type the type of the entry
     * @param value the value of the entry
     * @return the `NBTEntry` created
     */
    fun <TValue : Any> createNBTEntry(source: NBTCompound, key: String, type: NBTType<*>, value: TValue): NBTCompound.NBTEntry<TValue>

    /**
     * Create a new mutable nbt entry
     *
     * @param TValue the type of the value [MutableNBTCompound.MutableNBTEntry] stores
     * @param source the source `NBTCompound` created this entry
     * @param key the key of the entry
     * @param type the type of the entry
     * @param value the value of the entry
     * @return the `NBTEntry` created
     */
    fun <TValue : Any> createMutableNBTEntry(source: NBTCompound, key: String, type: NBTType<*>, value: TValue): MutableNBTCompound.MutableNBTEntry<TValue>
}