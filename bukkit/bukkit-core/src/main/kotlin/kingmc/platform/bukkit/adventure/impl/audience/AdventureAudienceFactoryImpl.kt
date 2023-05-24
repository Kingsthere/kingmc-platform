package kingmc.platform.bukkit.adventure.impl.audience

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import kingmc.common.application.application
import kingmc.common.application.withApplication
import kingmc.common.application.currentApplication
import kingmc.common.context.annotation.Autowired
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.platform.Server
import kingmc.platform.audience.*
import kingmc.platform.block.Block
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.adventure.Adventure
import kingmc.platform.bukkit.adventure.impl.AdventureBukkitServerImpl
import kingmc.platform.bukkit.audience.*
import kingmc.platform.bukkit.entity.player.*
import kingmc.platform.command.CommandSender
import kingmc.platform.entity.player.Player
import kingmc.platform.entity.player.Players
import kingmc.platform.server
import kingmc.platform.worldFactory
import kingmc.util.key.Key
import java.util.*
import java.util.function.Predicate


/**
 * A [BukkitAudienceFactory] implementation implemented with adventure
 *
 * @since 0.0.7
 * @author kingsthere
 */
@Component
@Scope(BeanScope.SINGLETON)
@BukkitImplementation
class AdventureAudienceFactoryImpl : BukkitAudienceFactory {
    @Autowired
    lateinit var server: Server

    @Autowired
    lateinit var adventure: Adventure

    /**
     * Cached players
     */
    private val _players: Cache<UUID, Player> = Caffeine.newBuilder()
        .build()

    /**
     * `AllBukkitPlayer` instance
     */
    private val _playersAudience by lazy {
        AllBukkitPlayer(application.server)
    }

    /**
     * `AllBukkitAudiences` instance
     */
    private val all by lazy {
        AllBukkitAudiences(application.server)
    }

    @Deprecated("CommandSenders are no longer supplied by the AudienceFactory, use World.getPlayer(uuid) instead")
    @Throws(IllegalArgumentException::class)
    override fun commandSender(commandSender: _BukkitCommandSender): CommandSender {
        return when (commandSender) {
            is _BukkitPlayer -> {
                player(commandSender)
            }

            is _BukkitConsoleCommandSender -> {
                console()
            }

            is _BukkitProxiedCommandSender -> {
                commandSender(commandSender.callee)
            }

            else -> {
                throw IllegalArgumentException("Illegal command sender: $commandSender")
            }
        }
    }

    @Deprecated("Entities such as player are no longer supplied by the AudienceFactory, use World.getPlayer(uuid) instead",
        ReplaceWith(
            "(currentApplication().server as BukkitServer).getPlayerForBukkit(bukkitPlayer)",
            "kingmc.common.application.currentApplication",
            "kingmc.platform.server",
            "kingmc.platform.bukkit.BukkitServer"
        )
    )
    override fun player(bukkitPlayer: _BukkitPlayer): Player {
        return (currentApplication().server as AdventureBukkitServerImpl).getPlayerForBukkit(bukkitPlayer)
    }

    @Deprecated("Blocks are no longer supplied by the AudienceFactory, use World.getBlock() instead")
    override fun block(block: Block): Audience {
        TODO("Finding a better way to convert a Block into Audience")
    }

    @Deprecated("Entities such as player are no longer supplied by the AudienceFactory, use World.getPlayer(uuid) instead",
        ReplaceWith(
            "currentApplication().server.getPlayer(uuid) ?: Audience.EMPTY",
            "kingmc.common.application.currentApplication",
            "kingmc.platform.server",
            "kingmc.platform.audience.Audience"
        )
    )
    override fun player(uuid: UUID): Audience {
        return currentApplication().server.getPlayer(uuid) ?: Audience.EMPTY
    }

    override fun all(): Audience {
        return all
    }

    @Deprecated("Use Server.console instead", ReplaceWith(
        "currentApplication().server.console",
        "kingmc.common.application.currentApplication",
        "kingmc.platform.server"
    )
    )
    override fun console(): Console = currentApplication().server.console

    @Deprecated("Worlds are no longer supplied by the AudienceFactory, use WorldFactory.getWorld() instead", ReplaceWith(
        "currentApplication().worlds.getWorld(uuid) ?: Audience.EMPTY",
        "kingmc.common.application.currentApplication",
        "kingmc.platform.worlds",
        "kingmc.platform.audience.Audience"
    )
    )
    override fun world(uuid: UUID): Audience {
        return currentApplication().worldFactory.getWorld(uuid) ?: Audience.EMPTY
    }

    @Deprecated("Worlds are no longer supplied by the AudienceFactory, use WorldFactory.getWorld() instead", ReplaceWith(
        "currentApplication().worlds.getWorld(key) ?: Audience.EMPTY",
        "kingmc.common.application.currentApplication",
        "kingmc.platform.worlds",
        "kingmc.platform.audience.Audience"
    )
    )
    override fun world(key: Key): Audience {
        return currentApplication().worldFactory.getWorld(key) ?: Audience.EMPTY
    }

    override fun close() {
        this._players.invalidateAll()
        adventure.getAudienceProvider().close()
    }

    override fun players(): Players =
        _playersAudience

    override fun players(predicate: Predicate<Player>): Players =
        FilteredAllBukkitPlayer(server, predicate)
}