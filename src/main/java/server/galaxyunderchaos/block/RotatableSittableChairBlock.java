package server.galaxyunderchaos.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import server.galaxyunderchaos.entity.SeatEntity;

public class RotatableSittableChairBlock extends HorizontalDirectionalBlock {

    public RotatableSittableChairBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {

        if (level.isClientSide()) return InteractionResult.SUCCESS;
        if (player.isPassenger()) return InteractionResult.FAIL;

        // Position seat slightly above block
        double yOffset = 0.4D;

        SeatEntity seat = new SeatEntity(level, pos.getX() + 0.5D, pos.getY() + yOffset, pos.getZ() + 0.5D);
        level.addFreshEntity(seat);
        player.startRiding(seat);

        return InteractionResult.CONSUME;
    }
}
