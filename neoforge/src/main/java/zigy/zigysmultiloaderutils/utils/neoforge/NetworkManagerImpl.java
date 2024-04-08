package zigy.zigysmultiloaderutils.utils.neoforge;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;
import zigy.zigysmultiloaderutils.neoforge.network.MultiloaderPayload;
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
        PacketDistributor.PLAYER.with(player).send(new MultiloaderPayload(buf, packet));
    }

    public static void sendToServer(ResourceLocation packet, FriendlyByteBuf buf) {
        PacketDistributor.SERVER.noArg().send(new MultiloaderPayload(buf, packet));
    }

    public static void registerReceiver(NetworkManager.Side side, ResourceLocation id, NetworkManager.NetworkInterface networkInterface) {
        recieverMap.put(id, new NetworkManager.NetworkReciever(side, networkInterface));
    }

    @Nullable
    public static NetworkManager.NetworkReciever getNetworkReciever(ResourceLocation id) {
        return recieverMap.get(id);
    }
}
