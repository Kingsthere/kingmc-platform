package kingmc.platform.entity

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.entityFactory
import kingmc.util.key.Key

/**
 * Gets a type of entity from `EntityFactory` by key
 *
 * @since 0.0.7
 * @author kingsthere
 */
@WithApplication
fun EntityType(key: Key): EntityType {
    return currentApplication().entityFactory.getEntityType(key)
}