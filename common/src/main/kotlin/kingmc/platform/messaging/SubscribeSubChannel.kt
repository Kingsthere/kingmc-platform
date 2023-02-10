package kingmc.platform.messaging

import com.ktil.annotation.AliasFor

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
     * The name of ths sub channel to subscribe, alias [subChannel]
     */
    @get:AliasFor(attribute = "subChannel")
    val value: String = "",

    /**
     * The name of ths sub channel to subscribe
     */
    @get:AliasFor(attribute = "value")
    val subChannel: String = ""
)
