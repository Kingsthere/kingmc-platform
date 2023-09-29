package kingmc.platform.command.alias

import kingmc.platform.command.model.Handler
import kingmc.platform.command.model.Node

/**
 * Add aliases to this handler
 *
 * @author kingsthere
 * @since 0.0.4
 * @see aliases
 */
fun Handler.aliases(vararg aliases: String): Handler {
    this.aliases.addAll(aliases)
    return this
}

/**
 * Add aliases to this handler
 *
 * @author kingsthere
 * @since 0.0.4
 * @see aliases
 */
fun Node.aliases(vararg aliases: String): Node {
    this.aliases.addAll(aliases)
    return this
}