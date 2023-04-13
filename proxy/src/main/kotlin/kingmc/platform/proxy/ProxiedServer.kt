package kingmc.platform.proxy

import kingmc.platform.Server

/**
 * A `ProxiedServer` represent a sub-server that `ProxyServer` could forward to
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface ProxiedServer : Server {
    /**
     * The `ServerInfo` for this `ProxiedServer`
     */
    val serverInfo: ServerInfo
}