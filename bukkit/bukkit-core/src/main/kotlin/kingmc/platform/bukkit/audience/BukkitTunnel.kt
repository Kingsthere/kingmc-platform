package kingmc.platform.bukkit.audience

import kingmc.platform.audience.Tunnel

/**
 * Represent a type of tunnel use in minecraft
 * bukkit servers
 *
 * @since 0.0.3
 * @author kingsthere
 * @see MinecraftTunnel
 */
interface BukkitTunnel : Tunnel {
    val bukkitPlayer: OriginalBukkitPlayer
}