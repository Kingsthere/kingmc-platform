package kingmc.platform.bukkit.nms.v1_20_1.impl.brigadier

import com.mojang.brigadier.LiteralMessage
import com.mojang.brigadier.context.StringRange
import com.mojang.brigadier.suggestion.Suggestion
import com.mojang.brigadier.suggestion.SuggestionProvider
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import kingmc.common.application.Application
import kingmc.common.application.withApplication
import kingmc.common.coroutine.ApplicationCoroutineScope
import kingmc.platform.bukkit.brigadier.BrigadierNMS
import kingmc.platform.command.parameter.Parameters
import kingmc.platform.command.suggestion.BlockingSuggestionProvider
import kingmc.platform.commandContextFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.minecraft.commands.CommandListenerWrapper
import java.util.concurrent.CompletableFuture

class BlockingSuggestionProvider_1_19_2(
    private val suggestionProvider: BlockingSuggestionProvider,
    private val brigadierNMS: BrigadierNMS<CommandListenerWrapper>,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default,
    val application: Application
) : SuggestionProvider<CommandListenerWrapper> {
    val commandContextFactory = application.commandContextFactory

    override fun getSuggestions(
        css: com.mojang.brigadier.context.CommandContext<CommandListenerWrapper>,
        builder: SuggestionsBuilder,
    ): CompletableFuture<Suggestions> {
        val future = CompletableFuture<Suggestions>()
        val wrappedSender = brigadierNMS.getCommandSender(css)
        val wrappedSuggestionBuilder =
            kingmc.platform.command.suggestion.SuggestionsBuilder(builder.input, builder.start)
        val parameters = Parameters.EMPTY
        ApplicationCoroutineScope(coroutineDispatcher, application).launch {
            withApplication(application) {
                suggestionProvider.function(
                    wrappedSuggestionBuilder,
                    this@BlockingSuggestionProvider_1_19_2.commandContextFactory.create(
                        wrappedSender,
                        parameters,
                        css.input
                    )
                )
                val suggestions = wrappedSuggestionBuilder.build()
                future.complete(Suggestions(getStringRange(suggestions.range), suggestions.list.map { getSuggestion(it) }))
            }
        }
        return future
    }

    private fun getSuggestion(suggestion: kingmc.platform.command.suggestion.Suggestion): Suggestion {
        return Suggestion(
            getStringRange(suggestion.range),
            suggestion.text,
            suggestion.tooltip?.let { LiteralMessage(it) })
    }

    private fun getStringRange(stringRange: kingmc.platform.command.suggestion.StringRange): StringRange {
        return StringRange(stringRange.start, stringRange.end)
    }
}