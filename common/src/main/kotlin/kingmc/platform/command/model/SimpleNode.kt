package kingmc.platform.command.model

import kingmc.common.application.Application

/**
 * A default implementation of [Node]
 *
 * @since 0.0.3
 * @author kingsthere
 */
class SimpleNode(override var name: String, override var parent: Node?, override var description: String?,
                 override val children: MutableSet<Node> = mutableSetOf(), override val handlers: MutableSet<Handler> = mutableSetOf(),
                 override val aliases: MutableSet<String> = mutableSetOf(),
                 override val application: Application
) : Node {
    override fun toString(): String {
        return "<$name>"
    }
}