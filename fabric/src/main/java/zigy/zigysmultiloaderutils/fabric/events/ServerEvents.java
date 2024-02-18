package zigy.zigysmultiloaderutils.fabric.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import zigy.zigysmultiloaderutils.utils.fabric.PlatformImpl;

public class ServerEvents implements ServerLifecycleEvents.ServerStarting, ServerLifecycleEvents.ServerStopped {
    @Override
    public void onServerStarting(MinecraftServer server) {
        PlatformImpl.server = server;
    }

    @Override
    public void onServerStopped(MinecraftServer server) {
        PlatformImpl.server = null;
    }
}
