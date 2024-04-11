package lio.liosmultiloaderutils.fabric.events;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.login.ClientboundLoginDisconnectPacket;
import net.minecraft.resources.ResourceLocation;
import lio.liosmultiloaderutils.MultiloaderUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ClientLoginEvent implements ClientLoginConnectionEvents.QueryStart {

    public static Map<String, String> requiredMods = new HashMap<>();

    @Override
    public void onLoginQueryStart(ClientHandshakePacketListenerImpl handler, Minecraft client) {
        ClientLoginNetworking.registerGlobalReceiver(new ResourceLocation(MultiloaderUtils.MOD_ID, "forceclienttohavemod"), ((client1, handler1, buf, listenerAdder) -> {
            Map<String, byte[]> map = new HashMap<>();
            for (ModContainer modContainer : FabricLoader.getInstance().getAllMods()) {
                map.put(modContainer.getMetadata().getId(), modContainer.getMetadata().getVersion().getFriendlyString().getBytes());
            }
            FriendlyByteBuf buf1 = new FriendlyByteBuf(Unpooled.buffer());
            buf1.writeMap(map, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeByteArray);
            return CompletableFuture.completedFuture(buf1);
        }));
        ClientLoginNetworking.registerGlobalReceiver(new ResourceLocation(MultiloaderUtils.MOD_ID, "forcesamemodversion"), (client1, handler1, buf, listenerAdder) -> {
            Map<String, String> map = new HashMap<>();
            StringBuilder disconnectMessage = new StringBuilder();
            boolean firstAppend = true;
            for (Map.Entry<String, byte[]> entry : buf.readMap(FriendlyByteBuf::readUtf, FriendlyByteBuf::readByteArray).entrySet()) {
                map.put(entry.getKey(), new String(entry.getValue()));
            }
            for (Map.Entry<String, String> entry2 : requiredMods.entrySet()) {
                if (map.containsKey(entry2.getKey()) && map.get(entry2.getKey()).equals(entry2.getValue())) {
                    continue;
                } else {
                    if (firstAppend) {
                        firstAppend = firstAppend;
                        disconnectMessage.append(Component.translatable("zigysmultiloaderutils.servermissingmod") + "\n");
                    }
                    disconnectMessage.append(entry2.getKey() + " Version: " + entry2.getValue() + "\n");
                }
            }
            if (!disconnectMessage.isEmpty()) {
                handler1.handleDisconnect(new ClientboundLoginDisconnectPacket(Component.literal(disconnectMessage.toString())));
            }
            return CompletableFuture.completedFuture(new FriendlyByteBuf(Unpooled.buffer()));
        });
    }
}
