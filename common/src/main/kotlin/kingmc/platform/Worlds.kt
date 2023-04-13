package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.util.key.Key
import java.util.*

/**
 * Gets a world by the name
 *
 * @param name the name of the world
 * @since 0.0.7
 */
@WithApplication
fun World(name: String): World {
    return requireNotNull(currentApplication().worldFactory.getWorld(name)) { "World with name $name not exists" }
}

/**
 * Gets a world by the key
 *
 * @param key the key of the world
 * @since 0.0.7
 */
@WithApplication
fun World(key: Key): World {
    return requireNotNull(currentApplication().worldFactory.getWorld(key)) { "World with key $key not exists" }
}

/**
 * Gets a world by the unique id
 *
 * @param uuid the uuid of the world
 * @since 0.0.7
 */
@WithApplication
fun World(uuid: UUID): World {
    return requireNotNull(currentApplication().worldFactory.getWorld(uuid)) { "World with uuid $uuid not exists" }
}

/**
 * Main level `world`
 */
val WorldFactory.OVERWORLD
    get() = this.getWorld("world")

/**
 * Level `world_nether`
 */
val WorldFactory.WORLD_NETHER
    get() = this.getWorld("world_nether")

/**
 * Level `world_the_end`
 */
val WorldFactory.WORLD_THE_END
    get() = this.getWorld("world_the_end")

/**
 * Main world
 */
@get:WithApplication
val mainWorld: World
    get() = currentApplication().worldFactory.mainWorld