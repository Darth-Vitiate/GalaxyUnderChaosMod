package server.galaxyunderchaos.worldgen.biome;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.RegistryOps;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import server.galaxyunderchaos.galaxyunderchaos;
import server.galaxyunderchaos.worldgen.dimension.BootstrapContextLookup;

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
        ResourceLocation resource = ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "worldgen/biome/" + fileName + ".json");
        JsonObject json = readJson(resource).getAsJsonObject();

        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);
        HolderGetter<SoundEvent> sounds = context.lookup(Registries.SOUND_EVENT);

        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(placedFeatures, carvers);

        if (json.has("carvers")) {
            JsonObject carverObj = json.getAsJsonObject("carvers");

            for (Map.Entry<String, JsonElement> entry : carverObj.entrySet()) {

                GenerationStep.Carving step =
                        GenerationStep.Carving.valueOf(entry.getKey().toUpperCase());

                JsonElement value = entry.getValue();

                // --- STRING FORMAT ---
                if (value.isJsonPrimitive()) {
                    ResourceLocation id = ResourceLocation.parse(value.getAsString());
                    generation.addCarver(step,
                            carvers.getOrThrow(ResourceKey.create(Registries.CONFIGURED_CARVER, id)));
                }

                // --- ARRAY FORMAT ---
                else if (value.isJsonArray()) {
                    for (JsonElement e : value.getAsJsonArray()) {
                        ResourceLocation id = ResourceLocation.parse(e.getAsString());
                        generation.addCarver(step,
                                carvers.getOrThrow(ResourceKey.create(Registries.CONFIGURED_CARVER, id)));
                    }
                }

                else {
                    throw new IllegalStateException(
                            "Invalid carver format in biome JSON for step: " + entry.getKey()
                    );
                }
            }
        }

        if (json.has("features")) {
            JsonArray featureSteps = json.getAsJsonArray("features");
            int max = GenerationStep.Decoration.values().length - 1;

            for (int i = 0; i < featureSteps.size(); i++) {

                JsonElement rawStep = featureSteps.get(i);
                int safeIndex = Math.min(i, max);
                GenerationStep.Decoration step = GenerationStep.Decoration.values()[safeIndex];

                // --- SINGLE STRING ---
                if (rawStep.isJsonPrimitive()) {
                    ResourceLocation id = ResourceLocation.parse(rawStep.getAsString());
                    Holder<PlacedFeature> holder = placedFeatures.getOrThrow(
                            ResourceKey.create(Registries.PLACED_FEATURE, id)
                    );

                    if (holder == null) {
                        throw new IllegalStateException("Unknown placed feature: " + id);
                    }

                    generation.addFeature(step, holder);
                    continue;
                }

                // --- MUST BE ARRAY ---
                if (!rawStep.isJsonArray()) {
                    throw new IllegalStateException("Invalid feature entry at index " + i);
                }

                JsonArray arr = rawStep.getAsJsonArray();

                for (JsonElement e : arr) {
                    ResourceLocation id = ResourceLocation.parse(e.getAsString());
                    Holder<PlacedFeature> holder = placedFeatures.getOrThrow(
                            ResourceKey.create(Registries.PLACED_FEATURE, id)
                    );

                    if (holder == null) {
                        throw new IllegalStateException("Unknown placed feature: " + id);
                    }

                    generation.addFeature(step, holder);
                }
            }
        }


        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        if (json.has("spawners")) {
            JsonObject sp = json.getAsJsonObject("spawners");
            for (Map.Entry<String, JsonElement> entry : sp.entrySet()) {
                MobCategory cat = MobCategory.valueOf(entry.getKey().toUpperCase());
                for (JsonElement e : entry.getValue().getAsJsonArray()) {
                    JsonObject mob = e.getAsJsonObject();
                    EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse(mob.get("type").getAsString()));
                    spawns.addSpawn(cat, new MobSpawnSettings.SpawnerData(type, mob.get("weight").getAsInt(), mob.get("minCount").getAsInt(), mob.get("maxCount").getAsInt()));
                }
            }
        }

        float temperature = json.get("temperature").getAsFloat();
        float downfall = json.get("downfall").getAsFloat();
        boolean precipitation = json.get("has_precipitation").getAsBoolean();

        BiomeSpecialEffects effects = loadEffects(json);

        return new Biome.BiomeBuilder()
                .temperature(temperature)
                .downfall(downfall)
                .hasPrecipitation(precipitation)
                .specialEffects(effects)
                .mobSpawnSettings(spawns.build())
                .generationSettings(generation.build())
                .build();
    }

    private static BiomeSpecialEffects loadEffects(JsonObject json) {
        JsonObject effectsJson = json.getAsJsonObject("effects");
        BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder();

        // ---- SAFE NUMBER PARSER ----
        java.util.function.Function<JsonElement, Integer> getIntSafe = el -> {
            if (el == null) return 0;
            if (el.isJsonPrimitive()) return el.getAsInt();
            if (el.isJsonObject() && el.getAsJsonObject().has("value"))
                return el.getAsJsonObject().get("value").getAsInt();
            return 0;
        };

        // ---- SAFE STRING PARSER ----
        java.util.function.Function<JsonElement, String> getStringSafe = el -> {
            if (el == null) return "";
            if (el.isJsonPrimitive()) return el.getAsString();
            if (el.isJsonObject() && el.getAsJsonObject().has("name"))
                return el.getAsJsonObject().get("name").getAsString();
            return "";
        };

        // ----------- COLORS -----------
        effects.fogColor(getIntSafe.apply(effectsJson.get("fog_color")));
        effects.waterColor(getIntSafe.apply(effectsJson.get("water_color")));
        effects.waterFogColor(getIntSafe.apply(effectsJson.get("water_fog_color")));
        effects.skyColor(getIntSafe.apply(effectsJson.get("sky_color")));

        if (effectsJson.has("grass_color"))
            effects.grassColorOverride(getIntSafe.apply(effectsJson.get("grass_color")));

        if (effectsJson.has("foliage_color"))
            effects.foliageColorOverride(getIntSafe.apply(effectsJson.get("foliage_color")));

        // ----------- MOOD SOUND -----------
        if (effectsJson.has("mood_sound")) {
            JsonObject mood = effectsJson.getAsJsonObject("mood_sound");

            String soundId = getStringSafe.apply(mood.get("sound"));
            SoundEvent sound = BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse(soundId));

            effects.ambientMoodSound(
                    new AmbientMoodSettings(
                            Holder.direct(sound),
                            getIntSafe.apply(mood.get("tick_delay")),
                            getIntSafe.apply(mood.get("block_search_extent")),
                            mood.has("offset") && mood.get("offset").isJsonPrimitive()
                                    ? mood.get("offset").getAsDouble()
                                    : 0.0
                    )
            );
        }

        // ----------- MUSIC -----------
        if (effectsJson.has("music")) {
            JsonObject musicJson = effectsJson.getAsJsonObject("music");

            String soundId = getStringSafe.apply(musicJson.get("sound"));
            SoundEvent sound = BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse(soundId));

            effects.backgroundMusic(
                    new Music(
                            Holder.direct(sound),
                            getIntSafe.apply(musicJson.get("min_delay")),
                            getIntSafe.apply(musicJson.get("max_delay")),
                            musicJson.has("replace_current_music") &&
                                    musicJson.get("replace_current_music").getAsBoolean()
                    )
            );
        }

        return effects.build();
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
