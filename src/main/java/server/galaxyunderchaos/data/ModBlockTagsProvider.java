package server.galaxyunderchaos.data;


import server.galaxyunderchaos.galaxyunderchaos;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, galaxyunderchaos.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(galaxyunderchaos.ANCIENT_FORCE_STONE.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(galaxyunderchaos.ANCIENT_FORCE_STONE.get());
    }
}