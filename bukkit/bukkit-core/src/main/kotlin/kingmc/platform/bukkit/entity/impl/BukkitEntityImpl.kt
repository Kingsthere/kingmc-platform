package kingmc.platform.bukkit.entity.impl

import kingmc.common.application.Application
import kingmc.common.text.Text
import kingmc.common.text.serializer.deserializeFromJsonToText
import kingmc.common.text.serializer.deserializeFromLegacyToText
import kingmc.common.text.serializer.serializeFromTextToLegacy
import kingmc.platform.Location
import kingmc.platform.Vector
import kingmc.platform.audience.Audience
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.adventure._AdventureKey
import kingmc.platform.bukkit.asBukkit
import kingmc.platform.bukkit.asKingMC
import kingmc.platform.bukkit.entity.*
import kingmc.platform.bukkit.nbt.BukkitNBTFactory
import kingmc.platform.entity.Entity
import kingmc.platform.entity.EntityType
import kingmc.platform.nbt.MutableNBTCompound
import kingmc.platform.nbtFactory
import net.kyori.adventure.text.event.HoverEvent
import java.util.*

/**
 * An official implementation of [BukkitEntity]
 *
 * @since 0.0.7
 * @author kingsthere
 */
@BukkitImplementation
open class BukkitEntityImpl(private val _bukkitEntity: _BukkitEntity, override val application: Application)
    : BukkitEntity, Audience by Audience.EMPTY {
    protected val _nbt by lazy {
        (application.nbtFactory as BukkitNBTFactory).createMutableNBTCompoundForEntity(_bukkitEntity)
    }
    override fun toBukkitEntity(): _BukkitEntity = _bukkitEntity

    override var velocity: Vector
        get() = _bukkitEntity.velocity.asKingMC()
        set(value) {
            _bukkitEntity.velocity = value.asBukkit()
        }
    override val nbt: MutableNBTCompound
        get() = _nbt
    override val entityId: Int
        get() = _bukkitEntity.entityId
    override var fireTicks: Int
        get() = _bukkitEntity.fireTicks
        set(value) {
            _bukkitEntity.fireTicks = value
        }
    override var visualFire: Boolean
        get() = _bukkitEntity.isVisualFire
        set(value) {
            _bukkitEntity.isVisualFire = value
        }
    override var freezeTicks: Int
        get() = _bukkitEntity.freezeTicks
        set(value) {
            _bukkitEntity.freezeTicks = value
        }
    override val maxFireTicks: Int
        get() = _bukkitEntity.maxFireTicks
    override val maxFreezeTicks: Int
        get() = _bukkitEntity.maxFreezeTicks
    override val isOnGround: Boolean
        get() = _bukkitEntity.isOnGround
    override val isInWater: Boolean
        get() = _bukkitEntity.isInWater

    override fun isFrozen(): Boolean {
        return _bukkitEntity.isFrozen
    }

    override fun remove() {
        _bukkitEntity.remove()
    }

    override fun isDead(): Boolean {
        return _bukkitEntity.isDead
    }

    override fun isValid(): Boolean {
        return _bukkitEntity.isValid
    }

    @Suppress("DEPRECATION")
    @Deprecated("entities may have multiple passengers", replaceWith = ReplaceWith("addPassenger"))
    override fun setPassenger(passenger: Entity): Boolean {
        return _bukkitEntity.setPassenger(passenger.asBukkit())
    }

    override val passengers: List<Entity?>
        get() = _bukkitEntity.passengers.map { it.asKingMC() }

    override fun addPassenger(passenger: Entity): Boolean {
        return _bukkitEntity.addPassenger(passenger.asBukkit())
    }

    override fun removePassenger(passenger: Entity): Boolean {
        return _bukkitEntity.removePassenger(passenger.asBukkit())
    }

    override fun eject(): Boolean {
        return _bukkitEntity.eject()
    }

    override var fallDistance: Float
        get() = _bukkitEntity.fallDistance
        set(value) {
            _bukkitEntity.fallDistance = value
        }
    override var ticksLived: Int
        get() = _bukkitEntity.ticksLived
        set(value) {
            _bukkitEntity.ticksLived = value
        }
    override var vehicle: Entity?
        get() = _bukkitEntity.vehicle?.asKingMC()
        set(value) {
            if (value != null) {
                mount(value)
            } else {
                dismount()
            }
        }

    override fun mount(vehicle: Entity) {
        vehicle.addPassenger(this)
    }

    override fun dismount(): Boolean {
        return _bukkitEntity.leaveVehicle()
    }

    override var isInvisible: Boolean
        get() = (_bukkitEntity as _BukkitLivingEntity).isInvisible
        set(value) {
            (_bukkitEntity as _BukkitLivingEntity).isInvisible = value
        }

    @Suppress("UNCHECKED_CAST")
    override val type: EntityType<Entity>
        get() = _bukkitEntity.type.asKingMC() as EntityType<Entity>

    override fun chat(message: String) {
        _bukkitEntity.server.dispatchCommand(_bukkitEntity, message.removePrefix("/"))
    }

    override val name: String
        get() = _bukkitEntity.name
    override var customName: Text
        get() {
            val customName = _bukkitEntity.customName
            return customName?.deserializeFromJsonToText() ?: name.deserializeFromLegacyToText()
        }
        set(value) {
            _bukkitEntity.customName = value.serializeFromTextToLegacy()
        }
    override val uuid: UUID
        get() = _bukkitEntity.uniqueId

    override fun asText(): Text {
        return customName
    }

    override var location: Location
        get() = _bukkitEntity.location.asKingMC()
        set(value) {
            _bukkitEntity.teleport(value.asBukkit())
        }

    override fun teleport(location: Location) {
        _bukkitEntity.teleport(location.asBukkit())
    }

    override fun asHoverEvent(): HoverEvent<*> {
        return HoverEvent.showEntity(HoverEvent.ShowEntity.of(
            /* type = */ _AdventureKey.key(this.type.key.namespace(), this.type.key.value()),
            /* id = */ this.uuid,
            /* name = */ this.customName)
        )
    }
}