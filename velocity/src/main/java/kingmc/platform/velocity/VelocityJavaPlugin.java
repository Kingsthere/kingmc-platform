package kingmc.platform.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import kingmc.common.OpenAPI;
import kingmc.common.environment.KotlinCoroutineEnv;
import kingmc.common.environment.KotlinEnv;
import kingmc.common.environment.RuntimeEnv;
import org.slf4j.Logger;

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
    public static final RuntimeEnv env = RuntimeEnv.ENV;

    @Inject
    public VelocityJavaPlugin(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
        OpenAPI.supportClassLoader(this.getClass().getClassLoader());

        // Load dependencies
        env.loadDependency(KotlinEnv.class, true);
        env.loadDependency(KotlinCoroutineEnv.class, true);
    }

    @Subscribe
    public void onInitialize(ProxyInitializeEvent event) {

    }
}
