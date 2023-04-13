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
}