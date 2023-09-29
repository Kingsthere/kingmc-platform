package kingmc.platform.command.model

/**
 * Add a child node to current node
 *
 * @author kingsthere
 * @since 0.0.3
 */
operator fun Node.plus(node: Node) {
    this.children.add(node)
}

