package kingmc.platform.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import kingmc.common.OpenAPI;
import kingmc.platform.velocity.driver.VelocityPlatformDriver;
import kingmc.platform.velocity.impl.driver.VelocityPlatformSelector;
import kingmc.platform.velocity.impl.driver.VelocityPlatformSelectorImpl;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Path;

/**
 * The main class for boot up kingmc in velocity server, main-class in Plugin.yml pointed here
 *
 * @since 0.0.9
 * @author kingsthere
 */
@Plugin(
        id = "kingmc",
        name = "KingMC",
        version = "0.0.93",
        authors = {"Kingsthere"},
        description = "A plugin framework for minecraft"
)
public class VelocityJavaPlugin {
    /**
     * The proxy server instance of this plugin
     */
    public final ProxyServer server;

    /**
     * The logger instance of this plugin
     */
    public final Logger logger;

    /**
     * Plugin file
     */
    public final File file = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile());

    /**
     * Plugin data folder - {@code server_root/plugins/KingMC/}
     */
    public File dataFolder;

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
     * The class loader that loads this class
     */
    public final ClassLoader classLoader = this.getClass().getClassLoader();

    /**
     * Platform selector to adapt current velocity server environment
     */
    public VelocityPlatformSelector platform;

    /**
     * Platform driver to launch kingmc framework on current velocity server
     */
    public VelocityPlatformDriver driver;

    /**
     *Instantiate this VelocityJavaPlugin, called internally by velocity
     *
     * @param server ProxyServer
     * @param logger Logger
     * @param dataDirectory Path
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Inject
    public VelocityJavaPlugin(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataFolder = dataDirectory.toFile();
        long stopwatch = System.currentTimeMillis();

        OpenAPI.supportClassLoader(this.getClass().getClassLoader());

        if (!kingmcRoot.exists()) {
            kingmcRoot.mkdir();
        }
        if (!tempFolder.exists()) {
            tempFolder.mkdir();
        }

        // Load dependencies
        platform = new VelocityPlatformSelectorImpl(this);
        driver = platform.select();
        driver.load();
    }

    /**
     * @param event ProxyInitializeEvent
     */
    @Subscribe
    public void onInitialize(ProxyInitializeEvent event) {
        driver.enable();
    }

    /**
     * @param event ProxyShutdownEvent
     */
    @Subscribe
    public void onShutdown(ProxyShutdownEvent event) {
        driver.disable();
    }
}
