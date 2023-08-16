package kingmc.platform

import kingmc.common.application.Isolated
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.util.key.Key
import java.util.*

/**
 * Factory responsible for providing worlds
 *
 * @since 0.0.1
 * @author kingsthere
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