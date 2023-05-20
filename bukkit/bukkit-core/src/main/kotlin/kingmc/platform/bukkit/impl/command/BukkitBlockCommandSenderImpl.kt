package kingmc.platform.bukkit.impl.command

import kingmc.common.application.Application
import kingmc.platform.audience.Audience
import kingmc.platform.block.Block
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.command.BukkitBlockCommandSender
import kingmc.platform.bukkit.entity.player._BukkitBlockCommandSender
import kingmc.platform.bukkit.impl.block.BukkitBlockImpl
import kingmc.platform.permission.Permissible

class BukkitBlockCommandSenderImpl(private val _bukkitBlockCommandSender: _BukkitBlockCommandSender, val application: Application)
    : BukkitBlockCommandSender(), Audience by Audience.EMPTY, Permissible by Permissible.ALWAYS {
    private val _block = BukkitBlockImpl(_bukkitBlockCommandSender.block, application)

    /**
     * Gets the [Block] instance that this command sender represents
     */
    override fun getBlock(): Block {
        return _block
    }

    /**
     * To let this command sender send a
     * chat message
     *
     * @since 0.0.3
     * @author kingsthere
     */
    override fun chat(message: String) {
        Bukkit.dispatchCommand(this._bukkitBlockCommandSender, message.removePrefix("/"))
    }
}