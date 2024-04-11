package lio.liosmultiloaderutils;

import dev.architectury.injectables.annotations.ExpectPlatform;
import org.apache.commons.lang3.NotImplementedException;
import lio.liosmultiloaderutils.utils.Platform;

public class MultiloaderUtils {
    public static final String MOD_ID = "liosmultiloaderutils";
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
