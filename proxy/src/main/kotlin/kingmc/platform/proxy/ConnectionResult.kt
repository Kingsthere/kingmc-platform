package kingmc.platform.proxy

import kingmc.common.text.Text

/**
 * Interface for result to a connection request
 *
 * @since 0.0.9
 * @author kingsthere
 */
interface ConnectionResult {
    /**
     * Represent the connection result is success
     */
    object Success : ConnectionResult

    /**
     * Represent the connection result is denied
     * @property reason reason that the connection result is denied
     */
    open class Denied(val reason: Text?) : ConnectionResult

    /**
     * Represent the connection result is failed
     * @property exception exception
     */
    open class Failed(val exception: Exception?) : ConnectionResult

    /**
     * Represent the connection is redirected to another server
     * @property redirectTo another server redirected to
     */
    open class Redirected(val redirectTo: ProxiedServer): ConnectionResult
}