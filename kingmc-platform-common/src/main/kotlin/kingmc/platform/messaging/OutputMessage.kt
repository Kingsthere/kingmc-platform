package kingmc.platform.messaging

import java.io.DataOutput

/**
 * Output message is a packaged class to build output plugin messages
 *
 * @author kingsthere
 * @since 0.0.4
 */
interface OutputMessage : DataOutput {

    override fun write(b: Int)

    override fun write(b: ByteArray)

    override fun write(b: ByteArray, off: Int, len: Int)

    override fun writeBoolean(v: Boolean)

    override fun writeByte(v: Int)

    override fun writeShort(v: Int)

    override fun writeChar(v: Int)

    override fun writeInt(v: Int)

    override fun writeLong(v: Long)

    override fun writeFloat(v: Float)

    override fun writeDouble(v: Double)

    override fun writeChars(s: String)

    override fun writeUTF(s: String)


    @Deprecated(
        """This method is dangerous as it discards the high byte of every character. For
        UTF-8, use {@code write(s.getBytes(StandardCharsets.UTF_8))}."""
    )
    override fun writeBytes(s: String)

    /** Returns the contents that have been written to this instance, as a byte array.  */
    fun toByteArray(): ByteArray
}