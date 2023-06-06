package kingmc.platform.proxy

import kingmc.platform.proxy.entity.player.ProxiedPlayer
import kotlinx.coroutines.Deferred

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
     * The target `ProxiedServer` is connecting without being modified
     */
    val target: ProxiedServer

    /**
     * The target server we actually tried to connect to
     */
    @Deprecated("Use ConnectionResult.attemptedConnection instead")
    val resultTarget: ProxiedServer

    /**
     * The result to this connection request
     */
    val result: Deferred<ConnectionResult>
}