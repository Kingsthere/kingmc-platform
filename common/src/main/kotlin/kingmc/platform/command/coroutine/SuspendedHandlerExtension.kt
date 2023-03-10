
package kingmc.platform.command.coroutine

import kingmc.common.application.Application
import kingmc.platform.command.failed
import kingmc.platform.command.model.Node
import kotlin.coroutines.CoroutineContext

/**
 * Register and configure a suspended command handler executor
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun Node.suspendHandler(coroutineContext: CoroutineContext? = null, configurer: SuspendedHandler.() -> Unit = {  }, application: Application<*>): SuspendedHandler {
    val handler: SuspendedHandler = object : CoroutineContextSuspendHandler(coroutineContext, executor = { failed() }, application = application) {  }
    configurer.invoke(handler)
    this.handlers.add(handler)
    return handler
}

/**
 * Set the coroutine context of current suspended handler
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun <THandler : SuspendedHandler> THandler.context(coroutineContext: CoroutineContext?): SuspendedHandler =
    this.apply {
        this.coroutineContext = coroutineContext
    }

