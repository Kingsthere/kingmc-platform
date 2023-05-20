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
import kingmc.platform.inventory.InventoryFactory
import kingmc.platform.material.MaterialProvider
import kingmc.platform.nbt.NBTFactory
import kingmc.platform.permission.PermissionDispatcher
import kingmc.platform.permission.PermissionRegistry
import kingmc.platform.schedule.Scheduler
import kingmc.util.Version

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
    val minecraftVersion: Version

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
 * The command manager supports this application
 */
val Application.commandFactory: CommandManager
    get() = this.context.getBean(CommandManager::class)

/**
 * The default event publisher supports this application
 */
val Application.publisher: Publisher
    get() = this.context.getBean(Publisher::class)

/**
 * The listener manager supports this application
 */
val Application.listenerManager: ListenerManager
    get() = this.context.getBean(ListenerManager::class)

/**
 * The world provider supports this application
 */
val Application.worldFactory: WorldFactory
    get() = this.context.getBean(WorldFactory::class)

/**
 * The command context factory supports this application
 */
val Application.commandContextFactory: CommandContextFactory
    get() = this.context.getBean(CommandContextFactory::class)

/**
 * The audience factory supports this application
 */
val Application.audienceFactory: AudienceFactory
    get() = this.context.getBean(AudienceFactory::class)

/**
 * The materials support this application
 */
val Application.materialProvider: MaterialProvider
    get() = this.context.getBean(MaterialProvider::class)

/**
 * The entity factory supports this application
 */
val Application.entityFactory: EntityFactory
    get() = this.context.getBean(EntityFactory::class)

/**
 * The nbt factory supports this application
 */
val Application.nbtFactory: NBTFactory
    get() = this.context.getBean(NBTFactory::class)

/**
 * The inventory factory supports this application
 */
val Application.inventoryFactory: InventoryFactory
    get() = this.context.getBean(InventoryFactory::class)

/**
 * The permission registry supports this application
 */
val Application.permissionRegistry: PermissionRegistry
    get() = this.context.getBean(PermissionRegistry::class)

/**
 * The permission dispatcher supports this application
 */
val Application.permissionDispatcher: PermissionDispatcher
    get() = this.context.getBean(PermissionDispatcher::class)

/**
 * The permission factory supports this application
 */
val Application.scheduler: Scheduler
    get() = this.context.getBean(Scheduler::class)

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