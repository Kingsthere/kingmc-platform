package kingmc.platform.entity.entities

import java.util.*

/**
 * Represents an Animal
 */
interface Animals : Breedable {
    /**
     * The uuid of entity that breed this animal
     */
    var breedBy: UUID?

    /**
     * Get whether this entity is in love mode and will produce
     * offspring with another entity in love mode. Will return true if
     * and only if [.getLoveModeTicks] is greater than 0.
     *
     * @return true if in love mode, false otherwise
     */
    val isLoveMode: Boolean

    /**
     * The amount of ticks remaining for this entity in love mode.
     * If the entity is not in love mode, 0 will be returned.
     *
     * @return the remaining love mode ticks
     */
    var loveModeTicks: Int


}