package kingmc.platform.entity

import kingmc.platform.entity.player.Player

/**
 * Superinterface indicates subclasses is responsible to provide entities
 *
 * @see kingmc.platform.getWorld
 * @since 0.0.7
 * @author kingsthere
 */
interface EntityProvider {
    /**
     * Gets all entities from this provider
     *
     * @return The entities
     */
    fun getEntities(): List<Entity>

    /**
     * Gets a single entity by its [entity id][Entity.entityId]
     *
     * @param entityId the entityId of the entity
     * @return Entity with id [entityId], or `null` if entity with [entityId] is not exists
     */
    fun getEntity(entityId: Int): Entity?

    /**
     * Get multiple entity by its type
     *
     * @param entityType the type of entity
     * @return Each entity is an entity of [entityType]
     */
    fun <TEntity : Entity> getEntities(entityType: EntityType<TEntity>): List<TEntity>

    /**
     * Gets a single entity by its type
     *
     * @param entityType the type of entity
     * @return Entity is an entity of [entityType], or `null` if
     *         entity that is an entity of [entityType] not exists
     */
    fun <TEntity : Entity> getEntity(entityType: EntityType<TEntity>): TEntity
}

/**
 * Gets all players from this provider
 *
 * @return The players
 */
fun EntityProvider.getPlayers(): List<Player> {
    return this.getEntities().filterIsInstance<Player>()
}