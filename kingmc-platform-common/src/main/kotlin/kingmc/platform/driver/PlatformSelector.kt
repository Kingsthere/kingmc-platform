package kingmc.platform.driver

/**
 * A superinterface responsible to select the platform based
 * on current environment and the best [PlatformDriver] to boot up kingmc
 * framework on this platform
 *
 * @author kingsthere
 * @since 0.0.8
 */
interface PlatformSelector {
    /**
     * Select the driver based on current platform
     */
    fun select(): PlatformDriver
}