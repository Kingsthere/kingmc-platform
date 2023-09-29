package kingmc.platform.bukkit.impl.inventory

import kingmc.platform.bukkit.entity.player._BukkitPlayer
import kingmc.platform.bukkit.entity.player.asKingMC
import kingmc.platform.bukkit.inventory.BukkitInventoryView
import kingmc.platform.bukkit.inventory._BukkitInventoryView
import kingmc.platform.inventory.Inventory
import kingmc.platform.inventory.InventoryHolder

class BukkitInventoryViewImpl(val _bukkitInventoryView: _BukkitInventoryView) : BukkitInventoryView {
    /**
     * Convert this `InventoryView` to a [org.bukkit.inventory.InventoryView]
     */
    override fun toBukkitInventoryView(): _BukkitInventoryView {
         return _bukkitInventoryView
    }

    /**
     * The holder that opened this inventory view
     */
    override val holder: InventoryHolder
        get() = (_bukkitInventoryView.player as _BukkitPlayer).asKingMC()

    /**
     * The base inventory holded by the [holder]
     */
    override val baseInventory: Inventory
        get() = TODO("Not yet implemented")

    /**
     * The extra inventory opening
     */
    override val extraInventory: Inventory
        get() = TODO("Not yet implemented")

    /**
     * The title of this inventory view
     */
    override var title: String
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun close() {
        TODO("Not yet implemented")
    }
}