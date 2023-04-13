package kingmc.platform.proxy

import kingmc.util.errorprone.Immutable
import java.net.InetSocketAddress

/**
 * A data class describe a `ProxiedServer` that `ProxyServer` forwards to
 *
 * @since 0.0.8
 * @author kingsthere
 */
@Immutable
data class ServerInfo(val name: String, val address: InetSocketAddress)