package kingmc.platform.bukkit.version

import kingmc.common.context.annotation.Component

/**
 * Utility version util to the server
 *
 * @author kingsthere
 * @since 0.0.3
 */
@Component
object ServerVersion {

    /**
     * A map cache all wrappers
     *
     * @since 0.0.3
     */
    // TODO Wrapping minecraft version support
    // @Bean
    // fun version(): VersionWrapper {
    //     val version =
    //         serverClass.getPackage().name.replace(".", ",").split(",".toRegex()).dropLastWhile { it.isEmpty() }
    //             .toTypedArray()[3]
    //     val split = version.substring(1).split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    //     @Suppress("UNCHECKED_CAST")
    //     return InstanceMap[classOf(buildWrapperName(version.substring(1))) as KClass<VersionWrapper>]
    // }

    private fun buildWrapperName(version: String) : String {
        return "kingmc.platform.bukkit.version.wrapper.VersionWrapper_$version"
    }
}