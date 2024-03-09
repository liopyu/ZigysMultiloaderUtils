package zigy.zigysmultiloaderutils.utils.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;

public class ServerInstanceImpl {

    public static MinecraftServer server = null;

    @Nullable
    public static MinecraftServer getServer() {
        MinecraftServer server = null;
        if (ServerInstanceImpl.server != null) server = ServerInstanceImpl.server;
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            server = getServerFromClient();
        }
        return server;
    }

    @Environment(EnvType.CLIENT)
    private static MinecraftServer getServerFromClient() {
        return Minecraft.getInstance().getSingleplayerServer();
    }
}
