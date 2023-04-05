package kingmc.platform.event.entity

import kingmc.platform.entity.Entity
import kingmc.platform.event.Event

/**
 * Represents an Entity-related event
 *
 * @since 0.0.7
 * @author kingsthere
 */
@Event
abstract class EntityEvent(open val entity: Entity)