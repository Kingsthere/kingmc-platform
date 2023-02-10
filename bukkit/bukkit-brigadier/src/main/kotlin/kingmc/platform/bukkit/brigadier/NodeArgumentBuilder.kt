package kingmc.platform.bukkit.brigadier

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.tree.LiteralCommandNode
import kingmc.platform.command.model.Node

open class NodeArgumentBuilder<S>(
    val node: Node
) : LiteralArgumentBuilder<S>(node.name) {

    val description: String?
        get() = node.description
    val aliases: Set<String>
        get() = node.aliases

    override fun build(): LiteralCommandNode<S> {
        return super.build()
    }
}