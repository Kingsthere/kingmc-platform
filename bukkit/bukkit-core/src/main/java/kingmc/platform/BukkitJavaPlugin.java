package kingmc.platform;

import kingmc.common.OpenAPI;
import kingmc.platform.bukkit.driver.BukkitPlatformDriver;
import kingmc.platform.bukkit.impl.driver.BukkitPlatformSelector;
import kingmc.platform.bukkit.impl.driver.BukkitPlatformSelectorImpl;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * The main class for boot up kingmc in bukkit server, main-class in Plugin.yml pointed here
 *
 * @since 0.0.3
 * @author kingsthere
 */
public class BukkitJavaPlugin extends JavaPlugin {
    /**
     * Plugin file
     */
    public final File file = getFile();

    /**
     * Plugin class loader (org.bukkit.plugin.PluginClassLoader)
     */
    public final ClassLoader classLoader = getClassLoader();

    /**
     * Plugin data folder - {@code server_root/plugins/KingMC/}
     */
    public final File dataFolder = getDataFolder();

    /**
     * Server root - {@code server_root/}
     */
    public final File serverRoot = dataFolder.getParentFile().getParentFile();

    /**
     * KingMC root - server_root/kingmc/
     */
    public final File kingmcRoot = new File(serverRoot, "kingmc");

    /**
     * Temp folder - server_root/kingmc/temp
     */
    public final File tempFolder = new File(kingmcRoot, "temp");

    /**
     * Platform selector to adapt current bukkit server environment
     */
    public BukkitPlatformSelector platform;

    /**
     * Platform driver to launch kingmc framework on current bukkit server
     */
    public BukkitPlatformDriver driver;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void onLoad() {
        OpenAPI.supportClassLoader(this.getClass().getClassLoader());

        if (!kingmcRoot.exists()) {
            kingmcRoot.mkdir();
        }
        if (!tempFolder.exists()) {
            tempFolder.mkdir();
        }

        // Load dependencies
        platform = new BukkitPlatformSelectorImpl(this);
        driver = platform.select();
        driver.load();

    }

    @Override
    public void onEnable() {
        driver.enable();
    }

    @Override
    public void onDisable() {
        driver.disable();
    }
}
