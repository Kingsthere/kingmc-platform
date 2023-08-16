package kingmc.platform.command.suggestion

/**
 * The word that currently entering
 *
 * @since 0.1.0
 * @author kingsthere
 */
val SuggestionsBuilder.entering: String
    get() = input.substring(start).substringBefore(" ")

/**
 * Enable filter to filter suggestions with last string entering
 */
fun SuggestionsBuilder.enableFilterStartsWith() {
    val localFilter = filter
    filter = { text, tooltip ->
        localFilter.invoke(text, tooltip) && text.startsWith(entering)
    }
}