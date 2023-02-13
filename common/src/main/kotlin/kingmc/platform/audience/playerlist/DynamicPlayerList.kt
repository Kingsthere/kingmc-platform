package kingmc.platform.audience.playerlist

import kingmc.platform.audience.capable.PlayerListCapable
import kingmc.platform.audience.text.Text

/**
 * A dynamic `PlayerList`, it auto apply to the audience once you change properties
 * in instances of this
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