package kingmc.platform.audience

import java.io.Closeable

/**
 * A Tunnel is a tunnel pass from the program
 * side to the receiver side, you can use tunnel
 * to easily control the packages to send into
 * the [Audience]
 *
 *
 * You could use [Closeable.close] to close this tunnel
 * so this tunnel will release when player quit
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface Tunnel : Closeable {
    /**
     * Check if this tunnel is active, the
     * tunnel should active when the tunnel
     * initialize, you can use [Closeable.close]
     * to close the tunnel
     *
     * @since 0.0.3
     * @author kingsthere
     * @return is the tunnel is active
     */
    fun active() : Boolean
}