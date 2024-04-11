package lio.liosmultiloaderutils.fabric.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import lio.liosmultiloaderutils.utils.fabric.ServerInstanceImpl;

public class ServerEvents implements ServerLifecycleEvents.ServerStarting, ServerLifecycleEvents.ServerStopped {
    @Override
    public void onServerStarting(MinecraftServer server) {
        ServerInstanceImpl.server = server;
    }

    @Override
    public void onServerStopped(MinecraftServer server) {
        ServerInstanceImpl.server = null;
    }
}
