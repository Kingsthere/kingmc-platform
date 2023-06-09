package kingmc.platform.bukkit.nms.v1_19_2.entity

import kingmc.common.application.Application
import kingmc.platform.audience._AdventureAudience
import kingmc.platform.audience.particle.Particle
import kingmc.platform.bukkit.adventure.impl.audience.AdventureOnlineBukkitPlayerImpl
import kingmc.platform.bukkit.nms.PlayerNMS
import kingmc.platform.bukkit.nms.audience.NMSPlayer
import kingmc.platform.bukkit.nms.v1_19_2.NMSServerPlayer_1_19_2

open class NMSAdventureOnlinePlayer_1_19_2Impl(val playerNMS: PlayerNMS<Any>,
                                               val adventureAudience: _AdventureAudience,
                                               val nms: NMSServerPlayer_1_19_2,
                                               application: Application)
    : AdventureOnlineBukkitPlayerImpl(nms.bukkitEntity, adventureAudience, application), NMSPlayer {
    override fun sendParticle(particle: Particle<*>) {
        this.ensurePlayerOnline()
    }
}