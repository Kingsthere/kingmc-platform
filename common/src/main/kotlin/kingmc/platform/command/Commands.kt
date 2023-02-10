package kingmc.platform.command

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.command.coroutine.CoroutineContextSuspendHandler
import kingmc.platform.command.model.*
import kingmc.platform.commands
import kingmc.platform.platform
import kingmc.util.KingMCDsl

/**
 * Run a statement with command manager of current application
 *
 * @since 0.0.3
 * @author kingsthere
 * @return the returning value for statement with command manager
 */
@WithApplication
@KingMCDsl
fun commands(run: @WithApplication CommandManager.() -> Unit) =
    currentApplication().platform.commands.apply(run)

/**
 * Create and configure a child node from current node
 *
 * @param name the name of this node
 * @return the node created
 */
@WithApplication
fun Node(name: String, config: @WithApplication Node.() -> Unit): Node {
    return SimpleNode(name, null, null, application = currentApplication()).apply(config)
}

/**
 * Create a [Header] and configure it
 *
 * @return the Header created
 */
@WithApplication
fun Header(name: String, namespace: String = currentApplication().platform.commands.defaultCommandNamespace, config: @WithApplication Node.() -> Unit = { }): Header {
    return SimpleHeader(name = name, namespace = namespace, application = currentApplication()).apply(config)
}

/**
 * Create a [BlockingHandler] and configure it
 *
 * @return the BlockingHandler created
 */
@WithApplication
fun BlockingHandler(name: String = ".root", config: @WithApplication BlockingHandler.() -> Unit): BlockingHandler {
    return BlockingHandler(name, application = currentApplication()).apply(config)
}

@WithApplication
fun CoroutineContextSuspendHandler(name: String, config: @WithApplication CoroutineContextSuspendHandler.() -> Unit): CoroutineContextSuspendHandler {
    return CoroutineContextSuspendHandler(name = name, application = currentApplication()).apply(config)
}

/**
 * Register a [Header] into this command manager
 */
fun <TNode : Header> CommandManager.register(node: TNode): RegisteredCommand<TNode> {
    val command = RegisteredCommandImpl(node)
    this + command
    return command
}

/**
 * Register a child node into this node
 */
fun <TNode : Node> TNode.register(node: Node) {
    this.children.add(node)
    node.parent = this
}

/**
 * Register a handler into this node
 */
fun <TNode : Node> TNode.register(handler: Handler) {
    this.handlers.add(handler)
}