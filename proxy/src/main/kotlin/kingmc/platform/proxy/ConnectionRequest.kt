package kingmc.platform.proxy

import kingmc.platform.proxy.entity.player.ProxiedPlayer

/**
 * An interface describe a request to connect a `ProxiedServer` proxied by the `ProxyServer`
 *
 * @since 0.0.9
 * @author kingsthere
 */
interface ConnectionRequest {
    /**
     * The invoker that created this connection request
     */
    val invoker: ProxiedPlayer

    /**
     * The raw target `ProxiedServer` is connecting without being redirected
     */
    val rawTarget: ProxiedServer

    /**
     * The result target `ProxiedServer` is connecting within being redirected
     */
    val resultTarget: ProxiedServer

    /**
     * The result to this connection request
     */
    var result: ConnectionResult
}