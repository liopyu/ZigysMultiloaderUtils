package zigy.zigysmultiloaderutils.neoforge.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class VersionCheckPacket implements CustomPacketPayload {

    public final ResourceLocation ID;

    public VersionCheckPacket(ResourceLocation id) {
        super();
        this.ID = id;
    }

    @Override
    public void write(FriendlyByteBuf buffer) {}

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
