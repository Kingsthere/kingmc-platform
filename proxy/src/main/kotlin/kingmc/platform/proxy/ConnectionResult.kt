package kingmc.platform.proxy

import kingmc.common.text.Text

/**
 * Interface for result to a connection request
 *
 * @since 0.0.9
 * @author kingsthere
 */
sealed interface ConnectionResult {
    /**
     * The target server we actually tried to connect to
     */
    val attemptedConnection: ProxiedServer

    /**
     * Represent the connection result is success
     */
    class Success(override val attemptedConnection: ProxiedServer) : ConnectionResult

    /**
     * Represent the connection result is denied
     * @property reason reason that the connection result is denied
     */
    open class Denied(reason: Text?, override val attemptedConnection: ProxiedServer) : Failed(reason, attemptedConnection = attemptedConnection)

    /**
     * Represent the connection result is failed
     * @property exception exception
     * @property reason reason that the connection result is failed
     */
    open class Failed(val reason: Text?,
                      val exception: Exception? = null,
                      override val attemptedConnection: ProxiedServer
    ) : ConnectionResult

    /**
     * Represents the connection result is failed because the player is already
     * connected to the target server
     */
    class AlreadyConnected(reason: Text?, attemptedConnection: ProxiedServer) : Failed(reason, attemptedConnection = attemptedConnection)

    /**
     * Represent the connection is redirected to another server
     * @property redirectTo another server redirected to
     */
    open class Redirected(val redirectTo: ProxiedServer, override val attemptedConnection: ProxiedServer): ConnectionResult
}