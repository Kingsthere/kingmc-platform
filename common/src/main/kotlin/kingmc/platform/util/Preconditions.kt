@file:Utility

package kingmc.platform.util

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.platform
import kingmc.util.Utility
import kingmc.util.Version
import kingmc.util.VersionRange

/**
 * Throws an [UnsupportedOperationException] if current server's version is
 * not compatible in specified [versions]
 *
 * @param versions the range of version to check
 * @since 0.0.8
 * @author kingsthere
 */
@WithApplication
fun checkMinecraftVersion(versions: VersionRange) {
    val currentVersion = currentApplication().platform.minecraftVersion
    if (currentVersion !in versions) {
        throw UnsupportedOperationException("Incompatible version, require: $versions current: $currentVersion")
    }
}

/**
 * Throws an [UnsupportedOperationException] if current server's version is
 * lower than specified [version]
 *
 * @param version the range of version to check
 * @since 0.0.8
 * @author kingsthere
 */
@WithApplication
fun checkMinecraftVersion(version: Version) {
    val currentVersion = currentApplication().platform.minecraftVersion
    if (version > currentVersion) {
        throw UnsupportedOperationException("Incompatible version, require: $version current: $currentVersion")
    }
}