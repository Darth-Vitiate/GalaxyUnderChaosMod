package server.galaxyunderchaos.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CrystalOre extends Block {
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.values());
    public static final VoxelShape SHAPE = Block.box(6.5, -0.7, 5.9, 8.5, 3.0, 9.0);

    public CrystalOre() {
        super(Properties.of()
                .strength(4.0f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.AMETHYST)
                .lightLevel((state) -> 12)
                .pushReaction(PushReaction.NORMAL));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos below = context.getClickedPos().below();
        LevelReader level = context.getLevel();

        if (!level.getBlockState(below).isSolidRender(level, below)) {
            // If no block below, attach to the wall
            return this.defaultBlockState().setValue(FACING, context.getClickedFace());
        }

        // Default to facing up if there's a block below
        return this.defaultBlockState().setValue(FACING, Direction.UP);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction facing = state.getValue(FACING);
        BlockPos attachedPos = pos.relative(facing.getOpposite());
        return level.getBlockState(attachedPos).isSolidRender(level, attachedPos);
    }
}
