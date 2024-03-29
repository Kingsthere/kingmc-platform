package kingmc.platform.proxy

import kingmc.common.context.annotation.DisableScan
import kingmc.platform.Server
import kotlinx.coroutines.Deferred

/**
 * A `ProxiedServer` represent a sub-server that `ProxyServer` could forward to
 *
 * @author kingsthere
 * @since 0.0.8
 */
@DisableScan
interface ProxiedServer : Server {
    /**
     * The `ServerInfo` for this `ProxiedServer`
     */
    val serverInfo: ServerInfo

    /**
     * Ping this server and return a [ServerPing] as result
     */
    fun ping(): Deferred<ServerPing>
}