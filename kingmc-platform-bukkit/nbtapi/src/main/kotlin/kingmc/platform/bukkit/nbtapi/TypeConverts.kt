package kingmc.platform.bukkit.nbtapi

import kingmc.platform.nbt.NBTType

fun _NBTAPINBTType.asKingMC(): NBTType<out Any> {
    return when (this) {
        _NBTAPINBTType.NBTTagByte -> NBTType.BYTE
        _NBTAPINBTType.NBTTagShort -> NBTType.SHORT
        _NBTAPINBTType.NBTTagInt -> NBTType.INT
        _NBTAPINBTType.NBTTagLong -> NBTType.LONG
        _NBTAPINBTType.NBTTagFloat -> NBTType.FLOAT
        _NBTAPINBTType.NBTTagDouble -> NBTType.DOUBLE
        _NBTAPINBTType.NBTTagByteArray -> NBTType.BYTE_ARRAY
        _NBTAPINBTType.NBTTagIntArray -> NBTType.INT_ARRAY
        _NBTAPINBTType.NBTTagString -> NBTType.STRING
        _NBTAPINBTType.NBTTagList -> NBTType.LIST
        _NBTAPINBTType.NBTTagCompound -> NBTType.COMPOUND
        _NBTAPINBTType.NBTTagEnd -> NBTType.END
        else -> throw IllegalArgumentException("Unsupported nbt type")
    }
}