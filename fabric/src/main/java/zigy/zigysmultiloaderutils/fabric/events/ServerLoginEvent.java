package zigy.zigysmultiloaderutils.fabric.events;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerLoginConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerLoginPacketListenerImpl;
import zigy.zigysmultiloaderutils.MultiloaderUtils;

import java.util.HashMap;
import java.util.Map;

public class ServerLoginEvent implements ServerLoginConnectionEvents.QueryStart{

    public static Map<String, String> requiredMods = new HashMap<>();
    @Override
    public void onLoginStart(ServerLoginPacketListenerImpl handler2, MinecraftServer server2, PacketSender sender, ServerLoginNetworking.LoginSynchronizer synchronizer2) {
        ServerLoginNetworking.registerGlobalReceiver(new ResourceLocation(MultiloaderUtils.MOD_ID, "forceclienttohavemod"), ((server, handler, understood, buf, synchronizer, responseSender) -> {
            if (!understood) {
                handler.disconnect(Component.literal("You are trying to join a modded Fabric server without Fabric or are missing the Zigy's Multiloader Utils mod"));
                return;
            }
            Map<String, String> map = new HashMap<>();
            StringBuilder disconnectMessage = new StringBuilder();
            for (Map.Entry<String, byte[]> entry : buf.readMap(FriendlyByteBuf::readUtf, FriendlyByteBuf::readByteArray).entrySet()) {
                map.put(entry.getKey(), new String(entry.getValue()));
            }
            for (Map.Entry<String, String> entry2 : requiredMods.entrySet()) {
                if (!(map.containsKey(entry2.getKey()) && map.get(entry2.getKey()).equals(entry2.getValue()))) {
                    disconnectMessage.append(Component.translatable("zigysmultiloaderutils.missingmod") + " " + entry2.getKey() + " Version: " + entry2.getValue() + "\n");
                }
            }
            if (!disconnectMessage.isEmpty()) {
                handler.disconnect(Component.literal(disconnectMessage.toString()));
            }
        }));
        ServerLoginNetworking.registerGlobalReceiver(new ResourceLocation(MultiloaderUtils.MOD_ID, "forcesamemodversion"), ((server, handler, understood, buf, synchronizer, responseSender) -> {
            if (!understood) {
                handler.disconnect(Component.literal("You are trying to join a modded Fabric server without Fabric or are missing the Zigy's Multiloader Utils mod"));
            }
        }));
        sender.sendPacket(sender.createPacket(new ResourceLocation(MultiloaderUtils.MOD_ID, "forceclienttohavemod"), new FriendlyByteBuf(Unpooled.buffer())));
        Map<String, byte[]> map = new HashMap<>();
        for (ModContainer modContainer : FabricLoader.getInstance().getAllMods()) {
            map.put(modContainer.getMetadata().getId(), modContainer.getMetadata().getVersion().getFriendlyString().getBytes());
        }
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeMap(map, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeByteArray);
        sender.sendPacket(sender.createPacket(new ResourceLocation(MultiloaderUtils.MOD_ID, "forcesamemodversion"), buf));
    }
}
