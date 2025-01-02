package server.galaxyunderchaos.item;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import server.galaxyunderchaos.worldgen.dimension.ModDimensions;

public class MustafarPortalItem extends Item {

    public MustafarPortalItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            BlockPos playerPos = serverPlayer.blockPosition(); // Get the player's current position
            handleMustafarPortal(serverPlayer, playerPos);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    private void handleMustafarPortal(ServerPlayer player, BlockPos pPos) {
        if (player.level() instanceof ServerLevel serverLevel) {
            MinecraftServer minecraftServer = serverLevel.getServer();
            ResourceKey<Level> targetDimension = player.level().dimension() == ModDimensions.MUSTAFAR_LEVEL_KEY ?
                    Level.OVERWORLD : ModDimensions.MUSTAFAR_LEVEL_KEY;

            ServerLevel targetServerLevel = minecraftServer.getLevel(targetDimension);
            if (targetServerLevel != null && !player.isPassenger()) {
                player.teleportTo(targetServerLevel, pPos.getX(), pPos.getY(), pPos.getZ(), player.getYRot(), player.getXRot());
            }
        }
    }
}