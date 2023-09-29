package kingmc.platform.bukkit.util

import kingmc.common.application.Application
import kingmc.platform.bukkit.BukkitServer
import kingmc.platform.bukkit.entity.player._BukkitCommandSender
import kingmc.platform.command.CommandSender
import kingmc.platform.server
import kingmc.util.key.Key
import org.bukkit.NamespacedKey

/**
 * Convert this [org.bukkit.command.CommandSender] to `CommandSender`
 *
 * @receiver [org.bukkit.command.CommandSender] to convert to
 * @return `CommandSender` converted
 */
fun _BukkitCommandSender.asKingMC(application: Application): CommandSender {
    return (application.server as BukkitServer).getCommandSenderForBukkit(this)
}

fun NamespacedKey.asKingMC(): Key = Key(this.namespace, this.key)