package kingmc.platform.messaging

import kingmc.util.annotation.AliasFor
import kingmc.util.annotation.Extended
import kingmc.common.context.annotation.Component

/**
 * Annotation to register a plugin messaging channel listener
 *
 * @since 0.0.4
 * @author kingsthere
 */
@Extended(Component::class)
@Retention
@Target(AnnotationTarget.CLASS)
annotation class PluginMessageListener(
    /**
     * The name of the plugin messaging channel this listener registered to
     * be listened to
     */
    val pluginMessagingChannel: String,

    /**
     * The bean name, aliased [name]
     */
    @get:AliasFor("name")
    val value: String = "",

    /**
     * The bean name of this type to the container, left
     * to default it will become the decapitalized name of the class
     *
     * @since 0.0.1
     */
    @get:AliasFor("value")
    val name: String = ""
)
