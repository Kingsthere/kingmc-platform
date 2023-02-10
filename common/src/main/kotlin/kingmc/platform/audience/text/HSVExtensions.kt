package kingmc.platform.audience.text
/**
 * The [HSVLike.h] component.
 *
 * Allows for destructuring into `(h, s, v)`.
 *
 * @return the [HSVLike.h] component
 * @since 4.10.0
 */
operator fun HSVLike.component1(): Float = h()

/**
 * The [HSVLike.s] component.
 *
 * Allows for destructuring into `(h, s, v)`.
 *
 * @return the [HSVLike.s] component
 * @since 4.10.0
 */
operator fun HSVLike.component2(): Float = s()

/**
 * The [HSVLike.v] component.
 *
 * Allows for destructuring into `(h, s, v)`.
 *
 * @return the [HSVLike.v] component
 * @since 4.10.0
 */
operator fun HSVLike.component3(): Float = v()