package kingmc.platform.event

import kingmc.util.annotation.AliasFor
import kingmc.util.annotation.Extended
import kingmc.common.context.annotation.Component
import kingmc.util.SubclassSingleton
import kotlin.reflect.KClass

/**
 * An annotation indicate that the classes annotated with
 * [@Listener][Listener] is a listener to listen the events
 * from [Publisher]
 *
 *
 * The listener classes must:
 * + is a [Singleton][kingmc.util.Singleton]
 *
 *
 * Use annotation [Subscribe] to annotate the functions
 * in the listener class to register a handler to handle
 * the events when events received
 *
 * @since 0.0.1
 * @author kingsthere
 * @see Subscribe
 * @see Publisher
 * @see Event
 */
@Extended(Component::class)
@Retention
@Target(AnnotationTarget.CLASS)
@SubclassSingleton
annotation class Listener (
    /**
     * The class of publisher of this
     * listener listening to
     *
     * @since 0.0.1
     * @see KClass
     * @see Publisher
     */
    val publisher: KClass<out Publisher> = Publisher::class,

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
