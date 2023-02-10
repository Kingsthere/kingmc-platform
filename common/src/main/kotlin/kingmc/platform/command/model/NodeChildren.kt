package kingmc.platform.command.model

/**
 * Add a child node to current node
 *
 * @since 0.0.3
 * @author kingsthere
 */
operator fun Node.plus(node: Node) {
    this.children.add(node)
}

