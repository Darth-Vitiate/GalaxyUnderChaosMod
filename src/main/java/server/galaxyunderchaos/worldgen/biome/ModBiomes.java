package server.galaxyunderchaos.worldgen.biome;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import server.galaxyunderchaos.galaxyunderchaos;

public class ModBiomes {
    public static final ResourceKey<Biome> NABOO_BIOME = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "naboo_biome"));
    public static final ResourceKey<Biome> ILUM_BIOME = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ilum_biome"));
    public static final ResourceKey<Biome> ILUM_BIOME_FOREST = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ilum_biome_forest"));
    public static final ResourceKey<Biome> MUSTAFAR_LAVA_FIELD = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "mustafar_lava_field"));
    public static final ResourceKey<Biome> MUSTAFAR_VOLCANIC_PLAINS = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "mustafar_volcanic_plains"));
    public static final ResourceKey<Biome> MUSTAFAR_MAGMA_LAKE = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "mustafar_magma_lake"));
    public static final ResourceKey<Biome> OSSUS_FOREST = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ossus_forest"));
    public static final ResourceKey<Biome> OSSUS_PLAINS = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ossus_plains"));
    public static final ResourceKey<Biome> OSSUS_MOUNTAINS = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ossus_mountains"));
    public static final ResourceKey<Biome> OSSUS_OCEAN = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ossus_ocean"));
    public static final ResourceKey<Biome> OSSUS_DEEP_OCEAN = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ossus_deep_ocean"));
    public static final ResourceKey<Biome> MALACHOR_UPPER_LAYER = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "malachor_upper_layer"));
    public static final ResourceKey<Biome> MALACHOR_LOWER_SURFACE = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "malachor_lower_surface"));

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, "galaxyunderchaos");

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(NABOO_BIOME, createNabooBiome(context));
        context.register(ILUM_BIOME, createIlumBiome(context));
        context.register(ILUM_BIOME_FOREST, createIlumBiomeForest(context));
        context.register(MUSTAFAR_LAVA_FIELD, createMustafarLavaField(context));
        context.register(MUSTAFAR_VOLCANIC_PLAINS, createMustafarVolcanicPlains(context));
        context.register(MUSTAFAR_MAGMA_LAKE, createMustafarMagmaLake(context));
        context.register(OSSUS_FOREST, createOssusForest(context));
        context.register(OSSUS_PLAINS, createOssusPlains(context));
        context.register(OSSUS_MOUNTAINS, createOssusMountains(context));
        context.register(OSSUS_OCEAN, createOssusOcean(context));
        context.register(OSSUS_DEEP_OCEAN, createOssusDeepOcean(context));
        context.register(MALACHOR_UPPER_LAYER, createMalachorUpperLayer(context));
        context.register(MALACHOR_LOWER_SURFACE, createMalachorLowerSurface(context));
    }
    private static Biome createMalachorUpperLayer(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverHolder = context.lookup(Registries.CONFIGURED_CARVER);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(-0.8f)
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatureHolder, carverHolder).build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .fogColor(0x1A1A1A)
                        .skyColor(0x2C2F33)
                        .build())
                .build();
    }

    private static Biome createMalachorLowerSurface(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverHolder = context.lookup(Registries.CONFIGURED_CARVER);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(0.0f)
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatureHolder, carverHolder).build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .fogColor(0x3D3D3D)
                        .skyColor(0x1C1C1C)
                        .build())
                .build();
    }
    private static Biome createOssusOcean(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverHolder = context.lookup(Registries.CONFIGURED_CARVER);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.5f)
                .downfall(0.8f)
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatureHolder, carverHolder).build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x5C5C5D) // 6050877
                        .waterFogColor(0x5C5C5D) // 6050877
                        .skyColor(0x92949A) // 9613498
                        .grassColorOverride(0x535745) // 5458976
                        .foliageColorOverride(0x575E4E) // 5722926
                        .fogColor(0xD6E7FF) // 14083839
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    private static Biome createOssusDeepOcean(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverHolder = context.lookup(Registries.CONFIGURED_CARVER);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5f)
                .downfall(0.8f)
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatureHolder, carverHolder).build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x5C5C5D) // 6050877
                        .waterFogColor(0x5C5C5D) // 6050877
                        .skyColor(0x92949A) // 9613498
                        .grassColorOverride(0x535745) // 5458976
                        .foliageColorOverride(0x575E4E) // 5722926
                        .fogColor(0xD6E7FF) // 14083839
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }
    private static Biome createOssusForest(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverHolder = context.lookup(Registries.CONFIGURED_CARVER);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.7f)
                .temperature(0.6f)
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatureHolder, carverHolder).build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x5C5C5D) // 6050877
                        .waterFogColor(0x5C5C5D) // 6050877
                        .skyColor(0x92949A) // 9613498
                        .grassColorOverride(0x535745) // 5458976
                        .foliageColorOverride(0x575E4E) // 5722926
                        .fogColor(0xD6E7FF) // 14083839
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    private static Biome createOssusPlains(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverHolder = context.lookup(Registries.CONFIGURED_CARVER);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.9f)
                .temperature(0.95f)
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatureHolder, carverHolder).build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x5C5C5D) // 6050877
                        .waterFogColor(0x5C5C5D) // 6050877
                        .skyColor(0x92949A) // 9613498
                        .grassColorOverride(0x535745) // 5458976
                        .foliageColorOverride(0x575E4E) // 5722926
                        .fogColor(0xD6E7FF) // 14083839
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }


    private static Biome createOssusMountains(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverHolder = context.lookup(Registries.CONFIGURED_CARVER);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.5f)
                .temperature(0.4f)
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatureHolder, carverHolder).build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x5C5C5D) // 6050877
                        .waterFogColor(0x5C5C5D) // 6050877
                        .skyColor(0x92949A) // 9613498
                        .grassColorOverride(0x535745) // 5458976
                        .foliageColorOverride(0x575E4E) // 5722926
                        .fogColor(0xD6E7FF) // 14083839
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
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
    private static Biome createIlumBiome(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverHolder = context.lookup(Registries.CONFIGURED_CARVER);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.9f) // Matching JSON value
                .temperature(-1.0f) // Matching JSON value
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatureHolder, carverHolder).build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xCCB5A3) // 13622258 in decimal
                        .waterFogColor(0xAC8A0F) // 11250623 in decimal
                        .skyColor(0xFACA83) // 15462399 in decimal
                        .grassColorOverride(0xB0B0B0) // Light gray for snowy terrain
                        .foliageColorOverride(0xA0A0A0) // Slightly darker gray for foliage
                        .fogColor(0xF0F0F0) // Light gray fog color
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }
    private static Biome createIlumBiomeForest(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverHolder = context.lookup(Registries.CONFIGURED_CARVER);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.9f) // Matching JSON value
                .temperature(-1.0f) // Matching JSON value
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatureHolder, carverHolder).build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xCCB5A3) // 13622258 in decimal
                        .waterFogColor(0xAC8A0F) // 11250623 in decimal
                        .skyColor(0xFACA83) // 15462399 in decimal
                        .grassColorOverride(0xB0B0B0) // Light gray for snowy terrain
                        .foliageColorOverride(0xA0A0A0) // Slightly darker gray for foliage
                        .fogColor(0xF0F0F0) // Light gray fog color
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }
    private static Biome createMustafarLavaField(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverHolder = context.lookup(Registries.CONFIGURED_CARVER);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(2.0f)
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatureHolder, carverHolder).build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xFF4500)
                        .waterFogColor(0x8B0000)
                        .skyColor(0xAA3300)
                        .fogColor(0xCC1100)
                        .build())
                .build();
    }

    private static Biome createMustafarVolcanicPlains(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverHolder = context.lookup(Registries.CONFIGURED_CARVER);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(1.5f)
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatureHolder, carverHolder).build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x8B0000)
                        .waterFogColor(0x550000)
                        .skyColor(0xFF3300)
                        .fogColor(0xAA1100)
                        .build())
                .build();
    }

    private static Biome createMustafarMagmaLake(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carverHolder = context.lookup(Registries.CONFIGURED_CARVER);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(2.5f)
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatureHolder, carverHolder).build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xFF6347)
                        .waterFogColor(0xB22222)
                        .skyColor(0xFF4500)
                        .fogColor(0x800000)
                        .build())
                .build();
    }
}