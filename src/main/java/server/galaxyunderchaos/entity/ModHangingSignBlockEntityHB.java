package server.galaxyunderchaos.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;


public class ModHangingSignBlockEntityHB extends SignBlockEntity {

    public ModHangingSignBlockEntityHB(BlockPos pos, BlockState state) {
        super(ModBlockEntities.HEART_BERRY_HANGING_SIGN_BE.get(), pos, state);
    }
}
