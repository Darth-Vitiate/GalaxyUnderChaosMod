package server.galaxyunderchaos.worldgen.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import server.galaxyunderchaos.galaxyunderchaos;

public class ModBiomes {
    public static final ResourceKey<Biome> NABOO_BIOME = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "naboo_biome"));

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, "galaxyunderchaos");


    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(NABOO_BIOME, createNabooBiome(context));
    }
    private static Biome createNabooBiome(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverHolder = context.lookup(Registries.CONFIGURED_CARVER);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.9f)
                .temperature(0.8f)
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatureHolder, carverHolder).build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3C88DA)
                        .waterFogColor(0x050533)
                        .skyColor(0x87CEEB)
                        .grassColorOverride(0x91BD59)
                        .foliageColorOverride(0x77AB59)
                        .fogColor(0xC0D8FF)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }
}