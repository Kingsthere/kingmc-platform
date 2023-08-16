package kingmc.platform.bukkit.nms.v1_20_1.impl

import kingmc.common.application.application
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Priority
import kingmc.platform.bukkit.adventure.impl.AdventureBukkitServerImpl
import kingmc.platform.bukkit.entity.player._BukkitPlayer
import kingmc.platform.bukkit.nms.v1_20_1.CraftPlayer_1_20_1
import kingmc.platform.bukkit.nms.v1_20_1.impl.entity.player.NMSAdventureOnlineBukkitPlayerImpl_1_20_1
import kingmc.platform.entity.player.Player
import kingmc.platform.version.ConditionalOnVersion

@Component
@ConditionalOnVersion("1.20.1")
@Priority(1)
class NMSAdventureBukkitServerImpl_1_20_1 : AdventureBukkitServerImpl() {
    override fun createPlayerForBukkit(bukkitPlayer: _BukkitPlayer): Player {
        val audience = adventure.getAudienceProvider().player(bukkitPlayer)
        require(bukkitPlayer is CraftPlayer_1_20_1) { "Bad bukkit player instance $bukkitPlayer" }
        return NMSAdventureOnlineBukkitPlayerImpl_1_20_1(audience, bukkitPlayer.handle, application)
    }
}