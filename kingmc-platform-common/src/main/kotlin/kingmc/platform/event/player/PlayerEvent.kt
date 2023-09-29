package kingmc.platform.event.player

import kingmc.platform.entity.player.Player
import kingmc.platform.event.Event

/**
 * Represents a Player-related event
 *
 * @author kingsthere
 * @since 0.1.1
 */
@Event
abstract class PlayerEvent(open val player: Player)