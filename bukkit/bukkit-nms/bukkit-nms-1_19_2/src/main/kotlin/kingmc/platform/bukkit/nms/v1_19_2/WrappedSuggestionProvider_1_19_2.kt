package kingmc.platform.bukkit.nms.v1_19_2

import com.mojang.brigadier.LiteralMessage
import com.mojang.brigadier.context.StringRange
import com.mojang.brigadier.suggestion.Suggestion
import com.mojang.brigadier.suggestion.SuggestionProvider
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import kingmc.platform.bukkit.brigadier.BrigadierNMS
import kingmc.platform.command.CommandContext
import kingmc.platform.command.parameter.Parameters
import kotlinx.coroutines.runBlocking
import net.minecraft.commands.CommandSourceStack
import java.util.concurrent.CompletableFuture

class WrappedSuggestionProvider_1_19_2(val suggestionProvider: kingmc.platform.command.suggestion.SuggestionProvider, val brigadierNMS: BrigadierNMS<CommandSourceStack>) : SuggestionProvider<CommandSourceStack> {
    override fun getSuggestions(
        css: com.mojang.brigadier.context.CommandContext<CommandSourceStack>,
        builder: SuggestionsBuilder,
    ): CompletableFuture<Suggestions> {
         val wrappedSender = brigadierNMS.getCommandSender(css)
         val wrappedSuggestionBuilder = kingmc.platform.command.suggestion.SuggestionsBuilder(builder.input, builder.start)
         val parameters = Parameters.EMPTY
         return CompletableFuture.supplyAsync {
             return@supplyAsync runBlocking {
                 suggestionProvider.invoke(wrappedSuggestionBuilder, CommandContext(wrappedSender, parameters, css.input))
                 val suggestions = wrappedSuggestionBuilder.build()
                 return@runBlocking Suggestions(getStringRange(suggestions.range), suggestions.list.map { getSuggestion(it) })
             }
         }
    }

    private fun getSuggestion(suggestion: kingmc.platform.command.suggestion.Suggestion): Suggestion {
        return Suggestion(getStringRange(suggestion.range), suggestion.text, suggestion.tooltip?.let { LiteralMessage(it) })
    }

    private fun getStringRange(stringRange: kingmc.platform.command.suggestion.StringRange): StringRange {
        return StringRange(stringRange.start, stringRange.end)
    }
}