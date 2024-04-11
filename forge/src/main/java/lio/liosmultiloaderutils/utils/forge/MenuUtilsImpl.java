package lio.liosmultiloaderutils.utils.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.NetworkHooks;
import lio.liosmultiloaderutils.interfaces.AdvancedMenuHandler;

import java.lang.reflect.InvocationTargetException;

public class MenuUtilsImpl {
    public static <M extends AbstractContainerMenu> MenuType<M> getContainerMenuType(Class<? extends M> menuClass) {
        return IForgeMenuType.create(((i, arg, arg2) -> {
            try {
                return menuClass.getConstructor(Integer.class, Player.class, BlockPos.class).newInstance(i, arg.player, arg2.readBlockPos());
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    public static void openMenu(ServerPlayer player, Level level, BlockState state, BlockPos pos) {
        AdvancedMenuHandler blockEntity = (AdvancedMenuHandler) level.getBlockEntity(pos);
        MenuProvider containerProvider = new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return blockEntity.getDisplayName();
            }

            @Override
            public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
                return blockEntity.createMenu(windowId, playerInventory, playerEntity);
            }
        };
        NetworkHooks.openScreen(player, containerProvider, pos);
    }
}
