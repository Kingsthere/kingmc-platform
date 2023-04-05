package kingmc.platform.bukkit.nms.v1_19_2.registry

import kingmc.platform.bukkit.nms.registry.Registry
import kingmc.platform.bukkit.nms.v1_19_2.NMSRegistryMaterials_1_19_2
import kingmc.util.key.Key
import net.minecraft.resources.MinecraftKey

open class Registry_1_19_2<T>(val nms: NMSRegistryMaterials_1_19_2<T>) : Registry<T> {
    /**
     * Gets all entry keys in this registry
     *
     * @return all entry keys in this registry
     */
    override fun getKeys(): Set<Key> {
        return nms.d().map { Key(it.e(), it.d()) }.toSet()
    }

    /**
     * Gets a resource in this registry
     *
     * @param key the key of the resource to get
     * @return the resource got
     */
    override fun get(key: Key): T? {
        return nms.a(MinecraftKey.a(key.namespace(), key.value()))
    }

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<T> {
        return nms.iterator()
    }
}