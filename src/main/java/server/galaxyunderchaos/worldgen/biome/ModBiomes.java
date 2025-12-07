package server.galaxyunderchaos.worldgen.biome;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import server.galaxyunderchaos.galaxyunderchaos;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.Objects;

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
    public static final ResourceKey<Biome> NABOO_SWAMP = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "naboo_swamp"));
    public static final ResourceKey<Biome> NABOO_PLAINS = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "naboo_plains"));
    public static final ResourceKey<Biome> NABOO_OCEAN = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "naboo_ocean"));
    public static final ResourceKey<Biome> KORRIBAN_DRY_CANYON = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "korriban_dry_canyon"));
    public static final ResourceKey<Biome> KORRIBAN_SITH_TOMB = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "korriban_sith_tomb"));
    public static final ResourceKey<Biome> TYTHON_FOREST = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "tython_forest"));
    public static final ResourceKey<Biome> TYTHON_PLAINS = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "tython_plains"));
    public static final ResourceKey<Biome> TYTHON_MOUNTAINS = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "tython_mountains"));
    public static final ResourceKey<Biome> DANTOOINE_PLAINS = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "dantooine_plains"));
    public static final ResourceKey<Biome> DANTOOINE_FOREST = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "dantooine_forest"));
    public static final ResourceKey<Biome> DANTOOINE_HILLS = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "dantooine_hills"));
    public static final ResourceKey<Biome> ASHLA_BIOME = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ashla_biome"));
    public static final ResourceKey<Biome> BOGAN_BIOME = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "bogan_biome"));

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, "galaxyunderchaos");

    private static final Map<ResourceKey<Biome>, String> BIOME_FILES = Map.ofEntries(
            Map.entry(NABOO_BIOME, "naboo_biome"),
            Map.entry(ILUM_BIOME, "ilum_biome"),
            Map.entry(ILUM_BIOME_FOREST, "ilum_biome_forest"),
            Map.entry(MUSTAFAR_LAVA_FIELD, "mustafar_lava_field"),
            Map.entry(MUSTAFAR_VOLCANIC_PLAINS, "mustafar_volcanic_plains"),
            Map.entry(MUSTAFAR_MAGMA_LAKE, "mustafar_magma_lake"),
            Map.entry(OSSUS_FOREST, "ossus_forest"),
            Map.entry(OSSUS_PLAINS, "ossus_plains"),
            Map.entry(OSSUS_MOUNTAINS, "ossus_mountains"),
            Map.entry(OSSUS_OCEAN, "ossus_ocean"),
            Map.entry(OSSUS_DEEP_OCEAN, "ossus_deep_ocean"),
            Map.entry(MALACHOR_UPPER_LAYER, "malachor_upper_layer"),
            Map.entry(MALACHOR_LOWER_SURFACE, "malachor_lower_surface"),
            Map.entry(NABOO_SWAMP, "naboo_swamp"),
            Map.entry(NABOO_PLAINS, "naboo_plains"),
            Map.entry(NABOO_OCEAN, "naboo_ocean"),
            Map.entry(KORRIBAN_DRY_CANYON, "korriban_dry_canyon"),
            Map.entry(KORRIBAN_SITH_TOMB, "korriban_sith_tomb"),
            Map.entry(TYTHON_FOREST, "tython_forest"),
            Map.entry(TYTHON_PLAINS, "tython_plains"),
            Map.entry(TYTHON_MOUNTAINS, "tython_mountains"),
            Map.entry(DANTOOINE_PLAINS, "dantooine_plains"),
            Map.entry(DANTOOINE_FOREST, "dantooine_forest"),
            Map.entry(DANTOOINE_HILLS, "dantooine_hills"),
            Map.entry(ASHLA_BIOME, "ashla_biome"),
            Map.entry(BOGAN_BIOME, "bogan_biome")
    );

    public static void bootstrap(BootstrapContext<Biome> context) {
        BIOME_FILES.forEach((key, file) -> context.register(key, loadBiome(context, file)));
    }

    private static Biome loadBiome(BootstrapContext<Biome> context, String fileName) {
        ResourceLocation resource = ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID,
                "worldgen/biome/" + fileName + ".json");
        JsonElement json = readJson(resource);
        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, context.lookupProvider());
        DataResult<Biome> parsed = Biome.DIRECT_CODEC.parse(ops, json);
        return parsed.getOrThrow(false, (message) ->
                galaxyunderchaos.LOGGER.error("Failed to parse biome {}: {}", resource, message));
    }

    private static JsonElement readJson(ResourceLocation resource) {
        String resourcePath = "/data/" + resource.getNamespace() + "/" + resource.getPath();
        try (InputStream stream = Objects.requireNonNull(ModBiomes.class.getResourceAsStream(resourcePath),
                "Missing biome resource: " + resourcePath);
             Reader reader = new InputStreamReader(stream)) {
            return JsonParser.parseReader(reader);
        } catch (Exception exception) {
            throw new IllegalStateException("Unable to load biome from " + resourcePath, exception);
        }
    }
}
