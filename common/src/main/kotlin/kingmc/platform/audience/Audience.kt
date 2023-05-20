package kingmc.platform.audience

import kingmc.common.text.Mark
import kingmc.common.text.Text
import kingmc.platform.Location
import kingmc.platform.audience.bossbar.BossBar
import kingmc.platform.audience.kind.*
import kingmc.platform.audience.playerlist.PlayerList
import kingmc.platform.audience.sound.Sound
import kingmc.platform.audience.sound.SoundStop
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePartType

/**
 * A receiver of Minecraft media
 *
 *
 * Audience is designed to be a universal interface for any player, command sender, console,
 * or otherwise who can receive text, titles, boss bars, and other Minecraft media.
 * It is also designed for a **group** of receivers such as a team, server, world, or permission.
 *
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface Audience : TextCapable, PlayerListCapable, BossBarCapable, TitleCapable, ActionBarCapable, SoundCapable {
    companion object {
        val EMPTY: Audience = object : Audience {
            override fun sendText(text: Text) {  }

            override fun sendText(text: Text, vararg marks: Mark) {  }

            override var playerlist: PlayerList
                get() = error("Empty audience don't have a player list")
                set(_) {  }

            override fun showBossBar(bossBar: BossBar) {  }

            override fun hideBossBar(bossBar: BossBar) {  }

            override fun sendTitle(title: Title) {  }

            override fun <T : Any> sendTitlePart(titlePart: TitlePartType<T>, value: T) {  }

            override fun clearTitle() {  }

            override fun resetTitle() {  }

            override fun sendActionBar(text: Text) {  }

            override fun playSound(sound: Sound) {  }

            override fun playSound(sound: Sound, location: Location) {  }

            override fun stopSound(soundStop: SoundStop) {  }

        }
    }
}