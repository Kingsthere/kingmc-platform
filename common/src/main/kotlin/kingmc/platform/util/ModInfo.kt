package kingmc.platform.util

import java.util.*

/**
 * A data class describe information to mods of a server ping response
 */
data class ModInfo(val type: String,
                   val mods: List<Mod>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val modInfo = other as ModInfo
        return type == modInfo.type && mods == modInfo.mods
    }

    override fun hashCode(): Int {
        return Objects.hash(type, mods)
    }

    class Mod(val id: String,
              val version: String) {

        override fun toString(): String {
            return ("Mod{"
                    + "id='" + id + '\''
                    + ", version='" + version + '\''
                    + '}')
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }
            if (other == null || javaClass != other.javaClass) {
                return false
            }
            val mod = other as Mod
            return id == mod.id && version == mod.version
        }

        override fun hashCode(): Int {
            return Objects.hash(id, version)
        }
    }

    companion object {
        val DEFAULT = ModInfo("FML", listOf())
    }
}