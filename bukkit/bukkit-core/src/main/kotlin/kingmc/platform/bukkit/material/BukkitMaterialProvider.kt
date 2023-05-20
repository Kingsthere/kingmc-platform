package kingmc.platform.bukkit.material

import kingmc.common.context.annotation.Autowired
import kingmc.common.context.annotation.Component
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.block.BukkitMaterialData
import kingmc.platform.bukkit.nms.RegistryNMS
import kingmc.platform.bukkit.util.asKingMC
import kingmc.platform.material.MaterialProvider
import kingmc.platform.material.MaterialType
import kingmc.platform.material.block.ChestBlock
import kingmc.util.key.Key
import org.bukkit.block.data.type.Chest
import org.bukkit.material.MaterialData
import java.util.*

/**
 * A [MaterialProvider] implementation for bukkit
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component
@BukkitImplementation
open class BukkitMaterialProvider : MaterialProvider {
    @Autowired
    lateinit var registryNMS: RegistryNMS<*>

    protected val _materialTypesByBukkit: MutableMap<_BukkitMaterial, MaterialType<*>> = EnumMap(org.bukkit.Material::class.java)
    protected val _materialTypesByName: MutableMap<String, MaterialType<*>> = HashMap()
    protected val _materialTypes: MutableMap<Key, MaterialType<*>> = initMaterialTypes()

    @Suppress("DEPRECATION")
    protected open fun initMaterialTypes(): MutableMap<Key, MaterialType<*>> {
        val mutableMap: MutableMap<Key, MaterialType<*>> = LinkedHashMap(64)
        _BukkitMaterial.values().forEach {
            if (it.data == MaterialData::class.java) {
                val materialType = BukkitMaterialType(it, Unit::class)
                mutableMap.put(it.key.asKingMC(), materialType)
                _materialTypesByBukkit.put(it, materialType)
                _materialTypesByName.put(it.name, materialType)
                return@forEach
            }
            if (it.data == Chest::class.java) {
                val materialType = BukkitMaterialType(it, ChestBlock::class)
                mutableMap.put(it.key.asKingMC(), materialType)
                _materialTypesByBukkit.put(it, materialType)
                _materialTypesByName.put(it.name, materialType)
                return@forEach
            }
            val materialType = BukkitMaterialType(it, BukkitMaterialData::class)
            mutableMap.put(it.key.asKingMC(), materialType)
            _materialTypesByBukkit.put(it, materialType)
            _materialTypesByName.put(it.name, materialType)
            return@forEach
        }
        return mutableMap
    }

    /**
     * Get a Material by the name
     *
     * @since 0.0.1
     * @param name the name of the Material
     */
    @Deprecated("Name is no longer the identifier for MaterialType(s)")
    override fun getTypeByName(name: String): MaterialType<*>? {
        return _materialTypesByName[name]
    }

    /**
     * Gets a type of `Material` by its KEY
     *
     * @since 0.0.1
     * @param key the key of the Material
     */
    override fun getTypeByKey(key: Key): MaterialType<*>? {
        return _materialTypes[key]
    }

    /**
     * Gets all type of `Material`
     *
     * @since 0.0.6
     * @return the materials got
     */
    override fun getTypes(): List<MaterialType<*>> {
        return _materialTypes.values.toList()
    }

    /**
     * Get a `Material` for [bukkitMaterial]
     *
     * @since 0.0.5
     * @param bukkitMaterial the bukkit material to get from
     * @author kingsthere
     */
    fun getTypeForBukkit(bukkitMaterial: _BukkitMaterial): MaterialType<*> {
        return _materialTypesByBukkit[bukkitMaterial]
            ?: throw UnsupportedOperationException("Unsupported bukkit material $bukkitMaterial")
    }
}