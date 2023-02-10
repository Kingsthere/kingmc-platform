package kingmc.platform;

import kingmc.common.OpenAPI;
import kingmc.common.environment.KotlinCoroutineEnv;
import kingmc.common.environment.KotlinEnv;
import kingmc.common.environment.RuntimeEnv;
import kingmc.platform.bukkit.BukkitPlugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * The main class for boot up kingmc in bukkit server
 * independent of the kotlin environment
 *
 * @since 0.0.3
 * @author kingsthere
 */
public class BukkitJavaPlugin extends JavaPlugin {
    public final File file = getFile();
    public final ClassLoader classLoader = getClassLoader();
    public static final RuntimeEnv env = RuntimeEnv.ENV;
    public BukkitPlugin plugin;

    @Override
    public void onLoad() {
        long stopwatch = System.currentTimeMillis();

        OpenAPI.supportClassLoader(this.getClass().getClassLoader());

        // Load dependencies
        env.loadDependency(KotlinEnv.class, true);
        env.loadDependency(KotlinCoroutineEnv.class, true);

        plugin = new BukkitPlugin(this, stopwatch);
        plugin.onLoad();
    }

    @Override
    public void onEnable() {
        plugin.onEnable();
    }

    @Override
    public void onDisable() {
        plugin.onDisable();
    }
}
