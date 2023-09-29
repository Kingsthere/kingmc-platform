package kingmc.platform.bukkit.impl.entity

import kingmc.common.application.application
import kingmc.common.context.annotation.Autowired
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.platform.Location
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.BukkitServer
import kingmc.platform.bukkit.entity.*
import kingmc.platform.bukkit.entity.player._BukkitPlayer
import kingmc.platform.bukkit.util.asKingMC
import kingmc.platform.entity.Entity
import kingmc.platform.entity.EntityType
import kingmc.util.key.Key

/**
 * A bukkit side implementation of [BukkitEntityFactory] implemented by calling bukkit api
 *
 * @author kingsthere
 * @since 0.0.7
 */
@BukkitImplementation
@Component
@Scope(BeanScope.SINGLETON)
open class BukkitEntityFactoryImpl @Autowired constructor(val server: BukkitServer) : BukkitEntityFactory {
     private val _bukkitEntityTypes: Map<_BukkitEntityType, EntityType> by lazy {
         buildMap {
             _BukkitEntityType.entries.forEach {
                 put(it, BukkitEntityTypeImpl(it))
             }
         }
     }
    private val _keyedEntityTypes: Map<Key, EntityType>
        get() = buildMap {
            _bukkitEntityTypes.forEach { (bukkitEntityType, entityType) ->
                put(bukkitEntityType.key.asKingMC(), entityType)
            }
        }

    override fun getEntityForBukkit(bukkitEntity: _BukkitEntity): Entity {
        if (bukkitEntity is _BukkitLivingEntity) {
            if (bukkitEntity is _BukkitPlayer) {
                return server.getPlayerForBukkit(bukkitEntity)
            }
            if (bukkitEntity is _BukkitHumanEntity) {
                return BukkitHumanEntityImpl(bukkitEntity, application)
            }
            return BukkitLivingEntityImpl(bukkitEntity, application)
        }
        return BukkitEntityImpl(bukkitEntity, application)
    }

    override fun getEntityTypeForBukkit(bukkitEntityType: _BukkitEntityType): EntityType {
        return _bukkitEntityTypes[bukkitEntityType]!!
    }

    /**
     * Create an `Entity` at the given [location]
     *
     * @param location the location to create the entity
     * @param type the type of entity to create
     * @return `Entity` instance created
     */
    override fun createEntity(location: Location, type: EntityType): Entity {
        TODO("Not yet implemented")
    }

    override fun getEntityType(key: Key): EntityType {
        return requireNotNull(_keyedEntityTypes[key]) { "Entity type with key $key not found" }
    }

    override fun getEntityTypes(): Set<EntityType> {
        return _keyedEntityTypes.values.toSet()
    }

    override fun close() {
        // Close
    }
}