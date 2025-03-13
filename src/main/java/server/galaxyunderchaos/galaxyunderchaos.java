package server.galaxyunderchaos;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.SaplingBlock;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import server.galaxyunderchaos.block.*;
import server.galaxyunderchaos.data.ModDataComponentTypes;
import server.galaxyunderchaos.entity.AcidSpiderEntity;
import server.galaxyunderchaos.item.*;
import server.galaxyunderchaos.loot.ModLootModifiers;
import server.galaxyunderchaos.sound.ModSounds;
import server.galaxyunderchaos.worldgen.biome.ModBiomes;
import server.galaxyunderchaos.worldgen.tree.ModTreeGrowers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(galaxyunderchaos.MODID)
public class galaxyunderchaos
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "galaxyunderchaos";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, galaxyunderchaos.MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<Block, EarthCrystalOre> CHROMIUM_ORE = BLOCKS.register("chromium_ore", EarthCrystalOre::new);
    public static final DeferredHolder<Block, EarthCrystalOre> CHROMIUM_DEEPSLATE_ORE = BLOCKS.register("chromium_deepslate_ore", EarthCrystalOre::new);
    public static final DeferredHolder<Block, EarthCrystalOre> TITANIUM_ORE = BLOCKS.register("titanium_ore", EarthCrystalOre::new);
    public static final DeferredHolder<Block, EarthCrystalOre> TITANIUM_DEEPSLATE_ORE = BLOCKS.register("titanium_deepslate_ore", EarthCrystalOre::new);
    public static final DeferredHolder<Block, CrystalOre> BLUE_CRYSTAL_ORE = BLOCKS.register("blue_crystal_ore", CrystalOre::new);
    public static final DeferredHolder<Block, CrystalOre> ORANGE_CRYSTAL_ORE = BLOCKS.register("orange_crystal_ore", CrystalOre::new);
    public static final DeferredHolder<Block, CrystalOre> GREEN_CRYSTAL_ORE = BLOCKS.register("green_crystal_ore", CrystalOre::new);
    public static final DeferredHolder<Block, CrystalOre> YELLOW_CRYSTAL_ORE = BLOCKS.register("yellow_crystal_ore", CrystalOre::new);
    public static final DeferredHolder<Block, CrystalOre> CYAN_CRYSTAL_ORE = BLOCKS.register("cyan_crystal_ore", CrystalOre::new);
    public static final DeferredHolder<Block, CrystalOre> WHITE_CRYSTAL_ORE = BLOCKS.register("white_crystal_ore", CrystalOre::new);
    public static final DeferredHolder<Block, CrystalOre> MAGENTA_CRYSTAL_ORE = BLOCKS.register("magenta_crystal_ore", CrystalOre::new);
    public static final DeferredHolder<Block, CrystalOre> PURPLE_CRYSTAL_ORE = BLOCKS.register("purple_crystal_ore", CrystalOre::new);
    public static final DeferredHolder<Block, CrystalOre> PINK_CRYSTAL_ORE = BLOCKS.register("pink_crystal_ore", CrystalOre::new);
    public static final DeferredHolder<Block, CrystalOre> LIME_GREEN_CRYSTAL_ORE = BLOCKS.register("lime_green_crystal_ore", CrystalOre::new);
    public static final DeferredHolder<Block, CrystalOre> TURQUOISE_CRYSTAL_ORE = BLOCKS.register("turquoise_crystal_ore", CrystalOre::new);


    public static final DeferredItem<BlockItem> CHROMIUM_ORE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("chromium_ore",
            () -> new BlockItem(CHROMIUM_ORE.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> CHROMIUM_DEEPSLATE_ORE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("chromium_deepslate_ore",
            () -> new BlockItem(CHROMIUM_DEEPSLATE_ORE.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> TITANIUM_ORE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("titanium_ore",
            () -> new BlockItem(TITANIUM_ORE.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> TITANIUM_DEEPSLATE_ORE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("titanium_deepslate_ore",
            () -> new BlockItem(TITANIUM_DEEPSLATE_ORE.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> BLUE_KYBER_CRYSTAL_ORE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("blue_crystal_ore",
            () -> new BlockItem(BLUE_CRYSTAL_ORE.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> ORANGE_KYBER_CRYSTAL_ORE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("orange_crystal_ore",
            () -> new BlockItem(ORANGE_CRYSTAL_ORE.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> GREEN_KYBER_CRYSTAL_ORE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("green_crystal_ore",
            () -> new BlockItem(GREEN_CRYSTAL_ORE.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> YELLOW_KYBER_CRYSTAL_ORE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("yellow_crystal_ore",
            () -> new BlockItem(YELLOW_CRYSTAL_ORE.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> CYAN_KYBER_CRYSTAL_ORE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("cyan_crystal_ore",
            () -> new BlockItem(CYAN_CRYSTAL_ORE.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> WHITE_KYBER_CRYSTAL_ORE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("white_crystal_ore",
            () -> new BlockItem(WHITE_CRYSTAL_ORE.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> MAGENTA_KYBER_CRYSTAL_ORE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("magenta_crystal_ore",
            () -> new BlockItem(MAGENTA_CRYSTAL_ORE.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> PURPLE_KYBER_CRYSTAL_ORE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("purple_crystal_ore",
            () -> new BlockItem(PURPLE_CRYSTAL_ORE.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> PINK_KYBER_CRYSTAL_ORE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("pink_crystal_ore",
            () -> new BlockItem(PINK_CRYSTAL_ORE.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> LIME_GREEN_KYBER_CRYSTAL_ORE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("lime_green_crystal_ore",
            () -> new BlockItem(LIME_GREEN_CRYSTAL_ORE.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> TURQUOISE_KYBER_CRYSTAL_ORE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("turquoise_crystal_ore",
            () -> new BlockItem(TURQUOISE_CRYSTAL_ORE.get(), new Item.Properties()));


    public static final DeferredHolder<Block, TempleStone> TEMPLE_STONE = BLOCKS.register("temple_stone", TempleStone::new);
    public static final DeferredItem<BlockItem> TEMPLE_STONE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("temple_stone",
            () -> new BlockItem(TEMPLE_STONE.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TempleStonePillar> TEMPLE_STONE_PILLAR = BLOCKS.register("temple_stone_pillar", TempleStonePillar::new);
    public static final DeferredItem<BlockItem> TEMPLE_STONE_PILLAR_ITEM = (DeferredItem<BlockItem>) ITEMS.register("temple_stone_pillar",
            () -> new BlockItem(TEMPLE_STONE_PILLAR.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TempleStoneHolobook> TEMPLE_STONE_HOLOBOOK = BLOCKS.register("temple_stone_holobook", TempleStoneHolobook::new);
    public static final DeferredItem<BlockItem> TEMPLE_STONE_HOLOBOOK_ITEM = (DeferredItem<BlockItem>) ITEMS.register("temple_stone_holobook",
            () -> new BlockItem(TEMPLE_STONE_HOLOBOOK.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TempleStoneStairs> TEMPLE_STONE_STAIRS = BLOCKS.register("temple_stone_stairs",
            () -> new TempleStoneStairs(TEMPLE_STONE.get().defaultBlockState()));
    public static final DeferredItem<BlockItem> TEMPLE_STONE_STAIRS_ITEM = (DeferredItem<BlockItem>) ITEMS.register("temple_stone_stairs",
            () -> new BlockItem(TEMPLE_STONE_STAIRS.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TempleStoneSlab> TEMPLE_STONE_SLAB = BLOCKS.register("temple_stone_slab", TempleStoneSlab::new);
    public static final DeferredItem<BlockItem> TEMPLE_STONE_SLAB_ITEM = (DeferredItem<BlockItem>) ITEMS.register("temple_stone_slab",
            () -> new BlockItem(TEMPLE_STONE_SLAB.get(), new Item.Properties()));

    public static final DeferredHolder<Block, DarkTempleStone> DARK_TEMPLE_STONE = BLOCKS.register("dark_temple_stone", DarkTempleStone::new);
    public static final DeferredItem<BlockItem> DARK_TEMPLE_STONE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("dark_temple_stone",
            () -> new BlockItem(DARK_TEMPLE_STONE.get(), new Item.Properties()));

    public static final DeferredHolder<Block, DarkTempleStone> MALACHITE_OBSIDIAN = BLOCKS.register("malachite_obsidian", DarkTempleStone::new);
    public static final DeferredItem<BlockItem> MALACHITE_OBSIDIAN_ITEM = (DeferredItem<BlockItem>) ITEMS.register("malachite_obsidian",
            () -> new BlockItem(MALACHITE_OBSIDIAN.get(), new Item.Properties()));

    public static final DeferredHolder<Block, DarkTempleStonePillar> DARK_TEMPLE_STONE_PILLAR = BLOCKS.register("dark_temple_stone_pillar", DarkTempleStonePillar::new);
    public static final DeferredItem<BlockItem> DARK_TEMPLE_STONE_PILLAR_ITEM = (DeferredItem<BlockItem>) ITEMS.register("dark_temple_stone_pillar",
            () -> new BlockItem(DARK_TEMPLE_STONE_PILLAR.get(), new Item.Properties()));

    public static final DeferredHolder<Block, DarkTempleStoneHolobook> DARK_TEMPLE_STONE_HOLOBOOK = BLOCKS.register("dark_temple_stone_holobook", DarkTempleStoneHolobook::new);
    public static final DeferredItem<BlockItem> DARK_TEMPLE_STONE_HOLOBOOK_ITEM = (DeferredItem<BlockItem>) ITEMS.register("dark_temple_stone_holobook",
            () -> new BlockItem(DARK_TEMPLE_STONE_HOLOBOOK.get(), new Item.Properties()));

    public static final DeferredHolder<Block, DarkTempleStoneStairs> DARK_TEMPLE_STONE_STAIRS = BLOCKS.register("dark_temple_stone_stairs",
            () -> new DarkTempleStoneStairs(DARK_TEMPLE_STONE.get().defaultBlockState()));
    public static final DeferredItem<BlockItem> DARK_TEMPLE_STONE_STAIRS_ITEM = (DeferredItem<BlockItem>) ITEMS.register("dark_temple_stone_stairs",
            () -> new BlockItem(DARK_TEMPLE_STONE_STAIRS.get(), new Item.Properties()));

    public static final DeferredHolder<Block, DarkTempleStoneSlab> DARK_TEMPLE_STONE_SLAB = BLOCKS.register("dark_temple_stone_slab", DarkTempleStoneSlab::new);
    public static final DeferredItem<BlockItem> DARK_TEMPLE_STONE_SLAB_ITEM = (DeferredItem<BlockItem>) ITEMS.register("dark_temple_stone_slab",
            () -> new BlockItem(DARK_TEMPLE_STONE_SLAB.get(), new Item.Properties()));
    public static final DeferredHolder<Item, DeferredSpawnEggItem> ACID_SPIDER_SPAWN_EGG = ITEMS.register("acid_spider_spawn_egg",
            () -> new DeferredSpawnEggItem(galaxyunderchaos.ACID_SPIDER, 0x31afaf, 0xffac00,
                    new Item.Properties()));

    public static final DeferredItem<Item> SHUURA = (DeferredItem<Item>) ITEMS.register("shuura", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(6).saturationModifier(2f).build())));
    public static final DeferredHolder<Block, BleedingTable> BLEEDING_TABLE = BLOCKS.register("bleeding_table", BleedingTable::new);

    public static final DeferredItem<BlockItem> BLEEDING_TABLE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("bleeding_table",
            () -> new BlockItem(BLEEDING_TABLE.get(), new Item.Properties()));

    public static final DeferredHolder<Block, Holocron> JEDI_HOLOCRON = BLOCKS.register("jedi_holocron", Holocron::new);
    public static final DeferredItem<BlockItem> JEDI_HOLOCRON_ITEM = (DeferredItem<BlockItem>) ITEMS.register("jedi_holocron",
            () -> new BlockItem(JEDI_HOLOCRON.get(), new Item.Properties()));

    public static final DeferredHolder<Block, Holocron> SITH_HOLOCRON = BLOCKS.register("sith_holocron", Holocron::new);
    public static final DeferredItem<BlockItem> SITH_HOLOCRON_ITEM = (DeferredItem<BlockItem>) ITEMS.register("sith_holocron",
            () -> new BlockItem(SITH_HOLOCRON.get(), new Item.Properties()));

    public static final DeferredHolder<Block, JediGuard> JEDI_GUARD_STATUE = BLOCKS.register("jedi_guard_statue", JediGuard::new);
    public static final DeferredItem<BlockItem> JEDI_GUARD_STATUE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("jedi_guard_statue",
            () -> new BlockItem(JEDI_GUARD_STATUE.get(), new Item.Properties()));

    public static final DeferredHolder<Block, SithGuard> SITH_GUARD_STATUE = BLOCKS.register("sith_guard_statue", SithGuard::new);
    public static final DeferredItem<BlockItem> SITH_GUARD_STATUE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("sith_guard_statue",
            () -> new BlockItem(SITH_GUARD_STATUE.get(), new Item.Properties()));

    public static final DeferredHolder<Block, LightsaberCraftingTableBlock> LIGHTSABER_CRAFTING_TABLE = BLOCKS.register("lightsaber_crafting_table", LightsaberCraftingTableBlock::new);
    public static final DeferredItem<BlockItem> LIGHTSABER_CRAFTING_TABLE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("lightsaber_crafting_table",
            () -> new BlockItem(LIGHTSABER_CRAFTING_TABLE.get(), new Item.Properties()));

    public static final DeferredHolder<Block, AncientTempleStone> ANCIENT_TEMPLE_STONE = BLOCKS.register("ancient_temple_stone", AncientTempleStone::new);
    public static final DeferredItem<BlockItem> ANCIENT_TEMPLE_STONE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("ancient_temple_stone",
            () -> new BlockItem(ANCIENT_TEMPLE_STONE.get(), new Item.Properties()));

    public static final DeferredHolder<Block, AncientTempleStoneCracked> ANCIENT_TEMPLE_STONE_CRACKED = BLOCKS.register("ancient_temple_stone_cracked", AncientTempleStoneCracked::new);
    public static final DeferredItem<BlockItem> ANCIENT_TEMPLE_STONE_CRACKED_ITEM = (DeferredItem<BlockItem>) ITEMS.register("ancient_temple_stone_cracked",
            () -> new BlockItem(ANCIENT_TEMPLE_STONE_CRACKED.get(), new Item.Properties()));

    public static final DeferredHolder<Block, AncientTempleStonePillar> ANCIENT_TEMPLE_STONE_PILLAR = BLOCKS.register("ancient_temple_stone_pillar", AncientTempleStonePillar::new);
    public static final DeferredItem<BlockItem> ANCIENT_TEMPLE_STONE_PILLAR_ITEM = (DeferredItem<BlockItem>) ITEMS.register("ancient_temple_stone_pillar",
            () -> new BlockItem(ANCIENT_TEMPLE_STONE_PILLAR.get(), new Item.Properties()));

    public static final DeferredHolder<Block, AncientTempleStoneHolobook> ANCIENT_TEMPLE_STONE_HOLOBOOK = BLOCKS.register("ancient_temple_stone_holobook", AncientTempleStoneHolobook::new);
    public static final DeferredItem<BlockItem> ANCIENT_TEMPLE_STONE_HOLOBOOK_ITEM = (DeferredItem<BlockItem>) ITEMS.register("ancient_temple_stone_holobook",
            () -> new BlockItem(ANCIENT_TEMPLE_STONE_HOLOBOOK.get(), new Item.Properties()));

    public static final DeferredHolder<Block, AncientTempleStoneStairs> ANCIENT_TEMPLE_STONE_STAIRS = BLOCKS.register("ancient_temple_stone_stairs",
            () -> new AncientTempleStoneStairs(ANCIENT_TEMPLE_STONE.get().defaultBlockState()));
    public static final DeferredItem<BlockItem> ANCIENT_TEMPLE_STONE_STAIRS_ITEM = (DeferredItem<BlockItem>) ITEMS.register("ancient_temple_stone_stairs",
            () -> new BlockItem(ANCIENT_TEMPLE_STONE_STAIRS.get(), new Item.Properties()));

    public static final DeferredHolder<Block, AncientTempleStoneSlab> ANCIENT_TEMPLE_STONE_SLAB = BLOCKS.register("ancient_temple_stone_slab", AncientTempleStoneSlab::new);
    public static final DeferredItem<BlockItem> ANCIENT_TEMPLE_STONE_SLAB_ITEM = (DeferredItem<BlockItem>) ITEMS.register("ancient_temple_stone_slab",
            () -> new BlockItem(ANCIENT_TEMPLE_STONE_SLAB.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TythonGrass> TYTHON_GRASS = BLOCKS.register("tython_grass", TythonGrass::new);
    public static final DeferredItem<BlockItem> TYTHON_GRASS_ITEM = (DeferredItem<BlockItem>) ITEMS.register("tython_grass",
            () -> new BlockItem(TYTHON_GRASS.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TythonDirt> TYTHON_DIRT = BLOCKS.register("tython_dirt", TythonDirt::new);
    public static final DeferredItem<BlockItem> TYTHON_DIRT_ITEM = (DeferredItem<BlockItem>) ITEMS.register("tython_dirt",
            () -> new BlockItem(TYTHON_DIRT.get(), new Item.Properties()));

    public static final DeferredHolder<Block, AncientTempleStoneWall> ANCIENT_TEMPLE_STONE_WALL = BLOCKS.register("ancient_temple_stone_wall", AncientTempleStoneWall::new);
    public static final DeferredItem<BlockItem> ANCIENT_TEMPLE_STONE_WALL_ITEM = (DeferredItem<BlockItem>) ITEMS.register("ancient_temple_stone_wall",
            () -> new BlockItem(ANCIENT_TEMPLE_STONE_WALL.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TempleStoneWall> TEMPLE_STONE_WALL = BLOCKS.register("temple_stone_wall", TempleStoneWall::new);
    public static final DeferredItem<BlockItem> TEMPLE_STONE_WALL_ITEM = (DeferredItem<BlockItem>) ITEMS.register("temple_stone_wall",
            () -> new BlockItem(TEMPLE_STONE_WALL.get(), new Item.Properties()));

    public static final DeferredHolder<Block, DarkTempleStoneWall> DARK_TEMPLE_STONE_WALL = BLOCKS.register("dark_temple_stone_wall", DarkTempleStoneWall::new);
    public static final DeferredItem<BlockItem> DARK_TEMPLE_STONE_WALL_ITEM = (DeferredItem<BlockItem>) ITEMS.register("dark_temple_stone_wall",
            () -> new BlockItem(DARK_TEMPLE_STONE_WALL.get(), new Item.Properties()));

    public static final DeferredHolder<Block, SaplingBlock> BLBA_SAPLING = BLOCKS.register("blba_sapling",
            () -> new SaplingBlock(ModTreeGrowers.BLBA, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredItem<BlockItem> BLBA_SAPLING_ITEM = (DeferredItem<BlockItem>) ITEMS.register("blba_sapling",
            () -> new BlockItem(BLBA_SAPLING.get(), new Item.Properties()));
    public static final DeferredItem<Item> CHROMIUM_INGOT = (DeferredItem<Item>) ITEMS.register("chromium_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TITANIUM_INGOT = (DeferredItem<Item>) ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PORTAL_ITEM = (DeferredItem<Item>) ITEMS.register("portal_item",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> NAVIGATION_COMPUTER = (DeferredItem<Item>) ITEMS.register("navigation_computer",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> REACTOR_ASSEMBLY = (DeferredItem<Item>) ITEMS.register("reactor_assembly",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TITANIUM_CHROMIUM_INGOT = (DeferredItem<Item>) ITEMS.register("titanium_chromium_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CYAN_KYBER = (DeferredItem<Item>) ITEMS.register("cyan_kyber",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> WHITE_KYBER = (DeferredItem<Item>) ITEMS.register("white_kyber",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MAGENTA_KYBER = (DeferredItem<Item>) ITEMS.register("magenta_kyber",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PURPLE_KYBER = (DeferredItem<Item>) ITEMS.register("purple_kyber",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PINK_KYBER = (DeferredItem<Item>) ITEMS.register("pink_kyber",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LIME_GREEN_KYBER = (DeferredItem<Item>) ITEMS.register("lime_green_kyber",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TURQUOISE_KYBER = (DeferredItem<Item>) ITEMS.register("turquoise_kyber",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> JEDI_HOLOBOOK = (DeferredItem<Item>) ITEMS.register("jedi_holobook",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ANCIENT_HOLOBOOK = (DeferredItem<Item>) ITEMS.register("ancient_holobook",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SITH_HOLOBOOK = (DeferredItem<Item>) ITEMS.register("sith_holobook",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RED_KYBER = (DeferredItem<Item>) ITEMS.register("red_kyber",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BLOOD_ORANGE_KYBER = (DeferredItem<Item>) ITEMS.register("blood_orange_kyber",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BLUE_KYBER = (DeferredItem<Item>) ITEMS.register("blue_kyber",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ORANGE_KYBER = (DeferredItem<Item>) ITEMS.register("orange_kyber",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GREEN_KYBER = (DeferredItem<Item>) ITEMS.register("green_kyber",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> YELLOW_KYBER = (DeferredItem<Item>) ITEMS.register("yellow_kyber",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<TythonPortalItem> TYTHON_PORTAL_ITEM = (DeferredItem<TythonPortalItem>) ITEMS.register("tython_portal",
            () -> new TythonPortalItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<MustafarPortalItem> MUSTAFAR_PORTAL_ITEM = (DeferredItem<MustafarPortalItem>) ITEMS.register("mustafar_portal",
            () -> new MustafarPortalItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<NabooPortalItem> NABOO_PORTAL_ITEM = (DeferredItem<NabooPortalItem>) ITEMS.register("naboo_portal",
            () -> new NabooPortalItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<IlumPortalItem> ILUM_PORTAL_ITEM = (DeferredItem<IlumPortalItem>) ITEMS.register("ilum_portal",
            () -> new IlumPortalItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<OssusPortalItem> OSSUS_PORTAL_ITEM = (DeferredItem<OssusPortalItem>) ITEMS.register("ossus_portal",
            () -> new OssusPortalItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<MalachorPortalItem> MALACHOR_PORTAL_ITEM = (DeferredItem<MalachorPortalItem>) ITEMS.register("malachor_portal",
            () -> new MalachorPortalItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<KorribanPortalItem> KORRIBAN_PORTAL_ITEM = (DeferredItem<KorribanPortalItem>) ITEMS.register("korriban_portal",
            () -> new KorribanPortalItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<DantooinePortalItem> DANTOOINE_PORTAL_ITEM = (DeferredItem<DantooinePortalItem>) ITEMS.register("dantooine_portal",
            () -> new DantooinePortalItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<HiltItem> LOST_HILT = (DeferredItem<HiltItem>) ITEMS.register("lost_hilt",
            () -> new HiltItem("green", new Item.Properties()));
    public static final DeferredItem<HiltItem> AEGIS_HILT = (DeferredItem<HiltItem>) ITEMS.register("aegis_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final DeferredItem<HiltItem> APPRENTICE_HILT = (DeferredItem<HiltItem>) ITEMS.register("apprentice_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final DeferredItem<HiltItem> CHOSEN_HILT = (DeferredItem<HiltItem>) ITEMS.register("chosen_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final DeferredItem<HiltItem> EMPEROR_HILT = (DeferredItem<HiltItem>) ITEMS.register("emperor_hilt",
            () -> new HiltItem("red", new Item.Properties()));
    public static final DeferredItem<HiltItem> FALLEN_HILT = (DeferredItem<HiltItem>) ITEMS.register("fallen_hilt",
            () -> new HiltItem("red", new Item.Properties()));
    public static final DeferredItem<HiltItem> GRACE_HILT = (DeferredItem<HiltItem>) ITEMS.register("grace_hilt",
            () -> new HiltItem("red", new Item.Properties()));
    public static final DeferredItem<HiltItem> GUARD_HILT = (DeferredItem<HiltItem>) ITEMS.register("guard_hilt",
            () -> new HiltItem("red", new Item.Properties()));
    public static final DeferredItem<HiltItem> HARMONY_HILT = (DeferredItem<HiltItem>) ITEMS.register("harmony_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final DeferredItem<HiltItem> LEGACY_HILT = (DeferredItem<HiltItem>) ITEMS.register("legacy_hilt",
            () -> new HiltItem("green", new Item.Properties()));
    public static final DeferredItem<HiltItem> PADAWAN_HILT = (DeferredItem<HiltItem>) ITEMS.register("padawan_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final DeferredItem<HiltItem> RESOLVE_HILT = (DeferredItem<HiltItem>) ITEMS.register("resolve_hilt",
            () -> new HiltItem("purple", new Item.Properties()));
    public static final DeferredItem<HiltItem> SKUSTELL_HILT = (DeferredItem<HiltItem>) ITEMS.register("skustell_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final DeferredItem<HiltItem> TALON_HILT = (DeferredItem<HiltItem>) ITEMS.register("talon_hilt",
            () -> new HiltItem("orange", new Item.Properties()));
    public static final DeferredItem<HiltItem> VALOR_HILT = (DeferredItem<HiltItem>) ITEMS.register("valor_hilt",
            () -> new HiltItem("green", new Item.Properties()));
    public static final DeferredItem<HiltItem> WISDOM_HILT = (DeferredItem<HiltItem>) ITEMS.register("wisdom_hilt",
            () -> new HiltItem("blue", new Item.Properties()));

    public static final DeferredItem<BoganPortalItem> BOGAN_PORTAL_ITEM = (DeferredItem<BoganPortalItem>) ITEMS.register("bogan_portal",
            () -> new BoganPortalItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<AshlaPortalItem> ASHLA_PORTAL_ITEM = (DeferredItem<AshlaPortalItem>) ITEMS.register("ashla_portal",
            () -> new AshlaPortalItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> ACID_FORGED_PLATE = (DeferredItem<Item>) ITEMS.register("acid_forged_plate",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ACIDIC_VENOM_SAC = (DeferredItem<Item>) ITEMS.register("acidic_venom_sac",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILK_THREAD = (DeferredItem<Item>) ITEMS.register("silk_thread",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHITIN_FRAGMENTS = (DeferredItem<Item>) ITEMS.register("chitin_fragments",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<HiltItem> NEGOTIATOR_HILT = (DeferredItem<HiltItem>) ITEMS.register("negotiator_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final DeferredItem<HiltItem> KNIGHTFALL_HILT = (DeferredItem<HiltItem>) ITEMS.register("knightfall_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final DeferredItem<HiltItem> BAROSHE_HILT = (DeferredItem<HiltItem>) ITEMS.register("baroshe_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final Map<String, DeferredHolder<Item, Item>> LIGHTSABERS = new HashMap<>();

    public static void registerLightsabers() {
        String[] bladeColors = {
                "red", "blue", "green", "yellow", "cyan",
                "white", "magenta", "purple", "pink",
                "lime_green", "turquoise", "orange", "blood_orange"
        };

        String[] hiltNames = {
                "apprentice", "chosen", "emperor", "legacy", "padawan",
                "resolve", "talon", "valor", "wisdom", "lost", "aegis",
                "grace", "guard", "harmony", "skustell", "fallen",
                "negotiator", "baroshe", "knightfall"
        };

        for (String color : bladeColors) {
            for (String hilt : hiltNames) {
                String id = color + "_" + hilt + "_lightsaber";
                LIGHTSABERS.put(id, ITEMS.register(id, () -> new LightsaberItem(color, new Item.Properties().durability(1))));
            }
        }
    }

    // #ENTITIES
    public static final Supplier<EntityType<AcidSpiderEntity>> ACID_SPIDER =
            ENTITY_TYPES.register("acid_spider", () -> EntityType.Builder.of(AcidSpiderEntity::new, MobCategory.CREATURE)
                    .sized(1.5f, 1.5f).build("acid_spider"));

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public galaxyunderchaos(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);
        CreativeMenuTabs.register(modEventBus);
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
//        ENTITY_TYPES.register(modEventBus);
        ModBiomes.BIOMES.register(modEventBus);
        registerLightsabers();
//        KeyBindings.init();
        ModSounds.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        ModDataComponentTypes.register(modEventBus);
//        NeoForge.EVENT_BUS.register(LightsaberBeltRenderer.class);
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
//        modEventBus.addListener(this::clientsetup);
//        NeoForge.EVENT_BUS.register(HyperspaceOverlayRenderer.class);
        ModLootModifiers.register(modEventBus);
        // Register the item to a creative tab
//        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
//    private void addCreative(BuildCreativeModeTabContentsEvent event)
//    {
//        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
//            event.accept(EXAMPLE_BLOCK_ITEM);
//    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
            ModItemProperties.addCustomItemProperties();
            galaxyunderchaos.LIGHTSABERS.values().forEach(lightsaber ->
                    ItemBlockRenderTypes.setRenderLayer(Block.byItem(lightsaber.get()), RenderType.translucent())
            );
        }
    }
}
