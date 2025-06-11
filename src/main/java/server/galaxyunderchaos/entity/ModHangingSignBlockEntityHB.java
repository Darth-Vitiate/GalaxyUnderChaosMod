package server.galaxyunderchaos.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ModHangingSignBlockEntityHB extends HangingSignBlockEntity {
    public ModHangingSignBlockEntityHB(BlockPos pos, BlockState state) {
        super(pos, state);          // ← TWO args, no type
    }
}