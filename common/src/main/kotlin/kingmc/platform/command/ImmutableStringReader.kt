package kingmc.platform.command

import kingmc.platform.command.exceptions.CommandSyntaxException

/**
 * A superinterface used to read strings in specifies format
 *
 * @since 0.0.5
 * @author kingsthere
 */
interface ImmutableStringReader {
    val string: String
    val remainingLength: Int
    val totalLength: Int
    val cursor: Int
    val read: String
    val remaining: String
    fun canRead(length: Int): Boolean
    fun canRead(): Boolean
    fun peek(): Char
    fun peek(offset: Int): Char
}

open class StringReader : ImmutableStringReader {
    override val string: String
    override var cursor = 0

    constructor(other: StringReader) {
        string = other.string
        cursor = other.cursor
    }

    constructor(string: String) {
        this.string = string
    }

    override val totalLength: Int
        get() = string.length

    override val read: String
        get() = string.substring(0, cursor)

    override val remainingLength: Int
        get() = string.length - cursor

    override val remaining: String
        get() = string.substring(cursor)

    override fun canRead(length: Int): Boolean {
        return cursor + length <= string.length
    }

    override fun canRead(): Boolean {
        return canRead(1)
    }

    override fun peek(): Char {
        return string[cursor]
    }

    override fun peek(offset: Int): Char {
        return string[cursor + offset]
    }

    open fun read(): Char {
        return string[cursor++]
    }

    open fun skip() {
        cursor++
    }

    open fun skipWhitespace() {
        while (canRead() && Character.isWhitespace(peek())) {
            skip()
        }
    }

    @Throws(CommandSyntaxException::class)
    open fun readInt(): Int {
        val start = cursor
        while (canRead() && isAllowedNumber(peek())) {
            skip()
        }
        val number = string.substring(start, cursor)
        if (number.isEmpty()) {
            throw CommandSyntaxException("Expected integer", this.string, this.cursor)
        }
        return try {
            number.toInt()
        } catch (ex: NumberFormatException) {
            cursor = start
            throw CommandSyntaxException("Invalid integer $number", this.string, this.cursor)
        }
    }

    @Throws(CommandSyntaxException::class)
    open fun readLong(): Long {
        val start = cursor
        while (canRead() && isAllowedNumber(peek())) {
            skip()
        }
        val number = string.substring(start, cursor)
        if (number.isEmpty()) {
            throw CommandSyntaxException("Expected long", this.string, this.cursor)
        }
        return try {
            number.toLong()
        } catch (ex: NumberFormatException) {
            cursor = start
            throw CommandSyntaxException("Invalid long $number", this.string, this.cursor)
        }
    }

    @Throws(CommandSyntaxException::class)
    open fun readDouble(): Double {
        val start = cursor
        while (canRead() && isAllowedNumber(peek())) {
            skip()
        }
        val number = string.substring(start, cursor)
        if (number.isEmpty()) {
            throw CommandSyntaxException("Expected double", this.string, this.cursor)
        }
        return try {
            number.toDouble()
        } catch (ex: NumberFormatException) {
            cursor = start
            throw CommandSyntaxException("Invalid double $number", this.string, this.cursor)
        }
    }

    @Throws(CommandSyntaxException::class)
    open fun readFloat(): Float {
        val start = cursor
        while (canRead() && isAllowedNumber(peek())) {
            skip()
        }
        val number = string.substring(start, cursor)
        if (number.isEmpty()) {
            throw CommandSyntaxException("Expected float", this.string, this.cursor)
        }
        return try {
            number.toFloat()
        } catch (ex: NumberFormatException) {
            cursor = start
            throw CommandSyntaxException("Invalid float $number", this.string, this.cursor)
        }
    }

    open fun readUnsafeString(): String {
        val start = cursor
        while (canRead()) {
            skip()
        }
        return string.substring(start, cursor)
    }

    open fun readUnquotedString(): String {
        val start = cursor
        while (canRead() && isAllowedInUnquotedString(peek())) {
            skip()
        }
        return string.substring(start, cursor)
    }

    @Throws(CommandSyntaxException::class)
    open fun readQuotedString(): String {
        if (!canRead()) {
            return ""
        }
        val next = peek()
        if (!isQuotedStringStart(next)) {
            throw CommandSyntaxException("Expected quote to start a string", this.string, this.cursor)
        }
        skip()
        return readStringUntil(next)
    }

    @Throws(CommandSyntaxException::class)
    open fun readStringUntil(terminator: Char): String {
        val result = StringBuilder()
        var escaped = false
        while (canRead()) {
            val c = read()
            if (escaped) {
                if (c == terminator || c == SYNTAX_ESCAPE) {
                    result.append(c)
                    escaped = false
                } else {
                    cursor -= 1
                    throw CommandSyntaxException("Invalid escape sequence '$c' in quoted string")
                }
            } else if (c == SYNTAX_ESCAPE) {
                escaped = true
            } else if (c == terminator) {
                return result.toString()
            } else {
                result.append(c)
            }
        }
        throw CommandSyntaxException("Unclosed quoted string", this.string, this.cursor)
    }

    @Throws(CommandSyntaxException::class)
    open fun readString(): String {
        if (!canRead()) {
            return ""
        }
        val next = peek()
        if (isQuotedStringStart(next)) {
            skip()
            return readStringUntil(next)
        }
        return readUnquotedString()
    }

    @Throws(CommandSyntaxException::class)
    open fun readBoolean(): Boolean {
        val start = cursor
        val value = readString()
        if (value.isEmpty()) {
            throw CommandSyntaxException("Expected bool", this.string, this.cursor)
        }
        return when (value) {
            "true" -> {
                true
            }
            "false" -> {
                false
            }
            else -> {
                cursor = start
                throw CommandSyntaxException("Invalid bool, expected true or false but found '$value'", this.string, this.cursor)
            }
        }
    }

    @Throws(CommandSyntaxException::class)
    open fun expect(c: Char) {
        if (!canRead() || peek() != c) {
            throw CommandSyntaxException("Expected '$c'", this.string, this.cursor)
        }
        skip()
    }

    companion object {
        private const val SYNTAX_ESCAPE = '\\'
        private const val SYNTAX_DOUBLE_QUOTE = '"'
        private const val SYNTAX_SINGLE_QUOTE = '\''
        fun isAllowedNumber(c: Char): Boolean {
            return c in '0'..'9' || c == '.' || c == '-'
        }

        fun isQuotedStringStart(c: Char): Boolean {
            return c == SYNTAX_DOUBLE_QUOTE || c == SYNTAX_SINGLE_QUOTE
        }

        fun isAllowedInUnquotedString(c: Char): Boolean {
            return c in '0'..'9' || c in 'A'..'Z' || c in 'a'..'z' || c == '_' || c == '-' || c == '.' || c == '+'
        }
    }
}
