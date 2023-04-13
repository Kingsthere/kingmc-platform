package kingmc.platform.bukkit.impl

import com.github.benmanes.caffeine.cache.AsyncCache
import com.github.benmanes.caffeine.cache.Caffeine
import io.papermc.lib.PaperLib
import kingmc.common.application.Application
import kingmc.common.text.Text
import kingmc.platform.Chunk
import kingmc.platform.Location3D
import kingmc.platform.audience.Audience
import kingmc.platform.audience.particle.*
import kingmc.platform.block.Block
import kingmc.platform.bukkit.*
import kingmc.platform.bukkit.util.asKingMC
import kingmc.platform.entity.player.Player
import kingmc.platform.server
import kingmc.util.key.Key
import java.util.*
import java.util.concurrent.CompletableFuture

/**
 * Official implementation of [BukkitWorld]
 *
 * @since 0.0.7
 * @author kingsthere
 */
@BukkitImplementation
class BukkitWorldImpl(override val application: Application, private val _bukkitWorld: _BukkitWorld) : BukkitWorld {
     private val _chunks: AsyncCache<Pair<Int, Int>, Chunk> = Caffeine.newBuilder()
         .buildAsync { key, executor ->
             CompletableFuture.supplyAsync({
                 BukkitChunkImpl(PaperLib.getChunkAtAsync(this._bukkitWorld, key.first, key.second).join(), this, application)
             }, executor)
         }

     val _players: List<Player>
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
        TODO("Not yet implemented")
    }

    override fun getChunkAt(location: Location3D): Chunk {
        TODO("Not yet implemented")
    }

    override fun getBlockAt(x: Int, y: Int, z: Int): Block {
        TODO("Not yet implemented")
    }

    override fun getBlockAt(location: Location3D): Block {
        TODO("Not yet implemented")
    }

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

    override fun audiences(): Iterable<Audience> = _players

    override fun asText(): Text {
        return Text(name)
    }

    override fun sendParticle(particle: Particle<*>) {
        _players.forEach { it.sendParticle(particle) }
    }

    override fun sendParticle(particleGroup: ParticleGroup) {
        _players.forEach { it.sendParticle(particleGroup) }
    }

    override fun sendParticle(particleAnimation: ParticleAnimation): ParticleAnimationTask {
        return sendParticle(particleAnimation, 1)
    }

    override fun sendParticle(particleAnimation: ParticleAnimation, speed: Int): AcceleratedParticleAnimationTask {
        return ParticleAnimationTask.createSupervisorParticleAnimationTask(*_players.map {
            it.sendParticle(particleAnimation)
        }.toTypedArray(), speed = speed)
    }
}