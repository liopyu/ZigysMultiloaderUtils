package zigy.zigysmultiloaderutils.interfaces;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;

public interface AdvancedMenuHandler extends MenuProvider {
    void writeScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf);
}
