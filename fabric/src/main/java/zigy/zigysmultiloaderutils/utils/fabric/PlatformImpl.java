package zigy.zigysmultiloaderutils.utils.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import zigy.zigysmultiloaderutils.misc.ModEnv;
import zigy.zigysmultiloaderutils.misc.ModLoader;

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

    public static String getModVersion(String modID) {
        return FabricLoader.getInstance().getModContainer(modID).get().getMetadata().getVersion().getFriendlyString();
    }

    public static ModLoader getLoader() {
        return ModLoader.Fabric;
    }
}
