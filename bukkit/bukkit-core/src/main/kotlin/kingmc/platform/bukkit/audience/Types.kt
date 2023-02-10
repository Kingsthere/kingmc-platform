package kingmc.platform.bukkit.audience

import org.bukkit.OfflinePlayer
import org.bukkit.command.BlockCommandSender
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.command.ProxiedCommandSender
import org.bukkit.entity.Player

typealias OriginalBukkitPlayer = Player
typealias OriginalBukkitOfflinePlayer = OfflinePlayer
typealias OriginalBukkitCommandSender = CommandSender
typealias OriginalBukkitConsoleCommandSender = ConsoleCommandSender
typealias OriginalBukkitBlockCommandSender = BlockCommandSender
typealias OriginalBukkitProxiedCommandSender = ProxiedCommandSender