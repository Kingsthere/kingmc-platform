package kingmc.platform.audience.playerlist

import kingmc.common.text.Text

/**
 * A static `PlayerList`, it won't apply to the audience once you change properties
 * in instances of this
 *
 * @author kingsthere
 * @since 0.0.3
 */
class StaticPlayerList(override var header: Text, override var footer: Text) : PlayerList