package kingmc.platform.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import kingmc.common.OpenAPI;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Path;

@Plugin(
        id = "kingmc",
        name = "KingMC",
        version = "0.0.5",
        authors = {"Kingsthere"},
        description = "A plugin framework for minecraft"
)
public class VelocityJavaPlugin {
    public final ProxyServer server;
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
        plugin = new BukkitPlugin(this, stopwatch);
        plugin.onLoad();
    }

    @Subscribe
    public void onInitialize(ProxyInitializeEvent event) {

    }
}
