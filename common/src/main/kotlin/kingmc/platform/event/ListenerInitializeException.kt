package kingmc.platform.event

/**
 * Thrown when an exception thrown trying to initialize a listener
 *
 * @since 0.0.1
 * @author kingsthere
 */
class ListenerInitializeException(message: String?, cause: Throwable?) : RuntimeException(message, cause)