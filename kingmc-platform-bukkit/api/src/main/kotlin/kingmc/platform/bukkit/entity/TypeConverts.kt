package kingmc.platform.bukkit.entity

import kingmc.common.application.Application
import kingmc.platform.entity.Entity
import kingmc.platform.entity.EntityType
import kingmc.platform.entityFactory

/**
 * Convert this bukkit entity to an [Entity]
 *
 * @receiver the entity to convert to
 * @author kingsthere
 * @since 0.0.7
 */
// @WithApplication use parameter [application] instead
fun _BukkitEntity.asKingMC(application: Application): Entity {
    return (application.entityFactory as BukkitEntityFactory).getEntityForBukkit(this)
}

/**
 * Convert this [Entity] to a bukkit entity
 *
 * @receiver the entity to convert to
 * @author kingsthere
 * @since 0.0.7
 */
fun Entity.asBukkit(): _BukkitEntity = (this as BukkitEntity).toBukkitEntity()

/**
 * Convert this bukkit entity type to an [EntityType]
 *
 * @receiver the entity type to convert to
 * @author kingsthere
 * @since 0.0.7
 */
// @WithApplication use parameter [application[ instead
fun _BukkitEntityType.asKingMC(application: Application): EntityType {
    return (application.entityFactory as BukkitEntityFactory).getEntityTypeForBukkit(this)
}