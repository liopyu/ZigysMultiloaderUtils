package lio.liosmultiloaderutils.forge.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import lio.liosmultiloaderutils.utils.NetworkManager;
import lio.liosmultiloaderutils.utils.Platform;
import lio.liosmultiloaderutils.utils.forge.NetworkManagerImpl;

import java.util.function.Supplier;

public class BufPacketS2C {

    public FriendlyByteBuf data;
    public ResourceLocation id;

    public BufPacketS2C(FriendlyByteBuf buf, ResourceLocation id) {
        this.data = buf;
        this.id = id;
    }

    public BufPacketS2C(FriendlyByteBuf buf) {
        this.data = new FriendlyByteBuf(buf.readBytes(buf.readInt()));
        this.id = buf.readResourceLocation();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(data.readableBytes());
        buf.writeBytes(data);
        buf.writeResourceLocation(id);
    }

    public void apply(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            NetworkManagerImpl.getNetworkReciever(id).apply(data, context.getSender(), Platform.getEnv(), getSide(context.getDirection()));
        });
    }

    public static NetworkManager.Side getSide(NetworkDirection direction) {
        if (direction.getReceptionSide() == LogicalSide.CLIENT) {
            return NetworkManager.Side.S2C;
        } else {
            return NetworkManager.Side.C2S;
        }
    }
}
