package kingmc.platform.bukkit.nms.v1_19_2

import kingmc.common.context.annotation.Component
import kingmc.platform.bukkit.nms.RegistryNMS
import net.minecraft.core.Holder
import net.minecraft.core.IRegistry
import net.minecraft.core.RegistryMaterials
import java.lang.reflect.Field
import java.util.*

@Component
class RegistryNMS_1_19_2 : RegistryNMS<IRegistry<*>> {
    /**
     * net.minecraft.core.MappedRegistry#frozen `(ca)`
     */
    private val frozen: Field = RegistryMaterials::class.java.getDeclaredField("ca")
    /**
     * net.minecraft.core.MappedRegistry#intrusiveHolderCache `(cc)`
     */
    private val intrusiveHolderCache: Field = RegistryMaterials::class.java.getDeclaredField("cc")

    fun <T : Any> unfreezeRegistry(registry: IRegistry<T>) {
        frozen.isAccessible = true
        frozen.set(registry, false)
        intrusiveHolderCache.isAccessible = true
        val intrusiveHolderCacheIns = intrusiveHolderCache.get(registry)
        if (intrusiveHolderCacheIns == null) {
            intrusiveHolderCache.set(registry, IdentityHashMap<T, Holder.c<T>>())
        }
        frozen.isAccessible = false
        intrusiveHolderCache.isAccessible = false
    }

    fun refreezeRegistry(registry: IRegistry<*>) {
        registry.k()
    }

    /**
     * Add an adaptor to [registry]
     */
    override fun adaptRegistry(registry: IRegistry<*>) {
        unfreezeRegistry(registry)
    }

    /**
     * Gets a `Registry` from [Registries]
     */
    override fun getRegistry(registry: RegistryNMS.Registries): IRegistry<*> {
        return when (registry) {
            RegistryNMS.Registries.BLOCK -> IRegistry.V
            RegistryNMS.Registries.ITEM -> IRegistry.Y
            else -> throw IllegalArgumentException("Registry: $registry is not supported")
        }
    }
}