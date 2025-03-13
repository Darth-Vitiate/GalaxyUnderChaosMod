package server.galaxyunderchaos.data;


import net.neoforged.neoforge.common.data.BlockTagsProvider;
import server.galaxyunderchaos.galaxyunderchaos;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;


import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, galaxyunderchaos.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(galaxyunderchaos.ANCIENT_TEMPLE_STONE.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(galaxyunderchaos.ANCIENT_TEMPLE_STONE.get());
    }
}