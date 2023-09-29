package kingmc.platform.velocity.impl

import kingmc.platform.audience.Console
import kingmc.platform.entity.player.OfflinePlayer
import kingmc.platform.entity.player.Player
import kingmc.platform.messaging.OutputMessage
import kingmc.platform.proxy.ServerInfo
import kingmc.platform.proxy.ServerPing
import kingmc.platform.util.Favicon
import kingmc.platform.velocity.VelocityProxiedServer
import kingmc.platform.velocity.VelocityProxyServer
import kingmc.platform.velocity._VelocityProxiedServer
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import java.util.*
import kotlin.jvm.optionals.getOrNull

/**
 * Velocity `ProxiedServer` implementation
 *
 * @author kingsthere
 * @since 0.0.9
 */
class VelocityProxiedServerImpl(val proxyServer: VelocityProxyServer, private val _velocityProxiedServer: _VelocityProxiedServer) : VelocityProxiedServer {
    override fun asVelocityProxiedServer(): _VelocityProxiedServer = _velocityProxiedServer

    override val serverInfo: ServerInfo
        get() {
            val velocityServerInfo = _velocityProxiedServer.serverInfo
            return ServerInfo(velocityServerInfo.name, velocityServerInfo.address)
        }

    override fun ping(): Deferred<ServerPing> {
        val deferred = CompletableDeferred<ServerPing>()
        _velocityProxiedServer.ping().thenAccept { ping ->
            val pingInstance = ServerPing(ServerPing.Version(ping.version.protocol, ping.version.name),
                ping.players.getOrNull()?.let { players -> ServerPing.Players(players.online, players.max, players.sample.map { ServerPing.SamplePlayer(it.name, it.id) }) },
                ping.descriptionComponent,
                ping.favicon.getOrNull()?.let { favicon -> Favicon(favicon.base64Url) })
            deferred.complete(pingInstance)
        }
        return deferred
    }

    override val console: Console
        get() = TODO("Not yet implemented")

    override fun getOnlinePlayers(): Collection<Player> = _velocityProxiedServer.playersConnected.map { proxyServer.getPlayerForVelocity(it) }

    override fun getOfflinePlayers(): List<OfflinePlayer> {
        TODO("Not yet implemented")
    }

    override fun getPlayer(username: String): Player? =
        _velocityProxiedServer.playersConnected.find { it.username == username }
            ?.let { proxyServer.getPlayerForVelocity(it) }

    override fun getPlayer(uuid: UUID): Player? =
        _velocityProxiedServer.playersConnected.find { it.uniqueId == uuid }
            ?.let { proxyServer.getPlayerForVelocity(it) }

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
}