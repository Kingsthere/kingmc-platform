package kingmc.platform.proxy

import kingmc.common.context.annotation.Component
import kingmc.platform.Server

/**
 * A `ProxyServer` similar to a `Server` forward players connected to sub-servers
 *
 * @since 0.0.8
 * @author kingsthere
 */
@Component
interface ProxyServer : Server {
    /**
     * Retrieves all proxied servers in this `ProxyServer`
     */
    val proxiedServers: Collection<ProxiedServer>

    /**
     * Gets a proxied server by its name
     *
     * @param name the [ServerInfo.name] to the server
     * @return proxied server get, or `null` if proxied server with [ServerInfo.name] not registered
     *         in this `ProxyServer`
     */
    fun getProxiedServer(name: String): ProxiedServer?

    /**
     * Try to register a server
     *
     * @param serverInfo the server to register
     * @return `ProxiedServer` instance related to the registering server
     * @throws IllegalArgumentException if server with same [ServerInfo.name] already registered into
     *                                  this `ProxyServer`
     */
    fun registerProxiedServer(serverInfo: ServerInfo): ProxiedServer

    /**
     * Try to register a server
     *
     * @param server the server to register
     * @throws IllegalArgumentException if server with same [ServerInfo.name] already registered into
     *                                  this `ProxyServer`
     */
    fun registerProxiedServer(server: ProxiedServer)

    /**
     * Unregister a proxied server from this `ProxyServer`
     *
     * @param serverInfo the server info of the server to unregister
     */
    fun unregisterProxiedServer(serverInfo: ServerInfo)

    /**
     * Unregister a proxied server from this `ProxyServer`
     *
     * @param server the server to unregister
     */
    fun unregisterProxiedServer(server: ProxiedServer)
}