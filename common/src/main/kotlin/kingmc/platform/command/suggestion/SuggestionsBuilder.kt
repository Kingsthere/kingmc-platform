package kingmc.platform.command.suggestion

import java.util.concurrent.CompletableFuture

class SuggestionsBuilder(val input: String, private val inputLowerCase: String, val start: Int) {
    private val remaining: String = input.substring(start)
    private val remainingLowerCase: String = inputLowerCase.substring(start)
    private val result: MutableList<Suggestion> = ArrayList()
    var filter: (text: String, tooltip: String?) -> Boolean = { _, _ -> true }

    constructor(input: String, start: Int) : this(input, input.lowercase(), start)

    fun build(): Suggestions {
        return Suggestions.create(input, result)
    }

    fun buildFuture(): CompletableFuture<Suggestions> {
        return CompletableFuture.completedFuture(build())
    }

    fun suggest(text: String): SuggestionsBuilder {
        if (!filter(text, null)) {
            return this
        }
        if (text == remaining) {
            return this
        }
        result.add(
            Suggestion(
                StringRange.between(
                    start, input.length
                ), text
            )
        )
        return this
    }

    fun suggest(text: String, tooltip: String): SuggestionsBuilder {
        if (!filter(text, tooltip)) {
            return this
        }
        if (text == remaining) {
            return this
        }
        result.add(
            Suggestion(
                StringRange.between(
                    start, input.length
                ), text, tooltip
            )
        )
        return this
    }

    fun add(other: SuggestionsBuilder): SuggestionsBuilder {
        result.addAll(other.result)
        return this
    }

    fun createOffset(start: Int): SuggestionsBuilder {
        return SuggestionsBuilder(input, inputLowerCase, start)
    }

    fun restart(): SuggestionsBuilder {
        return createOffset(start)
    }
}