package kingmc.platform.bukkit.version.wrapper

import kingmc.util.key.Key
import kingmc.util.key.Keyed

/**
 * Warp all versioned things so it can support
 * many version of bukkit, kingmc split the wrappers
 * by their type, for example:
 *  + Audience
 *  + Block
 *  + Item
 *  + Map
 *  + ...
 *
 * @author kingsthere
 * @since 0.0.3
 */
interface VersionWrapper : Keyed {
     /**
     * The key of this wrapper to identify
     * the version wrappers
     *
     * @since 0.0.3
     */
    override val key: Key
}