package kingmc.platform.command.model

import kingmc.common.application.Application

/**
 * A default implementation of [Header]
 *
 * @since 0.0.3
 * @author kingsthere
 */
class SimpleHeader(override var name: String, override var description: String? = null, override var namespace: String,
                   override val children: MutableSet<Node> = mutableSetOf(), override val handlers: MutableSet<Handler> = mutableSetOf(),
                   override val aliases: MutableSet<String> = mutableSetOf(),
                   override val application: Application,
                 ) : Header {
    /**
     * The parent node that owning this node
     */
    override var parent: Node? = null

    override fun toString(): String {
        return "/$name"
    }
}