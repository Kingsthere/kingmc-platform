package kingmc.platform.audience.exception

/**
 * An exception thrown when sending information to an audience while it's not online
 *
 * @since 0.0.7
 * @author kingsthere
 */
class AudienceOfflineException(message: String?) : Exception(message)