@file:Suppress("UNCHECKED_CAST")

package kingmc.platform.nbt

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.text.Text
import kingmc.common.text.serializer.deserializeFromJsonToText
import kingmc.common.text.serializer.serializeFromTextToJson
import kingmc.platform.nbtFactory
import java.util.*

/**
 * Create and add data to a `NBTCompound`
 *
 * @param block block to configure a `NBTCompound`
 * @return `NBTCompound` created
 * @since 0.0.8
 * @author kingsthere
 */
@WithApplication
fun NBTCompound(block: MutableNBTCompound.() -> Unit): NBTCompound {
    return currentApplication().nbtFactory.createNBTCompound(block)
}
/**
 * Create an empty `NBTCompound`
 *
 * @return `NBTCompound` created
 * @since 0.0.8
 * @author kingsthere
 */
@WithApplication
fun NBTCompound(): NBTCompound {
    return currentApplication().nbtFactory.createNBTCompound()
}

/**
 * Create and add data to a `MutableNBTCompound`
 *
 * @param block block to configure a `NBTCompound`
 * @return `NBTCompound` created
 * @since 0.0.8
 * @author kingsthere
 */
@WithApplication
fun MutableNBTCompound(block: MutableNBTCompound.() -> Unit): MutableNBTCompound {
    return currentApplication().nbtFactory.createMutableNBTCompound(block)
}

/**
 * Create an empty `MutableNBTCompound`
 *
 * @return `NBTCompound` created
 * @since 0.0.8
 * @author kingsthere
 */
@WithApplication
fun MutableNBTCompound(): MutableNBTCompound {
    return currentApplication().nbtFactory.createMutableNBTCompound()
}

/**
 * Create a `NBTEntry`
 *
 * @param TValue the type of the value [NBTCompound.NBTEntry] stores
 * @param source the source `NBTCompound` created this entry
 * @param key the key of the entry
 * @param type the type of the entry
 * @param value the value of the entry
 * @return `NBTCompound` created
 * @since 0.0.8
 * @author kingsthere
 */
@WithApplication
fun <TValue : Any> NBTEntry(source: NBTCompound, key: String, type: NBTType<*>, value: TValue): NBTCompound.NBTEntry<TValue> {
    return currentApplication().nbtFactory.createNBTEntry(source, key, type, value)
}

/**
 * Create a `MutableNBTEntry`
 *
 * @param TValue the type of the value [NBTCompound.NBTEntry] stores
 * @param source the source `NBTCompound` created this entry
 * @param key the key of the entry
 * @param type the type of the entry
 * @param value the value of the entry
 * @return `NBTCompound` created
 * @since 0.0.8
 * @author kingsthere
 */
@WithApplication
fun <TValue : Any> MutableNBTEntry(source: NBTCompound, key: String, type: NBTType<*>, value: TValue): MutableNBTCompound.MutableNBTEntry<TValue> {
    return currentApplication().nbtFactory.createMutableNBTEntry(source, key, type, value)
}

/**
 * Convert to a `MutableNBTCompound` with same values in this `NBTCompound`
 *
 * @since 0.0.8
 * @author kingsthere
 */
fun NBTCompound.toMutableNBTCompound(): MutableNBTCompound {
    return MutableNBTCompound {
        this@toMutableNBTCompound.getEntries().forEach {
            add(it.toMutableNBTEntry())
        }
    }
}

/**
 * Convert to a `MutableNBTEntry` with same values in this `NBTEntry`
 *
 * @since 0.0.8
 * @author kingsthere
 */
fun <TValue : Any> NBTCompound.NBTEntry<TValue>.toMutableNBTEntry(): MutableNBTCompound.MutableNBTEntry<TValue> {
    return MutableNBTEntry(source, key, type, value)
}

/**
 * Merge contents from specified [nbtCompound] to receiver
 *
 * @receiver the receiver compound to merge into
 * @param nbtCompound `NBTCompound` to merge contents from
 */
fun MutableNBTCompound.merge(nbtCompound: NBTCompound) {
    nbtCompound.getEntries().forEach {
        this.add(it.toMutableNBTEntry())
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getByteEntry(key: String): NBTCompound.NBTEntry<Byte>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.BYTE) {
            entry as? NBTCompound.NBTEntry<Byte>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getShortEntry(key: String): NBTCompound.NBTEntry<Short>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.SHORT) {
            entry as? NBTCompound.NBTEntry<Short>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getIntEntry(key: String): NBTCompound.NBTEntry<Int>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.INT) {
            entry as? NBTCompound.NBTEntry<Int>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getLongEntry(key: String): NBTCompound.NBTEntry<Long>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.LONG) {
            entry as? NBTCompound.NBTEntry<Long>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getFloatEntry(key: String): NBTCompound.NBTEntry<Float>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.FLOAT) {
            entry as? NBTCompound.NBTEntry<Float>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getDoubleEntry(key: String): NBTCompound.NBTEntry<Double>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.DOUBLE) {
            entry as? NBTCompound.NBTEntry<Double>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getByteArrayEntry(key: String): NBTCompound.NBTEntry<ByteArray>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.BYTE_ARRAY) {
            entry as? NBTCompound.NBTEntry<ByteArray>
        } else {
            throw NBTEntryTypeException("Entry $entry is not a byte array entry")
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getIntArrayEntry(key: String): NBTCompound.NBTEntry<IntArray>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.INT_ARRAY) {
            entry as? NBTCompound.NBTEntry<IntArray>
        } else {
            throw NBTEntryTypeException("Entry $entry is not a integer array entry")
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getStringEntry(key: String): NBTCompound.NBTEntry<String>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.STRING) {
            entry as? NBTCompound.NBTEntry<String>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getUUIDEntry(key: String): NBTCompound.NBTEntry<UUID>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.STRING || entry.type == NBTType.INT_ARRAY) {
            entry as? NBTCompound.NBTEntry<UUID>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun <TValue : Any> NBTCompound.getListEntry(key: String): NBTCompound.NBTEntry<List<TValue>>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.LIST) {
            entry as? NBTCompound.NBTEntry<List<TValue>>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getStringListEntry(key: String): NBTCompound.NBTEntry<List<String>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getIntegerListEntry(key: String): NBTCompound.NBTEntry<List<Int>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getIntArrayListEntry(key: String): NBTCompound.NBTEntry<List<IntArray>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getUUIDListEntry(key: String): NBTCompound.NBTEntry<List<UUID>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getFloatListEntry(key: String): NBTCompound.NBTEntry<List<Float>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getDoubleListEntry(key: String): NBTCompound.NBTEntry<List<Double>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getLongListEntry(key: String): NBTCompound.NBTEntry<List<Long>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getCompoundListEntry(key: String): NBTCompound.NBTEntry<List<NBTCompound>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun NBTCompound.getCompoundEntry(key: String): NBTCompound.NBTEntry<NBTCompound>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.COMPOUND) {
            entry as? NBTCompound.NBTEntry<NBTCompound>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound or create a new compound
 */
fun NBTCompound.getOrCreateCompoundEntry(key: String): NBTCompound.NBTEntry<NBTCompound> {
    val entry = this[key]
    if (entry != null) {
        if (entry.type == NBTType.END) {
            return NBTEntry(this, key, NBTType.COMPOUND, NBTCompound())
        }
        return if (entry.type == NBTType.COMPOUND) {
            entry as NBTCompound.NBTEntry<NBTCompound>
        } else {
            NBTEntry(this, key, NBTType.COMPOUND, NBTCompound())
        }
    } else {
        return NBTEntry(this, key, NBTType.COMPOUND, NBTCompound())
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getByteEntry(key: String): MutableNBTCompound.MutableNBTEntry<Byte>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.BYTE) {
            entry as? MutableNBTCompound.MutableNBTEntry<Byte>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getShortEntry(key: String): MutableNBTCompound.MutableNBTEntry<Short>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.SHORT) {
            entry as? MutableNBTCompound.MutableNBTEntry<Short>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getIntEntry(key: String): MutableNBTCompound.MutableNBTEntry<Int>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.INT) {
            entry as? MutableNBTCompound.MutableNBTEntry<Int>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getLongEntry(key: String): MutableNBTCompound.MutableNBTEntry<Long>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.LONG) {
            entry as? MutableNBTCompound.MutableNBTEntry<Long>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getFloatEntry(key: String): MutableNBTCompound.MutableNBTEntry<Float>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.FLOAT) {
            entry as? MutableNBTCompound.MutableNBTEntry<Float>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getDoubleEntry(key: String): MutableNBTCompound.MutableNBTEntry<Double>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.DOUBLE) {
            entry as? MutableNBTCompound.MutableNBTEntry<Double>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getByteArrayEntry(key: String): MutableNBTCompound.MutableNBTEntry<ByteArray>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.BYTE_ARRAY) {
            entry as? MutableNBTCompound.MutableNBTEntry<ByteArray>
        } else {
            throw NBTEntryTypeException("Entry $entry is not a byte array entry")
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getIntArrayEntry(key: String): MutableNBTCompound.MutableNBTEntry<IntArray>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.INT_ARRAY) {
            entry as? MutableNBTCompound.MutableNBTEntry<IntArray>
        } else {
            throw NBTEntryTypeException("Entry $entry is not a integer array entry")
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getStringEntry(key: String): MutableNBTCompound.MutableNBTEntry<String>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.STRING) {
            entry as? MutableNBTCompound.MutableNBTEntry<String>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getUUIDEntry(key: String): MutableNBTCompound.MutableNBTEntry<UUID>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.STRING || entry.type == NBTType.INT_ARRAY) {
            entry as? MutableNBTCompound.MutableNBTEntry<UUID>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun <TValue : Any> MutableNBTCompound.getListEntry(key: String): MutableNBTCompound.MutableNBTEntry<List<TValue>>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.LIST) {
            entry as? MutableNBTCompound.MutableNBTEntry<List<TValue>>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getStringListEntry(key: String): MutableNBTCompound.MutableNBTEntry<List<String>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getIntegerListEntry(key: String): MutableNBTCompound.MutableNBTEntry<List<Int>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getIntArrayListEntry(key: String): MutableNBTCompound.MutableNBTEntry<List<IntArray>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getUUIDListEntry(key: String): MutableNBTCompound.MutableNBTEntry<List<UUID>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getFloatListEntry(key: String): MutableNBTCompound.MutableNBTEntry<List<Float>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getDoubleListEntry(key: String): MutableNBTCompound.MutableNBTEntry<List<Double>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getLongListEntry(key: String): MutableNBTCompound.MutableNBTEntry<List<Long>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getCompoundListEntry(key: String): MutableNBTCompound.MutableNBTEntry<List<MutableNBTCompound>>? = this.getListEntry(key)

/**
 * Gets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.getCompoundEntry(key: String): MutableNBTCompound.MutableNBTEntry<MutableNBTCompound>? {
    val entry = this[key]
    return if (entry != null) {
        if (entry.type == NBTType.COMPOUND) {
            entry as? MutableNBTCompound.MutableNBTEntry<MutableNBTCompound>
        } else {
            null
        }
    } else {
        null
    }
}

/**
 * Gets a nbt entry from this nbt compound or create a new compound
 */
fun MutableNBTCompound.getOrCreateCompoundEntry(key: String): MutableNBTCompound.MutableNBTEntry<MutableNBTCompound> {
    val entry = this[key]
    if (entry != null) {
        if (entry.type == NBTType.END) {
            return MutableNBTEntry(this, key, NBTType.COMPOUND, MutableNBTCompound())
        }
        return if (entry.type == NBTType.COMPOUND) {
            entry as MutableNBTCompound.MutableNBTEntry<MutableNBTCompound>
        } else {
            MutableNBTEntry(this, key, NBTType.COMPOUND, MutableNBTCompound())
        }
    } else {
        return MutableNBTEntry(this, key, NBTType.COMPOUND, MutableNBTCompound())
    }
}

/**
 * Gets a `Byte` value from this nbt compound
 */
fun NBTCompound.getByte(key: String): Byte? = this.getByteEntry(key)?.value

/**
 * Gets a `Boolean` value from this nbt compound
 */
fun NBTCompound.getBoolean(key: String): Boolean? {
    val byteValue = this.getByteEntry(key)?.value
    return if (byteValue != null) {
        byteValue != 0.toByte()
    } else {
        null
    }
}

/**
 * Gets a `Short` value from this nbt compound
 */
fun NBTCompound.getShort(key: String): Short? = this.getShortEntry(key)?.value

/**
 * Gets a `Int` value from this nbt compound
 */
fun NBTCompound.getInt(key: String): Int? = this.getIntEntry(key)?.value

/**
 * Gets a `Long` value from this nbt compound
 */
fun NBTCompound.getLong(key: String): Long? = this.getLongEntry(key)?.value

/**
 * Gets a `Float` value from this nbt compound
 */
fun NBTCompound.getFloat(key: String): Float? = this.getFloatEntry(key)?.value

/**
 * Gets a `Double` value from this nbt compound
 */
fun NBTCompound.getDouble(key: String): Double? = this.getDoubleEntry(key)?.value

/**
 * Gets a `ByteArray` value from this nbt compound
 */
fun NBTCompound.getByteArray(key: String): ByteArray? = this.getByteArrayEntry(key)?.value

/**
 * Gets a `IntArray` value from this nbt compound
 */
fun NBTCompound.getIntArray(key: String): IntArray? = this.getIntArrayEntry(key)?.value

/**
 * Gets a `String` value from this nbt compound
 */
fun NBTCompound.getString(key: String): String? = this.getStringEntry(key)?.value

/**
 * Gets a `UUID` value from this nbt compound
 */
fun NBTCompound.getUUID(key: String): UUID? = this.getUUIDEntry(key)?.value

/**
 * Gets a `List` value from this nbt compound
 */
fun <TValue : Any> NBTCompound.getList(key: String): List<TValue>? = this.getListEntry<TValue>(key)?.value

/**
 * Gets a `List` value from this nbt compound
 */
fun NBTCompound.getStringList(key: String): List<String>? = this.getListEntry<String>(key)?.value

/**
 * Gets a `List` value from this nbt compound
 */
fun NBTCompound.getIntegerList(key: String): List<Int>? = this.getListEntry<Int>(key)?.value

/**
 * Gets a `List` value from this nbt compound
 */
fun NBTCompound.getIntArrayList(key: String): List<IntArray>? = this.getListEntry<IntArray>(key)?.value

/**
 * Gets a `List` value from this nbt compound
 */
fun NBTCompound.getUUIDList(key: String): List<UUID>? = this.getListEntry<UUID>(key)?.value

/**
 * Gets a `List` value from this nbt compound
 */
fun NBTCompound.getFloatList(key: String): List<Float>? = this.getListEntry<Float>(key)?.value

/**
 * Gets a `List` value from this nbt compound
 */
fun NBTCompound.getDoubleList(key: String): List<Double> ? = this.getListEntry<Double>(key)?.value

/**
 * Gets a `List` value from this nbt compound
 */
fun NBTCompound.getLongList(key: String): List<Long>? = this.getListEntry<Long>(key)?.value

/**
 * Gets a `List` value from this nbt compound
 */
fun NBTCompound.getCompoundList(key: String): List<NBTCompound>? = this.getListEntry<NBTCompound>(key)?.value

/**
 * Gets a `NBTCompound` value from this nbt compound
 */
fun NBTCompound.getCompound(key: String): NBTCompound? = this.getCompoundEntry(key)?.value

/**
 * Gets a `NBTCompound` value from this nbt compound, or create a new `NBTCompound`
 */
fun NBTCompound.getOrCreateCompound(key: String): NBTCompound = this.getOrCreateCompoundEntry(key).value

/**
 * Gets a `MutableNBTCompound` value from this mutable nbt compound
 */
fun MutableNBTCompound.getCompound(key: String): MutableNBTCompound? = this.getCompoundEntry(key)?.value

/**
 * Gets a `NBTCompound` value from this nbt compound, or create a new `NBTCompound`
 */
fun MutableNBTCompound.getOrCreateCompound(key: String): MutableNBTCompound = this.getOrCreateCompoundEntry(key).value

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setByte(key: String, value: Byte) {
    add(MutableNBTEntry(this, key, NBTType.BYTE, value))
}

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setBoolean(key: String, value: Boolean) {
    if (value) {
        add(MutableNBTEntry(this, key, NBTType.BYTE, 1))
    } else {
        add(MutableNBTEntry(this, key, NBTType.BYTE, 0))
    }
}

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setShort(key: String, value: Short) {
    add(MutableNBTEntry(this, key, NBTType.SHORT, value))
}

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setInt(key: String, value: Int) {
    add(MutableNBTEntry(this, key, NBTType.INT, value))
}

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setLong(key: String, value: Long) {
    add(MutableNBTEntry(this, key, NBTType.LONG, value))
}

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setFloat(key: String, value: Float) {
    add(MutableNBTEntry(this, key, NBTType.FLOAT, value))
}

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setDouble(key: String, value: Double) {
    add(MutableNBTEntry(this, key, NBTType.DOUBLE, value))
}

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setByteArray(key: String, value: ByteArray) {
    add(MutableNBTEntry(this, key, NBTType.BYTE_ARRAY, value))
}

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setIntArray(key: String, value: IntArray) {
    add(MutableNBTEntry(this, key, NBTType.INT_ARRAY, value))
}

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setString(key: String, value: String) {
    add(MutableNBTEntry(this, key, NBTType.STRING, value))
}

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setUUID(key: String, value: UUID) {
    add(MutableNBTEntry(this, key, NBTType.STRING, value))
}

/**
 * Sets a nbt entry from this nbt compound
 */
fun <TValue : Any> MutableNBTCompound.setList(key: String, value: List<TValue>) {
    add(MutableNBTEntry(this, key, NBTType.LIST, value))
}

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setStringList(key: String, value: List<String>) = this.setList(key, value)

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setIntegerList(key: String, value: List<Int>) = this.setList(key, value)

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setIntArrayList(key: String, value: List<IntArray>) = this.setList(key, value)

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setUUIDList(key: String, value: List<UUID>) = this.setList(key, value)

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setFloatList(key: String, value: List<Float>) = this.setList(key, value)

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setDoubleList(key: String, value: List<Double>) = this.setList(key, value)

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setLongList(key: String, value: List<Long>) = this.setList(key, value)

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setCompoundList(key: String, value: List<MutableNBTCompound>) = this.setList(key, value)

/**
 * Sets a nbt entry from this nbt compound
 */
fun MutableNBTCompound.setCompound(key: String, value: MutableNBTCompound) {
    add(MutableNBTEntry(this, key, NBTType.COMPOUND, value))
}

/**
 * Gets the value of this string nbt entry as an `Enum` [TEnum]
 */
inline fun <reified TEnum : Enum<TEnum>> NBTCompound.NBTEntry<String>.getEnum(): TEnum {
    return java.lang.Enum.valueOf(TEnum::class.java, value)
}

/**
 * Gets the value of this string nbt entry as an `Enum` [TEnum]
 */
fun <TEnum : Enum<TEnum>> MutableNBTCompound.MutableNBTEntry<String>.getEnum(enum: TEnum) {
    value = enum.name
}

/**
 * Gets the value of this string nbt entry and deserialize it as a json to a `Text`
 */
fun NBTCompound.getTextForJson(key: String): Text? =
    getStringEntry(key)?.value?.deserializeFromJsonToText()

/**
 * Gets the value of this list string nbt entry and deserialize it as a json to a `Text`
 */
fun NBTCompound.getTextListForJson(key: String): List<Text>? =
    getStringListEntry(key)?.value?.map { it.deserializeFromJsonToText() }

/**
 * Gets the value of this string nbt entry and deserialize it as a legacy string to a `Text`
 */
fun NBTCompound.getTextForLegacy(key: String): Text? =
    getStringEntry(key)?.value?.deserializeFromJsonToText()

/**
 * Gets the value of this string nbt entry and deserialize it as a legacy string to a `Text`
 */
fun NBTCompound.getTextListForLegacy(key: String): List<Text>? =
    getStringListEntry(key)?.value?.map { it.deserializeFromJsonToText() }

/**
 * Sets the value of this string nbt entry
 */
fun MutableNBTCompound.setTextForJson(key: String, text: Text) {
    setString(key, text.serializeFromTextToJson())
}

/**
 * Sets the value of this string nbt list entry
 */
fun MutableNBTCompound.setTextListForJson(key: String, texts: List<Text>) {
    setStringList(key, texts.map { it.serializeFromTextToJson() })
}

/**
 * Sets the value of this string nbt entry
 */
fun MutableNBTCompound.setTextForLegacy(key: String, text: Text)  {
    setString(key, text.serializeFromTextToJson())
}

/**
 * Sets the value of this string nbt list entry
 */
fun MutableNBTCompound.setTextListForLegacy(key: String, texts: List<Text>) {
    setStringList(key, texts.map { it.serializeFromTextToJson() })
}