package kingmc.platform

import kingmc.common.context.annotation.Component
import kingmc.util.key.Key
import java.util.*

/**
 * Represents a world factory to provide `World` instances
 *
 * @author kingsthere
 * @since 0.1.3
 */
@Component
interface WorldFactory {
    /**
     * The main world, which players enter when they first join the server
     */
    val mainWorld: World

    /**
     * Gets a world by the name of the world, or `null` if
     * world with name [name] not found
     */
    fun getWorld(name: String): World?

    /**
     * Gets a world by the key of the world, or `null` if world
     * with key [key] not exists
     */
    fun getWorld(key: Key): World?

    /**
     * Gets a world by the uuid of the world, or `null` if world
     * with uuid [uuid] not exists
     */
    fun getWorld(uuid: UUID): World?

    /**
     * Gets all worlds
     */
    fun getWorlds(): List<World>
}