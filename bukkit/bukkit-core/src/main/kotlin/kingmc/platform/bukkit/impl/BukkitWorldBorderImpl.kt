package kingmc.platform.bukkit.impl

import kingmc.platform.Location
import kingmc.platform.Location3D
import kingmc.platform.WorldBorder
import kingmc.platform.bukkit._BukkitWorldBorder
import kingmc.platform.bukkit.asBukkit
import kingmc.platform.bukkit.asKingMCLocation3D

/**
 * `WorldBorder` implementation for bukkit
 *
 * @since 0.0.9
 * @author kingsthere
 */
class BukkitWorldBorderImpl(
    val _bukkitWorldBorder: _BukkitWorldBorder
) : WorldBorder {
    override fun reset() {
        _bukkitWorldBorder.reset()
    }

    override var size: Double
        get() = _bukkitWorldBorder.size
        set(value) {
            _bukkitWorldBorder.size = value
        }

    override fun setSize(newSize: Double, timeInSeconds: Long) {
        _bukkitWorldBorder.setSize(newSize, timeInSeconds)
    }

    override var center: Location3D
        get() = _bukkitWorldBorder.center.asKingMCLocation3D()
        set(value) {
            _bukkitWorldBorder.center = value.asBukkit()
        }

    override fun setCenter(x: Double, z: Double) {
        _bukkitWorldBorder.setCenter(x, z)
    }

    override var damageBuffer: Double
        get() = _bukkitWorldBorder.damageBuffer
        set(value) {
            _bukkitWorldBorder.damageBuffer = value
        }
    override var damageAmount: Double
        get() = _bukkitWorldBorder.damageAmount
        set(value) {
            _bukkitWorldBorder.damageAmount = value
        }
    override var warningTime: Int
        get() = _bukkitWorldBorder.warningTime
        set(value) {
            _bukkitWorldBorder.warningTime = value
        }
    override var warningDistance: Int
        get() = _bukkitWorldBorder.warningDistance
        set(value) {
            _bukkitWorldBorder.warningDistance = value
        }

    override fun contains(location: Location): Boolean {
        return _bukkitWorldBorder.isInside(location.asBukkit())
    }

    override val maxSize: Double
        get() = _bukkitWorldBorder.maxSize
    override val maxCenterCoordinate: Double
        get() = _bukkitWorldBorder.maxCenterCoordinate
}