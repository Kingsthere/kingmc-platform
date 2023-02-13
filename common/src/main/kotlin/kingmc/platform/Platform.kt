package kingmc.platform

import kingmc.common.application.Application
import kingmc.common.context.aware.ContextAware
import kingmc.platform.audience.AudienceFactory
import kingmc.platform.command.CommandContextFactory
import kingmc.platform.command.CommandManager
import kingmc.platform.context.PlatformApplication
import kingmc.platform.context.PlatformApplicationImpl
import kingmc.platform.event.ListenerManager
import kingmc.platform.event.Publisher

/**
 * A superinterface designed to represent a kind of platform the runs current application
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface Platform : ContextAware {
    /**
     * The locations on this platform
     *
     * @since 0.0.3
     */
    val locations: LocationProvider

    /**
     * The vectors on this platform
     *
     * @since 0.0.3
     */
    val vectors: VectorProvider

    /**
     * The identifiers for this platform, must lowercase
     *
     * @since 0.0.3
     */
    val id: Array<String>

    /**
     * The minecraft version of this platform
     *
     * @since 0.0.5
     */
    val minecraftVersion: String
}

/**
 * The command manager supports this platform
 */
val Application<*>.commands: CommandManager
    get() = this.context.getBean(CommandManager::class)

/**
 * The default event publisher supports this platform
 */
val Application<*>.publisher: Publisher
    get() = this.context.getBean(Publisher::class)

/**
 * The listener manager supports this platform
 */
val Application<*>.listeners: ListenerManager
    get() = this.context.getBean(ListenerManager::class)

/**
 * The world provider supports this platform
 */
val Application<*>.worlds: WorldProvider
    get() = this.context.getBean(WorldProvider::class)

/**
 * The command context factory supports this platform
 */
val Application<*>.invocations: CommandContextFactory
    get() = this.context.getBean(CommandContextFactory::class)

/**
 * The audience factory supports this platform
 */
val Application<*>.audiences: AudienceFactory
    get() = this.context.getBean(AudienceFactory::class)

/**
 * The materials support this platform
 */
val Application<*>.materials: MaterialProvider
    get() = this.context.getBean(MaterialProvider::class)

/**
 * Gets the platform of this application if this application is a [PlatformApplicationImpl]
 */
val Application<*>.platform: Platform
    get() {
        if (this is PlatformApplication<*>) {
            return this.platform
        } else {
            throw IllegalStateException("This application is not a PlatformApplication")
        }
    }