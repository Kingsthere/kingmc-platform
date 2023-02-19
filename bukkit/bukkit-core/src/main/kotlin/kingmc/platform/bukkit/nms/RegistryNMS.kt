package kingmc.platform.bukkit.nms

import kingmc.common.context.annotation.Component

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
     * Gets a `Registry` from [Registries]
     */
    fun getRegistry(registry: Registries): TRegistry

    /**
     * An enum listed known registries
     */
    enum class Registries {
        BLOCK,
        ITEM
    }
}