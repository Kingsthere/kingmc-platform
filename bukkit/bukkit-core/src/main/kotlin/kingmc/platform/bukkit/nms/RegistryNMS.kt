package kingmc.platform.bukkit.nms

import kingmc.common.context.annotation.Component
import kingmc.platform.bukkit.nms.registry.ListedRegistries
import kingmc.platform.bukkit.nms.registry.Registry

/**
 * A superinterface exposed few functions to interact with `Registry` on `net.minecraft.server`
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component
interface RegistryNMS<TRegistry : Any> {
    /**
     * Add an adaptor to [registry]
     */
    fun adaptRegistry(registry: TRegistry)

    /**
     * Gets a `Registry` in `net.minecraft` from [ListedRegistries]
     */
    fun getRawRegistry(registry: ListedRegistries<*>): TRegistry

    /**
     * Gets a `Registry` from [ListedRegistries]
     */
    fun <T> getRegistry(registry: ListedRegistries<T>): Registry<T>
}