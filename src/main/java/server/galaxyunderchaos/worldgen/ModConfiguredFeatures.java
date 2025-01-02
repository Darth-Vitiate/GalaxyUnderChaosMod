package server.galaxyunderchaos.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import server.galaxyunderchaos.galaxyunderchaos;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TITANIUM_ORE_KEY = registerKey("titanium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_CHROMIUM_ORE_KEY = registerKey("chromium_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldTitaniumOres = List.of(
                OreConfiguration.target(stoneReplaceables, galaxyunderchaos.TITANIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, galaxyunderchaos.TITANIUM_DEEPSLATE_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldChromiumOres = List.of(
                OreConfiguration.target(stoneReplaceables, galaxyunderchaos.CHROMIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, galaxyunderchaos.CHROMIUM_DEEPSLATE_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_TITANIUM_ORE_KEY, Feature.ORE, new OreConfiguration(overworldTitaniumOres, 9));
        register(context, OVERWORLD_CHROMIUM_ORE_KEY, Feature.ORE, new OreConfiguration(overworldChromiumOres, 9));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
