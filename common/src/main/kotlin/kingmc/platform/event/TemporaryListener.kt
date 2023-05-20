package kingmc.platform.event

/**
 * A `TemporaryListener` is a listener to register into [Publisher] for scoped stuff, such
 * as inventory closing..., this listener register into a [publisher] when instance constructing
 *
 * @param publisher the publisher of this listener to register into
 * @since 0.0.6
 * @author kingsthere
 */
abstract class TemporaryListener(publisher: Publisher? = null) : Listener(publisher) {
    /**
     * Register this listener into publisher
     */
    init {
        this.activate()
    }
}