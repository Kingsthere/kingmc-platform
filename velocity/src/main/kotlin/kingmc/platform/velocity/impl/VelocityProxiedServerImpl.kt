package kingmc.platform.velocity.impl

import kingmc.common.text.Text
import kingmc.platform.audience.Console
import kingmc.platform.entity.player.OfflinePlayer
import kingmc.platform.entity.player.Player
import kingmc.platform.messaging.OutputMessage
import kingmc.platform.proxy.ProxiedServer
import kingmc.platform.proxy.ServerInfo
import kingmc.platform.velocity.VelocityProxyServer
import kingmc.platform.velocity._VelocityPlayer
import kingmc.platform.velocity._VelocityProxiedServer
import java.util.*

class VelocityProxiedServerImpl(val _velocityProxiedServer: _VelocityProxiedServer) : VelocityProxyServer {
    override fun asVelocityProxiedServer(): _VelocityProxiedServer = _velocityProxiedServer

    override fun getProxiedServerForVelocity(velocityProxiedServer: _VelocityProxiedServer): ProxiedServer {
        TODO("Not yet implemented")
    }

    override fun getPlayerForVelocity(velocityPlayer: _VelocityPlayer): Player {
        TODO("Not yet implemented")
    }

    override val proxiedServers: Collection<ProxiedServer>
        get() = TODO("Not yet implemented")

    override fun getProxiedServer(name: String): ProxiedServer? {
        TODO("Not yet implemented")
    }

    override fun registerProxiedServer(serverInfo: ServerInfo): ProxiedServer {
        TODO("Not yet implemented")
    }

    override fun registerProxiedServer(server: ProxiedServer) {
        TODO("Not yet implemented")
    }

    override fun unregisterProxiedServer(serverInfo: ServerInfo) {
        TODO("Not yet implemented")
    }

    override fun unregisterProxiedServer(server: ProxiedServer) {
        TODO("Not yet implemented")
    }

    override val console: Console
        get() = TODO("Not yet implemented")

    override fun getOnlinePlayers(): Collection<Player> {
        TODO("Not yet implemented")
    }

    override fun getOfflinePlayers(): List<OfflinePlayer> {
        TODO("Not yet implemented")
    }

    override fun getPlayer(username: String): Player? {
        TODO("Not yet implemented")
    }

    override fun getPlayer(uuid: UUID): Player? {
        TODO("Not yet implemented")
    }

    @Deprecated("Persistent storage of users should be by UUID as names are no longer unique past a single session")
    override fun getOfflinePlayer(username: String): OfflinePlayer {
        TODO("Not yet implemented")
    }

    override fun getOfflinePlayer(uuid: UUID): OfflinePlayer {
        TODO("Not yet implemented")
    }

    override fun sendPluginMessage(channel: String, message: OutputMessage) {
        TODO("Not yet implemented")
    }

    override val listeningPluginChannels: Set<String>
        get() = TODO("Not yet implemented")

    fun asText(): Text {
        TODO("Not yet implemented")
    }
}