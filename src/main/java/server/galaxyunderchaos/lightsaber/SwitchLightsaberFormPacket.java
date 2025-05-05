package server.galaxyunderchaos.lightsaber;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import server.galaxyunderchaos.galaxyunderchaos;
import java.util.List;
import server.galaxyunderchaos.lightsaber.LightsaberFormCapability;
import server.galaxyunderchaos.lightsaber.LightsaberFormCapabilityManager;

public class SwitchLightsaberFormPacket implements CustomPacketPayload {
    public static final ResourceLocation ID =
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "switch_lightsaber_form");
    public static final Type<SwitchLightsaberFormPacket> TYPE = new Type<>(ID);

    // Supplier-based codec to allow fresh instances
    public static final StreamCodec<RegistryFriendlyByteBuf, SwitchLightsaberFormPacket> STREAM_CODEC =
            StreamCodec.of(
                    (buf, pkt) -> {
                        galaxyunderchaos.LOGGER.debug("Encoding SwitchLightsaberFormPacket");
                        // no data
                    },
                    buf -> {
                        galaxyunderchaos.LOGGER.debug("Decoding SwitchLightsaberFormPacket");
                        return new SwitchLightsaberFormPacket();
                    }
            );
    public SwitchLightsaberFormPacket() {}

    public static void handle(SwitchLightsaberFormPacket pkt, IPayloadContext ctx) {
        galaxyunderchaos.LOGGER.info("Handling SwitchLightsaberFormPacket");
        ctx.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) ctx.player();
            if (player == null) {
                galaxyunderchaos.LOGGER.warn("Player is null in SwitchLightsaberFormPacket.handle");
                return;
            }

            var cap = player.getCapability(LightsaberFormCapability.CAPABILITY);
            if (cap == null) return;

            List<String> unlocked = List.copyOf(cap.getUnlockedForms());
            if (unlocked.isEmpty()) {
                galaxyunderchaos.LOGGER.warn("No unlocked forms for player {}", player.getScoreboardName());
                player.sendSystemMessage(Component.literal(
                        "You have not unlocked any lightsaber forms yet!"), false);
                return;
            }

            String prev = cap.getSelectedForm();
            int current = unlocked.indexOf(prev);
            String next = unlocked.get((current + 1) % unlocked.size());

            cap.setSelectedForm(next);
            LightsaberFormCapabilityManager.syncCapability(player);

            player.sendSystemMessage(Component.literal(
                    "Switched lightsaber form from '" + prev + "' to '" + next + "'."), false);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public ServerboundCustomPayloadPacket toVanillaServerbound() {
        return CustomPacketPayload.super.toVanillaServerbound();
    }
}
