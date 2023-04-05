package kingmc.platform.bukkit.audience.particle

import kingmc.platform.audience.particle.Particle

val Particle<*>.bukkit: _BukkitParticle
    get() = _BukkitParticle.valueOf(bukkitName)

val Particle<*>.bukkitName: String
    get() = this.type.key.value().uppercase()