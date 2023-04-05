package kingmc.platform.bukkit.entity

import kingmc.platform.entity.Entity
import kingmc.platform.entity.EntityType

/**
 * A `EntityType` capable to convert into a `org.bukkit.entity.EntityType`
 *
 * @since 0.0.7
 * @author kingsthere
 */
interface BukkitEntityType<TEntity : Entity> : EntityType<TEntity> {
    /**
     * Convert this bukkit entity type to a `org.bukkit.entity.EntityType`
     */
    fun toBukkitEntityType(): _BukkitEntityType
}