package kingmc.platform.velocity.impl.entity.player

import kingmc.common.application.Application
import kingmc.common.text.Text
import kingmc.common.text.serializer.deserializeFromLegacyToText
import kingmc.platform.Location
import kingmc.platform.Vector
import kingmc.platform.audience.Audience
import kingmc.platform.audience.particle.Particle
import kingmc.platform.entity.Entity
import kingmc.platform.entity.EntityType
import kingmc.platform.entity.damage.DamageSource
import kingmc.platform.entity.damage.ModifiedDamage
import kingmc.platform.impl.adventure.AdventureAudience
import kingmc.platform.inventory.Inventory
import kingmc.platform.inventory.InventoryView
import kingmc.platform.inventory.MainHand
import kingmc.platform.inventory.PlayerInventory
import kingmc.platform.messaging.OutputMessage
import kingmc.platform.nbt.MutableNBTCompound
import kingmc.platform.permission.Permission
import kingmc.platform.permission.PermissionState
import kingmc.platform.proxy.ConnectionRequest
import kingmc.platform.proxy.ProxiedServer
import kingmc.platform.util.ProtocolVersion
import kingmc.platform.velocity.VelocityProxyServer
import kingmc.platform.velocity._VelocityPlayer
import kingmc.platform.velocity.asVelocity
import kingmc.platform.velocity.command._VelocityCommandSender
import kingmc.platform.velocity.entity.player.VelocityPlayer
import kingmc.platform.velocity.impl.VelocityConnectionRequestImpl
import net.kyori.adventure.text.event.HoverEvent
import java.net.InetSocketAddress
import java.util.*
import kotlin.jvm.optionals.getOrNull

/**
 * A simple `VelocityPlayer` implementation
 *
 * @author kingsthere
 * @since 0.0.9
 */
class VelocityPlayerImpl(
    private val _velocityProxyServer: VelocityProxyServer,
    private val _velocityPlayer: _VelocityPlayer,
    override val application: Application,
) :
    VelocityPlayer,
    Audience by AdventureAudience(_velocityPlayer) {
    override fun toVelocityPlayer(): _VelocityPlayer = _velocityPlayer
    override val currentServer: ProxiedServer
        get() = _velocityProxyServer.getProxiedServerForVelocity(_velocityPlayer.currentServer.get().server)

    override fun tryConnect(target: ProxiedServer): ConnectionRequest =
        kingmc.platform.velocity.impl.VelocityConnectionRequestImpl(
            _velocityConnectionRequest = _velocityPlayer.createConnectionRequest(target.asVelocity()),
            _velocityProxyServer = _velocityProxyServer,
            invoker = this
        )

    @Deprecated("This value is controlled only by the client and is therefore unreliable\n                            and vulnerable to spoofing and/or desync depending on the context/time \n                            which it is accessed")
    override val isOnGround: Boolean
        get() = TODO("Not yet implemented")
    var _displayName = _velocityPlayer.username.deserializeFromLegacyToText()
    override var displayName: Text
        get() = _displayName
        set(value) {
            _displayName = value
        }
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
        _velocityPlayer.disconnect(reason)
    }

    override val name: String
        get() = _velocityPlayer.username
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
        get() = _velocityPlayer.uniqueId
    override var velocity: Vector
        get() = TODO("Not yet implemented")
        set(value) {}
    override val nbt: MutableNBTCompound
        get() = TODO("Not yet implemented")
    override val isSpawned: Boolean
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

    @Deprecated("entities may have multiple passengers", replaceWith = ReplaceWith("addPassenger"))
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
        _velocityPlayer.spoofChatInput(message)
    }

    override fun command(command: String) {
        _velocityPlayer.spoofChatInput("/$command")
    }

    override fun asText(): Text = displayName

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

    @Deprecated("Use `Attribute.GENERIC_MAX_HEALTH` instead")
    override var maxHealth: Double
        get() = TODO("Not yet implemented")
        set(value) {}

    @Deprecated("Use `Attribute.GENERIC_MAX_HEALTH` instead")
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

    override fun sendParticle(
        particle: Particle<*>,
        x: Double,
        y: Double,
        z: Double,
        longDistance: Boolean,
        offsetX: Float,
        offsetY: Float,
        offsetZ: Float,
        maxSpeed: Float,
        count: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun sendPluginMessage(channel: String, message: OutputMessage) {
        TODO("Not yet implemented")
    }

    override val listeningPluginChannels: Set<String>
        get() = TODO("Not yet implemented")
    override val remoteAddress: InetSocketAddress
        get() = _velocityPlayer.remoteAddress
    override val virtualHost: InetSocketAddress?
        get() = _velocityPlayer.virtualHost.getOrNull()
    override val isActive: Boolean
        get() = TODO("Not yet implemented")
    override val protocolVersion: ProtocolVersion
        get() = ProtocolVersion.getProtocolVersion(_velocityPlayer.protocolVersion.protocol)

    override fun toVelocityCommandSender(): _VelocityCommandSender = _velocityPlayer
}