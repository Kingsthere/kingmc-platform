package kingmc.platform.messaging

/**
 * Annotate a bean to configure and open a plugin messaging channel
 *
 * @since 0.0.4
 * @author kingsthere
 */
@Retention
@Target(AnnotationTarget.CLASS)
annotation class OpenPluginMessagingChannel(
    /**
     * The plugin messaging channels to open
     */
    vararg val channel: String
)
