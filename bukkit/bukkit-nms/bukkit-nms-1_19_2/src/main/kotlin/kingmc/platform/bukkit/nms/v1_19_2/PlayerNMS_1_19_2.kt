package kingmc.platform.bukkit.nms.v1_19_2

import kingmc.common.context.annotation.Component
import kingmc.platform.audience.Player
import kingmc.platform.audience.particle.Particle
import kingmc.platform.bukkit.audience.particle.bukkit
import kingmc.platform.bukkit.bukkit
import kingmc.platform.bukkit.nms.PlayerNMS
import kingmc.platform.x
import kingmc.platform.y
import kingmc.platform.z
import net.minecraft.network.protocol.game.PacketPlayOutWorldParticles
import org.bukkit.craftbukkit.v1_19_R1.CraftParticle

@Component("playerNMS_1_19_2")
class PlayerNMS_1_19_2 : PlayerNMS<NMSServerPlayer_1_19_2> {
    /**
     * Gets a handle for [player]
     *
     * @param player the player to get handle from
     * @return the player handle got
     */
    override fun getHandle(player: Player): NMSServerPlayer_1_19_2 {
        val bukkitPlayer = (player.bukkit as CraftPlayer_1_19_2)
        return bukkitPlayer.handle
    }

    /**
     * Send a particle to targeting player [handle]
     *
     * @param handle the handle of the player to send particle to
     */
    override fun sendParticle(handle: NMSServerPlayer_1_19_2, particle: Particle<*>) {
        handle.b.b.a(PacketPlayOutWorldParticles(
            CraftParticle.toNMS(particle.bukkit),
            true,
            particle.x,
            particle.y,
            particle.z,
            particle.offsetX,
            particle.offsetY,
            particle.offsetZ,
            particle.maxSpeed,
            particle.count
        ))
    }

}