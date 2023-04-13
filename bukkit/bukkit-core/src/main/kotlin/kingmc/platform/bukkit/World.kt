package kingmc.platform.bukkit

import kingmc.common.application.Application
import kingmc.platform.World
import kingmc.platform.bukkit.impl.BukkitWorldImpl
import org.bukkit.WorldBorder

typealias _BukkitWorld = org.bukkit.World
typealias _BukkitWorldBorder = WorldBorder

/**
 * Get the bukkit type of current world
 *
 * @since 0.0.3
 * @author kingsthere
 * @see _BukkitLocation
 * @see World
 */
fun World.asBukkit(): _BukkitWorld = (this as BukkitWorld).toBukkitWorld()

/**
 * Turn an original bukkit world to [World]
 *
 * @since 0.0.3
 * @author kingsthere
 */
// @WithApplication use parameter [application] instead
fun _BukkitWorld.asKingMC(application: Application): World = BukkitWorldImpl(application, this)
//
///**
// * [World] implement in bukkit
// *
// * @since 0.0.3
// */
//@BukkitImplementation
//class BukkitWorld(
//    private val _BukkitWorld: _BukkitWorld,
//) : World {
//    private val _all = BukkitWorldAudiences(this)
//    private val _players BukkitWorldPlayers(this)
//
//    private val blockCaches: Cache<Location3D, Block> = Caffeine.newBuilder()
//        .build()
//    private val chunkCaches: Cache<Pair<Int, Int>, Chunk> = Caffeine.newBuilder()
//        .build()
//
//    /**
//     * Gets an audience for all audiences in this factory
//     *
//     * @since 0.0.3
//     * @see Player
//     * @see Set
//     */
//    override fun all(): Audience =
//        _all
//
//    /**
//     * Gets an audience for filtered audiences in this factory
//     *
//     * @since 0.0.3
//     * @see Predicate
//     * @see Set
//     * @see Player
//     */
//    override fun all(predicate: Predicate<Audience>): Audience =
//        FilteredBukkitWorldAudiences(this, predicate)
//
//    /**
//     * Convert this object into a [Text]
//     */
//    override fun asText(): Text =
//        displayName
//
//    /**
//     * Get an audience from this factory by using the audience
//     * identifier
//     *
//     * @since 0.0.3
//     */
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : Audience> audience(identifier: AudienceIdentifier<T>): T? {
//        when (identifier) {
//            is BukkitPlayerIdentifier -> {
//                if (identifier.name != null) {
//                    return try {
//                        AdventureAudienceFactory.player(identifier.name!!) as? T
//                    } catch (exception: IllegalArgumentException) {
//                        null
//                    }
//                } else if (identifier.uuid != null) {
//                    return try {
//                        AdventureAudienceFactory.player(identifier.uuid!!) as? T
//                    } catch (exception: IllegalArgumentException) {
//                        null
//                    }
//                } else if (identifier.player != null) {
//                    return try {
//                        (AdventureAudienceFactory as BukkitAudienceFactory).player(identifier.player) as? T
//                    } catch (exception: IllegalArgumentException) {
//                        null
//                    }
//                } else {
//                    throw IllegalArgumentException("Illegal audience identifier, an audience identifier must specify a uuid or a name")
//                }
//            }
//            is PlayerIdentifier -> {
//                return if (identifier.name != null) {
//                    try {
//                        AdventureAudienceFactory.player(identifier.name!!) as? T
//                    } catch (exception: IllegalArgumentException) {
//                        null
//                    }
//                } else if (identifier.uuid != null) {
//                    try {
//                        AdventureAudienceFactory.player(identifier.uuid!!) as? T
//                    } catch (exception: IllegalArgumentException) {
//                        null
//                    }
//                } else {
//                    throw IllegalArgumentException("Illegal audience identifier, an audience identifier must specify a uuid or a name")
//                }
//            }
//        }
//        return null
//    }
//
//    /**
//     * Gets the audiences to forward to
//     */
//    override fun audiences(): Iterable<Audience> =
//        _BukkitWorld.players.map { (AdventureAudienceFactory as BukkitAudienceFactory).player(it) } + console()
//
//    /**
//     * Gets a block command sender
//     *
//     * @since 0.0.5
//     */
//    override fun block(block: Block): Audience {
//        TODO("Not yet implemented")
//    }
//
//    /**
//     * Get the current [Console] audience
//     *
//     * @since 0.0.3
//     */
//    override fun console(): Console =
//        AdventureAudienceFactory.console()
//
//    /**
//     * Gets the [Block] at the given [Location]
//     *
//     * @param location Location of the block
//     * @return Block at the given location
//     */
//    override fun getBlockAt(location: Location3D): Block {
//        return blockCaches.get(location) { BukkitBlock(_BukkitWorld.getBlockAt(location.blockX, location.blockY, location.blockZ)) }!!
//    }
//
//    /**
//     * Gets the [Chunk] at the given [x], [z]
//     *
//     * @param x X-coordinate of the chunk
//     * @param z Z-coordinate of the chunk
//     * @return Chunk at the given location
//     */
//    override fun getChunkAt(x: Int, z: Int): Chunk {
//        return chunkCaches.get(x to z) { BukkitChunk(_BukkitWorld.getChunkAt(x, z), this) }!!
//    }
//
//    /**
//     * Gets the [Block] at the given coordinates
//     *
//     * @param x X-coordinate of the block
//     * @param y Y-coordinate of the block
//     * @param z Z-coordinate of the block
//     * @return Block at the given coordinates
//     */
//    override fun getBlockAt(x: Int, y: Int, z: Int): Block {
//        val location = bukkitPlatform.locations.createLocation(x.toDouble(), y.toDouble(), z.toDouble())
//        return blockCaches.get(location) { BukkitBlock(_BukkitWorld.getBlockAt(location.blockX, location.blockY, location.blockZ)) }!!
//    }
//
//    /**
//     * Gets the [Chunk] at the given [Location]
//     *
//     * @param location Location of the chunk
//     * @return Chunk at the given location
//     */
//    override fun getChunkAt(location: Location3D): Chunk {
//        val chunkX = location.blockX shr 4
//        val chunkZ = location.blockZ shr 4
//        return chunkCaches.get(chunkX to chunkZ) { BukkitChunk(_BukkitWorld.getChunkAt(chunkX, chunkZ), this) }!!
//    }
//
//    /**
//     * Gets all audience in this audience factory
//     *
//     * @since 0.0.3
//     */
//    override fun iterator(): Iterator<Audience> =
//        audiences().iterator()
//
//    /**
//     * Gets the maximum height of this world.
//     *
//     *
//     * If the max height is 100, there are only blocks from y=0 to y=99.
//     *
//     * @return Maximum height of the world
//     */
//    override val maxHeight: Int
//        get() = _BukkitWorld.maxHeight
//
//    /**
//     * Gets the minimum height of this world.
//     *
//     *
//     * If the min height is 0, there are only blocks from y=0.
//     *
//     * @return Minimum height of the world
//     */
//    override val minHeight: Int
//        get() = _BukkitWorld.minHeight
//
//    /**
//     * The name of this world, name
//     * is the only identifier to identity
//     * worlds
//     *
//     * @since 0.0.1
//     */
//    override val name: String
//        get() = _BukkitWorld.name
//
//    /**
//     * Gets the platform of this
//     */
//    override val platform: Platform
//        get() = bukkitPlatform
//
//    /**
//     * The uuid of this world
//     */
//    override val uuid: UUID
//        get() = _BukkitWorld.uid
//
//    /**
//     * Send a particle to this world
//     *
//     * @param particle the particle to send
//     * @since 0.0.3
//     * @see Particle
//     */
//    override fun sendParticle(particle: Particle<*>) {
//        this._players.sendParticle(particle)
//    }
//
//    /**
//     * Send a particle group to this world
//     *
//     * @param particleGroup the particle group to send
//     * @since 0.0.3
//     * @see ParticleGroup
//     */
//    override fun sendParticle(particleGroup: ParticleGroup) {
//        this.players().sendParticle(particleGroup)
//    }
//
//    /**
//     * Send a particle animation to this particle recipient
//     *
//     * @param particleAnimation the particle group to send
//     * @since 0.0.3
//     * @see ParticleAnimation
//     */
//    override fun sendParticle(particleAnimation: ParticleAnimation): ParticleAnimationTask {
//        return BukkitWorldParticleAnimationTask(particleAnimation, this)
//    }
//
//    /**
//     * Send a particle animation to this particle recipient
//     *
//     * @param particleAnimation the particle group to send
//     * @since 0.0.3
//     * @see ParticleAnimation
//     * @param speed the speed of this particle to show
//     */
//    override fun sendParticle(particleAnimation: ParticleAnimation, speed: Int): AcceleratedParticleAnimationTask {
//        return BukkitWorldParticleAnimationTask(particleAnimation, this, speed)
//    }
//
//    override fun playSound(sound: Sound) {
//        TODO("Not yet implemented")
//    }
//
//    override fun playSound(sound: Sound, location: Location) {
//        TODO("Not yet implemented")
//    }
//
//    override fun stopSound(soundStop: SoundStop) {
//        TODO("Not yet implemented")
//    }
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as BukkitWorld
//
//        if (uuid != other.uuid) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        return uuid.hashCode()
//    }
//
//    override fun toString(): String {
//        return "BukkitWorld(name='$name', uuid=$uuid)"
//    }
//}