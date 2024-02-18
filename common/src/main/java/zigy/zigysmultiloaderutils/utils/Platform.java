package zigy.zigysmultiloaderutils.utils;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;
import zigy.zigysmultiloaderutils.misc.ModEnv;

import java.nio.file.Path;

public class Platform {

    @ExpectPlatform
    public static boolean isModLoaded(String modID) {
        throw new NotImplementedException();
    }

    @ExpectPlatform
    public static boolean isModLoaded(String modID, String modClass) {
        throw new NotImplementedException();
    }

    @ExpectPlatform
    public static Path getConfigFolder() {
        throw new NotImplementedException();
    }

    @ExpectPlatform
    public static ModEnv getEnv() {
        throw new NotImplementedException();
    }

    @Nullable
    @ExpectPlatform
    public static MinecraftServer getServer() {
        throw new AssertionError();
    }
}
