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

    public static final ResourceKey<LevelStem> OSSUS_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ossus"));
    public static final ResourceKey<Level> OSSUS_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ossus"));
    public static final ResourceKey<DimensionType> OSSUS_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ossus_type"));
    public static final ResourceKey<LevelStem> ASHLA_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ashla"));
    public static final ResourceKey<Level> ASHLA_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ashla"));
    public static final ResourceKey<DimensionType> ASHLA_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ashla_type"));

    public static final ResourceKey<LevelStem> BOGAN_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "bogan"));
    public static final ResourceKey<Level> BOGAN_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "bogan"));
    public static final ResourceKey<DimensionType> BOGAN_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "bogan_type"));

    public static final ResourceKey<LevelStem> MALACHOR_KEY = ResourceKey.create(
            Registries.LEVEL_STEM, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "malachor"));
    public static final ResourceKey<Level> MALACHOR_LEVEL_KEY = ResourceKey.create(
            Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "malachor"));
    public static final ResourceKey<DimensionType> MALACHOR_DIM_TYPE = ResourceKey.create(
            Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "malachor_type"));

    public static final ResourceKey<LevelStem> KORRIBAN_KEY = ResourceKey.create(
            Registries.LEVEL_STEM, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "korriban"));
    public static final ResourceKey<Level> KORRIBAN_LEVEL_KEY = ResourceKey.create(
            Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "korriban"));
    public static final ResourceKey<DimensionType> KORRIBAN_DIM_TYPE = ResourceKey.create(
            Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "korriban_type"));
    public static final ResourceKey<LevelStem> DANTOOINE_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "dantooine"));
    public static final ResourceKey<Level> DANTOOINE_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "dantooine"));
    public static final ResourceKey<DimensionType> DANTOOINE_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "dantooine_type"));


    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        context.register(TYTHON_DIM_TYPE, new DimensionType(
                OptionalLong.empty(), true, false, false, true, 1.0, true, true,
                -64, 320, 256,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, 0.8f, new DimensionType.MonsterSettings(false, true, ConstantInt.of(7), 0) // Monster spawn rules
        ));
            context.register(DANTOOINE_DIM_TYPE, new DimensionType(
                    OptionalLong.empty(),
                    true, false, false, true, 1.0, true, true,
                    -64, 384, 384,
                    BlockTags.INFINIBURN_OVERWORLD,
                    BuiltinDimensionTypes.OVERWORLD_EFFECTS, 1.0f, new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0) // Monster spawning rules
            ));
        context.register(NABOO_DIM_TYPE, new DimensionType(
                OptionalLong.empty(), true, false, false, true, 1.0, true, true,
                -64, 384, 384,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, 1.0f, new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
        context.register(MUSTAFAR_DIM_TYPE, new DimensionType(
                OptionalLong.empty(), true, false, false, true, 1.0, true, true,
                -64, 384, 384,
                BlockTags.INFINIBURN_NETHER,
                BuiltinDimensionTypes.NETHER_EFFECTS, 1.0f,
                new DimensionType.MonsterSettings(true, false, ConstantInt.of(10), 8)));
        context.register(OSSUS_DIM_TYPE, new DimensionType(
                OptionalLong.empty(), true, false, false, true, 1.0, true, true,
                -64, 384, 384,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, 1.0f,
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)
        ));
        context.register(MALACHOR_DIM_TYPE, new DimensionType(
                OptionalLong.of(18000), false, false, false, true, 1.0, true, true,
                -64, 384, 256,
                BlockTags.INFINIBURN_NETHER,
                BuiltinDimensionTypes.NETHER_EFFECTS, 0.05f,
                new DimensionType.MonsterSettings(true, false, ConstantInt.of(7), 0)
        ));
        context.register(ILUM_DIM_TYPE, new DimensionType(
                OptionalLong.empty(), true, false, false, true, 1.0, true, true,
                -64, 384, 384, // Adjust height and Y min as needed
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // Change this to create a different environment feel
                1.0f, new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)
        ));
        context.register(KORRIBAN_DIM_TYPE, new DimensionType(
                OptionalLong.of(18000), false, false, false, true, 1.0, true, true,
                -64, 384, 256,
                BlockTags.INFINIBURN_NETHER, // Use NETHER infiniburn for Sith-like ambiance
                BuiltinDimensionTypes.NETHER_EFFECTS, // Sith ambiance effects
                0.1f, // Ambient light for a dark and foreboding atmosphere
                new DimensionType.MonsterSettings(true, false, ConstantInt.of(7), 0)
        ));
            context.register(ASHLA_DIM_TYPE, new DimensionType(
                    OptionalLong.empty(), true, false, false, true, 1.0, true, true,
                    -64, 384, 384, // Adjust height and Y min as needed
                    BlockTags.INFINIBURN_OVERWORLD,
                    BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                    1.0f,
                    new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)
            ));

            context.register(BOGAN_DIM_TYPE, new DimensionType(
                    OptionalLong.of(18000), false, false, false, true, 1.0, true, true,
                    -64, 384, 256,
                    BlockTags.INFINIBURN_NETHER, // Use Nether infiniburn for dark ambiance
                    BuiltinDimensionTypes.NETHER_EFFECTS,
                    0.05f, // Low ambient light for darkness
                    new DimensionType.MonsterSettings(true, false, ConstantInt.of(10), 0)
            ));
        }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);
        long nabooSeed = 123456789L;
        long ilumSeed = 987654321L;
        long korribanSeed = 456789123L;
        long malachorSeed = 789123456L;
        long mustafarSeed = 654321987L;
        long dantooineSeed = 321987654L;
        long ossusSeed = 159753258L;
        long tythonSeed = 753951852L;

        NoiseBasedChunkGenerator tythonChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(
                                        Climate.parameters(0.7F, 0.8F, 0.3F, 0.4F, 0.1F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.TYTHON_FOREST)),
                                Pair.of(
                                        Climate.parameters(0.8F, 0.6F, 0.5F, 0.3F, 0.0F, 0.0F, 0.1F),
                                        biomeRegistry.getOrThrow(ModBiomes.TYTHON_PLAINS)),
                                Pair.of(
                                        Climate.parameters(0.5F, 0.3F, 0.7F, 0.2F, 0.0F, 0.1F, 0.1F),
                                        biomeRegistry.getOrThrow(ModBiomes.TYTHON_MOUNTAINS))
                        ))
                ),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD)
        );

        LevelStem tythonStem = new LevelStem(dimTypes.getOrThrow(TYTHON_DIM_TYPE), tythonChunkGenerator);
        context.register(TYTHON_KEY, tythonStem);
        NoiseBasedChunkGenerator dantooineChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(
                                        Climate.parameters(0.8F, 0.7F, 0.3F, 0.4F, 0.1F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.DANTOOINE_PLAINS)),
                                Pair.of(
                                        Climate.parameters(0.6F, 0.6F, 0.5F, 0.3F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.DANTOOINE_FOREST)),
                                Pair.of(
                                        Climate.parameters(0.4F, 0.5F, 0.7F, 0.2F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.DANTOOINE_HILLS))
                        ))
                ),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD)
        );
        LevelStem dantooineStem = new LevelStem(dimTypes.getOrThrow(ModDimensions.DANTOOINE_DIM_TYPE), dantooineChunkGenerator);
        context.register(DANTOOINE_KEY, dantooineStem);

        NoiseBasedChunkGenerator nabooChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(
                                        Climate.parameters(0.9F, 0.9F, 0.2F, 0.4F, 0.05F, 0.2F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.NABOO_SWAMP)),
                                Pair.of(
                                        Climate.parameters(0.8F, 0.4F, 0.6F, 0.3F, 0.1F, 0.1F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.NABOO_PLAINS)),
                                Pair.of(
                                        Climate.parameters(0.7F, 0.7F, 0.3F, 0.2F, 0.125F, 0.3F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.NABOO_BIOME)),
                                Pair.of(
                                        Climate.parameters(0.3F, 0.7F, 0.6F, 0.2F, -0.2F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.NABOO_OCEAN))
                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD)
        );

        LevelStem nabooStem = new LevelStem(dimTypes.getOrThrow(ModDimensions.NABOO_DIM_TYPE), nabooChunkGenerator);
        context.register(NABOO_KEY, nabooStem);

        NoiseBasedChunkGenerator korribanChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(
                                        Climate.parameters(1.2F, 0.0F, 0.5F, 0.5F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.KORRIBAN_DRY_CANYON)),
                                Pair.of(
                                        Climate.parameters(1.0F, -0.2F, 0.7F, 0.4F, 0.1F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.KORRIBAN_SITH_TOMB))
                        ))
                ),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.NETHER) // Adjust settings if needed
        );

        LevelStem korribanStem = new LevelStem(dimTypes.getOrThrow(ModDimensions.KORRIBAN_DIM_TYPE),
                korribanChunkGenerator);
        context.register(KORRIBAN_KEY, korribanStem);


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

        NoiseBasedChunkGenerator ossusChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(
                                        Climate.parameters(0.3F, 0.5F, 0.2F, 0.1F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.OSSUS_FOREST)),
                                Pair.of(
                                        Climate.parameters(0.2F, 0.4F, 0.1F, 0.0F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.OSSUS_PLAINS)),
                                Pair.of(
                                        Climate.parameters(0.1F, 0.3F, 0.1F, 0.0F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.OSSUS_MOUNTAINS)),
                                Pair.of(
                                        Climate.parameters(0.5F, 0.6F, -0.2F, 0.2F, -0.3F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.OSSUS_OCEAN)),
                                Pair.of(
                                        Climate.parameters(0.5F, 0.7F, -0.3F, 0.1F, -0.5F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.OSSUS_DEEP_OCEAN))
                        ))
                ),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD)
        );
        LevelStem ossusStem = new LevelStem(dimTypes.getOrThrow(ModDimensions.OSSUS_DIM_TYPE), ossusChunkGenerator);
        context.register(OSSUS_KEY, ossusStem);

        MultiNoiseBiomeSource biomeSource = MultiNoiseBiomeSource.createFromList(
                new Climate.ParameterList<>(List.of(
                        Pair.of(
                                Climate.parameters(-0.8F, 0.4F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F),
                                biomeRegistry.getOrThrow(ModBiomes.MALACHOR_UPPER_LAYER)
                        ),
                        Pair.of(
                                Climate.parameters(0.0F, 0.2F, 0.3F, 0.1F, 0.0F, 0.0F, 0.0F),
                                biomeRegistry.getOrThrow(ModBiomes.MALACHOR_LOWER_SURFACE)
                        )
                ))
        );

        NoiseBasedChunkGenerator malachorChunkGenerator = new NoiseBasedChunkGenerator(biomeSource,
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.NETHER)
        );

        LevelStem malachorStem = new LevelStem(dimTypes.getOrThrow(MALACHOR_DIM_TYPE),
                malachorChunkGenerator);
        context.register(MALACHOR_KEY, malachorStem);
            NoiseBasedChunkGenerator ashlaChunkGenerator = new NoiseBasedChunkGenerator(
                    MultiNoiseBiomeSource.createFromList(
                            new Climate.ParameterList<>(List.of(
                                    Pair.of(Climate.parameters(1.0F, 0.5F, 0.2F, 0.3F, 0.1F, 0.0F, 0.0F),
                                            biomeRegistry.getOrThrow(ModBiomes.ASHLA_BIOME))
                            ))
                    ),
                    noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD)
            );

            LevelStem ashlaStem = new LevelStem(dimTypes.getOrThrow(ASHLA_DIM_TYPE), ashlaChunkGenerator);
            context.register(ASHLA_KEY, ashlaStem);

            NoiseBasedChunkGenerator boganChunkGenerator = new NoiseBasedChunkGenerator(
                    MultiNoiseBiomeSource.createFromList(
                            new Climate.ParameterList<>(List.of(
                                    Pair.of(Climate.parameters(0.0F, -0.5F, 0.4F, 0.7F, 0.3F, 0.2F, 0.1F),
                                            biomeRegistry.getOrThrow(ModBiomes.BOGAN_BIOME))
                            ))
                    ),
                    noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD)
            );

            LevelStem boganStem = new LevelStem(dimTypes.getOrThrow(BOGAN_DIM_TYPE), boganChunkGenerator);
            context.register(BOGAN_KEY, boganStem);
        }
}