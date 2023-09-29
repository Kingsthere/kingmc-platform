package kingmc.platform.messaging

import kingmc.platform.entity.player.Player
import java.io.DataInput

/**
 * A [InputMessage] implement by delegating [dataInput]
 *
 * @author kingsthere
 * @since 0.0.4
 */
open class DataInputMessage(private val dataInput: DataInput, override val player: Player, override val channel: String) : InputMessage, DataInput by dataInput {
    /**
     * The sub channel of this message
     */
    override val subChannel: String =
        readUTF()
}