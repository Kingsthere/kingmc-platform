package kingmc.platform.entity

import kingmc.platform.inventory.*

/**
 * Represents a human entity, this may is an NPC or a player
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface HumanEntity : LivingEntity, AnimalTamer, InventoryHolder, InventoryViewer {
    /**
     * The name of this player
     */
    override val name: String

    /**
     * The inventory of this player
     */
    override val inventory: PlayerInventory

    /**
     * Check if the player is currently blocking
     *
     * For minecraft version before 1.9 (e.g. 1.8.9, 1.8.8) it checks if player blocking with a sword
     *
     * For minecraft version after or equals 1.9 (e.g. 1.9.4 1.12.2) it checks if player blocking with a shield
     */
    val isBlocking: Boolean

    /**
     * The ender chest inventory of this player
     */
    val enderChest: Inventory

    /**
     * The selected `MainHand` of this player
     */
    val mainHand: MainHand

    /**
     * The player's current exhaustion level of this player
     *
     * Exhaustion controls how fast the food level drops. While you have a certain
     * amount of exhaustion, your saturation will drop to zero, and then your food will drop to zero
     */
    var exhaustion: Float

    /**
     * The player's current saturation level
     */
    var saturation: Float

    /**
     * The player's current food level
     */
    var foodLevel: Int

    /**
     * the regeneration rate (1 health per x ticks) of the `HumanEntity` when they
     * have saturation and their food level is >= 20. Default is 10
     */
    var saturatedRegenRate: Int

    /**
     * the regeneration rate (1 health per x ticks) of the HumanEntity when they
     * have no saturation and their food level is >= 18. Default is 80
     */
    var unsaturatedRegenRate: Int

    /**
     * Get the starvation rate (1 health per x ticks) of the HumanEntity. Default is 80
     */
    var starvationRate: Int
}