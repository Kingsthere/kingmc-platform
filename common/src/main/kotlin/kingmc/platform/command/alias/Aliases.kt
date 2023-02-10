package kingmc.platform.command.alias

import kingmc.platform.command.model.Handler
import kingmc.platform.command.model.Node

/**
 * Add aliases to this handler
 *
 * @since 0.0.4
 * @author kingsthere
 * @see aliases
 */
fun Handler.aliases(vararg aliases: String): Handler {
    this.aliases.addAll(aliases)
    return this
}

/**
 * Add aliases to this handler
 *
 * @since 0.0.4
 * @author kingsthere
 * @see aliases
 */
fun Node.aliases(vararg aliases: String): Node {
    this.aliases.addAll(aliases)
    return this
}