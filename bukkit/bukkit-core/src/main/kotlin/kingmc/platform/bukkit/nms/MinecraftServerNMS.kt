package kingmc.platform.bukkit.nms

import kingmc.common.context.annotation.Component

/**
 * A superinterface exposed an interface to interact `MinecraftServer` class on bukkit
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component("minecraftServerNMS")
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