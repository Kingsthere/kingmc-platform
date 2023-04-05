package kingmc.platform.bukkit.nbtapi.impl.nbt

import de.tr7zw.changeme.nbtapi.NBTList
import de.tr7zw.changeme.nbtapi.NBTReflectionUtil
import kingmc.platform.bukkit.nbtapi._NBTAPINBTCompound
import kingmc.platform.bukkit.nbtapi._NBTAPINBTContainer
import kingmc.platform.bukkit.nbtapi._NBTAPINBTType
import kingmc.platform.bukkit.nbtapi.asKingMC
import kingmc.platform.bukkit.nbtapi.nbt.NBTAPINBTCompound
import kingmc.platform.nbt.MutableNBTCompound
import kingmc.platform.nbt.NBTCompound
import kingmc.platform.nbt.NBTType
import kotlin.reflect.KClass

/**
 * nbt-api `NBTCompound` implementation
 *
 * @since 0.0.8
 * @author kingsthere
 */
open class NBTAPIMutableNBTCompoundImpl(nbtCompound: _NBTAPINBTCompound = _NBTAPINBTContainer()) : NBTAPINBTCompoundImpl(nbtCompound), MutableNBTCompound {
    /**
     * Gets all entries in this nbt compound
     */
    override fun getEntries(): List<MutableNBTCompound.MutableNBTEntry<*>> = buildList {
        sourceNBTCompound.keys.forEach {
            add(get(it)!!)
        }
    }

    override fun get(key: String): MutableNBTCompound.MutableNBTEntry<*>? {
        return getNBTEntry(this, sourceNBTCompound, key)
    }

    /**
     * Remove an entry in this nbt compound
     */
    override fun remove(key: String) {
        sourceNBTCompound.removeKey(key)
    }

    class MutableNBTEntryImpl<TValue : Any>(override val key: String,
                                            override val type: NBTType<TValue>,
                                            private var _nbtCompoundParent: _NBTAPINBTCompound,
                                            override var source: NBTCompound
    ) : MutableNBTCompound.MutableNBTEntry<TValue> {
        /**
         * The value of this `NBTEntry`
         */
        override var value: TValue
            get() = _nbtCompoundParent.getOrNull(key, type.clazz.java)
            set(value) {
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
            value = value
        }

        override fun clone(): NBTCompound.NBTEntry<TValue> {
            return MutableNBTEntryImpl(key, type, _nbtCompoundParent, source)
        }

    }

    class MutableNBTEntryCompoundImpl(override val key: String,
                                      private var _nbtCompoundParent: _NBTAPINBTCompound,
                                      override var source: NBTCompound
    ) :
        MutableNBTCompound.MutableNBTEntry<NBTCompound> {
        /**
         * The type for the value of this `NBTEntry`
         */
        override val type: NBTType<*>
            get() = NBTType.COMPOUND

        /**
         * The value of this `NBTEntry`
         */
        override var value: NBTCompound
            get() = NBTAPINBTCompoundImpl(_nbtCompoundParent.getCompound(key))
            set(value) {
                _nbtCompoundParent.getOrCreateCompound(key).mergeCompound((value as NBTAPINBTCompound).toNBTAPINBTCompound())
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
            value = value
        }

        override fun clone(): NBTCompound.NBTEntry<NBTCompound> {
            return MutableNBTEntryCompoundImpl(key, _nbtCompoundParent, source)
        }

    }

    class MutableNBTEntryListImpl<TValue : Any>(override val key: String,
                                                private var _nbtCompoundParent: _NBTAPINBTCompound,
                                                private val _listElementClass: KClass<*>,
                                                private val _listElementType: _NBTAPINBTType,
                                                override var source: NBTCompound
    ) : MutableNBTCompound.MutableNBTEntry<List<TValue>> {
        /**
         * The type for the value of this `NBTEntry`
         */
        override val type: NBTType<List<*>>
            get() = NBTType.LIST

        /**
         * The value of this `NBTEntry`
         */
        @Suppress("UNCHECKED_CAST")
        override var value: List<TValue>
            get() {
                if (_listElementType == _NBTAPINBTType.NBTTagCompound) {
                    return _nbtCompoundParent.getCompoundList(key).map { NBTAPINBTCompoundImpl(it as _NBTAPINBTCompound) } as List<TValue>
                }
                return NBTReflectionUtil.getList(_nbtCompoundParent, key, _listElementType, _listElementClass.java) as List<TValue>
            }
            set(value) {
                val list = getNBTList()
                list.clear()
                list.addAll(value)
            }

        @Suppress("UNCHECKED_CAST")
        private fun getNBTList(): NBTList<Any> {
            if (_listElementType == _NBTAPINBTType.NBTTagCompound) {
                return _nbtCompoundParent.getCompoundList(key) as NBTList<Any>
            }
            return NBTReflectionUtil.getList(_nbtCompoundParent, key, _listElementType, _listElementClass.java) as NBTList<Any>
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
            value = value
        }

        override fun clone(): NBTCompound.NBTEntry<List<TValue>> {
            return MutableNBTEntryListImpl(key, _nbtCompoundParent, _listElementClass, _listElementType, source)
        }
    }

    companion object {
        fun getNBTEntry(source: NBTCompound, nbtapiSource: _NBTAPINBTCompound, key: String): MutableNBTCompound.MutableNBTEntry<*>? {
            val type: NBTType<out Any> = nbtapiSource.getType(key)?.asKingMC() ?: return null
            if (type == NBTType.END) {
                return null
            }
            if (type == NBTType.LIST) {
                val rawListType = nbtapiSource.getListType(key)
                val listType = nbtapiSource.getListType(key).asKingMC()

                return NBTReflectionUtil.getList(nbtapiSource, key, rawListType, listType.clazz.java)?.let {
                    MutableNBTEntryListImpl<Any>(key, nbtapiSource, listType.clazz, rawListType, source)
                }
            }
            if (type == NBTType.COMPOUND) {
                return MutableNBTEntryCompoundImpl(key, nbtapiSource, source)
            }
            return nbtapiSource.getOrNull<Any>(key, type.clazz.java)?.let { MutableNBTEntryImpl(key, type, nbtapiSource, source) }
        }

        fun getOrCreateNBTEntry(source: NBTCompound, nbtapiSource: _NBTAPINBTCompound, key: String, type: NBTType<*>, value: Any): MutableNBTCompound.MutableNBTEntry<*> {
            val nbtEntry = getNBTEntry(source, nbtapiSource, key)
            if (nbtEntry != null) {
                return nbtEntry
            }
            return NBTAPIVirtualMutableNBTEntry(source, key, type, value)
        }

        @Suppress("UNCHECKED_CAST")
        fun getNBTEntry(source: NBTCompound, nbtapiSource: _NBTAPINBTCompound, key: String, value: Any): NBTCompound.NBTEntry<*>? {
            return (getNBTEntry(source, nbtapiSource, key) as? MutableNBTCompound.MutableNBTEntry<Any>)?.apply {
                this@apply.value = value
            }
        }
    }
}