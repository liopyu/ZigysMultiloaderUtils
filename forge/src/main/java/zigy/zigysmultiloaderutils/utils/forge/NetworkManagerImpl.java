package zigy.zigysmultiloaderutils.utils.forge;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;
import zigy.zigysmultiloaderutils.forge.network.BufPacketC2S;
import zigy.zigysmultiloaderutils.forge.network.BufPacketS2C;
import zigy.zigysmultiloaderutils.forge.network.ModPacketHandler;
import zigy.zigysmultiloaderutils.utils.NetworkManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NetworkManagerImpl {

    public static Map<ResourceLocation, NetworkManager.NetworkReciever> recieverMap = new HashMap<>();

    public static void sendToPlayers(Collection<ServerPlayer> players, ResourceLocation packet, FriendlyByteBuf buf) {
        for (ServerPlayer player : players) {
            sendToPlayer(player, packet, buf);
        }
    }

    public static void sendToPlayer(ServerPlayer player, ResourceLocation packet, FriendlyByteBuf buf) {
        ModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new BufPacketS2C(buf, packet));
    }

    public static void sendToServer(ResourceLocation packet, FriendlyByteBuf buf) {
        ModPacketHandler.INSTANCE.sendToServer(new BufPacketC2S(buf, packet));
    }

    public static void registerReceiver(NetworkManager.Side side, ResourceLocation id, NetworkManager.NetworkInterface networkInterface) {
        recieverMap.put(id, new NetworkManager.NetworkReciever(side, networkInterface));
    }

    @Nullable
    public static NetworkManager.NetworkReciever getNetworkReciever(ResourceLocation id) {
        return recieverMap.get(id);
    }
}
