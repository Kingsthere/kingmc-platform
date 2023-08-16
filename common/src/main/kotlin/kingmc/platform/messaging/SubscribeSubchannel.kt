package kingmc.platform.messaging

/**
 * An annotation to bound a function to a sub channel as the handler
 * of specified sub channel
 *
 * @since 0.0.4
 * @author kingsthere
 */
@Retention
@Target(AnnotationTarget.FUNCTION)
annotation class SubscribeSubChannel(
    /**
     * The name of ths sub channel to subscribe
     */
    val subChannel: String = ""
)
