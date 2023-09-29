package kingmc.platform.command.exceptions

/**
 * This exception thrown when exception happened while executing a command
 *
 * @author kingsthere
 * @since 0.0.5
 */
open class CommandExecutionException(message: String?, cause: Throwable?) : Exception(message, cause)