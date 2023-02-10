package kingmc.platform.bukkit.audience.adventure

import kingmc.common.context.annotation.Configuration
import kingmc.common.environment.RuntimeDependency
import kingmc.platform.Awake
import kingmc.platform.Releasable
import kingmc.platform.bukkit.bukkitPluginInstance

/**
 * Configuration class for adding compatible to kyori
 * adventure api
 *
 * See: [Adventure Docs](https://adventure-docs.minecraft.kim/getting-started.html)
 * @since 0.0.3
 * @author kingsthere
 */
@Awake
@Configuration
@RuntimeDependency(
    value = "net.kyori:adventure-platform-bukkit:4.1.2"
)
@RuntimeDependency(
    value = "net.kyori:adventure-platform-api:4.1.2"
)
@RuntimeDependency(
    value = "net.kyori:adventure-nbt:4.12.0"
)
@RuntimeDependency(
    value = "net.kyori:adventure-platform-facet:4.1.2"
)
@RuntimeDependency(
    value = "net.kyori:adventure-text-serializer-gson:4.11.0"
)
@RuntimeDependency(
    value = "net.kyori:adventure-text-serializer-legacy:4.11.0"
)
@RuntimeDependency(
    value = "net.kyori:adventure-text-serializer-gson-legacy-impl:4.11.0"
)
object Adventure : Releasable {
    @Awake(3)
    fun active() {
        // Set up the bukkit audience provider
        bukkitAudiences = AdventureBukkitAudiences.create(bukkitPluginInstance)
    }

    /**
     * Release the bukkit audiences
     *
     * @since 0.0.3
     */
    override fun release() {
         bukkitAudiences.close()
    }
}