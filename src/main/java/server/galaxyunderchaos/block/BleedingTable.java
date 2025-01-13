package server.galaxyunderchaos.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import server.galaxyunderchaos.galaxyunderchaos;
import java.util.List;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;


public class BleedingTable extends Block {
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);
    public static final VoxelShape SHAPE = Block.box(0.1, 0.1, 0.1, 16, 16, 16);

    public BleedingTable() {
        super(BlockBehaviour.Properties.of()
                .strength(3.0f, 10.0f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE)
                .pushReaction(PushReaction.NORMAL));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
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
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }
    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.galaxyunderchaos.bleeding_table.tooltip"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

        @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos,
                                               Player pPlayer, BlockHitResult pHitResult) {
        pLevel.playSound(pPlayer, pPos, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 1f, 1f);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pEntity instanceof ItemEntity itemEntity) {
            ItemStack itemStack = itemEntity.getItem();

            if (itemStack.getItem() == galaxyunderchaos.ORANGE_KYBER.get()) {
                itemEntity.setItem(new ItemStack(galaxyunderchaos.BLOOD_ORANGE_KYBER.get(), itemStack.getCount()));
                summonLightningEffect(pLevel, pPos);
                pLevel.playSound(null, pPos, SoundEvents.AMETHYST_BLOCK_STEP, SoundSource.BLOCKS, 1f, 1f);
            } else if (isValidKyber(itemStack)) {
                itemEntity.setItem(new ItemStack(galaxyunderchaos.RED_KYBER.get(), itemStack.getCount()));
                summonLightningEffect(pLevel, pPos);
                pLevel.playSound(null, pPos, SoundEvents.AMETHYST_BLOCK_STEP, SoundSource.BLOCKS, 1f, 1f);
            }
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    private boolean isValidKyber(ItemStack item) {
        return item.getItem() == galaxyunderchaos.BLUE_KYBER.get() ||
                item.getItem() == galaxyunderchaos.GREEN_KYBER.get() ||
                item.getItem() == galaxyunderchaos.YELLOW_KYBER.get() ||
                item.getItem() == galaxyunderchaos.CYAN_KYBER.get() ||
                item.getItem() == galaxyunderchaos.WHITE_KYBER.get() ||
                item.getItem() == galaxyunderchaos.MAGENTA_KYBER.get() ||
                item.getItem() == galaxyunderchaos.PURPLE_KYBER.get() ||
                item.getItem() == galaxyunderchaos.PINK_KYBER.get() ||
                item.getItem() == galaxyunderchaos.LIME_GREEN_KYBER.get() ||
                item.getItem() == galaxyunderchaos.TURQUOISE_KYBER.get();
    }

    private void summonLightningEffect(Level pLevel, BlockPos pPos) {
        if (!pLevel.isClientSide) {
            LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(pLevel);
            if (lightningBolt != null) {
                lightningBolt.moveTo(pPos.getX() + 0.5, pPos.getY(), pPos.getZ() + 0.5);
                lightningBolt.setCause(null); // Set the cause to null to prevent fire
                lightningBolt.setVisualOnly(true); // Ensure it is visual only (no fire)
                pLevel.addFreshEntity(lightningBolt);
            }
        }
    }
}
