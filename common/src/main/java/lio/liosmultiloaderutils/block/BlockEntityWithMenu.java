package lio.liosmultiloaderutils.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import lio.liosmultiloaderutils.interfaces.AdvancedMenuHandler;

public abstract class BlockEntityWithMenu extends BlockEntity implements AdvancedMenuHandler {
    public BlockEntityWithMenu(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }
}
