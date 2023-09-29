package kingmc.platform.bukkit.brigadier

import com.mojang.brigadier.StringReader

class WrappedStringReader(val from: StringReader): kingmc.platform.command.StringReader(from.string) {
    override var cursor: Int
        get() = from.cursor
        set(value) {
            from.cursor = value
        }
    override val read: String
        get() = from.read
    override val remaining: String
        get() = from.remaining
    override val remainingLength: Int
        get() = from.remainingLength
    override val string: String
        get() = from.string
    override val totalLength: Int
        get() = from.totalLength

    override fun canRead(): Boolean {
        return from.canRead()
    }

    override fun canRead(length: Int): Boolean {
        return from.canRead(length)
    }

    override fun expect(c: Char) {
        return from.expect(c)
    }

    override fun peek(): Char {
        return from.peek()
    }

    override fun peek(offset: Int): Char {
        return from.peek(offset)
    }

    override fun read(): Char {
        return from.read()
    }

    override fun readBoolean(): Boolean {
        return from.readBoolean()
    }

    override fun readDouble(): Double {
        return from.readDouble()
    }

    override fun readFloat(): Float {
        return from.readFloat()
    }

    override fun readInt(): Int {
        return from.readInt()
    }

    override fun readLong(): Long {
        return from.readLong()
    }

    override fun readQuotedString(): String {
        return from.readQuotedString()
    }

    override fun readString(): String {
        return from.readString()
    }

    override fun readStringUntil(terminator: Char): String {
        return from.readStringUntil(terminator)
    }

    override fun readUnquotedString(): String {
        return from.readUnquotedString()
    }

    override fun skip() {
        from.skip()
    }

    override fun skipWhitespace() {
        from.skipWhitespace()
    }

    override fun readUnsafeString(): String {
        val start = from.cursor
        while (from.canRead()) {
            from.skip()
        }
        return from.string.substring(start, from.cursor)
    }
}