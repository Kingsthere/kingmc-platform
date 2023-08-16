package kingmc.platform.command

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.command.coroutine.CoroutineContextHandler
import kingmc.platform.command.model.*
import kingmc.platform.commandFactory

/**
 * Run a statement with command manager of current application
 *
 * @since 0.0.3
 * @author kingsthere
 * @return the returning value for statement with command manager
 */
@WithApplication
@KingMCCommandDSL
inline fun commands(run: @WithApplication CommandManager.() -> Unit) =
    currentApplication().commandFactory.apply(run)

/**
 * Create & configure a new command node and register it to the receiver node 
 *
 * @since 0.1.1
 * @author kingsthere
 */
@KingMCCommandDSL
inline fun Node.node(name: String, block: @WithApplication Node.() -> Unit) =
    Node(name, block).also { register(it) }

/**
 * Create and configure a child node from current node
 *
 * @param name the name of this node
 * @return the node created
 */
@WithApplication
inline fun Node(name: String, config: @WithApplication Node.() -> Unit): Node {
    return SimpleNode(name, null, null, application = currentApplication()).apply(config)
}

/**
 * Create a [Header] and configure it
 *
 * @return the Header created
 */
@WithApplication
inline fun Header(name: String, namespace: String = currentApplication().commandFactory.defaultCommandNamespace, config: @WithApplication Node.() -> Unit = { }): Header {
    return SimpleHeader(name = name, namespace = namespace, application = currentApplication()).apply(config)
}

/**
 * Create a [BlockingHandler] and configure it
 *
 * @return the BlockingHandler created
 */
@WithApplication
inline fun BlockingHandler(name: String = ".root", config: @WithApplication BlockingHandler.() -> Unit): BlockingHandler {
    return BlockingHandler(name, application = currentApplication()).apply(config)
}

@WithApplication
inline fun CoroutineContextHandler(name: String, config: @WithApplication CoroutineContextHandler.() -> Unit): CoroutineContextHandler {
    return CoroutineContextHandler(name = name, application = currentApplication()).apply(config)
}

/**
 * A shortcut to register a [Header] into command manager of current application
 */
@WithApplication
fun <TNode : Header> registerCommand(node: TNode): Command<TNode> {
    return currentApplication().commandFactory.register(node)
}

/**
 * Register a [Header] into this command manager
 */
fun <TNode : Header> CommandManager.register(node: TNode): Command<TNode> {
    val command = CommandImpl(node)
    this.register(command)
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