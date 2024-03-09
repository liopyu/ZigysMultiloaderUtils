package zigy.zigysmultiloaderutils.utils;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;

public class ServerInstance {
    @Nullable
    @ExpectPlatform
    public static MinecraftServer getServer() {
        throw new AssertionError();
    }
}
