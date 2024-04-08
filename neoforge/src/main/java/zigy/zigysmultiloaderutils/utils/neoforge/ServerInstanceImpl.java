package zigy.zigysmultiloaderutils.utils.neoforge;

import net.minecraft.server.MinecraftServer;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;

public class ServerInstanceImpl {
    public static @NotNull MinecraftServer getServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }
}
