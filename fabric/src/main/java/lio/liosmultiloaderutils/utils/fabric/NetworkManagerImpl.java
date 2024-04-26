package lio.liosmultiloaderutils.utils.fabric;

import lio.liosmultiloaderutils.fabric.network.ClientNetworking;
import lio.liosmultiloaderutils.fabric.network.ServerNetworking;
import lio.liosmultiloaderutils.utils.NetworkManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class NetworkManagerImpl {


    public static void sendToPlayer(ServerPlayer player, ResourceLocation packet, FriendlyByteBuf buf) {
        ServerNetworking.sendToPlayer(player, packet, buf);
    }

    public static void sendToServer(ResourceLocation packet, FriendlyByteBuf buf) {
        ClientNetworking.sendToServer(packet, buf);
    }

    public static void registerReceiver(NetworkManager.Side side, ResourceLocation id, NetworkManager.NetworkInterface networkInterface) {
        if (side == NetworkManager.Side.S2C) {
            ClientNetworking.register(id, networkInterface);
        } else {
            ServerNetworking.register(id, networkInterface);
        }
    }
}
