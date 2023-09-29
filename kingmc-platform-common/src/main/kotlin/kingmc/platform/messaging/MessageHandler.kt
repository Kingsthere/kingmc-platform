package kingmc.platform.messaging

/**
 * Indicate a handler to handle messages come from a messaging channel
 *
 * @author kingsthere
 * @since 0.0.6
 */
interface MessageHandler {
    /**
     * Invoke this handler to handle an [InputMessage]
     */
    operator fun invoke(message: InputMessage)
}