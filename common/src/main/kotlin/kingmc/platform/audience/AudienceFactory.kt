package kingmc.platform.audience

import kingmc.common.context.annotation.Component
import kingmc.platform.PlatformExposed
import kingmc.platform.block.Block
import java.io.Closeable
import java.util.*
import java.util.function.Predicate

/**
 * Represent a factory to provide the [kingmc.platform.audience.Audience]
 * instances & implements of audiences such as:
 *  + [CommandSender][kingmc.platform.audience.CommandSender]
 *  + [Player][kingmc.platform.audience.Player]
 *  + [Console][kingmc.platform.audience.Console]
 *  + [BlockCommandSender][kingmc.platform.audience.BlockCommandSender]
 *
 *
 *  This factory also provide the audiences that **cast**
 *  from the other minecraft api instances, such as **bukkit api**
 *  players
 *
 *
 *  Audience factory is closeable, close the audience factory
 *  to release the resources, and this factory won't provide
 *  any audiences
 *
 * @author kingsthere
 * @since 0.0.3
 * @see Closeable
 */
@Component
interface AudienceFactory : PlatformExposed, Closeable, Iterable<Audience> {
    /**
     * Gets an [Player] for an individual player by the
     * name of that [Player]
     *
     * @since 0.0.3
     * @see Player
     * @see UUID
     */
    fun player(name: String): Player?

    /**
     * Gets an [Player] for an individual player by the
     * uuid of that [Player]
     *
     * @since 0.0.3
     * @see Player
     * @see UUID
     */
    fun player(uuid: UUID): Player?

    /**
     * Gets an [Player] for all online players
     *
     * @since 0.0.3
     * @return the [Player]
     */
    fun players(): Players

    /**
     * Gets an [Player] for filtered online players
     *
     * @since 0.0.3
     * @return the [Player]
     */
    fun players(predicate: Predicate<Player>): Players

    /**
     * Get an audience from this factory by using the audience
     * identifier
     *
     * @since 0.0.3
     */
    fun <T : Audience> audience(identifier: AudienceIdentifier<T>): T?

    /**
     * Gets an audience for all audiences in this factory
     *
     * @since 0.0.3
     * @see Player
     * @see Set
     */
    fun all(): Audience

    /**
     * Gets an audience for filtered audiences in this factory
     *
     * @since 0.0.3
     * @see Predicate
     * @see Set
     * @see Player
     */
    fun all(predicate: Predicate<Audience>): Audience

    /**
     * Gets the current [Console] audience
     *
     * @since 0.0.3
     */
    fun console(): Console

    /**
     * Gets a block command sender
     *
     * @since 0.0.5
     */
    fun block(block: Block): Audience

    /**
     * Gets all audience in this audience factory
     *
     * @since 0.0.3
     */
    override fun iterator(): Iterator<Audience>
}