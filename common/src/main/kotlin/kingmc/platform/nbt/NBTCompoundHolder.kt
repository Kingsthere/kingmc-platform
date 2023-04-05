package kingmc.platform.nbt

/**
 * A superinterface marking subclasses that could hold a [NBTCompound] data
 *
 * @since 0.0.5
 * @author kingsthere
 */
interface NBTCompoundHolder {
    /**
     * Gets the `NBTCompound` of this object
     */
    val nbt: NBTCompound
}