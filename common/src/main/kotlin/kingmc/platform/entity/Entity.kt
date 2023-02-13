package kingmc.platform.entity

import kingmc.platform.Physical
import kingmc.platform.audience.text.HoverEventDisplayable
import kingmc.platform.audience.text.TextDisplayable

/**
 * An interface represent an in-game entity that could display
 * on a player's client
 *
 * @since 0.0.1
 * @author kingsthere
 */
interface Entity : Physical, TextDisplayable, HoverEventDisplayable