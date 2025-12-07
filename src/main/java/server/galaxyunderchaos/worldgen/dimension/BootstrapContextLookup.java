package server.galaxyunderchaos.worldgen.dimension;

import com.mojang.serialization.Lifecycle;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;

import java.util.Optional;

public record BootstrapContextLookup(BootstrapContext<?> context) implements RegistryOps.RegistryInfoLookup {

    @Override
    public <T> Optional<RegistryOps.RegistryInfo<T>> lookup(ResourceKey<? extends Registry<? extends T>> key) {
        HolderGetter<T> getter = (HolderGetter<T>) context.lookup((ResourceKey) key);
        HolderOwner<T> owner = new HolderOwner<>() {};
        return Optional.of(new RegistryOps.RegistryInfo<>(owner, getter, Lifecycle.stable())
        );
    }
}
