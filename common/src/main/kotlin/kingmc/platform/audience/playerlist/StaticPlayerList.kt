package kingmc.platform.audience.playerlist

import kingmc.platform.audience.text.Text

/**
 * A static `PlayerList`, it won't apply to the audience once you change properties
 * in instances of this
 *
 * @since 0.0.3
 * @author kingsthere
 */
class StaticPlayerList(override var header: Text, override var footer: Text) : PlayerList