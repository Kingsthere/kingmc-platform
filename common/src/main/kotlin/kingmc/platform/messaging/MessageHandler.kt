package kingmc.platform.messaging

/**
 * Indicate a handler to handle messages come from a messaging channel
 *
 * @since 0.0.6
 * @author kingsthere
 */
interface MessageHandler {
    /**
     * Invoke this handler to handle an [InputMessage]
     */
    operator fun invoke(message: InputMessage)
}