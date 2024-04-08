package zigy.zigysmultiloaderutils.utils;

import dev.architectury.injectables.annotations.ExpectPlatform;
import org.apache.commons.lang3.NotImplementedException;
import zigy.zigysmultiloaderutils.misc.ModEnv;
import zigy.zigysmultiloaderutils.misc.ModLoader;

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
    public static String getModVersion(String modID) {throw new NotImplementedException();}

    @ExpectPlatform
    public static Path getConfigFolder() {
        throw new NotImplementedException();
    }

    @ExpectPlatform
    public static ModEnv getEnv() {
        throw new NotImplementedException();
    }

    @ExpectPlatform
    public static ModLoader getLoader() {throw new NotImplementedException();}
}
