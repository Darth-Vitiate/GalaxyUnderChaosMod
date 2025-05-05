package server.galaxyunderchaos.lightsaber;

import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import server.galaxyunderchaos.galaxyunderchaos;

import java.util.List;

public class SyncLightsaberFormPacket implements CustomPacketPayload {
    public static final ResourceLocation ID =
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "sync_lightsaber_form");
    public static final Type<SyncLightsaberFormPacket> TYPE = new Type<>(ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncLightsaberFormPacket>
            STREAM_CODEC = StreamCodec.of(SyncLightsaberFormPacket::encode, SyncLightsaberFormPacket::decode);

    private final List<String> unlocked;
    private final String selected;

    public SyncLightsaberFormPacket(List<String> unlocked, String selected) {
        this.unlocked = List.copyOf(unlocked);
        this.selected = selected;
    }

    public static void encode(RegistryFriendlyByteBuf buf, SyncLightsaberFormPacket pkt) {
        buf.writeVarInt(pkt.unlocked.size());
        pkt.unlocked.forEach(buf::writeUtf);
        buf.writeUtf(pkt.selected);
    }

    public static SyncLightsaberFormPacket decode(RegistryFriendlyByteBuf buf) {
        int size = buf.readVarInt();
        List<String> unlocked = new java.util.ArrayList<>(size);
        for (int i = 0; i < size; i++) unlocked.add(buf.readUtf());
        String selected = buf.readUtf();
        return new SyncLightsaberFormPacket(unlocked, selected);
    }

    public static void handle(SyncLightsaberFormPacket pkt, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            var player = Minecraft.getInstance().player;
            if (player == null) return;

            var cap = player.getCapability(LightsaberFormCapability.CAPABILITY);
            if (cap == null) return;

            CompoundTag tag = new CompoundTag();
            ListTag list = new ListTag();
            for (String form : pkt.unlocked) {
                list.add(StringTag.valueOf(form));
            }
            tag.put("UnlockedForms", list);
            tag.putString("SelectedForm", pkt.selected);

            HolderLookup.Provider lookup = Minecraft.getInstance().level.registryAccess(); // ← Correct HolderLookup.Provider
            cap.deserializeNBT(lookup, tag);
        });
    }


    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }

    @Override
    public ClientboundCustomPayloadPacket toVanillaClientbound() {
        return CustomPacketPayload.super.toVanillaClientbound();
    }
}
