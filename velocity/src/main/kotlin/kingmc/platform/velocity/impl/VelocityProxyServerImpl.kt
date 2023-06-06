package kingmc.platform.velocity.impl

import kingmc.common.application.application
import kingmc.common.application.withApplication
import kingmc.common.context.annotation.Component
import kingmc.common.text.Text
import kingmc.platform.audience.Console
import kingmc.platform.entity.player.OfflinePlayer
import kingmc.platform.entity.player.Player
import kingmc.platform.messaging.OutputMessage
import kingmc.platform.proxy.ProxiedServer
import kingmc.platform.proxy.ServerInfo
import kingmc.platform.velocity.*
import kingmc.platform.velocity.driver.velocityServer
import kingmc.platform.velocity.impl.audience.VelocityConsoleImpl
import kingmc.platform.velocity.impl.entity.player.VelocityPlayerImpl
import java.util.*
import kotlin.jvm.optionals.getOrNull

/**
 * A simple `VelocityProxyServer` implementation
 *
 * @since 0.0.9
 * @author kingsthere
 */
@Component
class VelocityProxyServerImpl : VelocityProxyServer {
    /**
     * `Player` instance caches
     */
    val _players: MutableMap<_VelocityPlayer, Player> = WeakHashMap()

    /**
     * `ProxiedServer` instance caches
     */
    val _servers: MutableMap<_VelocityProxiedServer, ProxiedServer> = WeakHashMap()

    val _velocityProxyServer: _VelocityProxyServer = withApplication { velocityServer }

    override val console: Console by lazy {
        VelocityConsoleImpl(_velocityProxyServer)
    }

    override fun asVelocityProxyServer(): _VelocityProxyServer {
        return _velocityProxyServer
    }

    override fun getProxiedServerForVelocity(velocityProxiedServer: _VelocityProxiedServer): ProxiedServer {
        return _servers.computeIfAbsent(velocityProxiedServer) {
            VelocityProxiedServerImpl(this, it)
        }
    }

    override fun getPlayerForVelocity(velocityPlayer: _VelocityPlayer): Player {
        return _players.computeIfAbsent(velocityPlayer) {
            VelocityPlayerImpl(this, it, application)
        }
    }

    override val proxiedServers: Collection<ProxiedServer>
        get() = _velocityProxyServer.allServers.map { getProxiedServerForVelocity(it) }

    override fun getProxiedServer(name: String): ProxiedServer? =
        _velocityProxyServer.allServers.find { it.serverInfo.name == name }?.let { getProxiedServerForVelocity(it) }

    override fun registerProxiedServer(serverInfo: ServerInfo): ProxiedServer =
        getProxiedServerForVelocity(_velocityProxyServer.registerServer(_VelocityServerInfo(serverInfo.name, serverInfo.address)))

    override fun unregisterProxiedServer(serverInfo: ServerInfo) {
        _velocityProxyServer.unregisterServer(_VelocityServerInfo(serverInfo.name, serverInfo.address))
    }

    override fun unregisterProxiedServer(server: ProxiedServer) {
        unregisterProxiedServer(server.serverInfo)
    }

    override fun getOnlinePlayers(): Collection<Player> =
        _velocityProxyServer.allPlayers.map { getPlayerForVelocity(it) }

    override fun getOfflinePlayers(): List<OfflinePlayer> {
        TODO("Not yet implemented")
    }

    override fun getPlayer(username: String): Player? =
        _velocityProxyServer.getPlayer(username).getOrNull()?.let { getPlayerForVelocity(it) }

    override fun getPlayer(uuid: UUID): Player? =
        _velocityProxyServer.getPlayer(uuid).getOrNull()?.let { getPlayerForVelocity(it) }

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

    fun asText(): Text = Text("VelocityProxyServerImpl")

    override fun toString(): String {
        return "VelocityProxyServerImpl(_velocityProxyServer=$_velocityProxyServer)"
    }

}