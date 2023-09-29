package kingmc.platform.bukkit.nms.v1_20_1.impl

import kingmc.common.application.application
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Priority
import kingmc.platform.bukkit.adventure.impl.AdventureBukkitServerImpl
import kingmc.platform.bukkit.entity.player._BukkitPlayer
import kingmc.platform.bukkit.nms.v1_20_1.CraftPlayer_1_19_2
import kingmc.platform.bukkit.nms.v1_20_1.impl.entity.player.NMSAdventureOnlineBukkitPlayerImpl_1_19_2
import kingmc.platform.entity.player.Player
import kingmc.platform.context.ConditionalOnVersion

@Component
@ConditionalOnVersion("1.20.1")
@Priority(1)
class NMSAdventureBukkitServerImpl_1_19_2 : AdventureBukkitServerImpl() {
    override fun createPlayerForBukkit(bukkitPlayer: _BukkitPlayer): Player {
        val audience = adventure.audienceProvider().player(bukkitPlayer)
        require(bukkitPlayer is CraftPlayer_1_19_2) { "Bad bukkit player instance $bukkitPlayer" }
        return NMSAdventureOnlineBukkitPlayerImpl_1_19_2(audience, bukkitPlayer.handle, application)
    }
}