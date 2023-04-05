package kingmc.platform.bukkit.nms.registry

import kingmc.util.key.Key

/**
 * A wrapper class for using `RegistryNMS` to reflect `net.minecraft`
 *
 * @since 0.0.7
 * @author kingsthere
 */
interface Registry<T> : Iterable<T> {
    /**
     * Gets all entry keys in this registry
     *
     * @return all entry keys in this registry
     */
    fun getKeys(): Set<Key>

    /**
     * Gets a resource in this registry
     *
     * @param key the key of the resource to get
     * @return the resource got
     */
    operator fun get(key: Key): T?
}