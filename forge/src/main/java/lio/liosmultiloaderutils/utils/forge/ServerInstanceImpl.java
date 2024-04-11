package lio.liosmultiloaderutils.utils.forge;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.Nullable;

public class ServerInstanceImpl {
    @Nullable
    public static MinecraftServer getServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }
}
