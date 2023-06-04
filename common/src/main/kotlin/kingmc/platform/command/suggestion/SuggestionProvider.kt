package kingmc.platform.command.suggestion

import kingmc.common.application.WithApplication
import kingmc.platform.command.CommandContext

/**
 * An interface for a command handler to suggest completion of
 * commands, to suggest possible completion that user is trying
 * to send
 * 
 * @since 0.0.3
 * @author kingsthere
 */
sealed interface SuggestionProvider

/**
 * A `SuggestionProvider` implementation provide suggestions **blocking**
 */
open class BlockingSuggestionProvider(val function: @WithApplication SuggestionsBuilder.(CommandContext) -> Unit): SuggestionProvider

/**
 * A `SuggestionProvider` implementation provide suggestions in **suspend function**
 */
open class SuspendSuggestionProvider(val function: @WithApplication suspend SuggestionsBuilder.(CommandContext) -> Unit): SuggestionProvider