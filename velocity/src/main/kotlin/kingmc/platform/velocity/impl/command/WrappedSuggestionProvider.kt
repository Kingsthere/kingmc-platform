package kingmc.platform.velocity.impl.command

import com.mojang.brigadier.LiteralMessage
import com.mojang.brigadier.context.StringRange
import com.mojang.brigadier.suggestion.Suggestion
import com.mojang.brigadier.suggestion.SuggestionProvider
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import com.velocitypowered.api.command.CommandSource
import kingmc.common.application.Application
import kingmc.common.application.withApplication
import kingmc.common.application.withApplicationSuspend
import kingmc.common.coroutine.ApplicationCoroutineScope
import kingmc.common.coroutine.CoroutineDispatcherWithApplication
import kingmc.platform.command.CommandContext
import kingmc.platform.command.parameter.Parameters
import kingmc.platform.command.suggestion.BlockingSuggestionProvider
import kingmc.platform.command.suggestion.SuspendSuggestionProvider
import kingmc.platform.velocity.asKingMC
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture

class WrappedSuggestionProvider(
    val suggestionProvider: kingmc.platform.command.suggestion.SuggestionProvider,
    val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default,
    val application: Application
) : SuggestionProvider<CommandSource> {
    override fun getSuggestions(
        css: com.mojang.brigadier.context.CommandContext<CommandSource>,
        builder: SuggestionsBuilder,
    ): CompletableFuture<Suggestions> {
        val future = CompletableFuture<Suggestions>()
        val wrappedSuggestionBuilder =
                kingmc.platform.command.suggestion.SuggestionsBuilder(input = builder.input, start = builder.start)
        val parameters = Parameters.EMPTY
        if (suggestionProvider is BlockingSuggestionProvider) {
            withApplication(application) {
                suggestionProvider.function.invoke(
                        wrappedSuggestionBuilder,
                        CommandContext(css.source.asKingMC(application), parameters, css.input)
                )
            }
        }
        if (suggestionProvider is SuspendSuggestionProvider) {
            val dispatcher = suggestionProvider.dispatcher
            // Make sure that the suspend suggester running with application
            if (dispatcher is CoroutineDispatcherWithApplication) {
                ApplicationCoroutineScope(context = dispatcher, application = application).launch {
                    suggestionProvider.function.invoke(wrappedSuggestionBuilder, CommandContext(css.source.asKingMC(application), parameters, css.input))
                }
            } else {
                ApplicationCoroutineScope(context = dispatcher, application = application).launch {
                    withApplicationSuspend(application) {
                        suggestionProvider.function.invoke(
                                wrappedSuggestionBuilder,
                                CommandContext(css.source.asKingMC(application), parameters, css.input)
                        )
                    }
                }
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