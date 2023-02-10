package kingmc.platform.event

/**
 * A `Proxy` is a type of [Publisher] that only
 * receive events from others and forward them
 * to the subscribed listeners
 *
 * @since 0.0.1
 * @author kingsthere
 */
abstract class Proxy : AbstractPublisher() {

}