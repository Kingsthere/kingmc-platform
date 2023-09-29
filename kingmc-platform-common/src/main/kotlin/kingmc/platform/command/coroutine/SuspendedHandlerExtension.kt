
package kingmc.platform.command.coroutine

import kingmc.common.application.Application
import kingmc.platform.command.failed
import kingmc.platform.command.model.Node
import kotlin.coroutines.CoroutineContext

/**
 * Register and configure a suspended command handler executor
 *
 * @author kingsthere
 * @since 0.0.3
 */
fun Node.suspendHandler(coroutineContext: CoroutineContext? = null, configurer: DeferredHandler.() -> Unit = {  }, application: Application): DeferredHandler {
    val handler: DeferredHandler = object : CoroutineContextHandler(coroutineContext, executor = { failed() }, application = application) {  }
    configurer.invoke(handler)
    this.handlers.add(handler)
    return handler
}

/**
 * Set the coroutine context of current suspended handler
 *
 * @author kingsthere
 * @since 0.0.3
 */
fun <THandler : DeferredHandler> THandler.context(coroutineContext: CoroutineContext?): DeferredHandler =
    this.apply {
        this.coroutineContext = coroutineContext
    }

