package kingmc.platform.bukkit.messaging

import kingmc.platform.messaging.InputMessage
import kingmc.platform.messaging.SubChannelHandler
import kotlin.reflect.KFunction

/**
 * An implementation of [SubChannelHandler] handled input messages
 * by kotlin reflection
 */
class KFunctionSubChannelHandler(override val subChannel: String, private val function: KFunction<*>) : SubChannelHandler {
    /**
     * Invoke this handler to handle an [InputMessage]
     */
    override fun invoke(message: InputMessage) {
        function.call(message)
    }
}