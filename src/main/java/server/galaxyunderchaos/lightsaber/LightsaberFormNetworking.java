package server.galaxyunderchaos.lightsaber;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import server.galaxyunderchaos.galaxyunderchaos;

public class LightsaberFormNetworking {

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(LightsaberFormNetworking::registerPackets);
    }

    private static void registerPackets(final RegisterPayloadHandlersEvent event) {
        final var registrar = event.registrar(galaxyunderchaos.MODID); // ← Corrected: Mod ID (String), not ResourceLocation

        registrar.playToClient(
                SyncLightsaberFormPacket.TYPE,
                SyncLightsaberFormPacket.STREAM_CODEC,
                SyncLightsaberFormPacket::handle
        );

        registrar.playToServer(
                SwitchLightsaberFormPacket.TYPE,
                SwitchLightsaberFormPacket.STREAM_CODEC,
                SwitchLightsaberFormPacket::handle
        );
    }

    public static void sendToServer(SwitchLightsaberFormPacket packet) {
        PacketDistributor.sendToServer(packet);
    }

    public static void sendToPlayer(ServerPlayer player, SyncLightsaberFormPacket packet) {
        PacketDistributor.sendToPlayer(player, packet);
    }
}
