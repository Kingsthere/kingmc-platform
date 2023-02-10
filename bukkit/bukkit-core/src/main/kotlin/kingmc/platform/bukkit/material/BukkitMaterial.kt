package kingmc.platform.bukkit.material

import kingmc.platform.Material
import kingmc.platform.audience.text.Text
import kingmc.platform.audience.text.Translatable
import kingmc.util.key.Key

/**
 * A material implementation supports for materials from bukkit
 * api
 *
 * @since 0.0.5
 * @author kingsthere
 */
open class BukkitMaterial(val originalBukkitMaterial: OriginalBukkitMaterial) : Material {
    /**
     * The key of this material
     *
     * @since 0.0.1
     */
    override val key: Key
        get() = Key("minecraft", originalBukkitMaterial.name.lowercase())

    /**
     * The name of this material
     *
     * @since 0.0.1
     */
    override val name: String
        get() = originalBukkitMaterial.name

    /**
     * Convert this object into a [Text]
     */
    override fun asText(): Text {
        val key = key
        return Translatable { key("block.${key.namespace()}.${key.value()}") }
    }
}