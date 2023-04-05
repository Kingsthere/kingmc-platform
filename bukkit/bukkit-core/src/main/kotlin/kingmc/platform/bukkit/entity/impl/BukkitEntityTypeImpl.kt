package kingmc.platform.bukkit.entity.impl

import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.entity.BukkitEntityType
import kingmc.platform.bukkit.entity._BukkitEntityType
import kingmc.platform.entity.Entity
import kingmc.util.key.Key

/**
 * An official implementation of [BukkitEntityType]
 */
@BukkitImplementation
class BukkitEntityTypeImpl<TEntity : Entity>(val bukkitEntityType: _BukkitEntityType) : BukkitEntityType<TEntity> {
    override val key: Key
        get() = Key(bukkitEntityType.key.namespace, bukkitEntityType.key.key)

    override fun toBukkitEntityType(): _BukkitEntityType {
        return bukkitEntityType
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BukkitEntityTypeImpl<*>) return false

        if (key != other.key) return false

        return true
    }

    override fun hashCode(): Int {
        return key.hashCode()
    }

    override fun toString(): String {
        return "BukkitEntityTypeImpl(key=$key)"
    }
}