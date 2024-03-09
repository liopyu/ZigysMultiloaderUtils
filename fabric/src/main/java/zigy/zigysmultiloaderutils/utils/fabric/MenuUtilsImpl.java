package zigy.zigysmultiloaderutils.utils.fabric;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.lang.reflect.InvocationTargetException;

public class MenuUtilsImpl {
    public static <M extends AbstractContainerMenu> MenuType<M> getContainerMenuType(Class<? extends M> menuClass) {
        return new ExtendedScreenHandlerType<>(((i, arg, arg2) -> {
            try {
                return menuClass.getConstructor(Integer.class, Player.class, BlockPos.class).newInstance(i, arg.player, arg2.readBlockPos());
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    public static void openMenu(ServerPlayer player, Level level, BlockState state, BlockPos pos) {
        player.openMenu(state.getMenuProvider(level, pos));
    }
}
