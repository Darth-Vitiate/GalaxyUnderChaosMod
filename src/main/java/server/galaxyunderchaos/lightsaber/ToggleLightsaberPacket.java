package server.galaxyunderchaos.lightsaber;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraft.world.item.ItemStack;
import server.galaxyunderchaos.item.LightsaberItem;

public class ToggleLightsaberPacket {
    public ToggleLightsaberPacket() {}

    public static void encode(ToggleLightsaberPacket packet, FriendlyByteBuf buf) {}

    public static ToggleLightsaberPacket decode(FriendlyByteBuf buf) {
        return new ToggleLightsaberPacket();
    }

    public static void handle(ToggleLightsaberPacket packet, CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player == null) return;
            ItemStack stack = player.getMainHandItem();
            if (!(stack.getItem() instanceof LightsaberItem)) {
                stack = player.getOffhandItem();
            }
            if (stack.getItem() instanceof LightsaberItem saber) {
                saber.setActive(stack, !saber.isActive(stack), player.level(), player);
            }
        });
        ctx.setPacketHandled(true);
    }
}
