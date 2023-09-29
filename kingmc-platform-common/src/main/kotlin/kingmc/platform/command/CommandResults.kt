package kingmc.platform.command

import kingmc.platform.command.model.Handler
import kingmc.platform.command.model.Node


/**
 * A command result indicating that the command
 * runs successfully
 *
 * @author kingsthere
 * @since 0.0.3
 */
interface Success : CommandResult {
    /**
     * Forward this command's invocation and parameter to the specified
     * command
     *
     * @since 0.0.3
     */
    override val forwardTo: Node?
        get() = null

    override fun asInt(): Int = 0
}

/**
 * A command result indicating that the command
 * runs failed without cause
 *
 * @author kingsthere
 * @since 0.0.3
 */
interface Failed : CommandResult {
    /**
     * Forward this command's invocation and parameter to the specified
     * command
     *
     * @since 0.0.3
     */
    override val forwardTo: Node?
        get() = null

    override fun asInt(): Int = 0
}

/**
 * Singleton implement of command result [Failed]
 *
 * @author kingsthere
 * @since 0.0.3
 */
object FailedSingleton : Failed {
    override fun toString(): String {
        return "Failed"
    }
}

/**
 * Singleton implement of command result [Failed]
 *
 * @author kingsthere
 * @since 0.0.3
 */
object SuccessSingleton : Success {
    override fun toString(): String {
        return "Success"
    }
}

/**
 * Implement of command result with a specifies integer
 *
 * @author kingsthere
 * @since 0.0.3
 */
class ResultedCommandResult(val result: Int) : CommandResult {
    override val forwardTo: Node?
        get() = null

    override fun asInt(): Int =
        result

    override fun toString(): String {
        return "Success"
    }
}

/**
 * A command result indicating that the command
 * runs failed with cause
 *
 * @author kingsthere
 * @since 0.0.3
 * @param cause the cause of this fail
 */
class FailedWithCause(private val cause: String) : Failed, CommandResult {
    /**
     * Forward this command's invocation and parameter to the specified
     * command
     *
     * @since 0.0.3
     */
    override val forwardTo: Node?
        get() = null

    override fun toString(): String {
        return "FailedWithCause(cause='$cause')"
    }
}

/**
 * A command result indicating that the command
 * runs failed cause by a syntax error
 *
 * @author kingsthere
 * @since 0.0.3
 * @param node the last node was found
 * @param handler the last handler was found
 */
class FailedCauseSyntax(
    val node: Node,
    val handler: Handler?
    ) : Failed, CommandResult {
    /**
     * Forward this command's invocation and parameter to the specified
     * command
     *
     * @since 0.0.3
     */
    override val forwardTo: Node?
        get() = null

    override fun toString(): String {
        return "FailedCauseSyntax(node=$node, handler=$handler)"
    }
}

@KingMCCommandDSL
fun success(): Success =
    SuccessSingleton

@KingMCCommandDSL
fun failed(): Failed =
    FailedSingleton

@KingMCCommandDSL
fun failed(cause: String) =
    FailedWithCause(cause)

@KingMCCommandDSL
fun failedSyntax(node: Node, handler: Handler?) =
    FailedCauseSyntax(node, handler)

@KingMCCommandDSL
fun resulted(node: Node, result: Int) =
    ResultedCommandResult(result)