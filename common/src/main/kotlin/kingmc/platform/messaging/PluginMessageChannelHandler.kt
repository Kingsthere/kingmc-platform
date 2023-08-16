package kingmc.platform.messaging

import kingmc.common.context.annotation.Component

/**
 * Base annotation for declaring a [MessageHandler] to handle plugin messages
 *
 * @since 0.0.4
 * @author kingsthere
 */
@Component
@Retention
@Target(AnnotationTarget.CLASS)
annotation class PluginMessageChannelHandler(
    /**
     * The name of the plugin messaging channel this listener registered to
     * be listened to
     */
    val pluginMessagingChannel: String,

    /**
     * The bean name
     */
    val value: String = "",
)
