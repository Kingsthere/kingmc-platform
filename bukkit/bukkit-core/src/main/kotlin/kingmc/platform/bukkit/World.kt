package kingmc.platform.bukkit

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import kingmc.platform.*
import kingmc.platform.audience.*
import kingmc.platform.audience.particle.*
import kingmc.platform.audience.sound.Sound
import kingmc.platform.audience.sound.SoundStop
import kingmc.platform.audience.text.Text
import kingmc.platform.block.Block
import kingmc.platform.bukkit.audience.*
import kingmc.platform.bukkit.audience.adventure.AdventureBukkitAudienceFactory
import kingmc.platform.bukkit.block.BukkitBlock
import org.bukkit.WorldBorder
import java.util.*
import java.util.function.Predicate

typealias OriginalBukkitWorld = org.bukkit.World
typealias OriginalBukkitWorldBorder = WorldBorder

/**
 * Get the bukkit type of current world
 *
 * @since 0.0.3
 * @author kingsthere
 * @see OriginalBukkitLocation
 * @see World
 */
fun World.toBukkit(): OriginalBukkitWorld = (this as BukkitWorld).toBukkit()

/**
 * Turn an original bukkit world to [World]
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun OriginalBukkitWorld.fromBukkit(): World = BukkitWorld(this)

/**
 * [World] implement in bukkit
 *
 * @since 0.0.3
 */
@PlatformImplementation
class BukkitWorld(
    private val originalBukkitWorld: OriginalBukkitWorld,
) : World {
    private val all = BukkitWorldAudiences(this)
    private val players = BukkitWorldPlayers(this)

    private val blockCaches: Cache<Location, Block> = Caffeine.newBuilder()
        .build()
    private val chunkCaches: Cache<Pair<Int, Int>, Chunk> = Caffeine.newBuilder()
        .build()

    /**
     * Gets an audience for all audiences in this factory
     *
     * @since 0.0.3
     * @see Player
     * @see Set
     */
    override fun all(): Audience =
        all

    /**
     * Gets an audience for filtered audiences in this factory
     *
     * @since 0.0.3
     * @see Predicate
     * @see Set
     * @see Player
     */
    override fun all(predicate: Predicate<Audience>): Audience =
        FilteredBukkitWorldAudiences(this, predicate)

    /**
     * Convert this object into a [Text]
     */
    override fun asText(): Text =
        displayName

    /**
     * Get an audience from this factory by using the audience
     * identifier
     *
     * @since 0.0.3
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : Audience> audience(identifier: AudienceIdentifier<T>): T? {
        when (identifier) {
            is BukkitPlayerIdentifier -> {
                if (identifier.name != null) {
                    return try {
                        AdventureBukkitAudienceFactory.player(identifier.name!!) as? T
                    } catch (exception: IllegalArgumentException) {
                        null
                    }
                } else if (identifier.uuid != null) {
                    return try {
                        AdventureBukkitAudienceFactory.player(identifier.uuid!!) as? T
                    } catch (exception: IllegalArgumentException) {
                        null
                    }
                } else if (identifier.player != null) {
                    return try {
                        (AdventureBukkitAudienceFactory as BukkitAudienceFactory).player(identifier.player) as? T
                    } catch (exception: IllegalArgumentException) {
                        null
                    }
                } else {
                    throw IllegalArgumentException("Illegal audience identifier, an audience identifier must specify a uuid or a name")
                }
            }
            is PlayerIdentifier -> {
                return if (identifier.name != null) {
                    try {
                        AdventureBukkitAudienceFactory.player(identifier.name!!) as? T
                    } catch (exception: IllegalArgumentException) {
                        null
                    }
                } else if (identifier.uuid != null) {
                    try {
                        AdventureBukkitAudienceFactory.player(identifier.uuid!!) as? T
                    } catch (exception: IllegalArgumentException) {
                        null
                    }
                } else {
                    throw IllegalArgumentException("Illegal audience identifier, an audience identifier must specify a uuid or a name")
                }
            }
        }
        return null
    }

    /**
     * Gets the audiences to forward to
     */
    override fun audiences(): Iterable<Audience> =
        originalBukkitWorld.players.map { (AdventureBukkitAudienceFactory as BukkitAudienceFactory).player(it) } + console()

    /**
     * Gets a block command sender
     *
     * @since 0.0.5
     */
    override fun block(block: Block): Audience {
        TODO("Not yet implemented")
    }

    /**
     * Get the current [Console] audience
     *
     * @since 0.0.3
     */
    override fun console(): Console =
        AdventureBukkitAudienceFactory.console()

    /**
     * Gets the [Block] at the given [Location]
     *
     * @param location Location of the block
     * @return Block at the given location
     */
    override fun getBlockAt(location: Location): Block {
        return blockCaches.get(location) { BukkitBlock(originalBukkitWorld.getBlockAt(location.blockX, location.blockY, location.blockZ)) }!!
    }

    /**
     * Gets the [Chunk] at the given [x], [z]
     *
     * @param x X-coordinate of the chunk
     * @param z Z-coordinate of the chunk
     * @return Chunk at the given location
     */
    override fun getChunkAt(x: Int, z: Int): Chunk {
        return chunkCaches.get(x to z) { BukkitChunk(originalBukkitWorld.getChunkAt(x, z), this) }!!
    }

    /**
     * Gets the [Block] at the given coordinates
     *
     * @param x X-coordinate of the block
     * @param y Y-coordinate of the block
     * @param z Z-coordinate of the block
     * @return Block at the given coordinates
     */
    override fun getBlockAt(x: Int, y: Int, z: Int): Block {
        val location = bukkitPlatform.locations.of(x, y, z)
        return blockCaches.get(location) { BukkitBlock(originalBukkitWorld.getBlockAt(location.blockX, location.blockY, location.blockZ)) }!!
    }

    /**
     * Gets the [Chunk] at the given [Location]
     *
     * @param location Location of the chunk
     * @return Chunk at the given location
     */
    override fun getChunkAt(location: Location): Chunk {
        val chunkX = location.blockX shr 4
        val chunkZ = location.blockZ shr 4
        return chunkCaches.get(chunkX to chunkZ) { BukkitChunk(originalBukkitWorld.getChunkAt(chunkX, chunkZ), this) }!!
    }

    /**
     * Gets all audience in this audience factory
     *
     * @since 0.0.3
     */
    override fun iterator(): Iterator<Audience> =
        audiences().iterator()

    /**
     * Gets the maximum height of this world.
     *
     *
     * If the max height is 100, there are only blocks from y=0 to y=99.
     *
     * @return Maximum height of the world
     */
    override val maxHeight: Int
        get() = originalBukkitWorld.maxHeight

    /**
     * Gets the minimum height of this world.
     *
     *
     * If the min height is 0, there are only blocks from y=0.
     *
     * @return Minimum height of the world
     */
    override val minHeight: Int
        get() = originalBukkitWorld.minHeight

    /**
     * The name of this world, name
     * is the only identifier to identity
     * worlds
     *
     * @since 0.0.1
     */
    override val name: String
        get() = originalBukkitWorld.name

    /**
     * Gets the platform of this
     */
    override val platform: Platform
        get() = bukkitPlatform

    /**
     * The uuid of this world
     */
    override val uuid: UUID
        get() = originalBukkitWorld.uid

    /**
     * Send a particle to this particle recipient
     *
     * @param particle the particle to send
     * @since 0.0.3
     * @see Particle
     */
    override fun particle(particle: Particle) {
        this.players().particle(particle)
    }

    /**
     * Send a particle group to this particle recipient
     *
     * @param particleGroup the particle group to send
     * @since 0.0.3
     * @see ParticleGroup
     */
    override fun particle(particleGroup: ParticleGroup) {
        this.players().particle(particleGroup)
    }

    /**
     * Send a particle animation to this particle recipient
     *
     * @param particleAnimation the particle group to send
     * @since 0.0.3
     * @see ParticleAnimation
     */
    override fun particle(particleAnimation: ParticleAnimation): ParticleAnimationTask {
        return BukkitWorldParticleAnimationTask(particleAnimation, this)
    }

    /**
     * Send a particle animation to this particle recipient
     *
     * @param particleAnimation the particle group to send
     * @since 0.0.3
     * @see ParticleAnimation
     * @param speed the speed of this particle to show
     */
    override fun particle(particleAnimation: ParticleAnimation, speed: Int): AcceleratedParticleAnimationTask {
        return BukkitWorldParticleAnimationTask(particleAnimation, this, speed)
    }

    override fun player(uuid: UUID): Player? =
        AdventureBukkitAudienceFactory.player(uuid)

    override fun player(name: String): Player? =
        AdventureBukkitAudienceFactory.player(name)

    /**
     * Gets an [Player] for all online players
     *
     * @since 0.0.3
     * @return the [Player]
     */
    override fun players(): Players =
        players

    /**
     * Gets an [Player] for filtered online players
     *
     * @since 0.0.3
     * @return the [Player]
     */
    override fun players(predicate: Predicate<Player>): Players =
        FilteredBukkitWorldPlayers(this, predicate)

    override fun sound(sound: Sound) {
        TODO("Not yet implemented")
    }

    override fun sound(sound: Sound, location: Location) {
        TODO("Not yet implemented")
    }

    override fun stopSound(soundStop: SoundStop) {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BukkitWorld

        if (uuid != other.uuid) return false

        return true
    }

    override fun hashCode(): Int {
        return uuid.hashCode()
    }

    override fun toString(): String {
        return "BukkitWorld(name='$name', uuid=$uuid)"
    }
}