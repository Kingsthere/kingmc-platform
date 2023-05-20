package kingmc.platform.velocity.impl.entity.player

import kingmc.common.application.Application
import kingmc.common.text.Mark
import kingmc.common.text.Text
import kingmc.common.text.serializer.deserializeFromJsonToText
import kingmc.platform.Location
import kingmc.platform.Vector
import kingmc.platform.audience.bossbar.BossBar
import kingmc.platform.audience.particle.*
import kingmc.platform.audience.playerlist.PlayerList
import kingmc.platform.audience.sound.Sound
import kingmc.platform.audience.sound.SoundStop
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePartType
import kingmc.platform.entity.Entity
import kingmc.platform.entity.EntityType
import kingmc.platform.entity.damage.DamageSource
import kingmc.platform.entity.damage.ModifiedDamage
import kingmc.platform.inventory.Inventory
import kingmc.platform.inventory.InventoryView
import kingmc.platform.inventory.MainHand
import kingmc.platform.inventory.PlayerInventory
import kingmc.platform.messaging.OutputMessage
import kingmc.platform.nbt.MutableNBTCompound
import kingmc.platform.permission.Permission
import kingmc.platform.permission.PermissionState
import kingmc.platform.util.ProtocolVersion
import kingmc.platform.velocity._VelocityPlayer
import kingmc.platform.velocity.command._VelocityCommandSender
import kingmc.platform.velocity.entity.player.VelocityPlayer
import net.kyori.adventure.text.event.HoverEvent
import java.net.InetSocketAddress
import java.util.*

class VelocityPlayerImpl(val _velocityPlayer: _VelocityPlayer) : VelocityPlayer {
    override fun toVelocityPlayer(): _VelocityPlayer = _velocityPlayer

    override val isOnGround: Boolean
        get() = TODO("Not yet implemented")
    override val displayName: Text
        get() = _velocityPlayer.username.deserializeFromJsonToText()
    override var isSneaking: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    override var isSprinting: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    override val ping: Long
        get() = TODO("Not yet implemented")
    override val clientBrand: String?
        get() = TODO("Not yet implemented")

    override fun disconnect(reason: Text) {
        TODO("Not yet implemented")
    }

    override val name: String
        get() = TODO("Not yet implemented")
    override val inventory: PlayerInventory
        get() = TODO("Not yet implemented")
    override val isBlocking: Boolean
        get() = TODO("Not yet implemented")
    override val enderChest: Inventory
        get() = TODO("Not yet implemented")
    override val mainHand: MainHand
        get() = TODO("Not yet implemented")
    override var exhaustion: Float
        get() = TODO("Not yet implemented")
        set(value) {}
    override var saturation: Float
        get() = TODO("Not yet implemented")
        set(value) {}
    override var foodLevel: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var saturatedRegenRate: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var unsaturatedRegenRate: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var starvationRate: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var remainingAir: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var maximumAir: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var isGliding: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    override var isSwimming: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    override var customName: Text?
        get() = TODO("Not yet implemented")
        set(value) {}
    override val uuid: UUID
        get() = TODO("Not yet implemented")
    override val application: Application
        get() = TODO("Not yet implemented")
    override var velocity: Vector
        get() = TODO("Not yet implemented")
        set(value) {}
    override val nbt: MutableNBTCompound
        get() = TODO("Not yet implemented")
    override val entityId: Int
        get() = TODO("Not yet implemented")
    override var fireTicks: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var visualFire: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    override var freezeTicks: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override val maxFireTicks: Int
        get() = TODO("Not yet implemented")
    override val maxFreezeTicks: Int
        get() = TODO("Not yet implemented")
    override val isInWater: Boolean
        get() = TODO("Not yet implemented")

    override fun isFrozen(): Boolean {
        TODO("Not yet implemented")
    }

    override fun remove() {
        TODO("Not yet implemented")
    }

    override fun isDead(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setPassenger(passenger: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override val passengers: List<Entity?>
        get() = TODO("Not yet implemented")

    override fun addPassenger(passenger: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun removePassenger(passenger: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun eject(): Boolean {
        TODO("Not yet implemented")
    }

    override var fallDistance: Float
        get() = TODO("Not yet implemented")
        set(value) {}
    override var ticksLived: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var vehicle: Entity?
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun mount(vehicle: Entity) {
        TODO("Not yet implemented")
    }

    override fun dismount(): Boolean {
        TODO("Not yet implemented")
    }

    override var isInvisible: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    override val type: EntityType
        get() = TODO("Not yet implemented")

    override fun chat(message: String) {
        TODO("Not yet implemented")
    }

    override fun sendText(text: Text) {
        TODO("Not yet implemented")
    }

    override fun sendText(text: Text, vararg marks: Mark) {
        TODO("Not yet implemented")
    }

    override var playerlist: PlayerList
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun showBossBar(bossBar: BossBar) {
        TODO("Not yet implemented")
    }

    override fun hideBossBar(bossBar: BossBar) {
        TODO("Not yet implemented")
    }

    override fun sendTitle(title: Title) {
        TODO("Not yet implemented")
    }

    override fun <T : Any> sendTitlePart(titlePart: TitlePartType<T>, value: T) {
        TODO("Not yet implemented")
    }

    override fun clearTitle() {
        TODO("Not yet implemented")
    }

    override fun resetTitle() {
        TODO("Not yet implemented")
    }

    override fun sendActionBar(text: Text) {
        TODO("Not yet implemented")
    }

    override fun playSound(sound: Sound) {
        TODO("Not yet implemented")
    }

    override fun playSound(sound: Sound, location: Location) {
        TODO("Not yet implemented")
    }

    override fun stopSound(soundStop: SoundStop) {
        TODO("Not yet implemented")
    }

    override fun asText(): Text {
        TODO("Not yet implemented")
    }

    override fun getPermission(permission: Permission): PermissionState {
        TODO("Not yet implemented")
    }

    override fun setPermission(permission: Permission, state: Boolean) {
        TODO("Not yet implemented")
    }

    override fun unsetPermission(permission: Permission) {
        TODO("Not yet implemented")
    }

    override var location: Location
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun teleport(location: Location) {
        TODO("Not yet implemented")
    }

    override fun asHoverEvent(): HoverEvent<*> {
        TODO("Not yet implemented")
    }

    override fun damage(amount: Double, damageSource: DamageSource): ModifiedDamage {
        TODO("Not yet implemented")
    }

    override var health: Double
        get() = TODO("Not yet implemented")
        set(value) {}
    override var absorptionAmount: Double
        get() = TODO("Not yet implemented")
        set(value) {}
    override var maxHealth: Double
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun resetMaxHealth() {
        TODO("Not yet implemented")
    }

    override val openedInventory: InventoryView?
        get() = TODO("Not yet implemented")

    override fun openInventory(inventory: Inventory): InventoryView {
        TODO("Not yet implemented")
    }

    override fun closeInventory() {
        TODO("Not yet implemented")
    }

    override val isOnline: Boolean
        get() = TODO("Not yet implemented")
    override val firstPlayed: Long
        get() = TODO("Not yet implemented")
    override val lastPlayed: Long
        get() = TODO("Not yet implemented")

    override fun hasPlayedBefore(): Boolean {
        TODO("Not yet implemented")
    }

    override fun sendParticle(particle: Particle<*>) {
        TODO("Not yet implemented")
    }

    override fun sendParticle(particleGroup: ParticleGroup) {
        TODO("Not yet implemented")
    }

    override fun sendParticle(particleAnimation: ParticleAnimation): ParticleAnimationTask {
        TODO("Not yet implemented")
    }

    override fun sendParticle(particleAnimation: ParticleAnimation, speed: Int): AcceleratedParticleAnimationTask {
        TODO("Not yet implemented")
    }

    override fun sendPluginMessage(channel: String, message: OutputMessage) {
        TODO("Not yet implemented")
    }

    override val listeningPluginChannels: Set<String>
        get() = TODO("Not yet implemented")
    override val remoteAddress: InetSocketAddress
        get() = TODO("Not yet implemented")
    override val virtualHost: InetSocketAddress?
        get() = TODO("Not yet implemented")
    override val isActive: Boolean
        get() = TODO("Not yet implemented")
    override val protocolVersion: ProtocolVersion
        get() = TODO("Not yet implemented")

    override fun toVelocityCommandSender(): _VelocityCommandSender {
        TODO("Not yet implemented")
    }
}