package lio.liosmultiloaderutils.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerLoginConnectionEvents;
import lio.liosmultiloaderutils.MultiloaderUtils;
import lio.liosmultiloaderutils.fabric.events.ServerEvents;
import lio.liosmultiloaderutils.fabric.events.ServerLoginEvent;

public class MultiloaderUtilsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        MultiloaderUtils.init();
        ServerLifecycleEvents.SERVER_STARTING.register(new ServerEvents());
        ServerLifecycleEvents.SERVER_STOPPED.register(new ServerEvents());
        ServerLoginConnectionEvents.QUERY_START.register(new ServerLoginEvent());
    }
}
