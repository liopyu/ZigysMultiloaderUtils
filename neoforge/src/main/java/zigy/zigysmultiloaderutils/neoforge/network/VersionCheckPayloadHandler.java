package zigy.zigysmultiloaderutils.neoforge.network;

import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class VersionCheckPayloadHandler {

    private static final VersionCheckPayloadHandler INSTANCE = new VersionCheckPayloadHandler();

    public static VersionCheckPayloadHandler getInstance() {
        return INSTANCE;
    }

    public void handleData(final VersionCheckPacket data, final PlayPayloadContext context) {}
}
