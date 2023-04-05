package kingmc.platform.bukkit.entity

import kingmc.platform.entity.Entity
import kingmc.platform.entity.EntityFactory
import kingmc.platform.entity.EntityType

/**
 * A [EntityFactory] capable for providing entities for bukkit entity
 *
 * @since 0.0.7
 * @author kingsthere
 */
interface BukkitEntityFactory : EntityFactory {
    /**
     * Gets an entity for a bukkit entity
     *
     * @param bukkitEntity the bukkit entity to get from
     */
    fun getEntityForBukkit(bukkitEntity: _BukkitEntity): Entity

    /**
     * Gets a type of entity for a bukkit entity type
     *
     * @param bukkitEntityType the bukkit entity type to get from
     */
    fun getEntityTypeForBukkit(bukkitEntityType: _BukkitEntityType): EntityType<*>
}