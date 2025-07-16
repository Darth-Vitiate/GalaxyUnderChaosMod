package server.galaxyunderchaos.lightsaber;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.SimpleChannel;
import server.galaxyunderchaos.galaxyunderchaos;

import java.lang.reflect.Constructor;

public class LightsaberFormNetworking {
    public static SimpleChannel NETWORK = null;
    public static void registerPackets(FMLCommonSetupEvent event) {
        if (NETWORK != null) return;
        NETWORK = ChannelBuilder
                .named(ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "lightsaber_form_sync"))
                .clientAcceptedVersions((version, netVersion) -> true) // accept any
                .serverAcceptedVersions((version, netVersion) -> true) // accept any
                .simpleChannel();
        NETWORK.messageBuilder(SyncLightsaberFormPacket.class, 0)
                .encoder(SyncLightsaberFormPacket::encode)
                .decoder(SyncLightsaberFormPacket::decode)
                .consumerMainThread(SyncLightsaberFormPacket::handle)
                .add();

        NETWORK.messageBuilder(SwitchLightsaberFormPacket.class, 1)
                .encoder(SwitchLightsaberFormPacket::encode)
                .decoder(SwitchLightsaberFormPacket::decode)
                .consumerMainThread(SwitchLightsaberFormPacket::handle)
                .add();
//        NETWORK.messageBuilder(ToggleLightsaberPacket.class, 2)
//                .encoder(ToggleLightsaberPacket::encode)
//                .decoder(ToggleLightsaberPacket::decode)
//                .consumerMainThread(ToggleLightsaberPacket::handle)
//                .add();
        NETWORK.build();
    }
    public static void sendToServer(Object message) {
        if (Minecraft.getInstance().getConnection() == null) return;

        // The logical play‑connection that SimpleChannel wants
        Connection connection = Minecraft.getInstance()
                .getConnection()
                .getConnection();

        // SimpleChannel has an overload that takes (msg, Connection)
        NETWORK.send(message, connection);
    }
 static void sendToPlayer(ServerPlayer player, SyncLightsaberFormPacket packet) {
        NETWORK.send(packet, player.connection.getConnection());
    }
}
