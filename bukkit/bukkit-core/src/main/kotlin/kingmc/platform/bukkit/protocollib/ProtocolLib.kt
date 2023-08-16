package kingmc.platform.bukkit.protocollib

import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.ProtocolManager
import kingmc.common.context.annotation.Bean
import kingmc.common.context.annotation.Configuration
import kingmc.common.context.condition.ConditionalOnBean


/**
 * Configuration class for using protocollib under kingmc framework
 *
 * @since 0.1.0
 * @author kingsthere
 */
@Configuration
@ConditionalOnBean(ProtocolLibEnvironment::class)
class ProtocolLib {
    @Bean
    fun protocolManager(): ProtocolManager = ProtocolLibrary.getProtocolManager()
}