package kingmc.platform.bukkit.nms.v1_20_1

import kingmc.common.context.annotation.Component
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.version.ConditionalOnVersion
import net.minecraft.server.MinecraftServer
import org.bukkit.craftbukkit.v1_20_R1.CraftServer

@Component("nms_1_20_1")
@ConditionalOnVersion("1.20.1")
class NMS_1_20_1 {
    fun getMinecraftServer(): MinecraftServer {
        return getCraftServer().server
    }

    fun getCraftServer(): CraftServer {
        return (Bukkit.getServer() as CraftServer)
    }
}