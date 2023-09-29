package kingmc.platform.nbt

import kotlin.reflect.KClass

inline fun <reified TType : Any> NBTType(): NBTType<TType> {
    return NBTType(TType::class)
}

/**
 * Enum of all NBT Types Minecraft contains
 */
class NBTType<TType : Any>(val clazz: KClass<out TType>) {

    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "NBTType(clazz=$clazz)"
    }

    companion object {
        val BYTE : NBTType<Byte> = NBTType()
        val SHORT : NBTType<Short> = NBTType()
        val INT : NBTType<Int> = NBTType()
        val LONG : NBTType<Long> = NBTType()
        val FLOAT : NBTType<Float> = NBTType()
        val DOUBLE : NBTType<Double> = NBTType()
        val BYTE_ARRAY : NBTType<ByteArray> = NBTType()
        val INT_ARRAY : NBTType<IntArray> = NBTType()
        val STRING : NBTType<String> = NBTType()
        val LIST : NBTType<List<*>> = NBTType()
        val COMPOUND : NBTType<NBTCompound> = NBTType()
        val END : NBTType<Unit> = NBTType()

        fun values(): Array<NBTType<*>> {
            return arrayOf(
                BYTE,
                SHORT,
                INT,
                LONG,
                FLOAT,
                DOUBLE,
                BYTE_ARRAY,
                INT_ARRAY,
                STRING,
                LIST,
                COMPOUND
            )
        }

        fun valueOf(value: String): NBTType<*> {
            return when (value) {
                "BYTE" -> BYTE
                "SHORT" -> SHORT
                "INT" -> INT
                "LONG" -> LONG
                "FLOAT" -> FLOAT
                "DOUBLE" -> DOUBLE
                "BYTE_ARRAY" -> BYTE_ARRAY
                "INT_ARRAY" -> INT_ARRAY
                "STRING" -> STRING
                "LIST" -> LIST
                "COMPOUND" -> COMPOUND
                else -> throw IllegalArgumentException("No object kingmc.platform.nbt.NBTType.$value")
            }
        }
    }
}