package server.galaxyunderchaos.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import server.galaxyunderchaos.item.HiltItem;
import server.galaxyunderchaos.lightsaber.LightsaberCrafting;

import java.util.Arrays;
import java.util.List;

public class LightsaberCraftingTableBlock extends Block {
    public static final DirectionProperty FACING = DirectionProperty.create("facing");
    private static final VoxelShape NORTH_SHAPE = Block.box(-8, 0.1, 0.1, 24, 16, 17);
    private static final VoxelShape WEST_SHAPE = Block.box(0.1, 0.1, -8, 17, 16, 24);
    private static final VoxelShape SOUTH_SHAPE = Block.box(-8, 0.1, -1, 24, 16, 16);
    private static final VoxelShape EAST_SHAPE = Block.box(-1, 0.1, -8, 16, 16, 24);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        switch (facing) {
            case NORTH: return NORTH_SHAPE;
            case EAST: return EAST_SHAPE;
            case SOUTH: return SOUTH_SHAPE;
            case WEST: return WEST_SHAPE;
            default: return NORTH_SHAPE;
        }
    }
    public LightsaberCraftingTableBlock() {
        super(Properties.of()
                .strength(4.0f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.COPPER)
                .noOcclusion()
                .pushReaction(PushReaction.NORMAL));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof ItemEntity hiltEntity) {
            ItemStack hiltStack = hiltEntity.getItem();

            // Check if the item is a hilt
            if (isHilt(hiltStack)) {
                // Search for a nearby kyber crystal
                List<ItemEntity> nearbyItems = level.getEntitiesOfClass(
                        ItemEntity.class,
                        new AABB(pos).inflate(1) // 1-block radius
                );

                ItemEntity kyberEntity = null;

                for (ItemEntity nearbyItem : nearbyItems) {
                    if (isKyberCrystal(nearbyItem.getItem())) {
                        kyberEntity = nearbyItem;
                        break;
                    }
                }

                if (kyberEntity != null) {
                    // Get the kyber crystal and craft the lightsaber
                    ItemStack kyberCrystal = kyberEntity.getItem();
                    ItemStack lightsaber = LightsaberCrafting.craftLightsaber(hiltStack, kyberCrystal);

                    if (!lightsaber.isEmpty()) {
                        // Spawn the crafted lightsaber
                        hiltEntity.setItem(lightsaber);

                        // Remove one hilt and one kyber crystal
                        hiltStack.shrink(1);
                        kyberCrystal.shrink(1);

                        // If the kyber crystal stack is now empty, discard the entity
                        if (kyberCrystal.isEmpty()) {
                            kyberEntity.discard();
                        }

                        // Play effects
                        spawnCraftingParticles(level, pos);
                        level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_BREAK, SoundSource.BLOCKS, 1f, 1f);
                    }
                }
            }
        }

        super.stepOn(level, pos, state, entity);
    }

    // Helper Method: Check if an item is a hilt
    private boolean isHilt(ItemStack itemStack) {
        return itemStack.getItem() instanceof HiltItem;
    }

    // Helper Method: Check if an item is a kyber crystal
    private boolean isKyberCrystal(ItemStack itemStack) {
        String[] validKybers = {
                "red_kyber", "blue_kyber", "green_kyber", "yellow_kyber",
                "cyan_kyber", "white_kyber", "magenta_kyber", "purple_kyber",
                "pink_kyber", "lime_green_kyber", "turquoise_kyber", "orange_kyber", "blood_orange_kyber"
        };

        ResourceLocation itemName = BuiltInRegistries.ITEM.getKey(itemStack.getItem());
        return itemName != null && Arrays.asList(validKybers).contains(itemName.getPath());
    }
    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.galaxyunderchaos.lightsaber_crafting_table.tooltip"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
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

    // Spawn crafting particles
    private void spawnCraftingParticles(Level level, BlockPos pos) {
        if (level.isClientSide) {
            for (int i = 0; i < 10; i++) {
                level.addParticle(ParticleTypes.ENCHANT,
                        pos.getX() + 0.5 + (level.random.nextDouble() - 0.5),
                        pos.getY() + 1,
                        pos.getZ() + 0.5 + (level.random.nextDouble() - 0.5),
                        0, 0, 0);
            }
        }
    }
}
