package kingmc.platform.bukkit.brigadier

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.tree.CommandNode
import com.mojang.brigadier.tree.LiteralCommandNode

@Suppress("UNCHECKED_CAST")
fun CommandDispatcher<*>.removeCommand(name: String) {
    val root = this.root
    val literals = root::class.java.getField("literals").get(root) as MutableMap<String, LiteralCommandNode<*>>
    val children = root::class.java.getField("children").get(root) as MutableMap<String, CommandNode<*>>
    literals.remove(name)
    children.remove(name)
}