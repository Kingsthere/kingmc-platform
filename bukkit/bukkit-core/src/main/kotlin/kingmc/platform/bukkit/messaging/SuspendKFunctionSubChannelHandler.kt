package kingmc.platform.bukkit.messaging

import kingmc.platform.messaging.InputMessage
import kingmc.platform.messaging.SubChannelHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction
import kotlin.reflect.full.callSuspend

/**
 * An implementation of [SubChannelHandler] handled input messages
 * suspend
 */
class SuspendKFunctionSubChannelHandler(private val coroutineScope: CoroutineScope, override val subChannel: String, private val function: KFunction<*>) : SubChannelHandler {
    /**
     * Invoke this handler to handle an [InputMessage]
     */
    override fun invoke(message: InputMessage) {
        coroutineScope.launch {
            function.callSuspend(message)
        }
    }
}