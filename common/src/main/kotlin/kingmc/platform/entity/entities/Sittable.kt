package kingmc.platform.entity.entities

/**
 * An entity that can sit still
 */
interface Sittable {
    /**
     * Whether this animal is sitting. Will remove any path that the animal
     * was following beforehand
     */
    var isSitting: Boolean
}

/**
 * Make this animal sit on the ground
 */
fun Sittable.sit() {
    this.isSitting = true
}