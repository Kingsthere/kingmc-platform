package kingmc.platform.entity

import kingmc.common.application.currentApplication
import kingmc.platform.entityFactory
import kingmc.util.key.Key

/**
 * Gets a type of entity from `EntityFactory` by key
 *
 * @since 0.0.7
 * @author kingsthere
 */
@Suppress("UNCHECKED_CAST")
fun <TEntity : Entity> EntityType(key: Key): EntityType<TEntity> {
    return currentApplication().entityFactory.getEntityType(key) as EntityType<TEntity>
}