package kingmc.platform.command.model

import kingmc.common.application.Application

/**
 * A node for a command tree's constructing, a node is to
 * split the branches of the command to execute the subcommands
 * from a command tree with different types of feature they do
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface Node {
    /**
     * The name of this node
     */
    var name: String

    /**
     * The parent node that owning this node
     */
    var parent: Node?

    /**
     * The description of this node
     */
    var description: String?

    /**
     * The children of this node
     */
    val children: MutableSet<Node>

    /**
     * The handlers of this node
     */
    val handlers: MutableSet<Handler>

    /**
     * The aliases of this node
     */
    val aliases: MutableSet<String>

    /**
     * The application that registers this node
     */
    val application: Application<*>
}