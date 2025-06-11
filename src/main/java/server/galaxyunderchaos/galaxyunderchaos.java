package server.galaxyunderchaos;

import client.renderer.*;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
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
import server.galaxyunderchaos.entity.ModBlockEntities;
import server.galaxyunderchaos.entity.ModEntityTypes;
import server.galaxyunderchaos.entity.WingmawEntity;
import server.galaxyunderchaos.item.*;
import server.galaxyunderchaos.lightsaber.LightsaberFormCapabilityHandler;
import server.galaxyunderchaos.lightsaber.LightsaberFormCommand;
import server.galaxyunderchaos.lightsaber.LightsaberFormNetworking;
import server.galaxyunderchaos.loot.ModLootModifiers;
import server.galaxyunderchaos.sound.ModSounds;
import server.galaxyunderchaos.worldgen.biome.ModBiomes;
import server.galaxyunderchaos.worldgen.tree.ModTreeGrowers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(galaxyunderchaos.MODID)
public class galaxyunderchaos {
    public static final String MODID = "galaxyunderchaos";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, galaxyunderchaos.MODID);

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

    public static final DeferredItem<Item> SHII_CHO_HOLOBOOK = ITEMS.register("shii_cho_holobook",
            () -> new SaberFormHolobookItem("Shii-Cho", new Item.Properties()));

    public static final DeferredItem<Item> MAKASHI_HOLBOOK = ITEMS.register("makashi_holobook",
            () -> new SaberFormHolobookItem("Makashi", new Item.Properties()));

    public static final DeferredItem<Item> SORESU_HOLOBOOK = ITEMS.register("soresu_holobook",
            () -> new SaberFormHolobookItem("Soresu", new Item.Properties()));

    public static final DeferredItem<Item> ATARU_HOLOBOOK = ITEMS.register("ataru_holobook",
            () -> new SaberFormHolobookItem("Ataru", new Item.Properties()));

    public static final DeferredItem<Item> SHIEN_DJEM_SO_HOLOBOOK = ITEMS.register("shien_djem_so_holobook",
            () -> new SaberFormHolobookItem("Shien / Djem So", new Item.Properties()));

    public static final DeferredItem<Item> NIMAN_HOLOBOOK = ITEMS.register("niman_holobook",
            () -> new SaberFormHolobookItem("Niman", new Item.Properties()));

    public static final DeferredItem<Item> JUYO_VAAPAD_HOLOBOOK = ITEMS.register("juyo_vaapad_holobook",
            () -> new SaberFormHolobookItem("Juyo / Vaapad", new Item.Properties()));

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


    public static final DeferredHolder<Block,TythonJediStatueHEAD> TYTHON_JEDI_IDLE_HEAD_STATUE = BLOCKS.register("tython_jedi_idle_head_statue", TythonJediStatueHEAD::new);
    public static final DeferredItem<Item> TYTHON_JEDI_IDLE_HEAD_STATUE_ITEM = ITEMS.register("tython_jedi_idle_head_statue", () -> new BlockItem(TYTHON_JEDI_IDLE_HEAD_STATUE.get(), new Item.Properties()));
    public static final DeferredHolder<Block,TythonJediStatueTORSO> TYTHON_JEDI_IDLE_TORSO_STATUE = BLOCKS.register("tython_jedi_idle_torso_statue", TythonJediStatueTORSO::new);
    public static final DeferredItem<Item> TYTHON_JEDI_IDLE_TORSO_STATUE_ITEM = ITEMS.register("tython_jedi_idle_torso_statue", () -> new BlockItem(TYTHON_JEDI_IDLE_TORSO_STATUE.get(), new Item.Properties()));
    public static final DeferredHolder<Block,TythonJediStatueLEG> TYTHON_JEDI_IDLE_LEG_1_STATUE = BLOCKS.register("tython_jedi_idle_leg_1_statue", TythonJediStatueLEG::new);
    public static final DeferredItem<Item> TYTHON_JEDI_IDLE_LEG_1_STATUE_ITEM = ITEMS.register("tython_jedi_idle_leg_1_statue", () -> new BlockItem(TYTHON_JEDI_IDLE_LEG_1_STATUE.get(), new Item.Properties()));
    public static final DeferredHolder<Block,TythonJediStatueLEG> TYTHON_JEDI_IDLE_LEG_2_STATUE = BLOCKS.register("tython_jedi_idle_leg_2_statue", TythonJediStatueLEG::new);
    public static final DeferredItem<Item> TYTHON_JEDI_IDLE_LEGS_2_STATUE_ITEM = ITEMS.register("tython_jedi_idle_leg_2_statue", () -> new BlockItem(TYTHON_JEDI_IDLE_LEG_2_STATUE.get(), new Item.Properties()));
    public static final DeferredHolder<Block,TythonJediStatueCTORSO> TYTHON_JEDI_CROSSED_TORSO_STATUE = BLOCKS.register("tython_jedi_crossed_torso_statue", TythonJediStatueCTORSO::new);
    public static final DeferredItem<Item> TYTHON_JEDI_CROSSED_TORSO_STATUE_ITEM = ITEMS.register("tython_jedi_crossed_torso_statue", () -> new BlockItem(TYTHON_JEDI_CROSSED_TORSO_STATUE.get(), new Item.Properties()));



    public static final DeferredHolder<Block, TempleStone> TEMPLE_STONE = BLOCKS.register("temple_stone", TempleStone::new);
    public static final DeferredItem<BlockItem> TEMPLE_STONE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("temple_stone",
            () -> new BlockItem(TEMPLE_STONE.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TempleStonePillar> TEMPLE_STONE_PILLAR = BLOCKS.register("temple_stone_pillar", TempleStonePillar::new);
    public static final DeferredItem<BlockItem> TEMPLE_STONE_PILLAR_ITEM = (DeferredItem<BlockItem>) ITEMS.register("temple_stone_pillar",
            () -> new BlockItem(TEMPLE_STONE_PILLAR.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TempleStoneHolobook> TEMPLE_STONE_HOLOBOOK = BLOCKS.register("temple_stone_holobook", TempleStoneHolobook::new);
    public static final DeferredItem<BlockItem> TEMPLE_STONE_HOLOBOOK_ITEM = (DeferredItem<BlockItem>) ITEMS.register("temple_stone_holobook",
            () -> new BlockItem(TEMPLE_STONE_HOLOBOOK.get(), new Item.Properties()));
    public static final DeferredHolder<Block, TempleStoneHolobook> TEMPLE_STONE_LIGHTS = BLOCKS.register("temple_stone_lights", TempleStoneHolobook::new);
    public static final DeferredItem<BlockItem> TEMPLE_STONE_LIGHTS_ITEM = (DeferredItem<BlockItem>) ITEMS.register("temple_stone_lights",
            () -> new BlockItem(TEMPLE_STONE_LIGHTS.get(), new Item.Properties()));

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
    public static final DeferredHolder<Block, DarkTempleStoneHolobook> DARK_TEMPLE_STONE_LIGHTS = BLOCKS.register("dark_temple_stone_lights", DarkTempleStoneHolobook::new);
    public static final DeferredItem<BlockItem> DARK_TEMPLE_STONE_LIGHTS_ITEM = (DeferredItem<BlockItem>) ITEMS.register("dark_temple_stone_lights",
            () -> new BlockItem(DARK_TEMPLE_STONE_LIGHTS.get(), new Item.Properties()));

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
            .alwaysEdible().nutrition(4).saturationModifier(2f).build())));
    public static final DeferredItem<Item> HEART_BERRY = (DeferredItem<Item>) ITEMS.register("heart_berry", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(8).saturationModifier(2f).build())));
    public static final DeferredHolder<Block, BleedingTable> BLEEDING_TABLE = BLOCKS.register("bleeding_table", BleedingTable::new);

    public static final DeferredItem<BlockItem> BLEEDING_TABLE_ITEM = (DeferredItem<BlockItem>) ITEMS.register("bleeding_table",
            () -> new BlockItem(BLEEDING_TABLE.get(), new Item.Properties()));

    public static final DeferredHolder<Block, Holocron> JEDI_HOLOCRON = BLOCKS.register("jedi_holocron", Holocron::new);
    public static final DeferredItem<BlockItem> JEDI_HOLOCRON_ITEM = (DeferredItem<BlockItem>) ITEMS.register("jedi_holocron",
            () -> new BlockItem(JEDI_HOLOCRON.get(), new Item.Properties()));

    public static final DeferredHolder<Block, Holocron> SITH_HOLOCRON = BLOCKS.register("sith_holocron", Holocron::new);
    public static final DeferredItem<BlockItem> SITH_HOLOCRON_ITEM = (DeferredItem<BlockItem>) ITEMS.register("sith_holocron",
            () -> new BlockItem(SITH_HOLOCRON.get(), new Item.Properties()));


    public static final DeferredHolder<Block, Holocron> ANCIENT_HOLOCRON = BLOCKS.register("ancient_holocron", Holocron::new);
    public static final DeferredItem<BlockItem> ANCIENT_HOLOCRON_ITEM = (DeferredItem<BlockItem>) ITEMS.register("ancient_holocron",
            () -> new BlockItem(ANCIENT_HOLOCRON.get(), new Item.Properties()));

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


    public static final BlockSetType AK_BLOCK_SET =
            BlockSetType.register(new BlockSetType("ak"));
    public static final WoodType AK_WOOD_TYPE =
            WoodType.register(new WoodType("ak", AK_BLOCK_SET));
    public static final BlockSetType HEART_BERRY_BLOCK_SET =
            BlockSetType.register(new BlockSetType("heart_berry"));
    public static final WoodType HEART_BERRY_WOOD_TYPE =
            WoodType.register(new WoodType("heart_berry", HEART_BERRY_BLOCK_SET));

    /* ──  Tython Temple-Stone family  ───────────────────────── */
    public static final DeferredHolder<Block, TempleStone> TYTHON_TEMPLE_STONE =
            BLOCKS.register("tython_temple_stone", TempleStone::new);
    public static final DeferredItem<BlockItem> TYTHON_TEMPLE_STONE_ITEM =
            ITEMS.register("tython_temple_stone",
                    () -> new BlockItem(TYTHON_TEMPLE_STONE.get(), new Item.Properties()));
    public static final DeferredHolder<Block, ChiseledTempleStoneBlock>
            CHISELED_TYTHON_TEMPLE_STONE = BLOCKS.register(
            "chiseled_tython_temple_stone", ChiseledTempleStoneBlock::new);

    public static final DeferredItem<BlockItem>
            CHISELED_TYTHON_TEMPLE_STONE_ITEM = ITEMS.register(
            "chiseled_tython_temple_stone",
            () -> new BlockItem(CHISELED_TYTHON_TEMPLE_STONE.get(), new Item.Properties()));
    public static final DeferredHolder<Block, TempleStoneHolobook> TYTHON_TEMPLE_STONE_LIGHTS = BLOCKS.register("tython_temple_stone_lights", TempleStoneHolobook::new);
    public static final DeferredItem<BlockItem> TYTHON_TEMPLE_STONE_LIGHTS_ITEM = (DeferredItem<BlockItem>) ITEMS.register("tython_temple_stone_lights",
            () -> new BlockItem(TYTHON_TEMPLE_STONE_LIGHTS.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TempleStonePillar> TYTHON_TEMPLE_STONE_PILLAR =
            BLOCKS.register("tython_temple_stone_pillar", TempleStonePillar::new);
    public static final DeferredItem<BlockItem> TYTHON_TEMPLE_STONE_PILLAR_ITEM =
            ITEMS.register("tython_temple_stone_pillar",
                    () -> new BlockItem(TYTHON_TEMPLE_STONE_PILLAR.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TempleStoneStairs> TYTHON_TEMPLE_STONE_STAIRS =
            BLOCKS.register("tython_temple_stone_stairs",
                    () -> new TempleStoneStairs(TYTHON_TEMPLE_STONE.get().defaultBlockState()));
    public static final DeferredItem<BlockItem> TYTHON_TEMPLE_STONE_STAIRS_ITEM =
            ITEMS.register("tython_temple_stone_stairs",
                    () -> new BlockItem(TYTHON_TEMPLE_STONE_STAIRS.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TempleStoneSlab> TYTHON_TEMPLE_STONE_SLAB =
            BLOCKS.register("tython_temple_stone_slab", TempleStoneSlab::new);
    public static final DeferredItem<BlockItem> TYTHON_TEMPLE_STONE_SLAB_ITEM =
            ITEMS.register("tython_temple_stone_slab",
                    () -> new BlockItem(TYTHON_TEMPLE_STONE_SLAB.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TempleStoneWall> TYTHON_TEMPLE_STONE_WALL =
            BLOCKS.register("tython_temple_stone_wall", TempleStoneWall::new);
    public static final DeferredItem<BlockItem> TYTHON_TEMPLE_STONE_WALL_ITEM =
            ITEMS.register("tython_temple_stone_wall",
                    () -> new BlockItem(TYTHON_TEMPLE_STONE_WALL.get(), new Item.Properties()));

    /* ──  Ashla Temple-Stone family  ─────────────────────────── */
    public static final DeferredHolder<Block, TempleStone> ASHLA_TEMPLE_STONE =
            BLOCKS.register("ashla_temple_stone", TempleStone::new);
    public static final DeferredItem<BlockItem> ASHLA_TEMPLE_STONE_ITEM =
            ITEMS.register("ashla_temple_stone",
                    () -> new BlockItem(ASHLA_TEMPLE_STONE.get(), new Item.Properties()));
    public static final DeferredHolder<Block, TempleStonePillar> ASHLA_TEMPLE_STONE_PILLAR =
            BLOCKS.register("ashla_temple_stone_pillar", TempleStonePillar::new);
    public static final DeferredItem<BlockItem> ASHLA_TEMPLE_STONE_PILLAR_ITEM =
            ITEMS.register("ashla_temple_stone_pillar",
                    () -> new BlockItem(ASHLA_TEMPLE_STONE_PILLAR.get(), new Item.Properties()));
    public static final DeferredHolder<Block, TempleStoneStairs> ASHLA_TEMPLE_STONE_STAIRS =
            BLOCKS.register("ashla_temple_stone_stairs",
                    () -> new TempleStoneStairs(ASHLA_TEMPLE_STONE.get().defaultBlockState()));
    public static final DeferredItem<BlockItem> ASHLA_TEMPLE_STONE_STAIRS_ITEM =
            ITEMS.register("ashla_temple_stone_stairs",
                    () -> new BlockItem(ASHLA_TEMPLE_STONE_STAIRS.get(), new Item.Properties()));
    public static final DeferredHolder<Block, TempleStoneSlab> ASHLA_TEMPLE_STONE_SLAB =
            BLOCKS.register("ashla_temple_stone_slab", TempleStoneSlab::new);
    public static final DeferredItem<BlockItem> ASHLA_TEMPLE_STONE_SLAB_ITEM =
            ITEMS.register("ashla_temple_stone_slab",
                    () -> new BlockItem(ASHLA_TEMPLE_STONE_SLAB.get(), new Item.Properties()));
    public static final DeferredHolder<Block, TempleStoneWall> ASHLA_TEMPLE_STONE_WALL =
            BLOCKS.register("ashla_temple_stone_wall", TempleStoneWall::new);
    public static final DeferredItem<BlockItem> ASHLA_TEMPLE_STONE_WALL_ITEM =
            ITEMS.register("ashla_temple_stone_wall",
                    () -> new BlockItem(ASHLA_TEMPLE_STONE_WALL.get(), new Item.Properties()));

    /* ──  Bogan Temple-Stone family  ─────────────────────────── */
    public static final DeferredHolder<Block, TempleStone> BOGAN_TEMPLE_STONE =
            BLOCKS.register("bogan_temple_stone", TempleStone::new);
    public static final DeferredItem<BlockItem> BOGAN_TEMPLE_STONE_ITEM =
            ITEMS.register("bogan_temple_stone",
                    () -> new BlockItem(BOGAN_TEMPLE_STONE.get(), new Item.Properties()));
    public static final DeferredHolder<Block, TempleStonePillar> BOGAN_TEMPLE_STONE_PILLAR =
            BLOCKS.register("bogan_temple_stone_pillar", TempleStonePillar::new);
    public static final DeferredItem<BlockItem> BOGAN_TEMPLE_STONE_PILLAR_ITEM =
            ITEMS.register("bogan_temple_stone_pillar",
                    () -> new BlockItem(BOGAN_TEMPLE_STONE_PILLAR.get(), new Item.Properties()));
    public static final DeferredHolder<Block, TempleStoneStairs> BOGAN_TEMPLE_STONE_STAIRS =
            BLOCKS.register("bogan_temple_stone_stairs",
                    () -> new TempleStoneStairs(BOGAN_TEMPLE_STONE.get().defaultBlockState()));
    public static final DeferredItem<BlockItem> BOGAN_TEMPLE_STONE_STAIRS_ITEM =
            ITEMS.register("bogan_temple_stone_stairs",
                    () -> new BlockItem(BOGAN_TEMPLE_STONE_STAIRS.get(), new Item.Properties()));
    public static final DeferredHolder<Block, TempleStoneSlab> BOGAN_TEMPLE_STONE_SLAB =
            BLOCKS.register("bogan_temple_stone_slab", TempleStoneSlab::new);
    public static final DeferredItem<BlockItem> BOGAN_TEMPLE_STONE_SLAB_ITEM =
            ITEMS.register("bogan_temple_stone_slab",
                    () -> new BlockItem(BOGAN_TEMPLE_STONE_SLAB.get(), new Item.Properties()));
    public static final DeferredHolder<Block, TempleStoneWall> BOGAN_TEMPLE_STONE_WALL =
            BLOCKS.register("bogan_temple_stone_wall", TempleStoneWall::new);
    public static final DeferredItem<BlockItem> BOGAN_TEMPLE_STONE_WALL_ITEM =
            ITEMS.register("bogan_temple_stone_wall",
                    () -> new BlockItem(BOGAN_TEMPLE_STONE_WALL.get(), new Item.Properties()));
    /* ────────────────────────────────────────────────
       1.  LOGS / WOODS (Ak + Heart-Berry)
       ─────────────────────────────────────────────── */
// Heart-Berry
    public static final DeferredHolder<Block, RotatedPillarBlock> HEART_BERRY_LOG =
            BLOCKS.register("heart_berry_log",
                    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredHolder<Block, RotatedPillarBlock> HEART_BERRY_WOOD =
            BLOCKS.register("heart_berry_wood",
                    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final DeferredHolder<Block, RotatedPillarBlock> STRIPPED_HEART_BERRY_LOG =
            BLOCKS.register("stripped_heart_berry_log",
                    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredHolder<Block, RotatedPillarBlock> STRIPPED_HEART_BERRY_WOOD =
            BLOCKS.register("stripped_heart_berry_wood",
                    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    // Ak
    public static final DeferredHolder<Block, RotatedPillarBlock> AK_LOG =
            BLOCKS.register("ak_log",
                    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredHolder<Block, RotatedPillarBlock> AK_WOOD =
            BLOCKS.register("ak_wood",
                    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final DeferredHolder<Block, RotatedPillarBlock> STRIPPED_AK_LOG =
            BLOCKS.register("stripped_ak_log",
                    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredHolder<Block, RotatedPillarBlock> STRIPPED_AK_WOOD =
            BLOCKS.register("stripped_ak_wood",
                    () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    /* BlockItems – NeoForge uses DeferredItem */
    public static final DeferredItem<BlockItem> HEART_BERRY_LOG_ITEM =
            ITEMS.register("heart_berry_log", () -> new BlockItem(HEART_BERRY_LOG.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> HEART_BERRY_WOOD_ITEM =
            ITEMS.register("heart_berry_wood", () -> new BlockItem(HEART_BERRY_WOOD.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> STRIPPED_HEART_BERRY_LOG_ITEM =
            ITEMS.register("stripped_heart_berry_log", () -> new BlockItem(STRIPPED_HEART_BERRY_LOG.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> STRIPPED_HEART_BERRY_WOOD_ITEM =
            ITEMS.register("stripped_heart_berry_wood", () -> new BlockItem(STRIPPED_HEART_BERRY_WOOD.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> AK_LOG_ITEM =
            ITEMS.register("ak_log", () -> new BlockItem(AK_LOG.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> AK_WOOD_ITEM =
            ITEMS.register("ak_wood", () -> new BlockItem(AK_WOOD.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> STRIPPED_AK_LOG_ITEM =
            ITEMS.register("stripped_ak_log", () -> new BlockItem(STRIPPED_AK_LOG.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> STRIPPED_AK_WOOD_ITEM =
            ITEMS.register("stripped_ak_wood", () -> new BlockItem(STRIPPED_AK_WOOD.get(), new Item.Properties()));

    /* ────────────────────────────────────────────────
       2.  PLANKS (custom flammability kept)
       ─────────────────────────────────────────────── */
    public static final DeferredHolder<Block, Block> AK_PLANKS =
            BLOCKS.register("ak_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {
                @Override public boolean isFlammable(BlockState s, BlockGetter l, BlockPos p, Direction d) { return true; }
                @Override public int getFlammability(BlockState s, BlockGetter l, BlockPos p, Direction d) { return 20; }
                @Override public int getFireSpreadSpeed(BlockState s, BlockGetter l, BlockPos p, Direction d) { return 5; }
            });
    public static final DeferredItem<BlockItem> AK_PLANKS_ITEM =
            ITEMS.register("ak_planks", () -> new BlockItem(AK_PLANKS.get(), new Item.Properties()));

    public static final DeferredHolder<Block, Block> HEART_BERRY_PLANKS =
            BLOCKS.register("heart_berry_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {
                @Override public boolean isFlammable(BlockState s, BlockGetter l, BlockPos p, Direction d) { return true; }
                @Override public int getFlammability(BlockState s, BlockGetter l, BlockPos p, Direction d) { return 20; }
                @Override public int getFireSpreadSpeed(BlockState s, BlockGetter l, BlockPos p, Direction d) { return 5; }
            });
    public static final DeferredItem<BlockItem> HEART_BERRY_PLANKS_ITEM =
            ITEMS.register("heart_berry_planks", () -> new BlockItem(HEART_BERRY_PLANKS.get(), new Item.Properties()));

    /* ────────────────────────────────────────────────
       3.  LEAVES (flammable) + BlockItems
       ─────────────────────────────────────────────── */
    public static final DeferredHolder<Block, LeavesBlock> AK_LEAVES =
            BLOCKS.register("ak_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override public boolean isFlammable(BlockState s, BlockGetter l, BlockPos p, Direction d) { return true; }
                @Override public int getFlammability(BlockState s, BlockGetter l, BlockPos p, Direction d) { return 60; }
                @Override public int getFireSpreadSpeed(BlockState s, BlockGetter l, BlockPos p, Direction d) { return 30; }
            });
    public static final DeferredItem<BlockItem> AK_LEAVES_ITEM =
            ITEMS.register("ak_leaves", () -> new BlockItem(AK_LEAVES.get(), new Item.Properties()));

    public static final DeferredHolder<Block, LeavesBlock> HEART_BERRY_LEAVES =
            BLOCKS.register("heart_berry_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override public boolean isFlammable(BlockState s, BlockGetter l, BlockPos p, Direction d) { return true; }
                @Override public int getFlammability(BlockState s, BlockGetter l, BlockPos p, Direction d) { return 60; }
                @Override public int getFireSpreadSpeed(BlockState s, BlockGetter l, BlockPos p, Direction d) { return 30; }
            });
    public static final DeferredHolder<Block, LeavesBlock> HEART_BERRY_FRUIT_LEAVES =
            BLOCKS.register("heart_berry_fruit_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override public boolean isFlammable(BlockState s, BlockGetter l, BlockPos p, Direction d) { return true; }
                @Override public int getFlammability(BlockState s, BlockGetter l, BlockPos p, Direction d) { return 60; }
                @Override public int getFireSpreadSpeed(BlockState s, BlockGetter l, BlockPos p, Direction d) { return 30; }
            });
    public static final DeferredItem<BlockItem> HEART_BERRY_LEAVES_ITEM =
            ITEMS.register("heart_berry_leaves", () -> new BlockItem(HEART_BERRY_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> HEART_BERRY_FRUIT_LEAVES_ITEM =
            ITEMS.register("heart_berry_fruit_leaves", () -> new BlockItem(HEART_BERRY_FRUIT_LEAVES.get(), new Item.Properties()));
    /* ─────────── Ak door, trapdoor, fence parts ─────────── */
    public static final DeferredHolder<Block, TreeDoor> AK_DOOR_BLOCK =
            BLOCKS.register("ak_door", () -> new TreeDoor(AK_BLOCK_SET));
    public static final DeferredItem<BlockItem> AK_DOOR_ITEM =
            ITEMS.register("ak_door", () -> new BlockItem(AK_DOOR_BLOCK.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TreeTrapdoor> AK_TRAPDOOR_BLOCK =
            BLOCKS.register("ak_trapdoor", () -> new TreeTrapdoor(AK_BLOCK_SET));
    public static final DeferredItem<BlockItem> AK_TRAPDOOR_ITEM =
            ITEMS.register("ak_trapdoor", () -> new BlockItem(AK_TRAPDOOR_BLOCK.get(), new Item.Properties()));

    public static final DeferredHolder<Block, FenceGateBlock> AK_FENCE_GATE =
            BLOCKS.register("ak_fence_gate",
                    () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredItem<BlockItem> AK_FENCE_GATE_ITEM =
            ITEMS.register("ak_fence_gate", () -> new BlockItem(AK_FENCE_GATE.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TreeFence> AK_FENCE_BLOCK =
            BLOCKS.register("ak_fence", TreeFence::new);
    public static final DeferredItem<BlockItem> AK_FENCE_ITEM =
            ITEMS.register("ak_fence", () -> new BlockItem(AK_FENCE_BLOCK.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TreeStairs> AK_STAIRS =
            BLOCKS.register("ak_stairs", () -> new TreeStairs(AK_PLANKS.get().defaultBlockState()));
    public static final DeferredItem<BlockItem> AK_STAIRS_ITEM =
            ITEMS.register("ak_stairs", () -> new BlockItem(AK_STAIRS.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TreeSlab> AK_SLAB =
            BLOCKS.register("ak_slab", TreeSlab::new);
    public static final DeferredItem<BlockItem> AK_SLAB_ITEM =
            ITEMS.register("ak_slab", () -> new BlockItem(AK_SLAB.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TreePressurePlate> AK_PRESSURE_PLATE =
            BLOCKS.register("ak_pressure_plate", () -> new TreePressurePlate(AK_BLOCK_SET));
    public static final DeferredItem<BlockItem> AK_PRESSURE_PLATE_ITEM =
            ITEMS.register("ak_pressure_plate", () -> new BlockItem(AK_PRESSURE_PLATE.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TreeButton> AK_BUTTON =
            BLOCKS.register("ak_button", () -> new TreeButton(AK_BLOCK_SET));
    public static final DeferredItem<BlockItem> AK_BUTTON_ITEM =
            ITEMS.register("ak_button", () -> new BlockItem(AK_BUTTON.get(), new Item.Properties()));



    /* ─────────── Heart-Berry door, trapdoor, fence parts ─────────── */
    public static final DeferredHolder<Block, TreeDoor> HEART_BERRY_DOOR_BLOCK =
            BLOCKS.register("heart_berry_door", () -> new TreeDoor(HEART_BERRY_BLOCK_SET));
    public static final DeferredItem<BlockItem> HEART_BERRY_DOOR_ITEM =
            ITEMS.register("heart_berry_door", () -> new BlockItem(HEART_BERRY_DOOR_BLOCK.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TreeTrapdoor> HEART_BERRY_TRAPDOOR_BLOCK =
            BLOCKS.register("heart_berry_trapdoor", () -> new TreeTrapdoor(HEART_BERRY_BLOCK_SET));
    public static final DeferredItem<BlockItem> HEART_BERRY_TRAPDOOR_ITEM =
            ITEMS.register("heart_berry_trapdoor", () -> new BlockItem(HEART_BERRY_TRAPDOOR_BLOCK.get(), new Item.Properties()));

    public static final DeferredHolder<Block, FenceGateBlock> HEART_BERRY_FENCE_GATE =
            BLOCKS.register("heart_berry_fence_gate",
                    () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredItem<BlockItem> HEART_BERRY_FENCE_GATE_ITEM =
            ITEMS.register("heart_berry_fence_gate", () -> new BlockItem(HEART_BERRY_FENCE_GATE.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TreeFence> HEART_BERRY_FENCE_BLOCK =
            BLOCKS.register("heart_berry_fence", TreeFence::new);
    public static final DeferredItem<BlockItem> HEART_BERRY_FENCE_ITEM =
            ITEMS.register("heart_berry_fence", () -> new BlockItem(HEART_BERRY_FENCE_BLOCK.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TreeStairs> HEART_BERRY_STAIRS =
            BLOCKS.register("heart_berry_stairs", () -> new TreeStairs(HEART_BERRY_PLANKS.get().defaultBlockState()));
    public static final DeferredItem<BlockItem> HEART_BERRY_STAIRS_ITEM =
            ITEMS.register("heart_berry_stairs", () -> new BlockItem(HEART_BERRY_STAIRS.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TreeSlab> HEART_BERRY_SLAB =
            BLOCKS.register("heart_berry_slab", TreeSlab::new);
    public static final DeferredItem<BlockItem> HEART_BERRY_SLAB_ITEM =
            ITEMS.register("heart_berry_slab", () -> new BlockItem(HEART_BERRY_SLAB.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TreePressurePlate> HEART_BERRY_PRESSURE_PLATE =
            BLOCKS.register("heart_berry_pressure_plate", () -> new TreePressurePlate(HEART_BERRY_BLOCK_SET));
    public static final DeferredItem<BlockItem> HEART_BERRY_PRESSURE_PLATE_ITEM =
            ITEMS.register("heart_berry_pressure_plate", () -> new BlockItem(HEART_BERRY_PRESSURE_PLATE.get(), new Item.Properties()));

    public static final DeferredHolder<Block, TreeButton> HEART_BERRY_BUTTON =
            BLOCKS.register("heart_berry_button", () -> new TreeButton(HEART_BERRY_BLOCK_SET));
    public static final DeferredItem<BlockItem> HEART_BERRY_BUTTON_ITEM =
            ITEMS.register("heart_berry_button", () -> new BlockItem(HEART_BERRY_BUTTON.get(), new Item.Properties()));

    /* ────────────────────────────────────────────────
       4.  SAPLINGS
       ─────────────────────────────────────────────── */
    public static final DeferredHolder<Block, SaplingBlock> AK_SAPLING =
            BLOCKS.register("ak_sapling", () -> new SaplingBlock(ModTreeGrowers.AK_TREE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredHolder<Block, SaplingBlock> HEART_BERRY_SAPLING =
            BLOCKS.register("heart_berry_sapling", () -> new SaplingBlock(ModTreeGrowers.HEART_BERRY_TREE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredItem<BlockItem> AK_SAPLING_ITEM =
            ITEMS.register("ak_sapling", () -> new BlockItem(AK_SAPLING.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> HEART_BERRY_SAPLING_ITEM =
            ITEMS.register("heart_berry_sapling", () -> new BlockItem(HEART_BERRY_SAPLING.get(), new Item.Properties()));

    /* Plain crafting component */
    public static final DeferredItem<Item> TEMPLE_GUARD_FABRIC =
            ITEMS.register("temple_guard_fabric",
                    () -> new Item(new Item.Properties()));

    /* Armor set */
    public static final DeferredItem<ArmorItem> TEMPLE_GUARD_HELMET =
            ITEMS.register("temple_guard_helmet",
                    () -> new ArmorItem(CustomArmor.TEMPLE_GUARD_ARMOR_MATERIAL,
                            ArmorItem.Type.HELMET,
                            new Item.Properties()
                                    .durability(ArmorItem.Type.HELMET.getDurability(15))));

    public static final DeferredItem<ArmorItem> TEMPLE_GUARD_CHESTPLATE =
            ITEMS.register("temple_guard_chestplate",
                    () -> new ArmorItem(CustomArmor.TEMPLE_GUARD_ARMOR_MATERIAL,
                            ArmorItem.Type.CHESTPLATE,
                            new Item.Properties()
                                    .durability(ArmorItem.Type.CHESTPLATE.getDurability(15))));

    public static final DeferredItem<ArmorItem> TEMPLE_GUARD_LEGGINGS =
            ITEMS.register("temple_guard_leggings",
                    () -> new ArmorItem(CustomArmor.TEMPLE_GUARD_ARMOR_MATERIAL,
                            ArmorItem.Type.LEGGINGS,
                            new Item.Properties()
                                    .durability(ArmorItem.Type.LEGGINGS.getDurability(15))));

    public static final DeferredItem<ArmorItem> TEMPLE_GUARD_BOOTS =
            ITEMS.register("temple_guard_boots",
                    () -> new ArmorItem(CustomArmor.TEMPLE_GUARD_ARMOR_MATERIAL,
                            ArmorItem.Type.BOOTS,
                            new Item.Properties()
                                    .durability(ArmorItem.Type.BOOTS.getDurability(15))));
    /* ───────────── Ak signs & hanging-signs ───────────── */
    public static final DeferredHolder<Block, TreeStandingSign> AK_SIGN =
            BLOCKS.register("ak_sign", () -> new TreeStandingSign(AK_WOOD_TYPE));
    public static final DeferredHolder<Block, TreeWallSign> AK_WALL_SIGN =
            BLOCKS.register("ak_wall_sign", () -> new TreeWallSign(AK_WOOD_TYPE));

    public static final DeferredItem<SignItem> AK_SIGN_ITEM =
            ITEMS.register("ak_sign",
                    () -> new SignItem(new Item.Properties(), AK_SIGN.get(), AK_WALL_SIGN.get()));

    public static final DeferredHolder<Block, TreeHangingSign> AK_HANGING_SIGN =
            BLOCKS.register("ak_hanging_sign", () -> new TreeHangingSign(AK_WOOD_TYPE));
    public static final DeferredHolder<Block, TreeWallHangingSign> AK_WALL_HANGING_SIGN =
            BLOCKS.register("ak_wall_hanging_sign", () -> new TreeWallHangingSign(AK_WOOD_TYPE));

    public static final DeferredItem<HangingSignItem> AK_HANGING_SIGN_ITEM =
            ITEMS.register("ak_hanging_sign",
                    () -> new HangingSignItem(AK_HANGING_SIGN.get(),
                            AK_WALL_HANGING_SIGN.get(),
                            new Item.Properties()));

    /* ───────────── Heart-Berry signs & hanging-signs ───── */
    public static final DeferredHolder<Block, TreeStandingSignHB> HEART_BERRY_SIGN =
            BLOCKS.register("heart_berry_sign", () -> new TreeStandingSignHB(HEART_BERRY_WOOD_TYPE));
    public static final DeferredHolder<Block, TreeWallSignHB> HEART_BERRY_WALL_SIGN =
            BLOCKS.register("heart_berry_wall_sign", () -> new TreeWallSignHB(HEART_BERRY_WOOD_TYPE));

    public static final DeferredItem<SignItem> HEART_BERRY_SIGN_ITEM =
            ITEMS.register("heart_berry_sign",
                    () -> new SignItem(new Item.Properties(), HEART_BERRY_SIGN.get(), HEART_BERRY_WALL_SIGN.get()));

    public static final DeferredHolder<Block, TreeHangingSignHB> HEART_BERRY_HANGING_SIGN =
            BLOCKS.register("heart_berry_hanging_sign", () -> new TreeHangingSignHB(HEART_BERRY_WOOD_TYPE));
    public static final DeferredHolder<Block, TreeWallHangingSignHB> HEART_BERRY_WALL_HANGING_SIGN =
            BLOCKS.register("heart_berry_wall_hanging_sign", () -> new TreeWallHangingSignHB(HEART_BERRY_WOOD_TYPE));

    public static final DeferredItem<HangingSignItem> HEART_BERRY_HANGING_SIGN_ITEM =
            ITEMS.register("heart_berry_hanging_sign",
                    () -> new HangingSignItem(HEART_BERRY_HANGING_SIGN.get(),
                            HEART_BERRY_WALL_HANGING_SIGN.get(),
                            new Item.Properties()));

    /* ───────────── Boats & chest-boats ─────────────────── */
    public static final DeferredItem<ModBoatItem> AK_BOAT =
            ITEMS.register("ak_boat",
                    () -> new ModBoatItem(false, ModEntityTypes.AK_BOAT::get, new Item.Properties()));
    public static final DeferredItem<ModBoatItem> AK_CHEST_BOAT =
            ITEMS.register("ak_chest_boat",
                    () -> new ModBoatItem(false, ModEntityTypes.AK_CHEST_BOAT::get, new Item.Properties()));

    public static final DeferredItem<ModBoatItem> HEART_BERRY_BOAT =
            ITEMS.register("heart_berry_boat",
                    () -> new ModBoatItem(false, ModEntityTypes.HEART_BERRY_BOAT::get, new Item.Properties()));
    public static final DeferredItem<ModBoatItem> HEART_BERRY_CHEST_BOAT =
            ITEMS.register("heart_berry_chest_boat",
                    () -> new ModBoatItem(false, ModEntityTypes.HEART_BERRY_CHEST_BOAT::get, new Item.Properties()));

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
    public static final Supplier<EntityType<WingmawEntity>> WINGMAW =
            ENTITY_TYPES.register("wingmaw",
                    () -> EntityType.Builder.of(WingmawEntity::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f).build("wingmaw"));
    public static final DeferredItem<DeferredSpawnEggItem> WINGMAW_SPAWN_EGG =
            ITEMS.register("wingmaw_spawn_egg",
                    () -> new DeferredSpawnEggItem(WINGMAW, 0x53524b, 0xdac741, new Item.Properties()));

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public galaxyunderchaos(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        CreativeMenuTabs.register(modEventBus);
        ModBiomes.BIOMES.register(modEventBus);
        ModSounds.register(modEventBus);
        ModDataComponentTypes.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        LightsaberFormNetworking.register(modEventBus);
        NeoForge.EVENT_BUS.register(this); // necessary for @SubscribeEvent methods here
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        registerLightsabers();
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        LightsaberFormCommand.register(event.getDispatcher());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        event.enqueueWork(DefaultAttributes::validate);
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
    private void clientSetup(final FMLClientSetupEvent event) {
        EntityRenderers.register(galaxyunderchaos.ACID_SPIDER.get(), AcidSpiderRenderer::new);
        EntityRenderers.register(galaxyunderchaos.WINGMAW.get(), WingmawRenderer::new);

        event.enqueueWork(() -> {
            ModItemProperties.addCustomItemProperties();
            Minecraft mc = Minecraft.getInstance();

            mc.getEntityRenderDispatcher()
                    .getSkinMap()
                    .values()
                    .forEach(renderer -> {
//                        if (renderer instanceof PlayerRenderer pr) {
//                            pr.addLayer(new LightsaberFirstPersonLayer(pr));
//                        }
                    });
        });

        NeoForge.EVENT_BUS.addListener(ClientEventSubscriber::onClientTick);
    }
    @EventBusSubscriber(
            modid = galaxyunderchaos.MODID,
            bus   = EventBusSubscriber.Bus.MOD,
            value = Dist.CLIENT
    )
    public class ModClient {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(galaxyunderchaos.ACID_SPIDER.get(), AcidSpiderRenderer::new);
            EntityRenderers.register(galaxyunderchaos.WINGMAW.get(), WingmawRenderer::new);
            event.enqueueWork(() -> {
                EntityRenderers.register(ModEntityTypes.AK_BOAT.get(), pContext -> new AkBoatRenderer(pContext, false));
                EntityRenderers.register(ModEntityTypes.AK_CHEST_BOAT.get(), pContext -> new AkBoatRenderer(pContext, true));
                EntityRenderers.register(ModEntityTypes.HEART_BERRY_BOAT.get(), pContext -> new HBBoatRenderer(pContext, false));
                EntityRenderers.register(ModEntityTypes.HEART_BERRY_CHEST_BOAT.get(), pContext -> new HBBoatRenderer(pContext, true));
                Sheets.addWoodType(galaxyunderchaos.AK_WOOD_TYPE);
                Sheets.addWoodType(galaxyunderchaos.HEART_BERRY_WOOD_TYPE);

                BlockEntityRenderers.register(ModBlockEntities.AK_SIGN_BE.get(),    SignRenderer::new);
                BlockEntityRenderers.register(ModBlockEntities.AK_HANGING_SIGN_BE.get(), HangingSignRenderer::new);
                BlockEntityRenderers.register(ModBlockEntities.HEART_BERRY_SIGN_BE.get(),    SignRenderer::new);
                BlockEntityRenderers.register(ModBlockEntities.HEART_BERRY_HANGING_SIGN_BE.get(), HangingSignRenderer::new);


            });
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

}