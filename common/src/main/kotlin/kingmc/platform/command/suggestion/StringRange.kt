package kingmc.platform.command.suggestion

import kingmc.platform.command.ImmutableStringReader
import java.util.*

class StringRange(val start: Int, val end: Int) {

    operator fun get(reader: ImmutableStringReader): String {
        return reader.string.substring(start, end)
    }

    operator fun get(string: String): String {
        return string.substring(start, end)
    }

    val isEmpty: Boolean
        get() = start == end
    val length: Int
        get() = end - start

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o !is StringRange) {
            return false
        }
        return start == o.start && end == o.end
    }

    override fun hashCode(): Int {
        return Objects.hash(start, end)
    }

    override fun toString(): String {
        return "StringRange{" +
                "start=" + start +
                ", end=" + end +
                '}'
    }

    companion object {
        fun at(pos: Int): StringRange {
            return StringRange(pos, pos)
        }

        fun between(start: Int, end: Int): StringRange {
            return StringRange(start, end)
        }

        fun encompassing(a: StringRange, b: StringRange): StringRange {
            return StringRange(
                a.start.coerceAtMost(b.start), a.end.coerceAtLeast(b.end)
            )
        }
    }
}