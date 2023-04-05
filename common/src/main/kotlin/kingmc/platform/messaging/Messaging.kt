package kingmc.platform.messaging

import com.google.common.io.ByteStreams
import kingmc.common.application.Application
import kingmc.platform.entity.player.Player

/**
 * The messenger of current application
 *
 * @since 0.0.6
 * @author kingsthere
 */
val Application.messenger: Messenger
    get() = context.getBean(Messenger::class)

/**
 * Create a [InputMessage] from [ByteArray]
 *
 * @since 0.0.4
 * @author kingsthere
 * @param data the byte array to receive data from
 * @param player the player this message receive from
 * @param channel the channel this message receive from
 */
@Suppress("UnstableApiUsage")
fun arrayDataInputMessage(player: Player, channel: String, data: ByteArray) =
    DataInputMessage(ByteStreams.newDataInput(data), player, channel)

/**
 * New a [ByteArrayDataOutputMessage]
 *
 * @since 0.0.4
 * @author kingsthere
 * @param subChannel the sub channel to the message
 */
@Suppress("UnstableApiUsage")
fun newDataOutputMessage(subChannel: String) =
    ByteArrayDataOutputMessage(ByteStreams.newDataOutput(), subChannel)

/**
 * Build a [ByteArrayDataOutputMessage] from the [builder]
 *
 * @since 0.0.4
 * @author kingsthere
 * @param subChannel the sub channel to the message
 */
@Suppress("UnstableApiUsage")
fun buildDataOutputMessage(subChannel: String, builder: OutputMessage.() -> Unit) =
    ByteArrayDataOutputMessage(ByteStreams.newDataOutput(), subChannel).apply(builder)