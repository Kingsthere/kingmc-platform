package kingmc.platform.audience.playerlist

import kingmc.platform.audience.text.Text

/**
 * Represent a static player list, the
 * static player list is immutable, if
 * you modify the header or footer of this playlist
 * you can also use [PlayerListDefine.playerList] to
 * apply the player list
 *
 * @since 0.0.3
 * @author kingsthere
 */
class StaticPlayerList(override var header: Text, override var footer: Text) : PlayerList