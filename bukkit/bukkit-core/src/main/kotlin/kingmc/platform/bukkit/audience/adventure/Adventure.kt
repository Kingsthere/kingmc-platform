package kingmc.platform.bukkit.audience.adventure

import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Configuration
import kingmc.common.environment.maven.MavenDependency
import kingmc.platform.Awake
import kingmc.platform.Releasable
import kingmc.platform.bukkit.driver.bukkitPlugin

/**
 * Configuration class for adding compatible to kyori
 * adventure api
 *
 * See: [Adventure Docs](https://adventure-docs.minecraft.kim/getting-started.html)
 * @since 0.0.3
 * @author kingsthere
 */
@Component
@Configuration
@MavenDependency(
    groupId = "net.kyori",
    artifactId = "adventure-platform-bukkit",
    version = "4.1.2",
)
@MavenDependency(
    groupId = "net.kyori",
    artifactId = "adventure-platform-api",
    version = "4.1.2",
)
@MavenDependency(
    groupId = "net.kyori",
    artifactId = "adventure-nbt",
    version = "4.12.0",
)
@MavenDependency(
    groupId = "net.kyori",
    artifactId = "adventure-platform-facet",
    version = "4.1.2",
)
@MavenDependency(
    groupId = "net.kyori",
    artifactId = "adventure-text-serializer-gson",
    version = "4.11.0",
)
@MavenDependency(
    groupId = "net.kyori",
    artifactId = "adventure-text-serializer-legacy",
    version = "4.11.0",
)
@MavenDependency(
    groupId = "net.kyori",
    artifactId = "adventure-text-serializer-gson-legacy-impl",
    version = "4.11.0",
)
object Adventure : Releasable {
    @Awake(3)
    fun active() {
        // Set up the bukkit audience provider
        adventureBukkitAudiences = _AdventureBukkitAudiences.create(bukkitPlugin)
    }

    /**
     * Release the bukkit audiences
     *
     * @since 0.0.3
     */
    override fun release() {
         adventureBukkitAudiences.close()
    }
}