package kingmc.platform.bukkit.impl.command

import kingmc.common.application.Application
import kingmc.platform.audience._AdventureAudience
import kingmc.platform.block.Block
import kingmc.platform.bukkit.entity.player._BukkitBlockCommandSender
import kingmc.platform.bukkit.impl.block.BukkitBlockImpl
import kingmc.platform.bukkit.impl.permission.BukkitPermissibleImpl
import kingmc.platform.command.BlockCommandSender
import kingmc.platform.permission.Permissible

class AdventureBlockCommandSenderImpl(
    private val _bukkitBlockCommandSender: _BukkitBlockCommandSender,
    adventureAudience: _AdventureAudience,
    application: Application,
    permissibleDelegate: Permissible = BukkitPermissibleImpl(_bukkitBlockCommandSender, application)
) : BukkitCommandSenderImpl(_bukkitBlockCommandSender, adventureAudience, application, permissibleDelegate), BlockCommandSender {

    private val _block = BukkitBlockImpl(_bukkitBlockCommandSender.block, application)

    /**
     * Gets the [Block] instance that this command sender represents
     */
    override fun getBlock(): Block {
        return _block
    }
}