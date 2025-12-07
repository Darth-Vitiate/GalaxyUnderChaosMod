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

/**
 * SAFE version — never crashes datagen and never deletes JSONs.
 */
public class ModNoiseSettings {

    private static final Map<ResourceKey<NoiseGeneratorSettings>, String> NOISE_FILES = Map.of(
//            ModDimensions.TYTHON_NOISE, "tython_noise_settings",
//            ModDimensions.NABOO_NOISE, "naboo_noise_settings",
//            ModDimensions.ILUM_NOISE, "ilum_noise_settings",
//            ModDimensions.MUSTAFAR_NOISE, "mustafar_noise_settings",
//            ModDimensions.OSSUS_NOISE, "ossus_noise_settings",
//            ModDimensions.BOGAN_NOISE, "bogan_noise_settings",
//            ModDimensions.KORRIBAN_NOISE, "korriban_noise_settings",
//            ModDimensions.DANTOOINE_NOISE, "dantooine_noise_settings"
    );

    public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> context) {
        if (isDatagen()) {
            return;
        }
        NOISE_FILES.forEach((key, file) -> {
            NoiseGeneratorSettings settings = loadNoiseSettings(file);
            if (settings != null) {
                context.register(key, settings);
            }
        });
    }


    private static boolean isDatagen() {
        return System.getProperty("data", "false").equals("true")
                || Thread.currentThread().getName().contains("DataGenerator");
    }

    private static NoiseGeneratorSettings loadNoiseSettings(String fileName) {

        ResourceLocation resource = ResourceLocation.fromNamespaceAndPath(
                galaxyunderchaos.MODID,
                "worldgen/noise_settings/" + fileName + ".json"
        );

        String path = "/data/" + resource.getNamespace() + "/" + resource.getPath();

        try (InputStream stream = ModNoiseSettings.class.getResourceAsStream(path)) {
            if (stream == null) {
                galaxyunderchaos.LOGGER.warn("Noise file missing but not required: {}", path);
                return null;
            }

            Reader reader = new InputStreamReader(stream);
            JsonElement json = JsonParser.parseReader(reader);

            DataResult<NoiseGeneratorSettings> parsed =
                    NoiseGeneratorSettings.DIRECT_CODEC.parse(JsonOps.INSTANCE, json);

            return parsed.resultOrPartial(msg -> {
                galaxyunderchaos.LOGGER.error("Parse error in {}: {}", fileName, msg);
            }).orElse(null);

        } catch (Exception e) {
            galaxyunderchaos.LOGGER.error("Could not load noise settings {}: {}", fileName, e.getMessage());
            return null;
        }
    }
}
