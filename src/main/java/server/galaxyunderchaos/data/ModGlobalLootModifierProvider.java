package server.galaxyunderchaos.data;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import server.galaxyunderchaos.loot.AddItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import server.galaxyunderchaos.galaxyunderchaos;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, galaxyunderchaos.MODID, registries);
    }

    @Override
    protected void start(HolderLookup.Provider registries) {
        this.add("shuura_from_short_grass",
                new AddItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.25f).build() }, galaxyunderchaos.SHUURA.get()));
        this.add("shuura_from_tall_grass",
                new AddItemModifier(new LootItemCondition[] {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.25f).build() }, galaxyunderchaos.SHUURA.get()));

        List<RegistryObject<Item>> hilts = List.of(
                galaxyunderchaos.LOST_HILT,
                galaxyunderchaos.AEGIS_HILT,
                galaxyunderchaos.APPRENTICE_HILT,
                galaxyunderchaos.CHOSEN_HILT,
                galaxyunderchaos.EMPEROR_HILT,
                galaxyunderchaos.FALLEN_HILT,
                galaxyunderchaos.GRACE_HILT,
                galaxyunderchaos.GUARD_HILT,
                galaxyunderchaos.HARMONY_HILT,
                galaxyunderchaos.LEGACY_HILT,
                galaxyunderchaos.PADAWAN_HILT,
                galaxyunderchaos.RESOLVE_HILT,
                galaxyunderchaos.SKUSTELL_HILT,
                galaxyunderchaos.TALON_HILT,
                galaxyunderchaos.VALOR_HILT,
                galaxyunderchaos.WISDOM_HILT
        );

        Random rand = new Random();
        RegistryObject<Item> chosenHilt = hilts.get(rand.nextInt(hilts.size()));

        this.add("hilt_from_jungle_temple",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/jungle_temple")).build()
                }, chosenHilt.get()));
        this.add("hilt_from_desert_pyramid",
                new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/desert_pyramid")).build()
                }, chosenHilt.get()));



        add("titanium_chromium_from_zombie", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/zombie"))
                        .and(LootItemRandomChanceCondition.randomChance(0.5f)).build() }, // modified by the creeper's own loot table
                galaxyunderchaos.TITANIUM_CHROMIUM_INGOT.get()));

    }











}