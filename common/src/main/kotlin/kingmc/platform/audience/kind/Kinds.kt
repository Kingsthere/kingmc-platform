package kingmc.platform.audience.kind

import kingmc.common.text.Mark
import kingmc.common.text.Text
import kingmc.platform.Location
import kingmc.platform.audience.bossbar.BossBar
import kingmc.platform.audience.playerlist.PlayerList
import kingmc.platform.audience.sound.Sound
import kingmc.platform.audience.sound.SoundStop
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePartType
import kingmc.platform.inventory.Inventory
import kingmc.platform.inventory.InventoryView

/**
 * Represent a receiver could receive [sendText]
 *
 * @since 0.0.3
 * @author kingsthere
 */
@AudienceKind
interface TextCapable {
    /**
     * Send a text to this receivable
     *
     * @since 0.0.3
     * @see text
     */
    fun sendText(text: Text /* = net.kyori.adventure.text.Component */)

    /**
     * Send a text to this receivable with
     * tags
     *
     * @since 0.0.3
     * @see text
     * @see Mark
     */
    fun sendText(text: Text /* = net.kyori.adventure.text.Component */, vararg marks: Mark)
}

/**
 * Represent a receiver that could receive
 * [Title] and [TitlePartType]
 *
 * @since 0.0.3
 * @author kingsthere
 * @see Title
 * @see TitlePartType
 * @see AudienceKind
 */
@AudienceKind
interface TitleCapable {
    /**
     * Send a title to this audience
     *
     * @param title the title to send to
     *              the audience
     * @since 0.0.3
     * @see Title
     */
    fun sendTitle(title: Title)

    /**
     * Send/operate a title part to this
     * audience by the specify value
     *
     * @param titlePart the part of title to
     *                  send
     * @param value the value of the title part
     * @param T the type of value of the title part
     * @since 0.0.3
     */
    fun <T : Any> sendTitlePart(titlePart: TitlePartType<T>, value: T)

    /**
     * Clears the title, if one is being displayed
     *
     * @see Title
     * @since 0.0.3
     */
    fun clearTitle()

    /**
     * Resets the title and timings back to their default
     *
     * @see Title
     * @since 0.0.3
     */
    fun resetTitle()

}

/**
 * Represent a receiver that could receive
 * action bar
 *
 * @since 0.0.3
 * @author kingsthere
 * @see AudienceKind
 */
@AudienceKind
interface ActionBarCapable {
    /**
     * Send an actionbar to this audience
     *
     * @see text
     * @since 0.0.3
     */
    fun sendActionBar(text: Text)
}

/**
 * Represent a receiver that could receive
 * boss-bars
 *
 * @since 0.0.3
 * @author kingsthere
 * @see BossBar
 */
@AudienceKind
interface BossBarCapable {
    /**
     * Show a bossBar to this receiver
     *
     * @since 0.0.3
     * @see BossBar
     */
    fun showBossBar(bossBar: BossBar)

    /**
     * Hide a bossbar to this receiver
     *
     * @since 0.0.3
     * @see BossBar
     */
    fun hideBossBar(bossBar: BossBar)
}

/**
 * Represent a receiver that could receive
 * player list footer & header
 *
 * @since 0.0.3
 * @author kingsthere
 */
@AudienceKind
interface PlayerListCapable {
    /**
     * The player list that is displaying
     * to this receiver
     *
     * @since 0.0.3
     * @author kingsthere
     */
    var playerlist: PlayerList
}

/**
 * Represents a receiver that could receive sounds from server and play
 *
 * @since 0.0.5
 * @author kingsthere
 * @see Sound
 */
@AudienceKind
interface SoundCapable {
    /**
     * Play a sound to this audience
     *
     * @since 0.0.5
     */
    fun playSound(sound: Sound)

    /**
     * Play a sound to this audience in specifies location
     *
     * @since 0.0.5
     */
    fun playSound(sound: Sound, location: Location)

    /**
     * Stop a sound
     *
     * @since 0.0.5
     */
    fun stopSound(soundStop: SoundStop)
}

/**
 * Represents a receiver that could view an `gui(s)`, such as inventory, books
 *
 * @since 0.0.6
 * @author kingsthere
 */
@AudienceKind
interface GUICapable {
    /**
     * Open a [inventory] for this audience
     *
     * @since 0.0.6
     * @return the `InventoryView` refer to the opened [inventory]
     */
    fun openInventory(inventory: Inventory): InventoryView
}