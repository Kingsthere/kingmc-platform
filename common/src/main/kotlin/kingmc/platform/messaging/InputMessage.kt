package kingmc.platform.messaging

import kingmc.platform.entity.player.Player
import kingmc.util.errorprone.CanIgnoreReturnValue
import java.io.DataInput

/**
 * InputMessage is a package class to describe a plugin message received, and
 * to read them
 *
 * @since 0.0.4
 * @author kingsthere
 */
interface InputMessage : DataInput {
    /**
     * The player this message receive from
     */
    val player: Player

    /**
     * The channel this message receive from
     */
    val channel: String

    /**
     * The sub channel of this message
     */
    val subChannel: String

    override fun readFully(b: ByteArray)

    override fun readFully(b: ByteArray, off: Int, len: Int)

    // not guaranteed to skip n bytes so result should NOT be ignored
    // use ByteStreams.skipFully or one of the read methods instead
    override fun skipBytes(n: Int): Int

    @CanIgnoreReturnValue
    override // to skip a byte
    fun readBoolean(): Boolean

    @CanIgnoreReturnValue
    override // to skip a byte
    fun readByte(): Byte

    @CanIgnoreReturnValue
    override // to skip a byte
    fun readUnsignedByte(): Int

    @CanIgnoreReturnValue
    override // to skip some bytes
    fun readShort(): Short

    @CanIgnoreReturnValue
    override // to skip some bytes
    fun readUnsignedShort(): Int

    @CanIgnoreReturnValue
    override // to skip some bytes
    fun readChar(): Char

    @CanIgnoreReturnValue
    override // to skip some bytes
    fun readInt(): Int

    @CanIgnoreReturnValue
    override // to skip some bytes
    fun readLong(): Long

    @CanIgnoreReturnValue
    override // to skip some bytes
    fun readFloat(): Float

    @CanIgnoreReturnValue
    override // to skip some bytes
    fun readDouble(): Double

    @CanIgnoreReturnValue
    override // to skip a line
    fun readLine(): String?

    @CanIgnoreReturnValue
    override // to skip a field
    fun readUTF(): String
}