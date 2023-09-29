package kingmc.platform.bukkit.brigadier

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.tree.CommandNode
import com.mojang.brigadier.tree.LiteralCommandNode
import kingmc.common.logging.error

fun CommandDispatcher<*>.removeCommand(name: String) {
    val root = this.root
    root.removeCommand(name)
}

@Suppress("UNCHECKED_CAST")
fun CommandNode<*>.removeCommand(name: String) {
    try {
        val literals = this::class.java.getField("literals").get(this) as MutableMap<String, LiteralCommandNode<*>>
        val children = this::class.java.getField("children").get(this) as MutableMap<String, CommandNode<*>>
        literals.remove(name)
        children.remove(name)
    } catch (e: NoSuchFieldException) {
        try {
            // Remove command from CommandDispatcher on newer brigadier version
            this::class.java.getMethod("removeCommand", String::class.java).invoke(this, name)
        } catch (e: Exception) {
            error("Unable to remove command $name from command dispatcher", e)
        }
    }
}