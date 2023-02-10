package kingmc.platform.bukkit.nms.v1_19_2

import kingmc.common.context.annotation.Component
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.nms.MinecraftServerNMS
import kingmc.platform.version.ConditionalOnVersion
import net.minecraft.server.MinecraftServer
import org.bukkit.craftbukkit.v1_19_R1.CraftServer

@Component("minecraftServerNMS_1_19_2")
@ConditionalOnVersion("1.19.2")
class MinecraftServerNMS_1_19_2 : MinecraftServerNMS<MinecraftServer, CraftServer> {
    override fun getMinecraftServer(): MinecraftServer {
        return (Bukkit.getServer() as CraftServer).server
    }

    override fun getCraftServer(): CraftServer {
        return (Bukkit.getServer() as CraftServer)
    }
}