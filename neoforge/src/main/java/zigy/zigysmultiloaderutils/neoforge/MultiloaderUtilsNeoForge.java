package zigy.zigysmultiloaderutils.neoforge;

import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
import zigy.zigysmultiloaderutils.MultiloaderUtils;
import zigy.zigysmultiloaderutils.neoforge.network.MultiloaderPayloadHandler;
import zigy.zigysmultiloaderutils.neoforge.network.MultiloaderPacket;
import zigy.zigysmultiloaderutils.utils.neoforge.PlatformImpl;

@Mod(MultiloaderUtils.MOD_ID)
public class MultiloaderUtilsNeoForge {
    public MultiloaderUtilsNeoForge() {
        MultiloaderUtils.init();
        NeoForge.EVENT_BUS.addListener(MultiloaderUtilsNeoForge::registerPackets);
    }

    public static void registerPackets(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(MultiloaderUtils.MOD_ID);
        registrar.play(MultiloaderPacket.defaultID, MultiloaderPacket::new, handler -> handler
                .client(MultiloaderPayloadHandler.getInstance()::handleData)
                .server(MultiloaderPayloadHandler.getInstance()::handleData)).versioned(PlatformImpl.getModVersion(MultiloaderUtils.MOD_ID));
    }
}
