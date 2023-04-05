package kingmc.platform.audience

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.audience.ForwardingAudience
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.sound.SoundStop
import net.kyori.adventure.title.Title
import net.kyori.adventure.title.TitlePart

typealias _AdventureForwardingAudience = ForwardingAudience
typealias _AdventureAudience = Audience

// Deprecated for removal
// typealias MessageType = MessageType

typealias AdventureBossBar = BossBar
typealias AdventureTitle = Title
typealias AdventureTitlePart<T> = TitlePart<T>
typealias AdventureSound = Sound
typealias AdventureSoundSource = Sound.Source
typealias AdventureSoundStop = SoundStop
typealias AdventureTitleTimes = Title.Times