package zigy.zigysmultiloaderutils.fabric.network;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import zigy.zigysmultiloaderutils.misc.ModEnv;
import zigy.zigysmultiloaderutils.utils.NetworkManager;
import zigy.zigysmultiloaderutils.utils.Platform;

public class ClientNetworking {
    public static void sendToServer(ResourceLocation packet, FriendlyByteBuf data) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeInt(data.readableBytes());
        buf.writeBytes(data);
        ClientPlayNetworking.send(packet, buf);
    }

    public static void register(ResourceLocation id, NetworkManager.NetworkInterface networkInterface) {
        ClientPlayNetworking.registerGlobalReceiver(id, (client, handler, buf, responseSender) -> {
            FriendlyByteBuf data = new FriendlyByteBuf(buf.readBytes(buf.readInt()));

            client.execute(() -> {
                networkInterface.receive(data, new NetworkManager.PacketContext() {
                    @Override
                    public Player getPlayer() {
                        return null;
                    }

                    @Override
                    public ModEnv getEnvironment() {
                        return Platform.getEnv();
                    }
                });
            });
        });
    }
}
