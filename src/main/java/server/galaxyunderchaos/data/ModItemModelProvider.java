package server.galaxyunderchaos.data;

import server.galaxyunderchaos.galaxyunderchaos;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, galaxyunderchaos.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheldItem(galaxyunderchaos.LOST_HILT);
        handheldItem(galaxyunderchaos.AEGIS_HILT);
        handheldItem(galaxyunderchaos.APPRENTICE_HILT);
        handheldItem(galaxyunderchaos.CHOSEN_HILT);
        handheldItem(galaxyunderchaos.EMPEROR_HILT);
        handheldItem(galaxyunderchaos.FALLEN_HILT);
        handheldItem(galaxyunderchaos.GRACE_HILT);
        handheldItem(galaxyunderchaos.GUARD_HILT);
        handheldItem(galaxyunderchaos.HARMONY_HILT);
        handheldItem(galaxyunderchaos.LEGACY_HILT);
        handheldItem(galaxyunderchaos.PADAWAN_HILT);
        handheldItem(galaxyunderchaos.RESOLVE_HILT);
        handheldItem(galaxyunderchaos.SKUSTELL_HILT);
        handheldItem(galaxyunderchaos.TALON_HILT);
        handheldItem(galaxyunderchaos.VALOR_HILT);
        handheldItem(galaxyunderchaos.WISDOM_HILT);
        handheldItem(galaxyunderchaos.JEDI_HOLOCRON_ITEM);
        handheldItem(galaxyunderchaos.SITH_HOLOCRON_ITEM);

    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID,"item/" + item.getId().getPath()));
    }

    public void buttonItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void fenceItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<? extends Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID,"item/" + item.getId().getPath()));
    }
}