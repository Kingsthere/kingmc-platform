package kingmc.platform.nbt

/**
 * A superinterface marking subclasses that could hold a [MutableNBTCompound] data
 *
 * @author kingsthere
 * @since 0.0.5
 */
interface MutableNBTCompoundHolder : NBTCompoundHolder {
    /**
     * Gets the `MutableNBTCompound` of this object
     */
    override val nbt: MutableNBTCompound
}