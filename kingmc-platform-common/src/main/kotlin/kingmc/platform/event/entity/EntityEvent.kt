package kingmc.platform.event.entity

import kingmc.platform.entity.Entity
import kingmc.platform.event.Event

/**
 * Represents an Entity-related event
 *
 * @author kingsthere
 * @since 0.0.7
 */
@Event
abstract class EntityEvent(val entity: Entity)