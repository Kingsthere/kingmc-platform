package kingmc.platform.audience.text

/**
 * The [RGBLike.red] component.
 *
 * Allows for destructuring into `(r, g, b)`.
 *
 * @return the [RGBLike.red] component
 * @since 4.6.0
 */
operator fun RGBLike.component1(): Int = this.red()

/**
 * The [RGBLike.green] component.
 *
 * Allows for destructuring into `(r, g, b)`.
 *
 * @return the [RGBLike.green] component
 * @since 4.6.0
 */
operator fun RGBLike.component2(): Int = this.green()

/**
 * The [RGBLike.blue] component.
 *
 * Allows for destructuring into `(r, g, b)`.
 *
 * @return the [RGBLike.blue] component
 * @since 4.6.0
 */
operator fun RGBLike.component3(): Int = this.blue()