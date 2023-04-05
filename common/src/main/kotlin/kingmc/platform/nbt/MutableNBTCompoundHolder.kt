package kingmc.platform.nbt

/**
 * A superinterface marking subclasses that could hold a [MutableNBTCompound] data
 *
 * @since 0.0.5
 * @author kingsthere
 */
interface MutableNBTCompoundHolder : NBTCompoundHolder {
    /**
     * Gets the `MutableNBTCompound` of this object
     */
    override val nbt: MutableNBTCompound
}