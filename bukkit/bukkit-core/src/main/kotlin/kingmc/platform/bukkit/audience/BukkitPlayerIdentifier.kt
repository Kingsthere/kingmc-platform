package kingmc.platform.bukkit.audience

import kingmc.platform.audience.PlayerIdentifier
import java.util.*

/**
 * A type of player identifier point to a bukkit player
 *
 * @since 0.0.3
 * @author kingsthere
 */
open class BukkitPlayerIdentifier(
    override val name: String?,
    override val uuid: UUID?,
    val player: OriginalBukkitPlayer?
) : PlayerIdentifier(name, uuid) {
    fun withPlayer(player: OriginalBukkitPlayer?): BukkitPlayerIdentifier =
        BukkitPlayerIdentifier(name, uuid, player)
}