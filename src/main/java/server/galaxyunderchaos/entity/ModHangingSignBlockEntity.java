package server.galaxyunderchaos.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;


public class ModHangingSignBlockEntity extends SignBlockEntity {

    public ModHangingSignBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.AK_HANGING_SIGN_BE.get(), pos, state);
    }
}
