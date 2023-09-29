package kingmc.platform.bukkit.protocollib

import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.ConditionalOnClass
import kingmc.common.logging.info
import kingmc.platform.Load

/**
 * A bean to the mark that current environment has installed ProtocolLib
 *
 * @author kingsthere
 * @since 0.1.0
 */
@Component
@ConditionalOnClass(["com.comphenix.protocol.ProtocolLib"])
class ProtocolLibEnvironment {
    @Load
    fun load() {
        info("KingMC has hooked into ProtocolLib")
    }
}