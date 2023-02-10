// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package kingmc.platform.command.suggestion

import java.util.*
import java.util.concurrent.CompletableFuture

class Suggestions(val range: StringRange, val list: List<Suggestion>) {

    val isEmpty: Boolean
        get() = list.isEmpty()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is Suggestions) {
            return false
        }
        return range == other.range && list == other.list
    }

    override fun hashCode(): Int {
        return Objects.hash(range, list)
    }

    override fun toString(): String {
        return "Suggestions{" +
                "range=" + range +
                ", suggestions=" + list +
                '}'
    }

    companion object {
        private val EMPTY = Suggestions(StringRange.at(0), ArrayList())
        fun empty(): CompletableFuture<Suggestions> {
            return CompletableFuture.completedFuture(EMPTY)
        }

        fun merge(command: String?, input: Collection<Suggestions>): Suggestions {
            if (input.isEmpty()) {
                return EMPTY
            } else if (input.size == 1) {
                return input.iterator().next()
            }
            val texts: MutableSet<Suggestion> = HashSet()
            for (suggestions in input) {
                texts.addAll(suggestions.list)
            }
            return create(command, texts)
        }

        fun create(command: String?, suggestions: Collection<Suggestion>): Suggestions {
            if (suggestions.isEmpty()) {
                return EMPTY
            }
            var start = Int.MAX_VALUE
            var end = Int.MIN_VALUE
            for (suggestion in suggestions) {
                start = suggestion.range.start.coerceAtMost(start)
                end = suggestion.range.end.coerceAtLeast(end)
            }
            val range = StringRange(start, end)
            val texts: MutableSet<Suggestion> = HashSet()
            for (suggestion in suggestions) {
                texts.add(suggestion.expand(command!!, range))
            }
            val sorted: List<Suggestion> = ArrayList(texts)
            sorted.sortedWith { a: Suggestion, b: Suggestion? ->
                a.compareToIgnoreCase(
                    b!!
                )
            }
            return Suggestions(range, sorted)
        }
    }
}