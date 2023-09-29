package kingmc.platform.messaging

import com.google.common.io.ByteArrayDataOutput
import java.io.DataOutput

/**
 * A [OutputMessage] implement by delegating [dataOutput]
 *
 * @author kingsthere
 * @since 0.0.4
 */
open class ByteArrayDataOutputMessage(private val dataOutput: ByteArrayDataOutput, subChannel: String) : OutputMessage, DataOutput by dataOutput {
    init {
        writeUTF(subChannel)
    }

    /** Returns the contents that have been written to this instance, as a byte array.  */
    override fun toByteArray(): ByteArray =
        dataOutput.toByteArray()
}