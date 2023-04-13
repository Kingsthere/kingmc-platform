package kingmc.platform.bukkit.impl.entity

import kingmc.common.application.Application
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.entity.BukkitEntity
import kingmc.platform.bukkit.entity._BukkitHumanEntity
import kingmc.platform.bukkit.inventory.asBukkit
import kingmc.platform.bukkit.inventory.asKingMC
import kingmc.platform.entity.HumanEntity
import kingmc.platform.inventory.Inventory
import kingmc.platform.inventory.InventoryView
import kingmc.platform.inventory.MainHand
import kingmc.platform.inventory.PlayerInventory
import org.bukkit.event.inventory.InventoryType

/**
 * Bukkit [HumanEntity] implementation
 *
 * @since 0.0.8
 * @author kingsthere
 */
@BukkitImplementation
open class BukkitHumanEntityImpl(val _bukkitHumanEntity: _BukkitHumanEntity, application: Application) : BukkitLivingEntityImpl(
    _bukkitHumanEntity,
    application
), BukkitEntity, HumanEntity {
    /**
     * The inventory of this player
     */
    override val inventory: PlayerInventory
        get() = _bukkitHumanEntity.inventory.asKingMC(application)

    /**
     * Check if the player is currently blocking
     *
     * For minecraft version before 1.9 (e.g. 1.8.9, 1.8.8) it checks if player blocking with a sword
     *
     * For minecraft version after or equals 1.9 (e.g. 1.9.4 1.12.2) it checks if player blocking with a shield
     */
    override val isBlocking: Boolean
        get() = _bukkitHumanEntity.isBlocking

    /**
     * The ender chest inventory of this player
     */
    override val enderChest: Inventory
        get() = _bukkitHumanEntity.enderChest.asKingMC(application)

    /**
     * The selected `MainHand` of this player
     */
    override val mainHand: MainHand
        get() = _bukkitHumanEntity.mainHand.asKingMC()

    /**
     * The player's current exhaustion level of this player
     *
     * Exhaustion controls how fast the food level drops. While you have a certain
     * amount of exhaustion, your saturation will drop to zero, and then your food will drop to zero
     */
    override var exhaustion: Float
        get() = _bukkitHumanEntity.exhaustion
        set(value) {
            _bukkitHumanEntity.exhaustion = value
        }

    /**
     * The player's current saturation level
     */
    override var saturation: Float
        get() = _bukkitHumanEntity.saturation
        set(value) {
            _bukkitHumanEntity.saturation = value
        }

    /**
     * The player's current food level
     */
    override var foodLevel: Int
        get() = _bukkitHumanEntity.foodLevel
        set(value) {
            _bukkitHumanEntity.foodLevel = value
        }

    /**
     * the regeneration rate (1 health per x ticks) of the `HumanEntity` when they
     * have saturation and their food level is >= 20. Default is 10
     */
    override var saturatedRegenRate: Int
        get() = _bukkitHumanEntity.saturatedRegenRate
        set(value) {
            _bukkitHumanEntity.saturatedRegenRate = value
        }

    /**
     * the regeneration rate (1 health per x ticks) of the HumanEntity when they
     * have no saturation and their food level is >= 18. Default is 80
     */
    override var unsaturatedRegenRate: Int
        get() = _bukkitHumanEntity.unsaturatedRegenRate
        set(value) {
            _bukkitHumanEntity.unsaturatedRegenRate = value
        }

    /**
     * Get the starvation rate (1 health per x ticks) of the HumanEntity. Default is 80
     */
    override var starvationRate: Int
        get() = _bukkitHumanEntity.starvationRate
        set(value) {
            _bukkitHumanEntity.starvationRate = value
        }

    /**
     * The inventory that opened by this inventory viewer
     */
    override val openedInventory: InventoryView?
        get() {
            val openedInventory = _bukkitHumanEntity.openInventory
            return if (openedInventory.type == InventoryType.PLAYER) {
                null
            } else {
                openedInventory.asKingMC(application)
            }
        }

    /**
     * Open an inventory for this inventory viewer
     *
     * @param inventory the inventory to open
     * @return The newly opened inventory view
     */
    override fun openInventory(inventory: Inventory): InventoryView {
        return checkNotNull(_bukkitHumanEntity.openInventory(inventory.asBukkit())) { "Unable to open inventory for $this" }.asKingMC(application)
    }

    /**
     * Force-closes the currently open inventory view for this player, if any
     */
    override fun closeInventory() {
        _bukkitHumanEntity.closeInventory()
    }
}