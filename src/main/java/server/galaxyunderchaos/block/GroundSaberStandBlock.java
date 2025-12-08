// server.galaxyunderchaos.block.GroundSaberStandBlock

package server.galaxyunderchaos.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import server.galaxyunderchaos.entity.GroundSaberStandBlockEntity;

public class GroundSaberStandBlock extends BaseEntityBlock {

    // ---- proper codec (requires Properties ctor) ----
    public static final MapCodec<GroundSaberStandBlock> CODEC =
            simpleCodec(GroundSaberStandBlock::new);

    public GroundSaberStandBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    private static final VoxelShape SHAPE = Block.box(
            6.0, 0.0, 5.0,
            10.0, 1.4, 11.0
    );

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GroundSaberStandBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    // ----------------------------------------------------------------
    // Single use(...) method handles BOTH placing + taking sabers
    // ----------------------------------------------------------------
    @Override
    protected InteractionResult useWithoutItem(BlockState state,
                                               Level level,
                                               BlockPos pos,
                                               Player player,
                                               BlockHitResult hit) {


        if (level.isClientSide) {
            // Let server handle actual logic; client just plays animation
            return InteractionResult.SUCCESS;
        }

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof GroundSaberStandBlockEntity stand)) {
            return InteractionResult.PASS;
        }

        ItemStack held = player.getItemInHand(InteractionHand.MAIN_HAND);

        // ---- put saber on stand ----
        if (!held.isEmpty()) {
            if (stand.isEmpty()) {
                stand.setItem(held.copyWithCount(1));
                held.shrink(1);
                return InteractionResult.CONSUME;
            }
            return InteractionResult.PASS;
        }

        // ---- take saber off stand (empty hand) ----
        ItemStack removed = stand.removeItem();
        if (!removed.isEmpty()) {
            player.getInventory().placeItemBackInInventory(removed);
            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }
}
