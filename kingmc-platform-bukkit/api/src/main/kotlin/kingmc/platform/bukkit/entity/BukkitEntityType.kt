package kingmc.platform.bukkit.entity

import kingmc.platform.entity.EntityType

/**
 * A `EntityType` capable to convert into a `org.bukkit.entity.EntityType`
 *
 * @author kingsthere
 * @since 0.0.7
 */
interface BukkitEntityType : EntityType {
    /**
     * Convert this bukkit entity type to a `org.bukkit.entity.EntityType`
     */
    fun toBukkitEntityType(): _BukkitEntityType
}