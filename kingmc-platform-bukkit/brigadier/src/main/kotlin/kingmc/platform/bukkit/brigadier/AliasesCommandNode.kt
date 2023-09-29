package kingmc.platform.bukkit.brigadier

import com.mojang.brigadier.AmbiguityConsumer
import com.mojang.brigadier.StringReader
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.context.CommandContextBuilder
import com.mojang.brigadier.context.StringRange
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import com.mojang.brigadier.tree.ArgumentCommandNode
import com.mojang.brigadier.tree.CommandNode
import com.mojang.brigadier.tree.LiteralCommandNode
import java.util.*
import java.util.concurrent.CompletableFuture

@Suppress("UNCHECKED_CAST")
class AliasesCommandNode<S>(private val alias: String, private val target: LiteralCommandNode<S>)
    : LiteralCommandNode<S>(alias, target.command, target.requirement, target.redirect, target.redirectModifier, target.isFork) {
    init {
        val literalsField = CommandNode::class.java.getDeclaredField("literals")
        val childrenField = CommandNode::class.java.getDeclaredField("children")
        val argumentsField = CommandNode::class.java.getDeclaredField("arguments")
        literalsField.isAccessible = true
        childrenField.isAccessible = true
        argumentsField.isAccessible = true
        val targetLiterals = literalsField.get(target) as MutableMap<String, LiteralCommandNode<*>>
        val targetChildren = childrenField.get(target) as MutableMap<String, CommandNode<*>>
        val targetArguments = argumentsField.get(target) as MutableMap<String, ArgumentCommandNode<*, *>>
        val thisLiterals = literalsField.get(this) as MutableMap<String, LiteralCommandNode<*>>
        val thisChildren = childrenField.get(this) as MutableMap<String, CommandNode<*>>
        val thisArguments = argumentsField.get(this) as MutableMap<String, ArgumentCommandNode<*, *>>

        thisLiterals.putAll(targetLiterals)
        thisChildren.putAll(targetChildren)
        thisArguments.putAll(targetArguments)
        literalsField.isAccessible = false
        childrenField.isAccessible = false
        argumentsField.isAccessible = false
    }

    val literalLowercase
        get() = literal.lowercase()

    override fun isValidInput(input: String?): Boolean {
        return parse(StringReader(input)) > -1
    }

    override fun getName(): String {
        return alias
    }

    override fun getUsageText(): String {
        return alias
    }

    override fun createBuilder(): LiteralArgumentBuilder<S> {
        val builder = LiteralArgumentBuilder.literal<S>(literal)
        builder.requires(requirement)
        builder.forward(redirect, redirectModifier, isFork)
        if (command != null) {
            builder.executes(command)
        }
        return builder
    }

    override fun getSortedKey(): String {
        return alias
    }

    override fun getExamples(): MutableCollection<String> {
        return Collections.singleton(alias)
    }

    override fun listSuggestions(
        context: CommandContext<S>,
        builder: SuggestionsBuilder,
    ): CompletableFuture<Suggestions> {
        return if (literalLowercase.startsWith(builder.remainingLowerCase)) {
            builder.suggest(literal).buildFuture()
        } else {
            Suggestions.empty()
        }
    }

    override fun parse(reader: StringReader, contextBuilder: CommandContextBuilder<S>) {
        val start = reader.cursor
        val end = parse(reader)
        if (end > -1) {
            contextBuilder.withNode(this, StringRange.between(start, end))
            return
        }
    }

    private fun parse(reader: StringReader): Int {
        val start = reader.cursor
        if (reader.canRead(alias.length)) {
            val end: Int = start + alias.length
            if (reader.string.substring(start, end) == alias) {
                reader.cursor = end
                if (!reader.canRead() || reader.peek() == ' ') {
                    return end
                } else {
                    reader.cursor = start
                }
            }
        }
        return -1
    }

    override fun findAmbiguities(consumer: AmbiguityConsumer<S>?) {
        return target.findAmbiguities(consumer)
    }

    override fun getRelevantNodes(input: StringReader): Collection<CommandNode<S>?>? {
        return target.getRelevantNodes(input)
    }
}