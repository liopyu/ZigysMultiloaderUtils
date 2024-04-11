package lio.liosmultiloaderutils.utils;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.NotImplementedException;

public class MenuUtils {

    @ExpectPlatform
    public static <M extends AbstractContainerMenu> MenuType<M> getContainerMenuType(Class<? extends M> menuClass) {
        throw new NotImplementedException();
    }

    @ExpectPlatform
    public static void openMenu(ServerPlayer player, Level level, BlockState state, BlockPos pos) {
        throw new NotImplementedException();
    }
}
