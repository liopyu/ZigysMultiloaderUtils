package lio.liosmultiloaderutils.utils.forge;

import lio.liosmultiloaderutils.forge.network.BufPacketC2S;
import lio.liosmultiloaderutils.forge.network.BufPacketS2C;
import lio.liosmultiloaderutils.forge.network.ModPacketHandler;
import lio.liosmultiloaderutils.utils.NetworkManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class NetworkManagerImpl {

    public static Map<ResourceLocation, NetworkManager.NetworkReciever> recieverMap = new HashMap<>();



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
