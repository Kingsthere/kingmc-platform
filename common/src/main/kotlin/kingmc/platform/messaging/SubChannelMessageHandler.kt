package kingmc.platform.messaging

/**
 * A sub channel handler to handle [InputMessage]s receive from sub channels
 *
 * @since 0.0.4
 * @author kingsthere
 */
interface SubChannelMessageHandler {
    /**
     * The sub channel that this handler handled for
     */
    val subChannel: String

    /**
     * Invoke this handler to handle an [InputMessage]
     */
    operator fun invoke(message: InputMessage)
}