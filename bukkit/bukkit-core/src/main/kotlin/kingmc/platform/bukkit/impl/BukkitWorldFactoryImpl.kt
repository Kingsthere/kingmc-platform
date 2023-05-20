package kingmc.platform.bukkit.impl

import kingmc.common.application.application
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.platform.World
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.BukkitWorldFactory
import kingmc.platform.bukkit._BukkitWorld
import kingmc.platform.bukkit.util.asKingMC
import kingmc.util.key.Key
import java.util.*

/**
 * Official implementation for [BukkitWorldImpl]
 *
 * @since 0.0.7
 * @author kingsthere
 */
@BukkitImplementation
@Component
@Scope(BeanScope.SINGLETON)
open class BukkitWorldFactoryImpl : BukkitWorldFactory {
    val _worlds: MutableMap<Key, World> = initWorlds()

    /**
     * Gets a world from a [org.bukkit.World]
     */
    override fun getWorldForBukkit(bukkitWorld: _BukkitWorld): World {
        val key = getKeyForBukkitWorld(bukkitWorld)
        return _worlds.computeIfAbsent(key) { convertBukkitWorld(bukkitWorld) }
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
        return _worlds.values.find { it.name == name }
    }

    /**
     * Gets a world by the key of the world
     */
    override fun getWorld(key: Key): World? {
        return _worlds[key]
    }

    /**
     * Gets a world by the uuid of the world
     */
    override fun getWorld(uuid: UUID): World? {
        return _worlds.values.find { it.uuid == uuid }
    }

    /**
     * Gets all worlds
     */
    override fun getWorlds(): List<World> {
        return _worlds.values.toList()
    }

    protected open fun initWorlds(): MutableMap<Key, World> {
        val mutableMap = mutableMapOf<Key, World>()
        Bukkit.getWorlds().forEach {
            mutableMap.put(getKeyForBukkitWorld(it), convertBukkitWorld(it))
        }
        return mutableMap
    }

    protected open fun convertBukkitWorld(bukkitWorld: _BukkitWorld) =
        BukkitWorldImpl(application, bukkitWorld)

    private fun getKeyForBukkitWorld(world: _BukkitWorld) = world.key.asKingMC()
}