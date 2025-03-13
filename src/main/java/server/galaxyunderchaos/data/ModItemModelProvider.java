package server.galaxyunderchaos.data;

import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import server.galaxyunderchaos.galaxyunderchaos;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, galaxyunderchaos.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheldItem(galaxyunderchaos.LOST_HILT.getId());
        handheldItem(galaxyunderchaos.AEGIS_HILT.getId());
        handheldItem(galaxyunderchaos.APPRENTICE_HILT.getId());
        handheldItem(galaxyunderchaos.CHOSEN_HILT.getId());
        handheldItem(galaxyunderchaos.EMPEROR_HILT.getId());
        handheldItem(galaxyunderchaos.FALLEN_HILT.getId());
        handheldItem(galaxyunderchaos.GRACE_HILT.getId());
        handheldItem(galaxyunderchaos.GUARD_HILT.getId());
        handheldItem(galaxyunderchaos.HARMONY_HILT.getId());
        handheldItem(galaxyunderchaos.LEGACY_HILT.getId());
        handheldItem(galaxyunderchaos.PADAWAN_HILT.getId());
        handheldItem(galaxyunderchaos.RESOLVE_HILT.getId());
        handheldItem(galaxyunderchaos.SKUSTELL_HILT.getId());
        handheldItem(galaxyunderchaos.TALON_HILT.getId());
        handheldItem(galaxyunderchaos.VALOR_HILT.getId());
        handheldItem(galaxyunderchaos.WISDOM_HILT.getId());
        handheldItem(galaxyunderchaos.JEDI_HOLOCRON_ITEM.getId());
    }

    private ItemModelBuilder handheldItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(), ResourceLocation.parse("item/handheld"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "item/" + item.getId().getPath()));
    }

    public void buttonItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "block/" + baseBlock.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(), ResourceLocation.parse("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "item/" + item.getId().getPath()));
    }
}
