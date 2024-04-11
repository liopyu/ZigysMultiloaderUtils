package zigy.zigysmultiloaderutils.forge;

import zigy.zigysmultiloaderutils.forge.network.ModPacketHandler;

public class MultiloaderUtilsImpl {
    public static void forceClientToHaveMod(String mod_id, String version) {
        ModPacketHandler.registerVersionChecker(mod_id, version);
    }

    public static void forceServerToHaveMod(String mod_id, String version) {
        ModPacketHandler.registerVersionChecker(mod_id, version);
    }
}
