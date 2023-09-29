package kingmc.platform.entity

import java.util.*

/**
 * Superinterface represents a tamer of animal
 *
 * @author kingsthere
 * @since 0.0.7
 */
interface AnimalTamer {
    /**
     * The name of this animal tamer, or `null` if a name cannot be obtained
     */
    val name: String?

    /**
     * The unique id of this animal tamer
     */
    val uuid: UUID
}