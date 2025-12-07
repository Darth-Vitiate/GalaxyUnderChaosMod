package server.galaxyunderchaos.worldgen.dimension;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import server.galaxyunderchaos.galaxyunderchaos;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.Objects;

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
    public static final ResourceKey<NoiseGeneratorSettings> TYTHON_NOISE = ResourceKey.create(Registries.NOISE_SETTINGS,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "tython_noise_settings"));
    public static final ResourceKey<NoiseGeneratorSettings> NABOO_NOISE = ResourceKey.create(Registries.NOISE_SETTINGS,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "naboo_noise_settings"));
    public static final ResourceKey<NoiseGeneratorSettings> ILUM_NOISE = ResourceKey.create(Registries.NOISE_SETTINGS,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ilum_noise_settings"));
    public static final ResourceKey<NoiseGeneratorSettings> MUSTAFAR_NOISE = ResourceKey.create(Registries.NOISE_SETTINGS,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "mustafar_noise_settings"));
    public static final ResourceKey<NoiseGeneratorSettings> OSSUS_NOISE = ResourceKey.create(Registries.NOISE_SETTINGS,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ossus_noise_settings"));
    public static final ResourceKey<NoiseGeneratorSettings> ASHLA_NOISE = ResourceKey.create(Registries.NOISE_SETTINGS,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ashla_noise_settings"));
    public static final ResourceKey<NoiseGeneratorSettings> BOGAN_NOISE = ResourceKey.create(Registries.NOISE_SETTINGS,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "bogan_noise_settings"));
    public static final ResourceKey<NoiseGeneratorSettings> MALACHOR_NOISE = ResourceKey.create(Registries.NOISE_SETTINGS,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "malachor_noise_settings"));
    public static final ResourceKey<NoiseGeneratorSettings> KORRIBAN_NOISE = ResourceKey.create(Registries.NOISE_SETTINGS,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "korriban_noise_settings"));
    public static final ResourceKey<NoiseGeneratorSettings> DANTOOINE_NOISE = ResourceKey.create(Registries.NOISE_SETTINGS,
            ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "dantooine_noise_settings"));

    private static final Map<ResourceKey<DimensionType>, String> DIMENSION_TYPE_FILES = Map.ofEntries(
            Map.entry(TYTHON_DIM_TYPE, "tython_type"),
            Map.entry(DANTOOINE_DIM_TYPE, "dantooine_type"),
            Map.entry(NABOO_DIM_TYPE, "naboo_type"),
            Map.entry(MUSTAFAR_DIM_TYPE, "mustafar_type"),
            Map.entry(OSSUS_DIM_TYPE, "ossus_type"),
            Map.entry(MALACHOR_DIM_TYPE, "malachor_type"),
            Map.entry(ILUM_DIM_TYPE, "ilum_type"),
            Map.entry(KORRIBAN_DIM_TYPE, "korriban_type"),
            Map.entry(ASHLA_DIM_TYPE, "ashla_type"),
            Map.entry(BOGAN_DIM_TYPE, "bogan_type")
    );

    private static final Map<ResourceKey<LevelStem>, String> LEVEL_STEM_FILES = Map.ofEntries(
            Map.entry(TYTHON_KEY, "tython"),
            Map.entry(DANTOOINE_KEY, "dantooine"),
            Map.entry(NABOO_KEY, "naboo"),
            Map.entry(KORRIBAN_KEY, "korriban"),
            Map.entry(ILUM_KEY, "ilum"),
            Map.entry(MUSTAFAR_KEY, "mustafar"),
            Map.entry(OSSUS_KEY, "ossus"),
            Map.entry(MALACHOR_KEY, "malachor"),
            Map.entry(ASHLA_KEY, "ashla"),
            Map.entry(BOGAN_KEY, "bogan")
    );

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        DIMENSION_TYPE_FILES.forEach((key, file) -> context.register(key, loadDimensionType(context, file)));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        LEVEL_STEM_FILES.forEach((key, file) -> context.register(key, loadLevelStem(context, file)));
    }

    private static DimensionType loadDimensionType(BootstrapContext<DimensionType> context, String fileName) {
        ResourceLocation resource = ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID,
                "dimension_type/" + fileName + ".json");
        JsonElement json = readJson(resource);
        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, context.lookupProvider());
        DataResult<DimensionType> parsed = DimensionType.DIRECT_CODEC.parse(ops, json);
        return parsed.getOrThrow(false, (message) ->
                galaxyunderchaos.LOGGER.error("Failed to parse dimension type {}: {}", resource, message));
    }

    private static LevelStem loadLevelStem(BootstrapContext<LevelStem> context, String fileName) {
        ResourceLocation resource = ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID,
                "dimension/" + fileName + ".json");
        JsonElement json = readJson(resource);
        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, context.lookupProvider());
        DataResult<LevelStem> parsed = LevelStem.CODEC.parse(ops, json);
        return parsed.getOrThrow(false, (message) ->
                galaxyunderchaos.LOGGER.error("Failed to parse level stem {}: {}", resource, message));
    }

    private static JsonElement readJson(ResourceLocation resource) {
        String resourcePath = "/data/" + resource.getNamespace() + "/" + resource.getPath();
        try (InputStream stream = Objects.requireNonNull(ModDimensions.class.getResourceAsStream(resourcePath),
                "Missing dimension resource: " + resourcePath);
             Reader reader = new InputStreamReader(stream)) {
            return JsonParser.parseReader(reader);
        } catch (Exception exception) {
            throw new IllegalStateException("Unable to load dimension data from " + resourcePath, exception);
        }
    }
}
