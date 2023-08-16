package kingmc.platform.entity

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.entityFactory
import kingmc.util.key.Key

/**
 * Run the given [block] if this entity [is spawned][Entity.isSpawned]
 *
 * @receiver Entity to check
 * @param block block to run if the receiver entity is spawned
 * @return value returned by [block] given executed
 */
fun <T> Entity.ifSpawned(block: (entity: Entity) -> T): T? {
    return if (this.isSpawned) {
        block(this)
    } else {
        null
    }
}

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