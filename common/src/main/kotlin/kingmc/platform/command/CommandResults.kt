package kingmc.platform.command

import kingmc.platform.command.model.Handler
import kingmc.platform.command.model.Node
import kingmc.util.KingMCDsl


/**
 * A command result indicating that the command
 * runs successfully
 *
 * @since 0.0.3
 * @author kingsthere
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
 * @since 0.0.3
 * @author kingsthere
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
 * @since 0.0.3
 * @author kingsthere
 */
object FailedSingleton : Failed {
    override fun toString(): String {
        return "Failed"
    }
}

/**
 * Singleton implement of command result [Failed]
 *
 * @since 0.0.3
 * @author kingsthere
 */
object SuccessSingleton : Success {
    override fun toString(): String {
        return "Success"
    }
}

/**
 * Implement of command result with a specifies integer
 *
 * @since 0.0.3
 * @author kingsthere
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
 * @since 0.0.3
 * @author kingsthere
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
 * @since 0.0.3
 * @author kingsthere
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

@KingMCDsl
fun success(): Success =
    SuccessSingleton

@KingMCDsl
fun failed(): Failed =
    FailedSingleton

@KingMCDsl
fun failed(cause: String) =
    FailedWithCause(cause)

@KingMCDsl
fun failedSyntax(node: Node, handler: Handler?) =
    FailedCauseSyntax(node, handler)

@KingMCDsl
fun resulted(node: Node, result: Int) =
    ResultedCommandResult(result)