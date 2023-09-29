package kingmc.platform.audience.exception

/**
 * An exception thrown when sending information to an audience while it's not online
 *
 * @author kingsthere
 * @since 0.0.7
 */
class AudienceOfflineException(message: String?) : Exception(message)