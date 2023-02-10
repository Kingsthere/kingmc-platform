package kingmc.platform.event

import kingmc.common.application.WithApplication

/**
 * Indicate that the function annotated with this
 * annotation is a handler to handle the events
 * received from [Publisher], this annotation only
 * use in the [Listener classes][Listener]
 *
 * @see Event
 * @see Listener
 * @see Publisher
 * @since 0.0.1
 * @author kingsthere
 */
@MustBeDocumented
@Retention
@Target(AnnotationTarget.FUNCTION)
@WithApplication // When @Subscribe function called by publisher it auto surrounded with application {  }
annotation class Subscribe(
    /**
     * The priority of this event handler
     *
     *
     * An event could have many handlers, this
     * property is to order the handlers to handle
     * the event when received from publisher
     *
     * @since 0.0.1
     */
    val priority: Byte = 0

)
