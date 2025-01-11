package server.galaxyunderchaos.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import server.galaxyunderchaos.item.LightsaberItem;
import server.galaxyunderchaos.lightsaber.LightsaberCrafting;

import java.util.Arrays;
import java.util.List;

public class LightsaberCraftingTableBlock extends Block {
    public LightsaberCraftingTableBlock(Properties properties) {
        super(properties);
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
        return itemStack.getItem() instanceof LightsaberItem;
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
