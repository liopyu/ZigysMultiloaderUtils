package zigy.zigysmultiloaderutils.neoforge;

import zigy.zigysmultiloaderutils.events.ClientLoginEvent;
import zigy.zigysmultiloaderutils.events.ServerLoginEvent;

public class MultiloaderUtilsImpl {
    public static void forceClientToHaveMod(String mod_id, String version) {
        ServerLoginEvent.requiredMods.put(mod_id, version);
    }

    public static void forceServerToHaveMod(String mod_id, String version) {
        ClientLoginEvent.requiredMods.put(mod_id, version);
    }
}
