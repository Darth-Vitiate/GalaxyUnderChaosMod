package server.galaxyunderchaos.item;

import client.renderer.ModItemRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;
import server.galaxyunderchaos.sound.ModSounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class LightsaberItem extends SwordItem {
    private final String bladeColor;
    private boolean isActive = false;

    public LightsaberItem(String bladeColor, Item.Properties properties) {
        super(ModToolTiers.LIGHTSABER, new Item.Properties()
                .attributes(SwordItem.createAttributes(ModToolTiers.LIGHTSABER, 2, -2.4F)));
        this.bladeColor = bladeColor;
    }

    public boolean isActive() {
        return isActive;
    }


    public void setActive(boolean active) {
        if (this.isActive != active) {
            this.isActive = active;

            // Play sound based on whether it's being turned on or off
            if (active) {
                // Play "on" sound
                Minecraft.getInstance().player.level().playSound(
                        Minecraft.getInstance().player,
                        Minecraft.getInstance().player.getX(),
                        Minecraft.getInstance().player.getY(),
                        Minecraft.getInstance().player.getZ(),
                        ModSounds.LIGHTSABER_TURN_ON.get(),
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );
            } else {
                // Play "off" sound
                Minecraft.getInstance().player.level().playSound(
                        Minecraft.getInstance().player,
                        Minecraft.getInstance().player.getX(),
                        Minecraft.getInstance().player.getY(),
                        Minecraft.getInstance().player.getZ(),
                        ModSounds.LIGHTSABER_TURN_OFF.get(),
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );
            }
        }
    }    public String getBladeColor() {
        return bladeColor;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        // Check if the lightsaber is active
        if (!isActive()) {
            return InteractionResultHolder.pass(player.getItemInHand(hand));  // Don't do anything if not active
        }

        // Check if the player is left-clicking (attack action)
        if (player.isAttackable()) {
            // Raycast to get the block the player is targeting within range (5 blocks)
            BlockHitResult raycastResult = (BlockHitResult) player.pick(5.0, 1.0F, false); // Adjust range (5) and precision (1.0F)

            // Check if the player is targeting air (no block targeted)
            if (raycastResult.getType() == HitResult.Type.MISS) {
                // Play the swing sound if the player is left-clicking in the air
                level.playSound(player, player.getX(), player.getY(), player.getZ(),
                        ModSounds.LIGHTSABER_SWING.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

                // Return a successful result indicating the interaction was handled
                return InteractionResultHolder.success(player.getItemInHand(hand));
            }
        }

        // If the conditions aren't met, return pass (default behavior)
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return isActive();
    }


    public String getTextureLocation() {
        return isActive ?
                "galaxyunderchaos:item/" + bladeColor + "_blade" :
                "galaxyunderchaos:item/" + getHilt(new ItemStack(this)); // Dynamically get hilt
    }


    public static int getLightLevel(ItemStack stack) {
        if (stack.getItem() instanceof LightsaberItem lightsaber && lightsaber.isActive()) {
            return 15; // Full torch brightness
        }
        return 0; // No light when inactive
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        if (entity instanceof Player player) {
            // Play the swing sound when the player uses the lightsaber (swings it)
            if (remainingUseDuration % 10 == 0) {  // Control the rate of swing sound
                level.playSound(
                        player,
                        player.getX(), player.getY(), player.getZ(),
                        ModSounds.LIGHTSABER_SWING.get(), // You can add a specific swing sound if you have one
                        SoundSource.PLAYERS,
                        1.0F, // Volume
                        1.0F  // Pitch
                );
            }
        }
    }
    public String getHilt(ItemStack stack) {
        ResourceLocation registryName = ForgeRegistries.ITEMS.getKey(stack.getItem());
        if (registryName == null) {
            return "unknown";
        }

        String path = registryName.getPath(); // e.g., "blue_apprentice_lightsaber"
        String[] parts = path.split("_");
        if (parts.length < 3 || !path.endsWith("_lightsaber")) {
            return "unknown"; // Fallback for invalid formats
        }

        return parts[1]; // Extract hilt name (e.g., "apprentice")
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slot, isSelected);

        if (level.isClientSide && entity instanceof Player player) {
            if (isSelected && isActive()) {
                level.playSound(
                        player,
                        player.getX(), player.getY(), player.getZ(),
                        ModSounds.LIGHTSABER_IDLE.get(),
                        SoundSource.PLAYERS,
                        0.3F,
                        0.5F
                );
            }
            if (!level.isClientSide && entity instanceof Player) {
                level.getChunkSource().getLightEngine().checkBlock(entity.blockPosition());
            }
            if (isActive()) {
                entity.setGlowingTag(true);
            } else {
                entity.setGlowingTag(false);
            }
        }
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (isActive) {
            // Play the swing sound when mining a block
            if (level.isClientSide && entity instanceof Player player) {
                if (isActive()) {
                    level.playSound(
                            player,
                            player.getX(), player.getY(), player.getZ(),
                            ModSounds.LIGHTSABER_SWING.get(),
                            SoundSource.PLAYERS,
                            0.3F,
                            0.5F
                    );
                }
            }
        }

        // Proceed with the normal mining behavior
        return super.mineBlock(stack, level, state, pos, entity);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!isActive) {
            return false; // Lightsaber does not damage if inactive
        }

        if (!attacker.level().isClientSide) {
            attacker.level().playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(),
                    ModSounds.LIGHTSABER_HIT.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        if (!attacker.level().isClientSide && target.isDeadOrDying() && attacker instanceof ServerPlayer player) {
            ServerLevel serverLevel = (ServerLevel) target.level();

            ResourceKey<LootTable> lootTableLocation = target.getType().getDefaultLootTable();
            LootTable lootTable = serverLevel.getServer().reloadableRegistries().getLootTable(lootTableLocation);

            LootParams.Builder lootParams = new LootParams.Builder(serverLevel)
                    .withParameter(LootContextParams.ORIGIN, target.position())
                    .withParameter(LootContextParams.THIS_ENTITY, target)
                    .withParameter(LootContextParams.DAMAGE_SOURCE, target.getLastDamageSource());
            List<ItemStack> drops = lootTable.getRandomItems(lootParams.create(LootContextParamSets.ENTITY));

            List<ItemStack> cookedDrops = new ArrayList<>();
            for (ItemStack drop : drops) {
                Item cookedItem = getCookedVersion(drop.getItem());
                if (cookedItem != null) {
                    cookedDrops.add(new ItemStack(cookedItem, drop.getCount()));
                } else {
                    cookedDrops.add(drop);
                }
            }

            target.remove(Entity.RemovalReason.DISCARDED);

            for (ItemStack cookedDrop : cookedDrops) {
                target.spawnAtLocation(cookedDrop);
            }
        }

        return super.hurtEnemy(stack, target, attacker);
    }


    private Item getCookedVersion(Item rawFood) {
        Map<Item, Item> cookingMap = Map.of(
                Items.BEEF, Items.COOKED_BEEF,
                Items.CHICKEN, Items.COOKED_CHICKEN,
                Items.PORKCHOP, Items.COOKED_PORKCHOP,
                Items.MUTTON, Items.COOKED_MUTTON,
                Items.RABBIT, Items.COOKED_RABBIT,
                Items.SALMON, Items.COOKED_SALMON,
                Items.COD, Items.COOKED_COD
        );
        return cookingMap.getOrDefault(rawFood, null);
    }
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer customRenderer = new ModItemRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return customRenderer;
            }
        });
    }

    public boolean shouldRenderBlade(ItemStack stack) {
        return isActive(); // ✅ Only render the blade (layer1) when active
    }

}
