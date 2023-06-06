package kingmc.platform.impl.adventure

import kingmc.common.text.Mark
import kingmc.common.text.Text
import kingmc.platform.Location
import kingmc.platform.audience.*
import kingmc.platform.audience.bossbar.BossBar
import kingmc.platform.audience.playerlist.PlayerList
import kingmc.platform.audience.sound.Sound
import kingmc.platform.audience.sound.SoundStop
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePartType
import net.kyori.adventure.key.Key
import java.util.stream.Collectors
import kotlin.time.toJavaDuration

/**
 * A simple [Audience] implementation for [_AdventureAudience]
 */
open class AdventureAudience(val _adventureAudience: _AdventureAudience) : Audience {
    override fun sendText(text: Text) {
        _adventureAudience.sendMessage(text)
    }

    override fun sendText(text: Text, vararg marks: Mark) {
        _adventureAudience.sendMessage(text)
    }

    override var playerlist: PlayerList
        get() = TODO()
        set(_) {}

    override fun showBossBar(bossBar: BossBar) {
        _adventureAudience.showBossBar(/* bar = */ AdventureBossBar.bossBar(
            bossBar.name,
            bossBar.progress,
            net.kyori.adventure.bossbar.BossBar.Color.valueOf(bossBar.color.name),
            net.kyori.adventure.bossbar.BossBar.Overlay.valueOf(bossBar.overlay.name),
            bossBar.flags.stream()
                .map { flag -> net.kyori.adventure.bossbar.BossBar.Flag.valueOf(flag.name) }
                .collect(Collectors.toSet())
        ))
    }

    override fun hideBossBar(bossBar: BossBar) {
        this._adventureAudience.hideBossBar(/* bar = */ AdventureBossBar.bossBar(
            bossBar.name,
            bossBar.progress,
            net.kyori.adventure.bossbar.BossBar.Color.valueOf(bossBar.color.name),
            net.kyori.adventure.bossbar.BossBar.Overlay.valueOf(bossBar.overlay.name),
            bossBar.flags.stream()
                .map { flag -> net.kyori.adventure.bossbar.BossBar.Flag.valueOf(flag.name) }
                .collect(Collectors.toSet())
        ))
    }

    override fun sendTitle(title: Title) {
        this._adventureAudience.showTitle(AdventureTitle.title(
            title.title,
            title.subtitle,
            AdventureTitleTimes.times(
                title.times.fadeIn.toJavaDuration(),
                title.times.stay.toJavaDuration(),
                title.times.fadeOut.toJavaDuration()
            )
        ))
    }

    override fun <T : Any> sendTitlePart(titlePart: TitlePartType<T>, value: T) {
        when (titlePart) {
            TitlePartType.TITLE -> {
                _adventureAudience.sendTitlePart(AdventureTitlePart.TITLE, value as Text)
            }
            TitlePartType.SUBTITLE -> {
                _adventureAudience.sendTitlePart(AdventureTitlePart.SUBTITLE, value as Text)
            }
            TitlePartType.TIMES -> {
                _adventureAudience.sendTitlePart(AdventureTitlePart.TIMES, AdventureTitleTimes.times(
                    (value as Title.Times).fadeIn.toJavaDuration(),
                    (value as Title.Times).stay.toJavaDuration(),
                    (value as Title.Times).fadeOut.toJavaDuration()
                ))
            }
        }
    }

    override fun clearTitle() {
        this._adventureAudience.clearTitle()
    }

    override fun resetTitle() {
        this._adventureAudience.resetTitle()
    }

    override fun sendActionBar(text: Text) {
        _adventureAudience.sendActionBar(text)
    }

    override fun playSound(sound: Sound) {
        this._adventureAudience.playSound(AdventureSound.sound(
            Key.key(sound.type.namespace(), sound.type.value()),
            AdventureSoundSource.valueOf(sound.source.name),
            sound.pitch,
            sound.volume
        ))
    }

    override fun playSound(sound: Sound, location: Location) {
        this._adventureAudience.playSound(AdventureSound.sound(
            Key.key(sound.type.namespace(), sound.type.value()),
            AdventureSoundSource.valueOf(sound.source.name),
            sound.volume,
            sound.pitch
        ), location.x, location.y, location.z)
    }

    override fun stopSound(soundStop: SoundStop) {
        if (soundStop.type != null) {
            if (soundStop.source != null) {
                this._adventureAudience.stopSound(net.kyori.adventure.sound.SoundStop.namedOnSource(
                    Key.key(soundStop.type!!.namespace(), soundStop.type!!.value()),
                    AdventureSoundSource.valueOf(soundStop.source!!.name),
                ))
            } else {
                this._adventureAudience.stopSound(net.kyori.adventure.sound.SoundStop.named(
                    Key.key(soundStop.type!!.namespace(), soundStop.type!!.value()),
                ))
            }
        } else {
            if (soundStop.source != null) {
                this._adventureAudience.stopSound(net.kyori.adventure.sound.SoundStop.source(
                    AdventureSoundSource.valueOf(soundStop.source!!.name),
                ))
            } else {
                this._adventureAudience.stopSound(net.kyori.adventure.sound.SoundStop.all())
            }
        }
    }

    override fun toString(): String {
        return "AdventureAudience(_adventureAudience=$_adventureAudience)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AdventureAudience) return false

        return _adventureAudience == other._adventureAudience
    }

    override fun hashCode(): Int {
        return _adventureAudience.hashCode()
    }
}