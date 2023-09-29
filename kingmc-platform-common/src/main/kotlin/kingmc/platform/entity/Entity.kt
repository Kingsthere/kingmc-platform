package kingmc.platform.entity

import kingmc.common.application.Application
import kingmc.common.application.WithApplication
import kingmc.common.text.Text
import kingmc.platform.MutableLocatable
import kingmc.platform.Vector
import kingmc.platform.World
import kingmc.platform.command.CommandSender
import kingmc.platform.nbt.MutableNBTCompound
import kingmc.platform.nbt.MutableNBTCompoundHolder
import kingmc.platform.util.Nameable
import kingmc.platform.util.Versioned
import com.google.errorprone.annotations.CanIgnoreReturnValue
import kingmc.util.text.HoverEventDisplayable
import kingmc.util.text.TextDisplayable
import java.util.*

/**
 * An interface represent an in-game entity that could be spawned to a world
 *
 * @author kingsthere
 * @since 0.1.0
 */
interface Entity : Nameable, CommandSender, MutableLocatable, TextDisplayable, HoverEventDisplayable, MutableNBTCompoundHolder {
    /**
     * The name of this entity
     */
    val name: String

    /**
     * The application that created this entity
     */
    val application: Application

    /**
     * The custom name of this entity
     */
    override var customName: Text?

    /**
     * The uuid of this entity, unlike [entityId] this uuid is for persistence
     */
    val uuid: UUID

    /**
     * The velocity of this entity
     */
    var velocity: Vector

    /**
     * The nbt data of this entity
     */
    override val nbt: MutableNBTCompound

    /**
     * Returns `true` if this entity is spawned on any world
     */
    val isSpawned: Boolean

    /**
     * The entity id of this entity
     */
    val entityId: Int

    /**
     * The entity's fire ticks (ticks before the entity stops being on fire)
     */
    var fireTicks: Int

    /**
     * `true` If the entity has visual fire (it will always appear to be on fire)
     */
    var visualFire: Boolean

    /**
     * The entity's current freeze ticks (amount of ticks the entity has been in powdered snow)
     */
    @Versioned("1.17..")
    var freezeTicks: Int

    /**
     * The entity's maximum fire ticks
     */
    val maxFireTicks: Int

    /**
     * The entity's maximum freeze ticks
     */
    @Versioned("1.17..")
    val maxFreezeTicks: Int

    /**
     * `true` if the entity is supported by a block
     */
    val isOnGround: Boolean

    /**
     * `true` if the entity is in water
     */
    val isInWater: Boolean

    /**
     * Gets if the entity is fully frozen (it has been in powdered snow for max
     * freeze ticks)
     */
    @Versioned("1.17..")
    fun isFrozen(): Boolean

    /**
     * Remove this entity
     */
    fun remove()

    /**
     * Returns true if this entity has been marked for removal
     */
    fun isDead(): Boolean

    /**
     * Returns false if the entity has died or been despawned for some other
     * reason
     */
    fun isValid(): Boolean

    /**
     * Set the passenger of a vehicle.
     *
     * @param passenger The new passenger.
     * @return false if it could not be done for whatever reason
     */
    @Deprecated(
        message = "entities may have multiple passengers",
        replaceWith = ReplaceWith("addPassenger")
    )
    fun setPassenger(passenger: Entity): Boolean

    /**
     * A list of passengers of this vehicle
     */
    val passengers: List<Entity?>

    /**
     * Add a passenger to the vehicle
     *
     * @param passenger The passenger to add
     * @return false if it could not be done for whatever reason
     */
    @CanIgnoreReturnValue
    fun addPassenger(passenger: Entity): Boolean

    /**
     * Remove a passenger from the vehicle
     *
     * @param passenger The passenger to remove
     * @return false if it could not be done for whatever reason
     */
    @CanIgnoreReturnValue
    fun removePassenger(passenger: Entity): Boolean

    /**
     * Eject any passenger.
     *
     * @return True if there was a passenger.
     */
    @CanIgnoreReturnValue
    fun eject(): Boolean

    /**
     * The distance this entity has fallen
     */
    var fallDistance: Float

    /**
     * Ticks that this entity has lived
     */
    var ticksLived: Int

    /**
     * The vehicle that the entity is in, or "null" if the entity does not have any vehicles mounted on it
     */
    var vehicle: Entity?
        @Deprecated("Use `mount` or `dismount` instead")
        set

    /**
     * Let this entity mount on a [vehicle]
     */
    fun mount(vehicle: Entity)

    /**
     * Dismount the current vehicle. If the entity is currently in a vehicle (and is removed from it),
     * true will be returned, otherwise false will be returned
     */
    @CanIgnoreReturnValue
    fun dismount(): Boolean

    /**
     * Whether the entity is invisible or not
     */
    var isInvisible: Boolean


    /**
     * The type of entity this entity is
     */
    @get:WithApplication
    val type: EntityType

    /**
     * The world of entity, the world of an entity shouldn't be `null`
     */
    val world: World
        get() = location.world!!
}
