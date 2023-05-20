package kingmc.platform.entity

import kingmc.common.application.Isolated
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.util.key.Key
import java.io.Closeable

/**
 * A factory responsible for registering entity types & provide entities
 *
 * @since 0.0.7
 * @author kingsthere
 */
@Component
@Scope(BeanScope.SINGLETON)
@Isolated
interface EntityFactory : Closeable {
    /**
     * Gets a type of entity registered in this entity factory
     */
    fun getEntityType(key: Key) : EntityType

    /**
     * Gets all entity types registered in this factory
     */
    fun getEntityTypes(): Set<EntityType>
}