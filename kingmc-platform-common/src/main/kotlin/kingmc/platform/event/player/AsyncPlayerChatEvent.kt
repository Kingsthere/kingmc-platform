package kingmc.platform.event.player

import kingmc.common.text.Text
import kingmc.platform.audience.Audience
import kingmc.platform.entity.player.Player
import kingmc.platform.event.Cancellable
import kingmc.platform.event.Event
import net.kyori.adventure.chat.SignedMessage

/**
 * An implementation of a chat event, fired when players chatting
 *
 * @property viewers a mutable set for viewers that can see the message
 * @property message the message to display
 * @property originalMessage the original message provided by player
 * @property signedMessage the signed message instance
 * @author kingsthere
 * @since 0.1.1
 */
@Event
sealed class AbstractPlayerChatEvent(
    player: Player,
    val viewers: MutableSet<Audience>,
    var message: Text,
    val originalMessage: String,
    val signedMessage: SignedMessage,
) : PlayerEvent(player), Cancellable {
    override var cancelled: Boolean = false

    /**
     * Extended `PlayerChatEvent`, if the player chat event is fired asynchronously this event
     * should be fired instead of `SyncPlayerChatEvent`
     *
     * @since 0.1.1
     */
    @Event(async = true)
    class AsyncPlayerChatEvent(
        player: Player,
        viewers: MutableSet<Audience>,
        message: Text,
        originalMessage: String,
        signedMessage: SignedMessage
    ) : AbstractPlayerChatEvent(player, viewers, message, originalMessage, signedMessage) {
        override fun toString(): String {
            return "AsyncPlayerChatEvent(viewers=$viewers, message=$message, originalMessage='$originalMessage', signedMessage=$signedMessage, cancelled=$cancelled)"
        }
    }

    /**
     * Extended `PlayerChatEvent`, this event may only fire in the main thread
     *
     * @since 0.1.1
     */
    @Event
    @Deprecated("In consideration of performance, player chats should be handled asynchronously")
    class PlayerChatEvent(
        player: Player,
        viewers: MutableSet<Audience>,
        message: Text,
        originalMessage: String,
        signedMessage: SignedMessage
    ) : AbstractPlayerChatEvent(player, viewers, message, originalMessage, signedMessage) {
        override fun toString(): String {
            return "PlayerChatEvent(viewers=$viewers, message=$message, originalMessage='$originalMessage', signedMessage=$signedMessage, cancelled=$cancelled)"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractPlayerChatEvent) return false

        if (viewers != other.viewers) return false
        if (message != other.message) return false
        if (originalMessage != other.originalMessage) return false
        if (signedMessage != other.signedMessage) return false
        if (cancelled != other.cancelled) return false

        return true
    }

    override fun hashCode(): Int {
        var result = viewers.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + originalMessage.hashCode()
        result = 31 * result + signedMessage.hashCode()
        result = 31 * result + cancelled.hashCode()
        return result
    }

    override fun toString(): String {
        return "AbstractPlayerChatEvent(viewers=$viewers, message=$message, originalMessage='$originalMessage', signedMessage=$signedMessage, cancelled=$cancelled)"
    }
}

// region Shortcuts to AbstractPlayerChatEvent by type-alias
typealias AsyncPlayerChatEvent = AbstractPlayerChatEvent.AsyncPlayerChatEvent

@Deprecated("In consideration of performance, player chats should be handled asynchronously")
typealias PlayerChatEvent = AbstractPlayerChatEvent.PlayerChatEvent
// endregion