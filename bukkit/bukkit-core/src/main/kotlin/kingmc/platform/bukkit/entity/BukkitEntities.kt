package kingmc.platform.bukkit.entity

import kingmc.common.application.currentApplication
import kingmc.platform.entity.Entity
import kingmc.platform.entity.EntityType
import kingmc.platform.entityFactory

/**
 * Convert this bukkit entity to an [Entity]
 *
 * @receiver the entity to convert to
 * @since 0.0.7
 * @author kingsthere
 */
fun _BukkitEntity.asKingMC(): Entity {
    return (currentApplication().entityFactory as BukkitEntityFactory).getEntityForBukkit(this)
}

/**
 * Convert this [Entity] to a bukkit entity
 *
 * @receiver the entity to convert to
 * @since 0.0.7
 * @author kingsthere
 */
fun Entity.asBukkit(): _BukkitEntity = (this as BukkitEntity).toBukkitEntity()

/**
 * Convert this bukkit entity type to an [EntityType]
 *
 * @receiver the entity type to convert to
 * @since 0.0.7
 * @author kingsthere
 */
fun _BukkitEntityType.asKingMC(): EntityType<*> {
    return (currentApplication().entityFactory as BukkitEntityFactory).getEntityTypeForBukkit(this)
}