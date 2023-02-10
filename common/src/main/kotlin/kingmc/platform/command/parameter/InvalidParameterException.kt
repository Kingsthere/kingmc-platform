package kingmc.platform.command.parameter

/**
 * Thrown when trying to use [Parameters] to acquire a parameter
 * from command invocation but that parameter is not supported
 * in that [Parameters]
 *
 * @since 0.0.3
 * @author kingsthere
 */
class InvalidParameterException(message: String?) : Exception(message)