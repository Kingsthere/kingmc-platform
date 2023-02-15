package kingmc.platform.bukkit.audience.text

import kingmc.common.application.application
import kingmc.common.context.annotation.Bean
import kingmc.common.context.annotation.Configuration
import kingmc.platform.audience.text.*

@Configuration("bukkitTextResolvers")
object BukkitTextResolvers {
    private val defaultTextResolver by lazy {
        application {
            val miniMessage: MiniMessage = MiniMessage.builder()
                .build()
            val textResolver = enableMiniMessage()
                .miniMessage(miniMessage)
                .build()
            // Returns
            textResolver
        }
    }

    /**
     * The default text resolver to use (miniMessage is enabled by default)
     */
    @Bean("defaultTextResolver")
    fun defaultTextResolver(): TextSolver {
        return defaultTextResolver
    }

    // /**
    //  * Setup text resolver that support markdown syntax
    //  */
    // @Bean("markdownTextResolver")
    // fun markdownTextResolver(): TextResolver {
    //     return application {
    //         val miniMessage: MiniMessage = MiniMessage.builder()
    //             .build()
    //         val textResolver = textBuilders()
    //             .miniMessage()
    //             .miniMessage(miniMessage)
    //             .build()
    //         this.platform.textResolver = textResolver
    //         // Returns
    //         textResolver
    //     }
    // }
}