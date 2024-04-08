package zigy.zigysmultiloaderutils.neoforge.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import zigy.zigysmultiloaderutils.MultiloaderUtils;

public class MultiloaderPayload implements CustomPacketPayload {

    public FriendlyByteBuf data;
    public ResourceLocation id;

    public static final ResourceLocation defaultID = new ResourceLocation(MultiloaderUtils.MOD_ID, "multiloader_packet");

    public MultiloaderPayload(FriendlyByteBuf buf, ResourceLocation id) {
        this.data = buf;
        this.id = id;
    }

    public MultiloaderPayload(FriendlyByteBuf buf) {
        this.data = new FriendlyByteBuf(buf.readBytes(buf.readInt()));
        this.id = buf.readResourceLocation();
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeInt(data.readableBytes());
        buf.writeBytes(data);
        buf.writeResourceLocation(id);
    }

    @Override
    public ResourceLocation id() {
        return defaultID;
    }
}
