package zigy.zigysmultiloaderutils.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import zigy.zigysmultiloaderutils.fabric.events.ClientLoginEvent;

public class MultiloaderUtilsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientLoginConnectionEvents.QUERY_START.register(new ClientLoginEvent());
    }
}
