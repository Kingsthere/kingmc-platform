package kingmc.platform.audience.playerlist

import kingmc.platform.audience.capable.PlayerListCapable
import kingmc.platform.audience.text.Text

/**
 * Represent a dynamic player list, the
 * static player list is mutable, if
 * you modify the header or footer of this playlist it will
 * automatically apply to receiver
 * you can also use [PlayerListCapable.playerList] to
 * apply the player list manually
 *
 * @since 0.0.3
 * @author kingsthere
 */
class DynamicPlayerList(header: Text, footer: Text, val target: PlayerListCapable) : PlayerList {
    override var header: Text = header
        set(value) {
            field = value
            target.playerlist = this
        }
    override var footer: Text = footer
        set(value) {
            field = value
            target.playerlist = this
        }
}