package kingmc.platform.audience.text.serializer

import kingmc.platform.audience.text.Text
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

typealias LegacyComponentSerializer = LegacyComponentSerializer

/**
 * Current legacy component serializer to serialize
 * components to string
 *
 * @since 0.0.3
 * @author kingsthere
 * @see LegacyComponentSerializer
 */
val legacyComponentSerializer = LegacyComponentSerializer.builder()
    .hexColors()
    .build()

/**
 * Serialize from a [Text] to legacy string
 *
 * @since 0.0.3
 * @author kingsthere
 * @see LegacyComponentSerializer
 */
fun Text.serializeFromTextToLegacy(): String =
    legacyComponentSerializer.serialize(this)

/**
 * Deserialize from a legacy string to [Text]
 *
 * @since 0.0.3
 * @author kingsthere
 * @see LegacyComponentSerializer
 */
fun String.deserializeFromLegacyToText(): Text =
    legacyComponentSerializer.deserialize(this)