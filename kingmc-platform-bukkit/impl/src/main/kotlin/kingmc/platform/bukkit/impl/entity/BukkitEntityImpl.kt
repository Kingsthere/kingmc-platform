package kingmc.platform.bukkit.impl.entity

import kingmc.common.application.Application
import kingmc.common.text.EntityNBTText
import kingmc.common.text.Mark
import kingmc.common.text.Text
import kingmc.common.text.serializer.deserializeFromJsonToText
import kingmc.common.text.serializer.deserializeFromLegacyToText
import kingmc.common.text.serializer.serializeFromTextToLegacy
import kingmc.platform.Location
import kingmc.platform.Vector
import kingmc.platform.audience.Audience
import kingmc.platform.audience.bossbar.BossBar
import kingmc.platform.audience.playerlist.PlayerList
import kingmc.platform.audience.sound.Sound
import kingmc.platform.audience.sound.SoundStop
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePartType
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit._AdventureKey
import kingmc.platform.bukkit.asBukkit
import kingmc.platform.bukkit.asKingMC
import kingmc.platform.bukkit.entity.*
import kingmc.platform.bukkit.impl.permission.BukkitPermissibleImpl
import kingmc.platform.bukkit.nbt.createMutableNBTCompound
import kingmc.platform.entity.Entity
import kingmc.platform.entity.EntityType
import kingmc.platform.nbt.MutableNBTCompound
import kingmc.platform.permission.Permissible
import net.kyori.adventure.text.event.HoverEvent
import java.util.*

/**
 * A bukkit side implementation of [BukkitEntity] implemented by calling bukkit api
 *
 * @author kingsthere
 * @since 0.0.7
 */
@BukkitImplementation
open class BukkitEntityImpl(
    protected open val _bukkitEntity: _BukkitEntity,
    override val application: Application,
) : BukkitEntity,
    Permissible by BukkitPermissibleImpl(_bukkitEntity, application) {

    protected val _nbt by lazy {
        _bukkitEntity.createMutableNBTCompound(application)
    }
    override fun toBukkitEntity(): _BukkitEntity = _bukkitEntity

    override var velocity: Vector
        get() = _bukkitEntity.velocity.asKingMC()
        set(value) {
            _bukkitEntity.velocity = value.asBukkit()
        }
    override val nbt: MutableNBTCompound
        get() = _nbt
    @Deprecated("A BukkitEntityImpl always represents a spawned entity")
    override val isSpawned: Boolean
        get() = true
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
        get() = _bukkitEntity.passengers.map { it.asKingMC(application) }

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
        get() = _bukkitEntity.vehicle?.asKingMC(application)
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

    override val type: EntityType
        get() = _bukkitEntity.type.asKingMC(application)

    override fun command(command: String) {
        _bukkitEntity.server.dispatchCommand(_bukkitEntity, command)
    }

    override fun sendText(text: Text) {  }

    override fun sendText(text: Text, vararg marks: Mark) {  }

    override var playerlist: PlayerList
        get() = error("Entity don't have a player list")
        set(value) {}

    override fun showBossBar(bossBar: BossBar) {  }

    override fun hideBossBar(bossBar: BossBar) {  }

    override fun sendTitle(title: Title) {  }

    override fun <T : Any> sendTitlePart(titlePart: TitlePartType<T>, value: T) {  }

    override fun clearTitle() {  }

    override fun resetTitle() {  }

    override fun sendActionBar(text: Text) {  }

    override fun playSound(sound: Sound) {  }

    override fun playSound(sound: Sound, location: Location) {  }

    override fun stopSound(soundStop: SoundStop) {  }

    override val name: String
        get() = _bukkitEntity.name
    override var customName: Text?
        get() {
            val customName = _bukkitEntity.customName
            return customName?.deserializeFromJsonToText() ?: name.deserializeFromLegacyToText()
        }
        set(value) {
            _bukkitEntity.customName = value?.serializeFromTextToLegacy()
        }
    override val uuid: UUID
        get() = _bukkitEntity.uniqueId

    override fun asText(): Text {
        return customName ?: EntityNBTText { this.selector(uuid.toString()) }
    }

    override var location: Location
        get() = _bukkitEntity.location.asKingMC(application)
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BukkitEntityImpl) return false

        if (_bukkitEntity != other._bukkitEntity) return false

        return true
    }

    override fun hashCode(): Int {
        return _bukkitEntity.hashCode()
    }

    override fun toString(): String {
        return "BukkitEntityImpl(_bukkitEntity=$_bukkitEntity, application=$application)"
    }
}