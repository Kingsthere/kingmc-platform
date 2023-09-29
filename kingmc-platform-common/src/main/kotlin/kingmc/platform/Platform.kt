package kingmc.platform

import kingmc.common.application.Application
import kingmc.common.context.getBeanOrThrow
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
 * An interface represents the type of platform that runs an application
 *
 * @author kingsthere
 * @since 0.0.3
 */
interface Platform {
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
 * Interface extended [Platform] indicates an extendable platform that provides an
 * extension dispatcher to install extensions to this platform
 *
 * @author kingsthere
 * @since 0.0.7
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
    get() = this.context.getBeanOrThrow<CommandManager>()

/**
 * The default event publisher supports this application
 */
val Application.publisher: Publisher
    get() = this.context.getBeanOrThrow<Publisher>()

/**
 * The listener manager supports this application
 */
val Application.listenerManager: ListenerManager
    get() = this.context.getBeanOrThrow<ListenerManager>()

/**
 * The world provider supports this application
 */
val Application.worldFactory: WorldFactory
    get() = this.context.getBeanOrThrow<WorldFactory>()

/**
 * The command context factory supports this application
 */
val Application.commandContextFactory: CommandContextFactory
    get() = this.context.getBeanOrThrow<CommandContextFactory>()

/**
 * The audience factory supports this application
 */
@Deprecated("Use World or Server instead")
val Application.audienceFactory: AudienceFactory
    get() = this.context.getBeanOrThrow<AudienceFactory>()

/**
 * The materials support this application
 */
val Application.materialProvider: MaterialProvider
    get() = this.context.getBeanOrThrow<MaterialProvider>()

/**
 * The entity factory supports this application
 */
val Application.entityFactory: EntityFactory
    get() = this.context.getBeanOrThrow<EntityFactory>()

/**
 * The nbt factory supports this application
 */
val Application.nbtFactory: NBTFactory
    get() = this.context.getBeanOrThrow<NBTFactory>()

/**
 * The inventory factory supports this application
 */
val Application.inventoryFactory: InventoryFactory
    get() = this.context.getBeanOrThrow<InventoryFactory>()

/**
 * The permission registry supports this application
 */
val Application.permissionRegistry: PermissionRegistry
    get() = this.context.getBeanOrThrow<PermissionRegistry>()

/**
 * The permission dispatcher supports this application
 */
val Application.permissionDispatcher: PermissionDispatcher
    get() = this.context.getBeanOrThrow<PermissionDispatcher>()

/**
 * The permission factory supports this application
 */
val Application.scheduler: Scheduler
    get() = this.context.getBeanOrThrow<Scheduler>()

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
    get() = this.context.getBeanOrThrow<Server>()