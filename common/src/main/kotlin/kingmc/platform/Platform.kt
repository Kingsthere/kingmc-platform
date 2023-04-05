package kingmc.platform

import kingmc.common.application.Application
import kingmc.platform.audience.AudienceFactory
import kingmc.platform.command.CommandContextFactory
import kingmc.platform.command.CommandManager
import kingmc.platform.context.PlatformApplication
import kingmc.platform.context.PlatformApplicationImpl
import kingmc.platform.driver.PlatformDriver
import kingmc.platform.entity.EntityFactory
import kingmc.platform.event.ListenerManager
import kingmc.platform.event.Publisher
import kingmc.platform.extension.ExtensionDispatcher
import kingmc.platform.nbt.NBTFactory

/**
 * A superinterface designed to represent a kind of platform the runs current application
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface Platform {
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

    /**
     * The driver that launched this platform
     */
    val driver: PlatformDriver
}

/**
 * A superinterface extended [Platform] indicates an extendable platform that has a
 * extension dispatcher to install extensions to this platform
 *
 * @since 0.0.7
 * @author kingsthere
 * @see Platform
 */
interface ExtendablePlatform : Platform {
    /**
     * The extension dispatcher of this platform for dispatch extensions
     */
    val extensionDispatcher: ExtensionDispatcher
}

/**
 * The command manager supports this platform
 */
val Application.commandFactory: CommandManager
    get() = this.context.getBean(CommandManager::class)

/**
 * The default event publisher supports this platform
 */
val Application.publisher: Publisher
    get() = this.context.getBean(Publisher::class)

/**
 * The listener manager supports this platform
 */
val Application.listenerManager: ListenerManager
    get() = this.context.getBean(ListenerManager::class)

/**
 * The world provider supports this platform
 */
val Application.worldFactory: WorldFactory
    get() = this.context.getBean(WorldFactory::class)

/**
 * The command context factory supports this platform
 */
val Application.commandContextFactory: CommandContextFactory
    get() = this.context.getBean(CommandContextFactory::class)

/**
 * The audience factory supports this platform
 */
val Application.audienceFactory: AudienceFactory
    get() = this.context.getBean(AudienceFactory::class)

/**
 * The materials support this platform
 */
val Application.materialProvider: MaterialProvider
    get() = this.context.getBean(MaterialProvider::class)

/**
 * The entity factory supports this platform
 */
val Application.entityFactory: EntityFactory
    get() = this.context.getBean(EntityFactory::class)

/**
 * The nbt factory supports this platform
 */
val Application.nbtFactory: NBTFactory
    get() = this.context.getBean(NBTFactory::class)

/**
 * Gets the platform of this application if this application is a [PlatformApplicationImpl]
 */
val Application.platform: Platform
    get() {
        if (this is PlatformApplication) {
            return this.platform
        } else {
            throw IllegalStateException("This application is not a PlatformApplication")
        }
    }

/**
 * The `Server` instance to this server
 */
val Application.server: Server
    get() = this.context.getBean(Server::class)