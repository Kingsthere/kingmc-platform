package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.context.annotation.Component

/**
 * Get a world by the name of the
 * world
 *
 * @since 0.0.1
 */
@WithApplication
fun World(name: String): World {
    return currentApplication().worlds.getWorld(name)
}

/**
 * A provider to provide the instances of [World]
 * implemented by the current platform installed
 *
 * @since 0.0.1
 * @author kingsthere
 */
@Component
interface WorldProvider : PlatformExposed {
    /**
     * Gets a world by the name of the
     * world
     *
     * @since 0.0.1
     */
    fun getWorld(name: String): World
}