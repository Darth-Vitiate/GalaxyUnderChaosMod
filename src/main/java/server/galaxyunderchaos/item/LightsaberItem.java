package server.galaxyunderchaos.item;

import client.renderer.ModItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
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
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import server.galaxyunderchaos.data.ModDataComponentTypes;
import server.galaxyunderchaos.sound.ModSounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class LightsaberItem extends SwordItem {
    private static final DataComponentType<Boolean> ACTIVE_COMPONENT = ModDataComponentTypes.ACTIVE.get();
    private final String bladeColor;

    public LightsaberItem(String bladeColor, Item.Properties properties) {
        super(ModToolTiers.LIGHTSABER, new Item.Properties()
                .attributes(SwordItem.createAttributes(ModToolTiers.LIGHTSABER, 21, -2.4F)));
        this.bladeColor = bladeColor;
    }

    public boolean isActive(ItemStack stack) {
        return stack.getOrDefault(ACTIVE_COMPONENT, false);
    }

    public void setActive(ItemStack stack, boolean active, Level level, Player player) {
        if (isActive(stack) != active) {
            stack.set(ACTIVE_COMPONENT, active);
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    active ? ModSounds.LIGHTSABER_TURN_ON.get() : ModSounds.LIGHTSABER_TURN_OFF.get(),
                    SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        setActive(stack, !isActive(stack), level, player);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return isActive(stack);
    }

    public String getTextureLocation(ItemStack stack) {
        return isActive(stack) ?
                "galaxyunderchaos:item/" + bladeColor + "_blade" :
                "galaxyunderchaos:item/" + getHilt(stack);
    }

    public static int getLightLevel(ItemStack stack) {
        return (stack.getItem() instanceof LightsaberItem ls && ls.isActive(stack)) ? 15 : 0;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (isActive(stack) && level.isClientSide && entity instanceof Player player) {
            level.playSound(
                    player,
                    player.getX(), player.getY(), player.getZ(),
                    ModSounds.LIGHTSABER_SWING.get(),
                    SoundSource.PLAYERS,
                    0.3F,
                    0.5F
            );
        }
        return super.mineBlock(stack, level, state, pos, entity);
    }
    public String getHilt(ItemStack stack) {
        ResourceLocation registryName = stack.getItem().builtInRegistryHolder().key().location();
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
    public static String getBladeColor(ItemStack stack) {
        if (stack.getItem() instanceof LightsaberItem saber) {
            return saber.bladeColor; // Retrieves the predefined blade color
        }
        return "white"; // Default to white if no color is found
    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slot, isSelected);
        if (level.isClientSide && entity instanceof Player player && isSelected && isActive(stack)) {
            level.playSound(player, player.getX(), player.getY(), player.getZ(),
                    ModSounds.LIGHTSABER_IDLE.get(),
                    SoundSource.PLAYERS, 0.3F, 0.5F);
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!isActive(stack)) {
            setActive(stack, true, attacker.level(), attacker instanceof Player player ? player : null);
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
}