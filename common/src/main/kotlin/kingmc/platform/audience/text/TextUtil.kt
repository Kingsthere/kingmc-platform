package kingmc.platform.audience.text

import kingmc.platform.audience.capable.TextCapable

/**
 * Send a [TextDisplayable] to this audience
 *
 * @since 0.0.2
 * @author kingsthere
 */
fun TextCapable.text(obj: TextDisplayable) {
    this.text(obj.asText())
}

/**
 * Send a [TextDisplayable] to this audience with tags
 *
 * @since 0.0.2
 * @author kingsthere
 */
fun TextCapable.text(obj: TextDisplayable, vararg marks: Mark) {
    this.text(obj.asText(), *marks)
}
