package kingmc.platform.bukkit.nms

import kingmc.common.context.annotation.Component

/**
 * A superinterface exposed few functions to interact with `MinecraftServer` class on bukkit
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component
interface MinecraftServerNMS<TMinecraftServer, TCraftServer> {
    /**
     * Gets the `MinecraftServer` instance for current server
     */
    fun getMinecraftServer(): TMinecraftServer

    /**
     * Gets the `CraftServer` instance for current server
     */
    fun getCraftServer(): TCraftServer
}