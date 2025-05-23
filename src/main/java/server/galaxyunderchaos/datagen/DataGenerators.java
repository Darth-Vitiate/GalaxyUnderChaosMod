package server.galaxyunderchaos.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import server.galaxyunderchaos.data.ModGlobalLootModifierProvider;
import server.galaxyunderchaos.data.ModItemModelProvider;
import server.galaxyunderchaos.galaxyunderchaos;
import server.galaxyunderchaos.worldgen.ModDatapackEntries;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = galaxyunderchaos.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModDatapackEntries(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new ModGlobalLootModifierProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new ModWorldGenProvider(packOutput, lookupProvider));
    }
}