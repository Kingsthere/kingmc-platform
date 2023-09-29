package kingmc.platform.messaging

/**
 * Thrown when registering a listener into a plugin messaging channel
 */
class PluginMessageListenerRegistrationException(message: String?) : RuntimeException(message)