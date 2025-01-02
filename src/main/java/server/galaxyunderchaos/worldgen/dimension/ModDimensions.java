package server.galaxyunderchaos.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import server.galaxyunderchaos.galaxyunderchaos;
import server.galaxyunderchaos.worldgen.biome.ModBiomes;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensions {
    public static final ResourceKey<LevelStem> TYTHON_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "tython"));
    public static final ResourceKey<Level> TYTHON_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "tython"));
    public static final ResourceKey<DimensionType> TYTHON_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "tython_type"));

    public static final ResourceKey<LevelStem> NABOO_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "naboo"));
    public static final ResourceKey<Level> NABOO_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "naboo"));
    public static final ResourceKey<DimensionType> NABOO_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "naboo_type"));

    public static final ResourceKey<LevelStem> ILUM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ilum"));
    public static final ResourceKey<Level> ILUM_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ilum"));
    public static final ResourceKey<DimensionType> ILUM_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ilum_type"));

    public static final ResourceKey<LevelStem> MUSTAFAR_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "mustafar"));
    public static final ResourceKey<Level> MUSTAFAR_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "mustafar"));
    public static final ResourceKey<DimensionType> MUSTAFAR_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "mustafar_type"));


    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        context.register(TYTHON_DIM_TYPE, new DimensionType(
                OptionalLong.empty(), true, false, false, true, 1.0, true, true,
                -64, 384, 384,
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, 1.0f, new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));

        context.register(NABOO_DIM_TYPE, new DimensionType(
                OptionalLong.empty(), true, false, false, true, 1.0, true, true,
                -64, 384, 384,
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, 1.0f, new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
        context.register(MUSTAFAR_DIM_TYPE, new DimensionType(
                OptionalLong.empty(), true, false, false, true, 1.0, true, true,
                -64, 384, 384,
                BlockTags.INFINIBURN_NETHER,
                BuiltinDimensionTypes.NETHER_EFFECTS, 1.0f,
                new DimensionType.MonsterSettings(true, false, ConstantInt.of(10), 8)));

        context.register(ILUM_DIM_TYPE, new DimensionType(
                OptionalLong.empty(), true, false, false, true, 1.0, true, true,
                -64, 384, 384, // Adjust height and Y min as needed
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // Change this to create a different environment feel
                1.0f, new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)
        ));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator tythonChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(
                                        Climate.parameters(0.1F, 0.2F, 0.0F, 0.2F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA)),
                                Pair.of(
                                        Climate.parameters(0.3F, 0.6F, 0.1F, 0.1F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.OCEAN)),
                                Pair.of(
                                        Climate.parameters(0.1F, 0.2F, 0.0F, 0.2F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.PLAINS)),
                                Pair.of(
                                        Climate.parameters(0.3F, 0.6F, 0.1F, 0.1F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.DEEP_OCEAN)),
                                Pair.of(
                                        Climate.parameters(0.3F, 0.6F, 0.1F, 0.1F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.WARM_OCEAN)),
                                Pair.of(
                                        Climate.parameters(0.3F, 0.6F, 0.1F, 0.1F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.DEEP_LUKEWARM_OCEAN)),
                                Pair.of(
                                        Climate.parameters(0.3F, 0.6F, 0.1F, 0.1F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.LUKEWARM_OCEAN)),
                                Pair.of(
                                        Climate.parameters(0.4F, 0.3F, 0.2F, 0.1F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.WINDSWEPT_HILLS))
                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD));

        LevelStem tythonStem = new LevelStem(dimTypes.getOrThrow(ModDimensions.TYTHON_DIM_TYPE), tythonChunkGenerator);
        context.register(TYTHON_KEY, tythonStem);

        NoiseBasedChunkGenerator nabooChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(
                                        Climate.parameters(0.1F, 0.2F, 0.3F, 0.4F, 0.2F, 0.1F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.NABOO_BIOME)), // Custom biome
                                Pair.of(
                                        Climate.parameters(0.3F, 0.7F, 0.6F, 0.2F, -0.2F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.OCEAN)),
                                Pair.of(
                                        Climate.parameters(0.7F, 0.9F, 0.4F, 0.3F, -0.1F, 0.2F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.WARM_OCEAN)),
                                Pair.of(
                                        Climate.parameters(0.4F, 0.4F, 0.5F, 0.2F, 0.3F, 0.1F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.FOREST)),
                                Pair.of(
                                        Climate.parameters(0.6F, 0.6F, 0.7F, 0.3F, 0.4F, 0.2F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.FLOWER_FOREST)),
                                Pair.of(
                                        Climate.parameters(0.5F, 0.8F, 0.3F, 0.5F, -0.1F, 0.1F, 0.0F),
                                        biomeRegistry.getOrThrow(Biomes.LUKEWARM_OCEAN))
                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD));

        LevelStem nabooStem = new LevelStem(dimTypes.getOrThrow(ModDimensions.NABOO_DIM_TYPE), nabooChunkGenerator);
        context.register(NABOO_KEY, nabooStem);

        NoiseBasedChunkGenerator ilumChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(
                                        Climate.parameters(-1.0F, 0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.ILUM_BIOME)),
                                Pair.of(
                                        Climate.parameters(-0.8F, 1.0F, 0.2F, 0.1F, 0.2F, 0.1F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.ILUM_BIOME_FOREST))
                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.LARGE_BIOMES));

        LevelStem ilumStem = new LevelStem(dimTypes.getOrThrow(ModDimensions.ILUM_DIM_TYPE), ilumChunkGenerator);
        context.register(ILUM_KEY, ilumStem);

        NoiseBasedChunkGenerator mustafarChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(
                                        Climate.parameters(-0.5F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.MUSTAFAR_LAVA_FIELD)),
                                Pair.of(
                                        Climate.parameters(0.0F, 0.8F, 0.2F, 0.0F, 0.1F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.MUSTAFAR_VOLCANIC_PLAINS)),
                                Pair.of(
                                        Climate.parameters(0.2F, 0.7F, 0.3F, 0.1F, 0.2F, 0.1F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.MUSTAFAR_MAGMA_LAKE))
                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD));

        LevelStem mustafarStem = new LevelStem(dimTypes.getOrThrow(ModDimensions.MUSTAFAR_DIM_TYPE), mustafarChunkGenerator);
        context.register(MUSTAFAR_KEY, mustafarStem);
    }
}