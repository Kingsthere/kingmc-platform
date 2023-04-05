package kingmc.platform.bukkit.material

import kingmc.common.context.annotation.Autowired
import kingmc.common.context.annotation.Component
import kingmc.platform.MaterialProvider
import kingmc.platform.MaterialType
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.nms.RegistryNMS
import kingmc.platform.bukkit.util.asKingMC
import kingmc.util.key.Key
import org.bukkit.material.MaterialData

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

    protected val _materialTypesByName: MutableMap<String, MaterialType<*>> = mutableMapOf()
    protected val _materialTypes: MutableMap<Key, MaterialType<*>> = initMaterialTypes()

    @Suppress("DEPRECATION")
    protected open fun initMaterialTypes(): MutableMap<Key, MaterialType<*>> {
        val mutableMap: MutableMap<Key, MaterialType<*>> = LinkedHashMap(64)
        _BukkitMaterial.values().forEach {
            if (it.data == MaterialData::class.java) {
                mutableMap.put(it.key.asKingMC(), BukkitMaterialType<Unit>(it))
                _materialTypesByName.put(it.name, BukkitMaterialType<Unit>(it))
            }
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
     * Get a `Material` from [bukkitMaterial]
     *
     * @since 0.0.5
     * @param bukkitMaterial the bukkit material to get from
     * @author kingsthere
     */
    fun getFromBukkit(bukkitMaterial: _BukkitMaterial): MaterialType<*> {
        return _materialTypesByName[bukkitMaterial.name]
            ?: throw UnsupportedOperationException("Unsupported bukkit material $bukkitMaterial")
    }
}