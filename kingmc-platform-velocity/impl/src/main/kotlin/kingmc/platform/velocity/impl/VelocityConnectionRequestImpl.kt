package kingmc.platform.velocity.impl

import com.velocitypowered.api.proxy.ConnectionRequestBuilder
import kingmc.platform.proxy.ConnectionRequest
import kingmc.platform.proxy.ConnectionResult
import kingmc.platform.proxy.ProxiedServer
import kingmc.platform.proxy.entity.player.ProxiedPlayer
import kingmc.platform.velocity.VelocityProxyServer
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import kotlin.jvm.optionals.getOrNull

/**
 * A velocity `ConnectionRequest` implementation
 */
class VelocityConnectionRequestImpl(
    private val _velocityConnectionRequest: ConnectionRequestBuilder,
    private val _velocityProxyServer: VelocityProxyServer,
    override val invoker: ProxiedPlayer,
) : ConnectionRequest {
    override val target: ProxiedServer
        get() = _velocityProxyServer.getProxiedServerForVelocity(_velocityConnectionRequest.server)
    @Deprecated("Use ConnectionResult.attemptedConnection instead")
    override val resultTarget: ProxiedServer
        get() = runBlocking { result.await() }.attemptedConnection
    override val result: CompletableDeferred<ConnectionResult> = CompletableDeferred()

    init {
        _velocityConnectionRequest.connect().thenAccept { connectionResult ->
            result.complete(getConnectionResultForVelocity(connectionResult))
        }
    }

    fun getConnectionResultForVelocity(result: ConnectionRequestBuilder.Result): ConnectionResult = when (result.status) {
        ConnectionRequestBuilder.Status.SUCCESS -> ConnectionResult.Success(_velocityProxyServer.getProxiedServerForVelocity(result.attemptedConnection))
        ConnectionRequestBuilder.Status.ALREADY_CONNECTED -> ConnectionResult.AlreadyConnected(result.reasonComponent.getOrNull(), _velocityProxyServer.getProxiedServerForVelocity(result.attemptedConnection))
        ConnectionRequestBuilder.Status.CONNECTION_CANCELLED -> ConnectionResult.Denied(result.reasonComponent.getOrNull(), _velocityProxyServer.getProxiedServerForVelocity(result.attemptedConnection))
        ConnectionRequestBuilder.Status.CONNECTION_IN_PROGRESS -> ConnectionResult.Denied(result.reasonComponent.getOrNull(), _velocityProxyServer.getProxiedServerForVelocity(result.attemptedConnection))
        ConnectionRequestBuilder.Status.SERVER_DISCONNECTED -> ConnectionResult.Denied(result.reasonComponent.getOrNull(), _velocityProxyServer.getProxiedServerForVelocity(result.attemptedConnection))
        else -> throw IllegalArgumentException("Unrecognized velocity connection result instance $result")
    }
}