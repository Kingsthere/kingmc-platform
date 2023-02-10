package kingmc.platform.audience.text

import kingmc.platform.audience.text.format.Style
import kingmc.platform.audience.text.format.StyleBuilder
import org.jetbrains.annotations.Contract

/**
 * Builds a new [Style] from the specified [builder].
 *
 * @param builder the builder to apply values from
 * @return a new [Style]
 * @since 4.6.0
 */
fun style(builder: StyleBuilder.() -> Unit): Style = Style.style(builder)

/**
 * Allows editing using [Style.Builder] as the receiver parameter.
 *
 * @param consumer the consumer to edit this style with
 * @return a new style, with the changes applied from this builder
 * @since 4.10.0
 */
@Contract("_ -> new")
fun Style.edit(consumer: StyleBuilder.() -> Unit): Style = edit(consumer)