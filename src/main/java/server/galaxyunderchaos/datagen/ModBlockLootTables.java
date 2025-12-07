package server.galaxyunderchaos.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import server.galaxyunderchaos.galaxyunderchaos;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookupProvider);
    }

    @Override
    protected void generate() {
        galaxyunderchaos.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .forEach(block -> add(block, createSimpleDropTable(block)));
    }

    private LootTable.Builder createSimpleDropTable(Block block) {
        LootPool.Builder pool = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(applyExplosionCondition(block, LootItem.lootTableItem(block)));
        return LootTable.lootTable().withPool(pool);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return galaxyunderchaos.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
    }
}
