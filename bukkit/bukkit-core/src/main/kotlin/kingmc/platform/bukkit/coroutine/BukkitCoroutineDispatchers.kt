package kingmc.platform.bukkit.coroutine

import kingmc.common.context.annotation.Bean
import kingmc.common.context.annotation.Configuration
import kingmc.platform.bukkit.bukkitPluginDriver

/**
 * A configuration to add minecraft coroutine dispatchers into context
 * as a bean
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Configuration
object BukkitCoroutineDispatchers {
    /**
     * @see AsyncBukkitCoroutineDispatcher
     */
    @Bean
    fun asyncBukkitCoroutineDispatcher(): AsyncBukkitCoroutineDispatcher {
        return bukkitPluginDriver.asyncMinecraftCoroutineDispatcher
    }

    /**
     * @see SyncBukkitCoroutineDispatcher
     */
    @Bean
    fun syncBukkitCoroutineDispatcher(): SyncBukkitCoroutineDispatcher {
        return bukkitPluginDriver.syncMinecraftCoroutineDispatcher
    }
}