package kingmc.platform.bukkit.audience.adventure

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import kingmc.common.context.annotation.Component
import kingmc.platform.Platform
import kingmc.platform.PlatformImplementation
import kingmc.platform.audience.*
import kingmc.platform.block.Block
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.audience.*
import kingmc.platform.bukkit.block.OriginalBukkitBlock
import kingmc.platform.bukkit.bukkitPlatform
import java.util.*
import java.util.function.Predicate


/**
 * Current bukkit audience factory implement
 *
 * @since 0.0.3
 * @author kingsthere
 */
@Component("bukkitAudienceFactory")
@PlatformImplementation
object BukkitAudiences : BukkitAudienceFactory {
    private val playerCache: Cache<UUID, Player> = Caffeine.newBuilder()
        .build()
    private val players = AllBukkitPlayer
    private val all = AllBukkitAudiences

    @Throws(IllegalArgumentException::class)
    override fun commandSender(commandSender: OriginalBukkitCommandSender): CommandSender {
        return when (commandSender) {
            is OriginalBukkitPlayer -> {
                player(commandSender)
            }

            is OriginalBukkitConsoleCommandSender -> {
                console()
            }

            is OriginalBukkitBlockCommandSender -> {
                block(commandSender)
            }

            is OriginalBukkitProxiedCommandSender -> {
                commandSender(commandSender.callee)
            }

            else -> {
                throw IllegalArgumentException("Illegal command sender: $commandSender")
            }
        }
    }

    /**
     * Get a player from original bukkit
     * player
     *
     * @since 0.0.3
     * @see Player
     * @see OriginalBukkitCommandSender
     */
    override fun player(bukkitPlayer: OriginalBukkitPlayer): Player {
        return playerCache.get(bukkitPlayer.uniqueId) {
            AdventureOnlineBukkitPlayer(
            bukkitPlayer,
            bukkitAudiences.player(bukkitPlayer)) }!!
    }

    override fun player(name: String): Player? {
        val bukkitPlayer = Bukkit.getPlayer(name) ?: return null
        return playerCache.get(bukkitPlayer.uniqueId) {
            AdventureOnlineBukkitPlayer(
                bukkitPlayer,
                bukkitAudiences.player(bukkitPlayer)) }!!
    }

    /**
     * Gets a audience from original bukkit block
     *
     * @since 0.0.5
     * @see OriginalBukkitBlock
     */
    override fun block(bukkitBlock: OriginalBukkitBlockCommandSender): CommandSender {
        return BukkitBlockCommandSenderImpl(bukkitBlock)
    }

    /**
     * Gets a block command sender
     *
     * @since 0.0.5
     */
    override fun block(block: Block): Audience {
        TODO("Finding a better way to convert a Block into Audience")
    }

    /**
     * Gets the platform of this
     */
    override val platform: Platform
        get() = bukkitPlatform

    override fun player(uuid: UUID): Player? {
        val bukkitPlayer = Bukkit.getPlayer(uuid) ?: return null
        return playerCache.get(bukkitPlayer.uniqueId) {
            AdventureOnlineBukkitPlayer(
                bukkitPlayer,
                bukkitAudiences.player(bukkitPlayer)) }!!
    }

    override fun all(): Audience {
        return all
    }

    override fun all(predicate: Predicate<Audience>): Audience {
        return FilteredAllBukkitAudiences(predicate)
    }

    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <T : Audience> audience(identifier: AudienceIdentifier<T>): T? {
        when (identifier) {
            is BukkitPlayerIdentifier -> {
                if (identifier.name != null) {
                    return try {
                        player(identifier.name!!) as? T
                    } catch (exception: IllegalArgumentException) {
                        null
                    }
                } else if (identifier.uuid != null) {
                    return try {
                        player(identifier.uuid!!) as? T
                    } catch (exception: IllegalArgumentException) {
                        null
                    }
                } else if (identifier.player != null) {
                    return try {
                        player(identifier.player) as? T
                    } catch (exception: IllegalArgumentException) {
                        null
                    }
                } else {
                    throw IllegalArgumentException("Illegal audience identifier, an audience identifier must specify a uuid or a name")
                }
            }
            is PlayerIdentifier -> {
                return if (identifier.name != null) {
                    try {
                        player(identifier.name!!) as? T
                    } catch (exception: IllegalArgumentException) {
                        null
                    }
                } else if (identifier.uuid != null) {
                    try {
                        player(identifier.uuid!!) as? T
                    } catch (exception: IllegalArgumentException) {
                        null
                    }
                } else {
                    throw IllegalArgumentException("Illegal audience identifier, an audience identifier must specify a uuid or a name")
                }
            }
        }
        return null
    }

    override fun console(): Console =
        AdventureBukkitConsole(bukkitAudiences.console())

    override fun close() {
        bukkitAudiences.close()
    }

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<Audience> =
        AllBukkitAudiences.audiences().iterator()

    /**
     * Gets an [Player] for all online players
     *
     * @since 0.0.3
     * @return the [Player]
     */
    override fun players(): Players =
        players

    /**
     * Gets an [Player] for filtered online players
     *
     * @since 0.0.3
     * @return the [Player]
     */
    override fun players(predicate: Predicate<Player>): Players =
        FilteredAllBukkitPlayer(predicate)
}