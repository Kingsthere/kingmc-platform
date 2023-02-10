package kingmc.platform.messaging

import com.google.common.io.ByteArrayDataOutput
import java.io.DataOutput

/**
 * A [OutputMessage] implement by delegating [dataOutput]
 *
 * @since 0.0.4
 * @author kingsthere
 */
open class ByteArrayDataOutputMessage(private val dataOutput: ByteArrayDataOutput, subChannel: String) : OutputMessage, DataOutput by dataOutput {
    init {
        writeUTF(subChannel)
    }

    /** Returns the contents that have been written to this instance, as a byte array.  */
    override fun toByteArray(): ByteArray =
        dataOutput.toByteArray()
}