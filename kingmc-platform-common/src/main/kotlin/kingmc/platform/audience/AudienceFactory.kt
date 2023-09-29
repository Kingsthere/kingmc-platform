package kingmc.platform.audience

import kingmc.common.application.Isolated
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.platform.block.Block
import kingmc.platform.entity.player.Player
import kingmc.util.key.Key
import java.io.Closeable
import java.util.*
import java.util.function.Predicate

/**
 * A factory responsible for provide [audience][kingmc.platform.audience.Audience]
 * instances such as:
 *  + [Console][kingmc.platform.audience.Console]
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
@Scope(BeanScope.SINGLETON)
@Isolated
@Deprecated("Use World or Server instead")
interface AudienceFactory : Closeable {
    /**
     * Gets an `Audience` for all online player
     *
     * @return `Audience`
     */
    fun players(): Audience

    /**
     * Gets an `Audience` for individual player
     *
     * @param uuid the uuid of the player
     * @return `Audience`
     */
    @Deprecated("Entities such as player are no longer supplied by the AudienceFactory, use World.getPlayer(uuid) instead")
    fun player(uuid: UUID): Audience

    /**
     * Gets an `Audience` for filtered online players
     *
     * @param predicate the predicate to filter players
     * @return `Audience`
     */
    fun players(predicate: Predicate<Player>): Audience

    /**
     * Gets an audience for all online players, including the server's console
     *
     * @return `Audience` for all players and console
     */
    fun all(): Audience

    /**
     * Gets an `Audience` for current server's console
     *
     * @since 0.0.3
     */
    @Deprecated("Use Server.console instead")
    fun console(): Console

    /**
     * Gets an `Audience` for a world, it forward messages to the players on that world
     *
     * @param uuid the uuid of the world
     */
    @Deprecated("Worlds are no longer supplied by the AudienceFactory, use WorldFactory.getWorld() instead")
    fun world(uuid: UUID): Audience

    /**
     * Gets an `Audience` for a world, it forward messages to the players on that world
     *
     * @param key the key identifier to the world
     */
    @Deprecated("Worlds are no longer supplied by the AudienceFactory, use WorldFactory.getWorld() instead")
    fun world(key: Key): Audience

    /**
     * Gets a block command sender
     *
     * @since 0.0.5
     */
    @Deprecated("Blocks are no longer supplied by the AudienceFactory, use World.getBlock() instead")
    fun block(block: Block): Audience
}