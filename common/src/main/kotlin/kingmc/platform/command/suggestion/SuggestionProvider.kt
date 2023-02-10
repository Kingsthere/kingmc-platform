package kingmc.platform.command.suggestion

import kingmc.platform.command.CommandContext

/**
 * A functional interface for a command handler to suggest completion of
 * commands, to suggest possible completion that user is trying
 * to send
 * 
 * @since 0.0.3
 * @author kingsthere
 */
@FunctionalInterface
fun interface SuggestionProvider : suspend SuggestionsBuilder.(CommandContext) -> Unit