package kingmc.platform.command.exceptions

/**
 * This exception thrown when exception happened while executing a command
 *
 * @since 0.0.5
 * @author kingsthere
 */
open class CommandExecutionException(message: String?, cause: Throwable?) : Exception(message, cause)