package kingmc.platform.bukkit.nms.v1_19_2

import kingmc.common.context.annotation.Component
import kingmc.platform.bukkit.nms.RegistryNMS
import kingmc.platform.bukkit.nms.registry.ListedRegistries
import kingmc.platform.bukkit.nms.registry.Registry
import net.minecraft.core.Holder
import net.minecraft.core.IRegistry
import net.minecraft.core.RegistryMaterials
import net.minecraft.resources.ResourceKey
import java.lang.reflect.Field
import java.util.*

@Suppress("UNCHECKED_CAST")
@Component("registryNMS_1_19_2")
class RegistryNMS_1_19_2 : RegistryNMS<IRegistry<*>> {
    /**
     * net.minecraft.core.Registry#REGISTRY (`d`)
     */
    val REGISTRY: IRegistry<IRegistry<Any>> = (IRegistry.d as IRegistry<IRegistry<Any>>)
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
     * Gets a `Registry` in `net.minecraft` from [ListedRegistries]
     */
    override fun getRawRegistry(registry: ListedRegistries<*>): IRegistry<*> {
        return when (registry) {
            ListedRegistries.BLOCK -> IRegistry.V
            ListedRegistries.ITEM -> IRegistry.Y
            ListedRegistries.ENTITY -> IRegistry.X
            ListedRegistries.WORLD -> REGISTRY.f(IRegistry.P!! as ResourceKey<IRegistry<Any>>).get()
            else -> throw IllegalArgumentException("Registry: $registry is not supported")
        }
    }

    /**
     * Gets a `Registry` from [Registries]
     */
    override fun <T> getRegistry(registry: ListedRegistries<T>): Registry<T> {
        // return when (registry) {
        //     ListedRegistries.BLOCK -> IRegistry.V
        //     ListedRegistries.ITEM -> IRegistry.Y
        //     else -> throw IllegalArgumentException("Registry: $registry is not supported")
        // }
        TODO()
    }
}