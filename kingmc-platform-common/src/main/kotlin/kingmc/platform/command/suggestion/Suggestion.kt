// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package kingmc.platform.command.suggestion

import java.util.*

class Suggestion(val range: StringRange, val text: String, val tooltip: String? = null) : Comparable<Suggestion> {
    fun apply(input: String): String {
        if (range.start == 0 && range.end == input.length) {
            return text
        }
        val result = StringBuilder()
        if (range.start > 0) {
            result.append(input.substring(0, range.start))
        }
        result.append(text)
        if (range.end < input.length) {
            result.append(input.substring(range.end))
        }
        return result.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is Suggestion) {
            return false
        }
        return Objects.equals(range, other.range) && Objects.equals(text, other.text) && Objects.equals(
            tooltip,
            other.tooltip
        )
    }

    override fun hashCode(): Int {
        return Objects.hash(range, text, tooltip)
    }

    override fun toString(): String {
        return "Suggestion{" +
                "range=" + range +
                ", text='" + text + '\'' +
                ", tooltip='" + tooltip + '\'' +
                '}'
    }

    override fun compareTo(other: Suggestion): Int {
        return text.compareTo(other.text)
    }

    fun compareToIgnoreCase(b: Suggestion): Int {
        return text.compareTo(b.text, ignoreCase = true)
    }

    fun expand(command: String, range: StringRange): Suggestion {
        if (range == this.range) {
            return this
        }
        val result = StringBuilder()
        if (range.start < this.range.start) {
            result.append(command.substring(range.start, this.range.start))
        }
        result.append(text)
        if (range.end > this.range.end) {
            result.append(command.substring(this.range.end, range.end))
        }
        return Suggestion(range, result.toString(), tooltip)
    }
}