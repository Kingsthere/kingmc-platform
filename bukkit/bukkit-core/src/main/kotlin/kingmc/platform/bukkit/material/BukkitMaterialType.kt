package kingmc.platform.bukkit.material

import kingmc.common.text.Text
import kingmc.common.text.TranslatableText
import kingmc.platform.MaterialType
import kingmc.util.key.Key

/**
 * A material implementation supports for materials from bukkit
 * api
 *
 * @since 0.0.5
 * @author kingsthere
 */
open class BukkitMaterialType<TData>(val _bukkitMaterial: _BukkitMaterial) : MaterialType<TData> {
    /**
     * The key of this material
     *
     * @since 0.0.1
     */
    override val key: Key
        get() = Key("minecraft", _bukkitMaterial.name.lowercase())

    /**
     * The name of this material
     *
     * @since 0.0.1
     */
    @Deprecated("Name is no longer the identifier for MaterialType(s)", ReplaceWith("_bukkitMaterial.name"))
    override val name: String
        get() = _bukkitMaterial.name

    /**
     * The display name of this material
     */
    override val displayName: Text
        get() = TranslatableText { key(_bukkitMaterial.translationKey) }

    /**
     * Convert this object into a [Text]
     */
    override fun asText(): Text {
        return TranslatableText { key(_bukkitMaterial.translationKey) }
    }
}