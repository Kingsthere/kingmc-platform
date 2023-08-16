package kingmc.platform.entity

import kingmc.common.application.Isolated
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.platform.Location
import kingmc.util.key.Key

/**
 * A factory responsible for registering entity types & provide entities
 *
 * @since 0.0.7
 * @author kingsthere
 */
@Component
@Scope(BeanScope.SINGLETON)
@Isolated
interface EntityFactory {
    /**
     * Create an `Entity` at the given [location]
     *
     * @param location the location to create the entity
     * @param type the type of entity to create
     * @return `Entity` instance created
     */
    fun createEntity(location: Location, type: EntityType): Entity

    /**
     * Gets a type of entity registered in this entity factory
     */
    fun getEntityType(key: Key) : EntityType

    /**
     * Gets all entity types registered in this factory
     */
    fun getEntityTypes(): Set<EntityType>

    /**
     * Close this entity factory
     */
    fun close()
}