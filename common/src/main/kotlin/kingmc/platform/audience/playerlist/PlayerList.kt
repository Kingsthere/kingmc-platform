package kingmc.platform.audience.playerlist

import kingmc.platform.audience.text.Text

/**
 * Represent an in-game player list, players
 * use tab to list hte player list
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface PlayerList {
    /**
     * The header of this player list
     *
     * @since 0.0.3
     */
    var header: Text

    /**
     * THe footer of this player list
     *
     * @since 0.0.3
     */
    var footer: Text
}