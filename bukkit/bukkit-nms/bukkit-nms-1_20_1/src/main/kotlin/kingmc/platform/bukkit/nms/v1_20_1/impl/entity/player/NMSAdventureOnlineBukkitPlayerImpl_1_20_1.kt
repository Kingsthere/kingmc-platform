package kingmc.platform.bukkit.nms.v1_20_1.impl.entity.player

import kingmc.common.application.Application
import kingmc.platform.audience._AdventureAudience
import kingmc.platform.audience.particle.Particle
import kingmc.platform.bukkit.adventure.impl.audience.AdventureOnlineBukkitPlayerImpl
import kingmc.platform.bukkit.audience.particle.bukkit
import kingmc.platform.bukkit.nms.v1_20_1.NMSEntityPlayer_1_20_1
import net.minecraft.network.protocol.game.PacketPlayOutWorldParticles
import org.bukkit.craftbukkit.v1_20_R1.CraftParticle

open class NMSAdventureOnlineBukkitPlayerImpl_1_20_1(
    private val adventureAudience: _AdventureAudience,
    private val handle: NMSEntityPlayer_1_20_1,
    application: Application
) : AdventureOnlineBukkitPlayerImpl(handle.bukkitEntity, adventureAudience, application) {
    override fun sendParticle(
        particle: Particle<*>,
        x: Double,
        y: Double,
        z: Double,
        longDistance: Boolean,
        offsetX: Float,
        offsetY: Float,
        offsetZ: Float,
        maxSpeed: Float,
        count: Int
    ) {
        this.ensurePlayerOnline()
        this.handle.c.a(PacketPlayOutWorldParticles(
            CraftParticle.toNMS(particle.bukkit),
            true,
            x,
            y,
            z,
            offsetX,
            offsetY,
            offsetZ,
            maxSpeed,
            count
        ))
    }
}