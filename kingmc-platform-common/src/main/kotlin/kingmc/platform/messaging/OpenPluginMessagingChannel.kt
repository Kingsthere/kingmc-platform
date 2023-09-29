package kingmc.platform.messaging

/**
 * Annotate a bean to configure and open a plugin messaging channel
 *
 * @author kingsthere
 * @since 0.0.4
 */
@Retention
@Target(AnnotationTarget.CLASS)
annotation class OpenPluginMessagingChannel(
    /**
     * The plugin messaging channels to open
     */
    vararg val channel: String
)
