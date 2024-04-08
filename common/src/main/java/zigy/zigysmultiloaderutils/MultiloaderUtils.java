package zigy.zigysmultiloaderutils;

import dev.architectury.injectables.annotations.ExpectPlatform;
import org.apache.commons.lang3.NotImplementedException;
import zigy.zigysmultiloaderutils.utils.Platform;

public class MultiloaderUtils {
    public static final String MOD_ID = "zigysmultiloaderutils";
    public static void init() {
        forceClientToHaveMod(MOD_ID, Platform.getModVersion(MOD_ID));
    }

    @ExpectPlatform
    public static void forceClientToHaveMod(String mod_id, String version) {
        throw new NotImplementedException();
    }

    @ExpectPlatform
    public static void forceServerToHaveMod(String mod_id, String version) {
        throw new NotImplementedException();
    }
}
