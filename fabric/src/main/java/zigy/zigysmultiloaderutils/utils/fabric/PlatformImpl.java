package zigy.zigysmultiloaderutils.utils.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import zigy.zigysmultiloaderutils.misc.ModEnv;

import java.nio.file.Path;

public class PlatformImpl {
    public static boolean isModLoaded(String modID) {
        return FabricLoader.getInstance().isModLoaded(modID);
    }

    public static boolean isModLoaded(String modID, String modClass) {
        return FabricLoader.getInstance().isModLoaded(modID);
    }

    public static Path getConfigFolder() {
        return FabricLoader.getInstance()
                .getConfigDir()
                .toAbsolutePath()
                .normalize();
    }

    public static ModEnv getEnv() {
        if (FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT)) {
            return ModEnv.CLIENT;
        } else {
            return ModEnv.SERVER;
        }
    }

    public static MinecraftServer server = null;

    public static MinecraftServer getServer() {
        MinecraftServer server = null;
        if (PlatformImpl.server != null) server = PlatformImpl.server;
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
