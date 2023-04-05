package kingmc.platform.bukkit.util

import kingmc.util.key.Key
import org.bukkit.NamespacedKey

fun NamespacedKey.asKingMC(): Key = Key(this.namespace, this.key)