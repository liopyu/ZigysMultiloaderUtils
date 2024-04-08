package zigy.zigysmultiloaderutils.neoforge.network;

import net.minecraft.network.protocol.PacketFlow;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import zigy.zigysmultiloaderutils.utils.NetworkManager;
import zigy.zigysmultiloaderutils.utils.Platform;
import zigy.zigysmultiloaderutils.utils.neoforge.NetworkManagerImpl;

public class MultiloaderPayloadHandler {
    private static final MultiloaderPayloadHandler INSTANCE = new MultiloaderPayloadHandler();

    public static MultiloaderPayloadHandler getInstance() {
        return INSTANCE;
    }

    public void handleData(final MultiloaderPayload data, final PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
            NetworkManagerImpl.getNetworkReciever(data.id).apply(data.data, context.player(), Platform.getEnv(), getSide(context.flow()));
        });
    }

    public static NetworkManager.Side getSide(PacketFlow direction) {
        if (direction.getReceptionSide() == LogicalSide.CLIENT) {
            return NetworkManager.Side.S2C;
        } else {
            return NetworkManager.Side.C2S;
        }
    }
}
