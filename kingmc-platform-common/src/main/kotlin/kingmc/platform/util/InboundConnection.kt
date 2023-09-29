package kingmc.platform.util

import java.net.InetSocketAddress

/**
 * Represents an incoming connection to the proxy.
 */
interface InboundConnection {
    /**
     * The player's IP address
     */
    val remoteAddress: InetSocketAddress

    /**
     * The hostname that the user entered into the client, if applicable
     */
    val virtualHost: InetSocketAddress?

    /**
     * Determine whether the player remains online
     */
    val isActive: Boolean

    /**
     * Returns the current protocol version this connection uses.
     */
    val protocolVersion: ProtocolVersion
}