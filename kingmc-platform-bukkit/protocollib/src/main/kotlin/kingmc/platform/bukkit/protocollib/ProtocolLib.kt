package kingmc.platform.bukkit.protocollib

import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.ProtocolManager
import kingmc.common.context.annotation.Bean
import kingmc.common.context.annotation.ConditionalOnBean
import kingmc.common.context.annotation.Configuration


/**
 * Configuration class for using protocollib under kingmc framework
 *
 * @author kingsthere
 * @since 0.1.0
 */
@Configuration
@ConditionalOnBean([ProtocolLibEnvironment::class])
class ProtocolLib {
    @Bean
    fun protocolManager(): ProtocolManager = ProtocolLibrary.getProtocolManager()
}