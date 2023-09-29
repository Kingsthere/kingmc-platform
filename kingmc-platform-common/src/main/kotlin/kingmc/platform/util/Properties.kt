package kingmc.platform.util

import com.electronwill.nightconfig.core.Config
import java.util.*

fun loadConfigIntoProperties(config: Config, properties: Properties, path: String = "") {
    for (entry in config.entrySet()) {
        val value = entry.getValue<Any>()
        if (value is Config) {
            loadConfigIntoProperties(value, properties, path + "${entry.key}.")
        } else {
            properties[path + entry.key] = entry.getValue()
        }
    }
}