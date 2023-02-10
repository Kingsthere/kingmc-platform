package kingmc.platform.nbt

import de.tr7zw.changeme.nbtapi.NBTCompound
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT
import de.tr7zw.changeme.nbtapi.iface.ReadableNBT
import kingmc.platform.audience.text.Text
import kingmc.platform.audience.text.serializer.deserializeFromJsonToText
import kingmc.platform.audience.text.serializer.deserializeFromLegacyToText
import kingmc.platform.audience.text.serializer.serializeFromTextToJson
import kingmc.platform.audience.text.serializer.serializeFromTextToLegacy


/**
 * Gets an [Enum] [E] for the given [key]
 *
 * @throws IllegalArgumentException if the given key is not defined in this `NBT`
 */
inline fun <reified E : Enum<E>> ReadableNBT.getEnum(key: String): E {
    return java.lang.Enum.valueOf(E::class.java, getString(key))
}

/**
 * Gets a [Text] value for json string from this [ReadableNBT]
 */
fun ReadableNBT.getTextForJson(key: String): Text? =
    getString(key)?.deserializeFromJsonToText()

/**
 * Gets a list of [Text] value for list of json strings from this [ReadableNBT]
 */
fun ReadableNBT.getTextListForJson(key: String): List<Text> =
    getStringList(key).map { it.deserializeFromJsonToText() }

/**
 * Gets a [Text] value for legacy string from this [ReadableNBT]
 */
fun ReadableNBT.getTextForLegacy(key: String): Text? =
    getString(key)?.deserializeFromLegacyToText()

/**
 * Gets a list of [Text] value for list of legacy strings from this [ReadableNBT]
 */
fun ReadableNBT.getTextListForLegacy(key: String): List<Text> =
    getStringList(key).map { it.deserializeFromLegacyToText() }

/**
 * Set the value for the [key] to a [Text] (storing [Text] as a json string to nbt)
 */
fun ReadWriteNBT.setTextForJson(key: String, text: Text) =
    setString(key, text.serializeFromTextToJson())

/**
 * Set the value for the [key] to a list of [Text] (storing [Text] as json strings to nbt)
 */
fun ReadWriteNBT.setTextListForJson(key: String, texts: List<Text>) =
    getStringList(key).apply {
        clear()
        addAll(texts.map { it.serializeFromTextToJson() })
    }!!

/**
 * Set the value for the [key] to a [Text] (storing [Text] as a legacy string to nbt)
 */
fun ReadWriteNBT.setTextForLegacy(key: String, text: Text) =
    setString(key, text.serializeFromTextToLegacy())

/**
 * Gets a list of [Text] value for list of legacy strings from this [NBTCompound]
 */
fun ReadWriteNBT.setTextListForLegacy(key: String, texts: List<Text>) =
    getStringList(key).apply {
        clear()
        addAll(texts.map { it.serializeFromTextToLegacy() })
    }!!