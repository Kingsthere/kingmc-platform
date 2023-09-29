package kingmc.platform.bukkit.impl

import kingmc.common.application.application
import kingmc.common.context.annotation.Component
import kingmc.platform.Active
import kingmc.platform.World
import kingmc.platform.bukkit.*
import kingmc.platform.bukkit.util.asKingMC
import kingmc.util.key.Key
import java.util.*

/**
 * Official implementation for [BukkitWorldImpl]
 *
 * @author kingsthere
 * @since 0.0.7
 */
@BukkitImplementation
@Component
open class BukkitWorldFactoryImpl : BukkitWorldFactory, _BukkitListener {
    private val worlds: MutableMap<Key, World> = HashMap()
    // Cache worlds by its name
    private val worldsByName: MutableMap<String, World> = HashMap()

    private fun refreshWorlds() {
        worlds.clear()
        worldsByName.clear()
        Bukkit.getWorlds().forEach {
            cacheWorld(it, convertBukkitWorld(it))
        }
    }

    @Active fun refreshWorldsWhenActive() {
        refreshWorlds()
    }

    /**
     * Gets a world from a [org.bukkit.World]
     */
    override fun getWorldForBukkit(bukkitWorld: _BukkitWorld): World {
        val key = getKeyForBukkitWorld(bukkitWorld)
        return worlds[key]!!
    }

    /**
     * The main world, which players enter when they first join the server
     */
    override val mainWorld: World
        get() = getWorldForBukkit(Bukkit.getServer().worlds[0])

    /**
     * Gets a world by the name of the world
     */
    override fun getWorld(name: String): World? {
        return worlds.values.find { it.name == name }
    }

    /**
     * Gets a world by the key of the world
     */
    override fun getWorld(key: Key): World? {
        return worlds[key]
    }

    /**
     * Gets a world by the uuid of the world
     */
    override fun getWorld(uuid: UUID): World? {
        return worlds.values.find { it.uuid == uuid }
    }

    /**
     * Gets all worlds
     */
    override fun getWorlds(): List<World> {
        return worlds.values.toList()
    }

    protected fun cacheWorld(bukkitWorld: _BukkitWorld, world: World) {
        worlds[getKeyForBukkitWorld(bukkitWorld)] = world
        worldsByName[bukkitWorld.name] = world
    }

    protected open fun convertBukkitWorld(bukkitWorld: _BukkitWorld) =
        BukkitWorldImpl(application, bukkitWorld)

    private fun getKeyForBukkitWorld(world: _BukkitWorld) = world.key.asKingMC()
}