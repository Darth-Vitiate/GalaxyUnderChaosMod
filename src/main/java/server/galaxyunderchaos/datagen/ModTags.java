package server.galaxyunderchaos.datagen;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import server.galaxyunderchaos.galaxyunderchaos;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> BLEEDABLE_ITEMS = createTag("bleedable_items");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, name));
        }
    }
}