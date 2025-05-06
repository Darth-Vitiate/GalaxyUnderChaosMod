package server.galaxyunderchaos.lightsaber;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class LightsaberFormProvider implements ICapabilityProvider<Player, Void, LightsaberFormCapability>, INBTSerializable<CompoundTag> {
    private final LightsaberFormCapability inner = new LightsaberFormCapability();

    public LightsaberFormProvider() {
        // Give every new player the default form:
        inner.unlockForm("Shii-Cho");
        inner.setSelectedForm("Shii-Cho");
    }

    @Override
    public LightsaberFormCapability getCapability(Player player, Void unused) {
        return inner;
    }

    @Override
    public CompoundTag serializeNBT(@Nonnull HolderLookup.Provider provider) {
        return inner.serializeNBT(provider);
    }

    @Override
    public void deserializeNBT(@Nonnull HolderLookup.Provider provider, CompoundTag tag) {
        inner.deserializeNBT(provider, tag);
    }
}