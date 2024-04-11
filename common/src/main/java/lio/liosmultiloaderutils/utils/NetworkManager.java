package lio.liosmultiloaderutils.utils;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.apache.commons.lang3.NotImplementedException;
import lio.liosmultiloaderutils.misc.ModEnv;

import java.util.Collection;

public class NetworkManager {

    @ExpectPlatform
    public static void sendToPlayers(Collection<ServerPlayer> players, ResourceLocation packet, FriendlyByteBuf buf) {
        throw new NotImplementedException();
    }

    @ExpectPlatform
    public static void sendToPlayer(ServerPlayer player, ResourceLocation packet, FriendlyByteBuf buf) {
        throw new NotImplementedException();
    }

    @ExpectPlatform
    public static void sendToServer(ResourceLocation packet, FriendlyByteBuf buf) {
        throw new NotImplementedException();
    }

    @ExpectPlatform
    public static void registerReceiver(Side side, ResourceLocation id, NetworkInterface networkInterface) {
        throw new NotImplementedException();
    }

    @FunctionalInterface
    public interface NetworkInterface {
        void receive(FriendlyByteBuf buf, PacketContext context);
    }

    public interface PacketContext {
        Player getPlayer();

        ModEnv getEnvironment();
    }

    public static class NetworkReciever {
        Side side;
        NetworkInterface networkInterface;

        public NetworkReciever(Side side, NetworkInterface networkInterface) {
            this.side = side;
            this.networkInterface = networkInterface;
        }

        public void apply(FriendlyByteBuf buf, Player player, ModEnv env, Side side) {
            if (side == this.side) {
                PacketContext context = new PacketContext() {
                    @Override
                    public Player getPlayer() {
                        return player;
                    }

                    @Override
                    public ModEnv getEnvironment() {
                        return env;
                    }
                };
                this.networkInterface.receive(buf, context);
            }
        }
    }

    public enum Side {
        S2C,
        C2S
    }
}
