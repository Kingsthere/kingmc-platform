package kingmc.platform.proxy

import com.google.errorprone.annotations.Immutable
import java.net.InetSocketAddress

/**
 * A data class describe a `ProxiedServer` that `ProxyServer` forwards to
 *
 * @author kingsthere
 * @since 0.0.8
 */
@Immutable
data class ServerInfo(val name: String, val address: InetSocketAddress)