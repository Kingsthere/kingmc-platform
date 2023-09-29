package kingmc.platform.messaging

import com.google.common.io.ByteStreams
import kingmc.common.application.Application
import kingmc.common.context.getBeanOrThrow
import kingmc.platform.entity.player.Player

/**
 * The messenger of current application
 *
 * @author kingsthere
 * @since 0.0.6
 */
val Application.messenger: Messenger
    get() = context.getBeanOrThrow<Messenger>()

/**
 * Create a [InputMessage] from [ByteArray]
 *
 * @author kingsthere
 * @since 0.0.4
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
 * @author kingsthere
 * @since 0.0.4
 * @param subChannel the sub channel to the message
 */
@Suppress("UnstableApiUsage")
fun newDataOutputMessage(subChannel: String) =
    ByteArrayDataOutputMessage(ByteStreams.newDataOutput(), subChannel)

/**
 * Build a [ByteArrayDataOutputMessage] from the [builder]
 *
 * @author kingsthere
 * @since 0.0.4
 * @param subChannel the sub channel to the message
 */
@Suppress("UnstableApiUsage")
fun buildDataOutputMessage(subChannel: String, builder: OutputMessage.() -> Unit) =
    ByteArrayDataOutputMessage(ByteStreams.newDataOutput(), subChannel).apply(builder)