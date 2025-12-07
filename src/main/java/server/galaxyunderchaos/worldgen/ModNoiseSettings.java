package server.galaxyunderchaos.worldgen;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import server.galaxyunderchaos.galaxyunderchaos;
import server.galaxyunderchaos.worldgen.dimension.ModDimensions;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.Objects;

/**
 * Registers noise settings directly from the existing data JSON resources so that datagen
 * continues to output them even if the generated resources folder is cleaned.
 */
public class ModNoiseSettings {

    private static final Map<ResourceKey<NoiseGeneratorSettings>, String> NOISE_FILES = Map.of(
            ModDimensions.TYTHON_NOISE, "tython_noise_settings",
            ModDimensions.NABOO_NOISE, "naboo_noise_settings",
            ModDimensions.ILUM_NOISE, "ilum_noise_settings",
            ModDimensions.MUSTAFAR_NOISE, "mustafar_noise_settings",
            ModDimensions.OSSUS_NOISE, "ossus_noise_settings",
            ModDimensions.ASHLA_NOISE, "ashla_noise_settings",
            ModDimensions.BOGAN_NOISE, "bogan_noise_settings",
            ModDimensions.MALACHOR_NOISE, "malachor_noise_settings",
            ModDimensions.KORRIBAN_NOISE, "korriban_noise_settings",
            ModDimensions.DANTOOINE_NOISE, "dantooine_noise_settings"
    );

    public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> context) {
        NOISE_FILES.forEach((key, file) -> context.register(key, loadNoiseSettings(file)));
    }

    private static NoiseGeneratorSettings loadNoiseSettings(String fileName) {
        ResourceLocation resource = ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID,
                "worldgen/noise_settings/" + fileName + ".json");

        String resourcePath = "/data/" + resource.getNamespace() + "/" + resource.getPath();
        try (InputStream stream = Objects.requireNonNull(ModNoiseSettings.class.getResourceAsStream(resourcePath),
                "Missing noise settings resource: " + resourcePath);
             Reader reader = new InputStreamReader(stream)) {
            JsonElement json = JsonParser.parseReader(reader);
            DataResult<NoiseGeneratorSettings> parsed = NoiseGeneratorSettings.DIRECT_CODEC.parse(JsonOps.INSTANCE, json);
            return parsed.getOrThrow(false, (message) ->
                    galaxyunderchaos.LOGGER.error("Failed to parse noise settings {}: {}", fileName, message));
        } catch (Exception exception) {
            throw new IllegalStateException("Unable to load noise settings from " + resourcePath, exception);
        }
    }
}
