package kingmc.platform.bukkit.impl

import kingmc.common.application.Application
import kingmc.common.coroutine.ApplicationCoroutineScope
import kingmc.common.coroutine.asyncMinecraftCoroutineDispatcher
import kingmc.platform.*
import kingmc.platform.audience.Audience
import kingmc.platform.audience.particle.Particle
import kingmc.platform.block.Block
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.BukkitServer
import kingmc.platform.bukkit.BukkitWorld
import kingmc.platform.bukkit._BukkitWorld
import kingmc.platform.bukkit.entity.asKingMC
import kingmc.platform.bukkit.impl.block.BukkitBlockImpl
import kingmc.platform.bukkit.util.asKingMC
import kingmc.platform.entity.Entity
import kingmc.platform.entity.EntityType
import kingmc.platform.entity.player.Player
import kingmc.util.key.Key
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import java.util.*

/**
 * Official implementation of [BukkitWorld]
 *
 * @author kingsthere
 * @since 0.0.7
 */
@BukkitImplementation
class BukkitWorldImpl(override val application: Application, private val _bukkitWorld: _BukkitWorld) : BukkitWorld {
     private val _chunks: MutableMap<Pair<Int, Int>, Chunk> = WeakHashMap()

    private val _worldBorder: WorldBorder = BukkitWorldBorderImpl(_bukkitWorld.worldBorder)

     private val _players: List<Player>
         get() = buildList {
             _bukkitWorld.players.forEach {
                 add((this@BukkitWorldImpl.application.server as BukkitServer).getPlayerForBukkit(it))
             }
         }

    override fun toBukkitWorld(): _BukkitWorld = _bukkitWorld

    override val uuid: UUID
        get() = _bukkitWorld.uid
    override val name: String
        get() = _bukkitWorld.name
    override val key: Key
        get() = _bukkitWorld.key.asKingMC()

    override fun getChunkAt(x: Int, z: Int): Chunk {
        return _chunks.computeIfAbsent(x to z) { _ ->
            BukkitChunkImpl(_bukkitWorld.getChunkAt(x, z), this, application)
        }
    }

    override fun getChunkAt(location: Location3D): Chunk = getChunkAt(location.blockX shr 4, location.blockY shr 4)

    override fun getChunkAtAsync(x: Int, z: Int): Deferred<Chunk> = ApplicationCoroutineScope(asyncMinecraftCoroutineDispatcher).async {
        // Unsupported
        getChunkAt(x, z)
    }

    override fun getChunkAtAsync(location: Location3D): Deferred<Chunk> {
        return getChunkAtAsync(location.blockX shr 4, location.blockY shr 4)
    }

    override fun getBlockAt(x: Int, y: Int, z: Int): Block {
        return BukkitBlockImpl(_bukkitWorld.getBlockAt(x, y, z), application)
    }

    override fun getBlockAt(location: Location3D): Block = getBlockAt(location.blockX, location.blockY, location.blockZ)

    override val minHeight: Int
        get() = _bukkitWorld.minHeight
    override val maxHeight: Int
        get() = _bukkitWorld.maxHeight
    override var time: Long
        get() = _bukkitWorld.time
        set(value) {
            _bukkitWorld.time = value
        }
    override var fullTime: Long
        get() = _bukkitWorld.fullTime
        set(value) {
            _bukkitWorld.fullTime = value
        }
    override val worldBorder: WorldBorder
        get() = _worldBorder

    /**
     * Gets all entities from this provider
     *
     * @return The entities
     */
    override fun getEntities(): List<Entity> {
        return _bukkitWorld.entities.map {
            it.asKingMC(application)
        }
    }

    /**
     * Get multiple entity by its type
     *
     * @param entityType the type of entity
     * @return Each entity is an entity of [entityType]
     */
    override fun getEntities(entityType: EntityType): List<Entity> {
        return getEntities().filter { it.type == entityType }
    }

    /**
     * Gets a single entity by its [entity id][Entity.entityId]
     *
     * @param entityId the entityId of the entity
     * @return Entity with id [entityId], or `null` if entity with [entityId] is not exists
     */
    override fun getEntity(entityId: Int): Entity? {
        return _bukkitWorld.entities.find { it.entityId == entityId }?.asKingMC(application)
    }

    /**
     * Gets a single entity by its type
     *
     * @param entityType the type of entity
     * @return Entity is an entity of [entityType], or `null` if
     *         entity that is an entity of [entityType] not exists
     */
    override fun getEntity(entityType: EntityType): Entity? {
        return getEntities().find { it.type == entityType }
    }

    override fun audiences(): Iterable<Audience> = _players

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
        _players.forEach {
            it.sendParticle(particle, x, y, z, longDistance, offsetX, offsetY, offsetZ, maxSpeed, count)
        }
    }
}