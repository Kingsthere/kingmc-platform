package kingmc.platform.bukkit.material

import kingmc.common.text.Text
import kingmc.common.text.TranslatableText
import kingmc.platform.material.MaterialType
import kingmc.util.key.Key
import kotlin.reflect.KClass

/**
 * A material implementation supports for materials from bukkit
 * api
 *
 * @since 0.0.5
 * @author kingsthere
 */
open class BukkitMaterialType<TData : Any>(private val _bukkitMaterial: _BukkitMaterial,
                                           override val type: KClass<out TData>
) : MaterialType<TData> {
    /**
     * Convert this material type to a [org.bukkit.Material]
     */
    fun toBukkitMaterial(): _BukkitMaterial = _bukkitMaterial

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