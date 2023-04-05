package kingmc.platform.bukkit.nms

import kingmc.common.context.annotation.Component
import kingmc.platform.audience.particle.Particle
import kingmc.platform.bukkit.entity.player.BukkitPlayer

/**
 * A superinterface exposed few functions to interact with players on
 * `net.minecraft.server` to access more detail stuff that bukkit api not exposed
 *
 * @since 0.0.5
 * @author kingsthere
 * @param TPlayerHandle the type of the handle for interacting players
 */
@Component
interface PlayerNMS<TPlayerHandle> {
    /**
     * Gets a handle for [player]
     *
     * @param player the player to get handle from
     * @return the player handle got
     */
    fun getRawHandle(player: BukkitPlayer): TPlayerHandle

    /**
     * Send a particle to targeting player [handle]
     *
     * @param handle the handle of the player to send particle to
     */
    fun sendParticle(handle: TPlayerHandle, particle: Particle<*>)
}