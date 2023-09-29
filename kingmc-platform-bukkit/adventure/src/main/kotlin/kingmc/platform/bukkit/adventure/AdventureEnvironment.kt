package kingmc.platform.bukkit.adventure

import kingmc.common.application.withApplication
import kingmc.common.context.annotation.Bean
import kingmc.common.context.annotation.Configuration
import kingmc.common.context.annotation.Lateinit
import kingmc.common.environment.maven.MavenDependency
import kingmc.platform.*
import kingmc.platform.bukkit.driver.bukkitPlugin
import kingmc.util.lifecycle.Lifecycles
import net.kyori.adventure.platform.bukkit.BukkitAudiences

/**
 * Configuration class for adding compatible to kyori
 * adventure api
 *
 * See: [Adventure Docs](https://adventure-docs.minecraft.kim/getting-started.html)
 * @author kingsthere
 * @since 0.0.3
 */
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
object AdventureEnvironment : Releasable {
    // net.kyori.adventure.platform.AudienceProvider
    private var _adventureAudienceProvider: BukkitAudiences? = null

    @Active(2)
    fun init() {
        _adventureAudienceProvider = this@AdventureEnvironment.withApplication {
            BukkitAudiences.create(bukkitPlugin)
        }
    }

    /**
     * Return [net.kyori.adventure.platform.AudienceProvider] that supports current context
     */
    @Bean
    @Lateinit(Lifecycles.ACTIVE)
    fun audienceProvider(): BukkitAudiences {
        return _adventureAudienceProvider
            ?: throw IllegalStateException("Adventure audience provider is not ready yet, the getAudienceProvider statement should only called after Lifecycles.ACTIVE")
    }

    /**
     * Release the adventure audience provider
     */
    override fun release() {
         _adventureAudienceProvider?.close()
    }
}