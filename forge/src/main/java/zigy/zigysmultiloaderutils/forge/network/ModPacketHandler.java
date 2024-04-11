package zigy.zigysmultiloaderutils.forge.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import zigy.zigysmultiloaderutils.MultiloaderUtils;

public class ModPacketHandler {

    private static int ID() {
        return packetID++;
    }

    private static int packetID = 0;
    private static final String PROTOCOL_VERSION = "1.0";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MultiloaderUtils.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            s -> true,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        INSTANCE.messageBuilder(BufPacketS2C.class, ID(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(BufPacketS2C::new)
                .encoder(BufPacketS2C::encode)
                .consumerMainThread(BufPacketS2C::apply)
                .add();

        INSTANCE.messageBuilder(BufPacketC2S.class, ID(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BufPacketC2S::new)
                .encoder(BufPacketC2S::encode)
                .consumerMainThread(BufPacketC2S::apply)
                .add();
    }

    public static void registerVersionChecker(String mod_id, String version) {
        NetworkRegistry.newSimpleChannel(new ResourceLocation(mod_id, "version_checker"), () -> version, version::equals, version::equals);
    }
}
