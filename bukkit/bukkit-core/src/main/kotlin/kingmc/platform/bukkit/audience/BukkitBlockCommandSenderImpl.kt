package kingmc.platform.bukkit.audience

import kingmc.platform.audience.Audience
import kingmc.platform.block.Block
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.block.BukkitBlock
import kingmc.platform.bukkit.entity.player._BukkitBlockCommandSender

class BukkitBlockCommandSenderImpl(private val _bukkitBlockCommandSender: _BukkitBlockCommandSender)
    : BukkitBlockCommandSender(), Audience by Audience.EMPTY {
    private val _block = BukkitBlock(_bukkitBlockCommandSender.block)

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