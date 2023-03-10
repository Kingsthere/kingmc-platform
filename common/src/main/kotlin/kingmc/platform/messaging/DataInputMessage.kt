package kingmc.platform.messaging

import kingmc.platform.audience.Player
import java.io.DataInput

/**
 * A [InputMessage] implement by delegating [dataInput]
 *
 * @since 0.0.4
 * @author kingsthere
 */
open class DataInputMessage(private val dataInput: DataInput, override val player: Player, override val channel: String) : InputMessage, DataInput by dataInput {
    /**
     * The sub channel of this message
     */
    override val subChannel: String =
        readUTF()
}