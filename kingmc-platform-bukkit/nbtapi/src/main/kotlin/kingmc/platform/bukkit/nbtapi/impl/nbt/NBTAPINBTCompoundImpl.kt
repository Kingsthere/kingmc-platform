package kingmc.platform.bukkit.nbtapi.impl.nbt

import de.tr7zw.changeme.nbtapi.NBTList
import de.tr7zw.changeme.nbtapi.NBTReflectionUtil
import kingmc.platform.bukkit.nbtapi.*
import kingmc.platform.bukkit.nbtapi.nbt.NBTAPINBTCompound
import kingmc.platform.nbt.MutableNBTCompound
import kingmc.platform.nbt.NBTCompound
import kingmc.platform.nbt.NBTType
import kotlin.reflect.KClass

/**
 * nbt-api `NBTCompound` implementation
 *
 * @author kingsthere
 * @since 0.0.8
 */
open class NBTAPINBTCompoundImpl(protected val sourceNBTCompound: _NBTAPINBTCompound = _NBTAPINBTContainer()) :
    NBTAPINBTCompound {
    /**
     * Convert this `NBTCompound` to a [de.tr7zw.changeme.nbtapi.NBTCompound]
     */
    override fun toNBTAPINBTCompound(): _NBTAPINBTCompound {
        return sourceNBTCompound
    }

    /**
     * The name of this nbt compound
     */
    override val name: String
        get() = sourceNBTCompound.name

    /**
     * The parent of this nbt compound
     */
    override val parent: NBTCompound?
        get() = sourceNBTCompound.parent?.let { NBTAPINBTCompoundImpl(it) }

    /**
     * The size of entries in this nbt compound
     */
    override val size: Int
        get() = sourceNBTCompound.keys.size

    /**
     * Gets all entries in this nbt compound
     */
    override fun getEntries(): List<NBTCompound.NBTEntry<*>> = buildList {
        sourceNBTCompound.keys.forEach {
            add(get(it)!!)
        }
    }

    /**
     * Gets an entry from this nbt compound by it's [key]
     */
    override fun get(key: String): NBTCompound.NBTEntry<*>? = createNBTEntry(this, sourceNBTCompound, key, null)

    /**
     * Check if this nbt compound contains `NBTEntry` with key specified
     */
    override fun contains(key: String): Boolean {
        return sourceNBTCompound.hasTag(key)
    }

    /**
     * @return This `NBTCompound` as printable NBT-Json
     */
    override fun toString(): String {
        return sourceNBTCompound.toString()
    }

    class NBTEntryImpl<TValue : Any>(override val key: String,
                                     override val type: NBTType<TValue>,
                                     private var _nbtCompoundParent: _NBTAPINBTCompound,
                                     override var source: NBTCompound
    ) : NBTCompound.NBTEntry<TValue> {
        /**
         * The value of this `NBTEntry`
         */
        override val value: TValue
            get() = _nbtCompoundParent.getOrNull(key, type.clazz.java)

        /**
         * Merge this nbt entry to another nbt compound
         */
        override fun merge(nbtCompound: NBTCompound) {
            merge((nbtCompound as NBTAPINBTCompound).toNBTAPINBTCompound())
            this.source = nbtCompound
        }

        /**
         * Merge this entry to another [nbtCompound]
         */
        fun merge(nbtCompound: _NBTAPINBTCompound) {
            _nbtCompoundParent = nbtCompound
            this.refresh()
        }

        private fun refresh() {
            when (type) {
                NBTType.BYTE -> {
                    _nbtCompoundParent.setByte(key, value as Byte)
                }
                NBTType.SHORT -> {
                    _nbtCompoundParent.setShort(key, value as Short)
                }
                NBTType.INT -> {
                    _nbtCompoundParent.setInteger(key, value as Int)
                }
                NBTType.LONG -> {
                    _nbtCompoundParent.setLong(key, value as Long)
                }
                NBTType.FLOAT -> {
                    _nbtCompoundParent.setFloat(key, value as Float)
                }
                NBTType.DOUBLE -> {
                    _nbtCompoundParent.setDouble(key, value as Double)
                }
                NBTType.BYTE_ARRAY -> {
                    _nbtCompoundParent.setByteArray(key, value as ByteArray)
                }
                NBTType.INT_ARRAY -> {
                    _nbtCompoundParent.setIntArray(key, value as IntArray)
                }
                NBTType.STRING -> {
                    _nbtCompoundParent.setString(key, value as String)
                }
            }
        }

        override fun clone(): NBTCompound.NBTEntry<TValue> {
            return NBTEntryImpl(key, type, _nbtCompoundParent, source)
        }
    }

    class NBTEntryCompoundImpl(override val key: String,
                               private var _nbtCompoundParent: _NBTAPINBTCompound, override var source: NBTCompound
    ) : NBTCompound.NBTEntry<NBTCompound> {
        /**
         * The type for the value of this `NBTEntry`
         */
        override val type: NBTType<*>
            get() = NBTType.COMPOUND

        /**
         * The value of this `NBTEntry`
         */
        override val value: NBTCompound
            get() = NBTAPINBTCompoundImpl(_nbtCompoundParent.getCompound(key))

        /**
         * Merge this nbt entry to another nbt compound
         */
        override fun merge(nbtCompound: NBTCompound) {
            merge((nbtCompound as NBTAPINBTCompound).toNBTAPINBTCompound())
            this.source = nbtCompound
        }

        /**
         * Merge this entry to another [nbtCompound]
         */
        fun merge(nbtCompound: _NBTAPINBTCompound) {
            _nbtCompoundParent = nbtCompound
            this.refresh()
        }

        private fun refresh() {
            _nbtCompoundParent.getOrCreateCompound(key).mergeCompound((value as NBTAPINBTCompound).toNBTAPINBTCompound())
        }

        override fun clone(): NBTCompound.NBTEntry<NBTCompound> {
            return NBTEntryCompoundImpl(key, _nbtCompoundParent, source)
        }
    }

    class NBTEntryListImpl<TValue : Any>(override val key: String,
                                         private var _nbtCompoundParent: _NBTAPINBTCompound,
                                         private val _listElementClass: KClass<*>,
                                         private val _listElementType: _NBTAPINBTType, override var source: NBTCompound
    ) : NBTCompound.NBTEntry<List<TValue>> {
        /**
         * The type for the value of this `NBTEntry`
         */
        override val type: NBTType<List<*>>
            get() = NBTType.LIST

        /**
         * The value of this `NBTEntry`
         */
        @Suppress("UNCHECKED_CAST")
        override val value: List<TValue>
            get() {
                if (_listElementType == _NBTAPINBTType.NBTTagCompound) {
                    return _nbtCompoundParent.getCompoundList(key).map { NBTAPINBTCompoundImpl(it as _NBTAPINBTCompound) } as List<TValue>
                }
                return NBTReflectionUtil.getList(_nbtCompoundParent, key, _listElementType, _listElementClass.java) as List<TValue>
            }

        /**
         * Merge this nbt entry to another nbt compound
         */
        override fun merge(nbtCompound: NBTCompound) {
            merge((nbtCompound as NBTAPINBTCompound).toNBTAPINBTCompound())
            this.source = nbtCompound
        }

        /**
         * Merge this entry to another [nbtCompound]
         */
        fun merge(nbtCompound: _NBTAPINBTCompound) {
            _nbtCompoundParent = nbtCompound
        }

        @Suppress("UNCHECKED_CAST")
        private fun getNBTList(): NBTList<Any> {
            if (_listElementType == _NBTAPINBTType.NBTTagCompound) {
                return _nbtCompoundParent.getCompoundList(key) as NBTList<Any>
            }
            return NBTReflectionUtil.getList(_nbtCompoundParent, key, _listElementType, _listElementClass.java) as NBTList<Any>
        }

        private fun refresh() {
            val list = getNBTList()
            list.clear()
            list.addAll(value)
        }

        override fun clone(): NBTCompound.NBTEntry<List<TValue>> {
            return NBTEntryListImpl(key, _nbtCompoundParent, _listElementClass, _listElementType, source)
        }
    }

    companion object {
        fun createNBTEntry(source: NBTCompound, nbtapiSource: _NBTAPINBTCompound, key: String, presentNBTType: NBTType<*>?): NBTCompound.NBTEntry<*> {
            val type: NBTType<out Any> = presentNBTType ?: nbtapiSource.getType(key)?.asKingMC() ?: NBTType.END
            if (type == NBTType.END) {
                return NBTEntryImpl(key, NBTType.END, nbtapiSource, source)
            }
            if (type == NBTType.LIST) {
                val rawListType = nbtapiSource.getListType(key)
                val listType = nbtapiSource.getListType(key).asKingMC()

                return NBTEntryListImpl<Any>(
                    key,
                    nbtapiSource,
                    listType.clazz,
                    rawListType,
                    source
                )
            }
            if (type == NBTType.COMPOUND) {
                return NBTEntryCompoundImpl(key, nbtapiSource, source)
            }
            return NBTEntryImpl(key, type, nbtapiSource, source)
        }

        @Suppress("UNCHECKED_CAST")
        fun createNBTEntry(source: NBTCompound, nbtapiSource: _NBTAPINBTCompound, key: String, value: Any, presentNBTType: NBTType<*>?): NBTCompound.NBTEntry<*> {
            return (createNBTEntry(
                source,
                nbtapiSource,
                key,
                presentNBTType
            ) as MutableNBTCompound.MutableNBTEntry<Any>).apply {
                this@apply.value = value
            }
        }
    }
}