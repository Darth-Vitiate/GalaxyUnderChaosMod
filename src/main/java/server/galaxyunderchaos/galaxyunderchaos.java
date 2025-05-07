package server.galaxyunderchaos;

import client.renderer.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import server.galaxyunderchaos.block.*;
import server.galaxyunderchaos.data.KeyBindings;
import server.galaxyunderchaos.data.ModDataComponentTypes;
import server.galaxyunderchaos.entity.AcidSpiderEntity;
import server.galaxyunderchaos.event.LightsaberFormEventHandler;
import server.galaxyunderchaos.item.*;
import server.galaxyunderchaos.lightsaber.LightsaberFormNetworking;
import server.galaxyunderchaos.loot.ModLootModifiers;
import server.galaxyunderchaos.sound.ModSounds;
import server.galaxyunderchaos.worldgen.biome.ModBiomes;
import server.galaxyunderchaos.worldgen.tree.ModTreeGrowers;

import java.util.HashMap;
import java.util.Map;

@Mod(galaxyunderchaos.MODID)public class galaxyunderchaos {
    public static final String MODID = "galaxyunderchaos";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    // #BLOCKS
    public static final RegistryObject<Block> CHROMIUM_ORE = BLOCKS.register("chromium_ore", EarthCrystalOre::new);
    public static final RegistryObject<Block> CHROMIUM_DEEPSLATE_ORE = BLOCKS.register("chromium_deepslate_ore", EarthCrystalOre::new);
    public static final RegistryObject<Block> TITANIUM_ORE = BLOCKS.register("titanium_ore", EarthCrystalOre::new);
    public static final RegistryObject<Block> TITANIUM_DEEPSLATE_ORE = BLOCKS.register("titanium_deepslate_ore", EarthCrystalOre::new);
    public static final RegistryObject<Block> BLUE_CRYSTAL_ORE = BLOCKS.register("blue_crystal_ore", CrystalOre::new);
    public static final RegistryObject<Block> ORANGE_CRYSTAL_ORE = BLOCKS.register("orange_crystal_ore", CrystalOre::new);
    public static final RegistryObject<Block> GREEN_CRYSTAL_ORE = BLOCKS.register("green_crystal_ore", CrystalOre::new);
    public static final RegistryObject<Block> YELLOW_CRYSTAL_ORE = BLOCKS.register("yellow_crystal_ore", CrystalOre::new);
    public static final RegistryObject<Block> CYAN_CRYSTAL_ORE = BLOCKS.register("cyan_crystal_ore", CrystalOre::new);
    public static final RegistryObject<Block> WHITE_CRYSTAL_ORE = BLOCKS.register("white_crystal_ore", CrystalOre::new);
    public static final RegistryObject<Block> MAGENTA_CRYSTAL_ORE = BLOCKS.register("magenta_crystal_ore", CrystalOre::new);
    public static final RegistryObject<Block> PURPLE_CRYSTAL_ORE = BLOCKS.register("purple_crystal_ore", CrystalOre::new);
    public static final RegistryObject<Block> PINK_CRYSTAL_ORE = BLOCKS.register("pink_crystal_ore", CrystalOre::new);
    public static final RegistryObject<Block> LIME_GREEN_CRYSTAL_ORE = BLOCKS.register("lime_green_crystal_ore", CrystalOre::new);
    public static final RegistryObject<Block> TURQUOISE_CRYSTAL_ORE = BLOCKS.register("turquoise_crystal_ore", CrystalOre::new);

    public static final RegistryObject<Item> CHROMIUM_ORE_ITEM = ITEMS.register("chromium_ore", () -> new BlockItem(CHROMIUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHROMIUM_DEEPSLATE_ORE_ITEM = ITEMS.register("chromium_deepslate_ore", () -> new BlockItem(CHROMIUM_DEEPSLATE_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> TIATANIUM_ORE_ITEM = ITEMS.register("titanium_ore", () -> new BlockItem(TITANIUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_DEEPSLATE_ORE_ITEM = ITEMS.register("titanium_deepslate_ore", () -> new BlockItem(TITANIUM_DEEPSLATE_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BLUE_KYBER_CRYSTAL_ORE_ITEM = ITEMS.register("blue_crystal_ore", () -> new BlockItem(BLUE_CRYSTAL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_KYBER_CRYSTAL_ORE_ITEM = ITEMS.register("orange_crystal_ore", () -> new BlockItem(ORANGE_CRYSTAL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> GREEN_KYBER_CRYSTAL_ORE_ITEM = ITEMS.register("green_crystal_ore", () -> new BlockItem(GREEN_CRYSTAL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_KYBER_CRYSTAL_ORE_ITEM = ITEMS.register("yellow_crystal_ore", () -> new BlockItem(YELLOW_CRYSTAL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CYAN_KYBER_CRYSTAL_ORE_ITEM = ITEMS.register("cyan_crystal_ore", () -> new BlockItem(CYAN_CRYSTAL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> WHITE_KYBER_CRYSTAL_ORE_ITEM = ITEMS.register("white_crystal_ore", () -> new BlockItem(WHITE_CRYSTAL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_KYBER_CRYSTAL_ORE_ITEM = ITEMS.register("magenta_crystal_ore", () -> new BlockItem(MAGENTA_CRYSTAL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_KYBER_CRYSTAL_ORE_ITEM = ITEMS.register("purple_crystal_ore", () -> new BlockItem(PURPLE_CRYSTAL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> PINK_KYBER_CRYSTAL_ORE_ITEM = ITEMS.register("pink_crystal_ore", () -> new BlockItem(PINK_CRYSTAL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> LIME_GREEN_KYBER_CRYSTAL_ORE_ITEM = ITEMS.register("lime_green_crystal_ore", () -> new BlockItem(LIME_GREEN_CRYSTAL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> TURQUOISE_KYBER_CRYSTAL_ORE_ITEM = ITEMS.register("turquoise_crystal_ore", () -> new BlockItem(TURQUOISE_CRYSTAL_ORE.get(), new Item.Properties()));

    public static final RegistryObject<Block> TEMPLE_STONE = BLOCKS.register("temple_stone", TempleStone::new);
    public static final RegistryObject<Item> TEMPLE_STONE_ITEM = ITEMS.register("temple_stone", () -> new BlockItem(TEMPLE_STONE.get(), new Item.Properties()));

    public static final RegistryObject<Block> TEMPLE_STONE_PILLAR = BLOCKS.register("temple_stone_pillar", TempleStonePillar::new);
    public static final RegistryObject<Item> TEMPLE_STONE_PILLAR_ITEM = ITEMS.register("temple_stone_pillar", () -> new BlockItem(TEMPLE_STONE_PILLAR.get(), new Item.Properties()));

    public static final RegistryObject<Block> TEMPLE_STONE_HOLOBOOK = BLOCKS.register("temple_stone_holobook", TempleStoneHolobook::new);
    public static final RegistryObject<Item> TEMPLE_STONE_HOLOBOOK_ITEM = ITEMS.register("temple_stone_holobook", () -> new BlockItem(TEMPLE_STONE_HOLOBOOK.get(), new Item.Properties()));

    public static final RegistryObject<Block> TEMPLE_STONE_STAIRS = BLOCKS.register("temple_stone_stairs", () -> new TempleStoneStairs(TEMPLE_STONE.get().defaultBlockState()));
    public static final RegistryObject<Item> TEMPLE_STONE_STAIRS_ITEM = ITEMS.register("temple_stone_stairs", () -> new BlockItem(TEMPLE_STONE_STAIRS.get(), new Item.Properties()));

    public static final RegistryObject<Block> TEMPLE_STONE_SLAB = BLOCKS.register("temple_stone_slab", TempleStoneSlab::new);
    public static final RegistryObject<Item> TEMPLE_STONE_SLAB_ITEM = ITEMS.register("temple_stone_slab", () -> new BlockItem(TEMPLE_STONE_SLAB.get(), new Item.Properties()));

    public static final RegistryObject<Block> DARK_TEMPLE_STONE = BLOCKS.register("dark_temple_stone", DarkTempleStone::new);
    public static final RegistryObject<Item> DARK_TEMPLE_STONE_ITEM = ITEMS.register("dark_temple_stone", () -> new BlockItem(DARK_TEMPLE_STONE.get(), new Item.Properties()));

    public static final RegistryObject<Block> MALACHITE_OBSIDIAN = BLOCKS.register("malachite_obsidian", DarkTempleStone::new);
    public static final RegistryObject<Item> MALACHITE_OBSIDIAN_ITEM = ITEMS.register("malachite_obsidian", () -> new BlockItem(MALACHITE_OBSIDIAN.get(), new Item.Properties()));

    public static final RegistryObject<Block> DARK_TEMPLE_STONE_PILLAR = BLOCKS.register("dark_temple_stone_pillar", DarkTempleStonePillar::new);
    public static final RegistryObject<Item> DARK_TEMPLE_STONE_PILLAR_ITEM = ITEMS.register("dark_temple_stone_pillar", () -> new BlockItem(DARK_TEMPLE_STONE_PILLAR.get(), new Item.Properties()));

    public static final RegistryObject<Block> DARK_TEMPLE_STONE_HOLOBOOK = BLOCKS.register("dark_temple_stone_holobook", DarkTempleStoneHolobook::new);
    public static final RegistryObject<Item> DARK_TEMPLE_STONE_HOLOBOOK_ITEM = ITEMS.register("dark_temple_stone_holobook", () -> new BlockItem(DARK_TEMPLE_STONE_HOLOBOOK.get(), new Item.Properties()));

    public static final RegistryObject<Block> DARK_TEMPLE_STONE_STAIRS = BLOCKS.register("dark_temple_stone_stairs", () -> new DarkTempleStoneStairs(DARK_TEMPLE_STONE.get().defaultBlockState()));
    public static final RegistryObject<Item> DARK_TEMPLE_STONE_STAIRS_ITEM = ITEMS.register("dark_temple_stone_stairs", () -> new BlockItem(DARK_TEMPLE_STONE_STAIRS.get(), new Item.Properties()));

    public static final RegistryObject<Block> DARK_TEMPLE_STONE_SLAB = BLOCKS.register("dark_temple_stone_slab", DarkTempleStoneSlab::new);
    public static final RegistryObject<Item> DARK_TEMPLE_STONE_SLAB_ITEM = ITEMS.register("dark_temple_stone_slab", () -> new BlockItem(DARK_TEMPLE_STONE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Block> BLEEDING_TABLE = BLOCKS.register("bleeding_table", BleedingTable::new);
    public static final RegistryObject<Item> BLEEDING_TABLE_ITEM = ITEMS.register("bleeding_table", () -> new BlockItem(BLEEDING_TABLE.get(), new Item.Properties()));

    public static final RegistryObject<Item> SHII_CHO_HOLOBOOK = ITEMS.register("shii_cho_holobook",
            () -> new SaberFormHolobookItem("Shii-Cho", new Item.Properties()));

    public static final RegistryObject<Item> MAKASHI_HOLBOOK = ITEMS.register("makashi_holobook",
            () -> new SaberFormHolobookItem("Makashi", new Item.Properties()));

    public static final RegistryObject<Item> SORESU_HOLOBOOK = ITEMS.register("soresu_holobook",
            () -> new SaberFormHolobookItem("Soresu", new Item.Properties()));

    public static final RegistryObject<Item> ATARU_HOLOBOOK = ITEMS.register("ataru_holobook",
            () -> new SaberFormHolobookItem("Ataru", new Item.Properties()));

    public static final RegistryObject<Item> SHIEN_DJEM_SO_HOLOBOOK = ITEMS.register("shien_djem_so_holobook",
            () -> new SaberFormHolobookItem("Shien / Djem So", new Item.Properties()));

    public static final RegistryObject<Item> NIMAN_HOLOBOOK = ITEMS.register("niman_holobook",
            () -> new SaberFormHolobookItem("Niman", new Item.Properties()));

    public static final RegistryObject<Item> JUYO_VAAPAD_HOLOBOOK = ITEMS.register("juyo_vaapad_holobook",
            () -> new SaberFormHolobookItem("Juyo / Vaapad", new Item.Properties()));
    public static final RegistryObject<Block> JEDI_HOLOCRON = BLOCKS.register("jedi_holocron", Holocron::new);
    public static final RegistryObject<Item> JEDI_HOLOCRON_ITEM = ITEMS.register("jedi_holocron", () -> new BlockItem(JEDI_HOLOCRON.get(), new Item.Properties()));

    public static final RegistryObject<Block> SITH_HOLOCRON = BLOCKS.register("sith_holocron", Holocron::new);
    public static final RegistryObject<Item> SITH_HOLOCRON_ITEM = ITEMS.register("sith_holocron", () -> new BlockItem(SITH_HOLOCRON.get(), new Item.Properties()));

    public static final RegistryObject<Block> JEDI_GUARD_STATUE = BLOCKS.register("jedi_guard_statue", JediGuard::new);
    public static final RegistryObject<Item> JEDI_GUARD_STATUE_ITEM = ITEMS.register("jedi_guard_statue", () -> new BlockItem(JEDI_GUARD_STATUE.get(), new Item.Properties()));


    public static final RegistryObject<Block> TYTHON_JEDI_IDLE_HEAD_STATUE = BLOCKS.register("tython_jedi_idle_head_statue", TythonJediStatueHEAD::new);
    public static final RegistryObject<Item> TYTHON_JEDI_IDLE_HEAD_STATUE_ITEM = ITEMS.register("tython_jedi_idle_head_statue", () -> new BlockItem(TYTHON_JEDI_IDLE_HEAD_STATUE.get(), new Item.Properties()));
    public static final RegistryObject<Block> TYTHON_JEDI_IDLE_TORSO_STATUE = BLOCKS.register("tython_jedi_idle_torso_statue", TythonJediStatueTORSO::new);
    public static final RegistryObject<Item> TYTHON_JEDI_IDLE_TORSO_STATUE_ITEM = ITEMS.register("tython_jedi_idle_torso_statue", () -> new BlockItem(TYTHON_JEDI_IDLE_TORSO_STATUE.get(), new Item.Properties()));
    public static final RegistryObject<Block> TYTHON_JEDI_IDLE_LEG_1_STATUE = BLOCKS.register("tython_jedi_idle_leg_1_statue", TythonJediStatueLEG::new);
    public static final RegistryObject<Item> TYTHON_JEDI_IDLE_LEG_1_STATUE_ITEM = ITEMS.register("tython_jedi_idle_leg_1_statue", () -> new BlockItem(TYTHON_JEDI_IDLE_LEG_1_STATUE.get(), new Item.Properties()));
    public static final RegistryObject<Block> TYTHON_JEDI_IDLE_LEG_2_STATUE = BLOCKS.register("tython_jedi_idle_leg_2_statue", TythonJediStatueLEG::new);
    public static final RegistryObject<Item> TYTHON_JEDI_IDLE_LEGS_2_STATUE_ITEM = ITEMS.register("tython_jedi_idle_leg_2_statue", () -> new BlockItem(TYTHON_JEDI_IDLE_LEG_2_STATUE.get(), new Item.Properties()));
    public static final RegistryObject<Block> TYTHON_JEDI_CROSSED_TORSO_STATUE = BLOCKS.register("tython_jedi_crossed_torso_statue", TythonJediStatueCTORSO::new);
    public static final RegistryObject<Item> TYTHON_JEDI_CROSSED_TORSO_STATUE_ITEM = ITEMS.register("tython_jedi_crossed_torso_statue", () -> new BlockItem(TYTHON_JEDI_CROSSED_TORSO_STATUE.get(), new Item.Properties()));


    public static final RegistryObject<Block> SITH_GUARD_STATUE = BLOCKS.register("sith_guard_statue", SithGuard::new);
    public static final RegistryObject<Item> SITH_GUARD_STATUE_ITEM = ITEMS.register("sith_guard_statue", () -> new BlockItem(SITH_GUARD_STATUE.get(), new Item.Properties()));
    public static final RegistryObject<Block> LIGHTSABER_CRAFTING_TABLE = BLOCKS.register("lightsaber_crafting_table", () -> new LightsaberCraftingTableBlock());
    public static final RegistryObject<Item> LIGHTSABER_CRAFTING_TABLE_ITEM = ITEMS.register("lightsaber_crafting_table", () -> new BlockItem(LIGHTSABER_CRAFTING_TABLE.get(), new Item.Properties()));

    public static final RegistryObject<Block> ILUM_STONE = BLOCKS.register("ilum_stone", AncientTempleStone::new);
    public static final RegistryObject<Item> ILUM_STONE_ITEM = ITEMS.register("ilum_stone", () -> new BlockItem(ILUM_STONE.get(), new Item.Properties()));

    public static final RegistryObject<Block> ANCIENT_TEMPLE_STONE = BLOCKS.register("ancient_temple_stone", AncientTempleStone::new);
    public static final RegistryObject<Item> ANCIENT_TEMPLE_STONE_ITEM = ITEMS.register("ancient_temple_stone", () -> new BlockItem(ANCIENT_TEMPLE_STONE.get(), new Item.Properties()));
    public static final RegistryObject<Block> ANCIENT_TEMPLE_STONE_CRACKED = BLOCKS.register("ancient_temple_stone_cracked", AncientTempleStoneCracked::new);
    public static final RegistryObject<Item> ANCIENT_TEMPLE_STONE_CRACKED_ITEM = ITEMS.register("ancient_temple_stone_cracked", () -> new BlockItem(ANCIENT_TEMPLE_STONE_CRACKED.get(), new Item.Properties()));
    public static final RegistryObject<Block> ANCIENT_TEMPLE_STONE_PILLAR = BLOCKS.register("ancient_temple_stone_pillar", AncientTempleStonePillar::new);
    public static final RegistryObject<Item> ANCIENT_TEMPLE_STONE_PILLAR_ITEM = ITEMS.register("ancient_temple_stone_pillar", () -> new BlockItem(ANCIENT_TEMPLE_STONE_PILLAR.get(), new Item.Properties()));
    public static final RegistryObject<Block> ANCIENT_TEMPLE_STONE_HOLOBOOK = BLOCKS.register("ancient_temple_stone_holobook", AncientTempleStoneHolobook::new);
    public static final RegistryObject<Item> ANCIENT_TEMPLE_STONE_HOLOBOOK_ITEM = ITEMS.register("ancient_temple_stone_holobook", () -> new BlockItem(ANCIENT_TEMPLE_STONE_HOLOBOOK.get(), new Item.Properties()));
    public static final RegistryObject<Block> ANCIENT_TEMPLE_STONE_STAIRS = BLOCKS.register("ancient_temple_stone_stairs", () -> new AncientTempleStoneStairs(ANCIENT_TEMPLE_STONE.get().defaultBlockState()));
    public static final RegistryObject<Item> ANCIENT_TEMPLE_STONE_STAIRS_ITEM = ITEMS.register("ancient_temple_stone_stairs", () -> new BlockItem(ANCIENT_TEMPLE_STONE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Block> ANCIENT_TEMPLE_STONE_SLAB = BLOCKS.register("ancient_temple_stone_slab", AncientTempleStoneSlab::new);
    public static final RegistryObject<Item> ANCIENT_TEMPLE_STONE_SLAB_ITEM = ITEMS.register("ancient_temple_stone_slab", () -> new BlockItem(ANCIENT_TEMPLE_STONE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Block> TYTHON_GRASS = BLOCKS.register("tython_grass", TythonGrass::new);
    public static final RegistryObject<Item> TYTHON_GRASS_ITEM = ITEMS.register("tython_grass", () -> new BlockItem(TYTHON_GRASS.get(), new Item.Properties()));
    public static final RegistryObject<Block> TYTHON_DIRT = BLOCKS.register("tython_dirt", TythonDirt::new);
    public static final RegistryObject<Item> TYTHON_DIRT_ITEM = ITEMS.register("tython_dirt", () -> new BlockItem(TYTHON_DIRT.get(), new Item.Properties()));
    public static final RegistryObject<WallBlock> ANCIENT_TEMPLE_STONE_WALL = BLOCKS.register("ancient_temple_stone_wall", AncientTempleStoneWall::new);
    public static final RegistryObject<Item> ANCIENT_TEMPLE_STONE_WALL_ITEM = ITEMS.register("ancient_temple_stone_wall", () -> new BlockItem(ANCIENT_TEMPLE_STONE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<WallBlock> TEMPLE_STONE_WALL = BLOCKS.register("temple_stone_wall", TempleStoneWall::new);
    public static final RegistryObject<Item> TEMPLE_STONE_WALL_ITEM = ITEMS.register("temple_stone_wall", () -> new BlockItem(TEMPLE_STONE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<WallBlock> DARK_TEMPLE_STONE_WALL = BLOCKS.register("dark_temple_stone_wall", DarkTempleStoneWall::new);
    public static final RegistryObject<Item> DARK_TEMPLE_STONE_WALL_ITEM = ITEMS.register("dark_temple_stone_wall", () -> new BlockItem(DARK_TEMPLE_STONE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Block> BLBA_SAPLING = BLOCKS.register("blba_sapling", () -> new SaplingBlock(ModTreeGrowers.BLBA, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Item> BLBA_SAPLING_ITEM = ITEMS.register("blba_sapling", () -> new BlockItem(BLBA_SAPLING.get(), new Item.Properties()));
    // #ITEMS

    public static final RegistryObject<Item> SHUURA = ITEMS.register("shuura", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(6).saturationModifier(2f).build())));
    public static final RegistryObject<Item> JEDI_HOLOBOOK = ITEMS.register("jedi_holobook", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANCIENT_HOLOBOOK = ITEMS.register("ancient_holobook", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SITH_HOLOBOOK = ITEMS.register("sith_holobook", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RED_KYBER = ITEMS.register("red_kyber", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOOD_ORANGE_KYBER = ITEMS.register("blood_orange_kyber", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLUE_KYBER = ITEMS.register("blue_kyber", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORANGE_KYBER = ITEMS.register("orange_kyber", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GREEN_KYBER = ITEMS.register("green_kyber", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> YELLOW_KYBER = ITEMS.register("yellow_kyber", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHROMIUM_INGOT = ITEMS.register("chromium_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PORTAL_ITEM = ITEMS.register("portal_item", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NAVIGATION_COMPUTER = ITEMS.register("navigation_computer", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REACTOR_ASSEMBLY = ITEMS.register("reactor_assembly", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_CHROMIUM_INGOT = ITEMS.register("titanium_chromium_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CYAN_KYBER = ITEMS.register("cyan_kyber", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WHITE_KYBER = ITEMS.register("white_kyber", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_KYBER = ITEMS.register("magenta_kyber", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_KYBER = ITEMS.register("purple_kyber", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PINK_KYBER = ITEMS.register("pink_kyber", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIME_GREEN_KYBER = ITEMS.register("lime_green_kyber", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TURQUOISE_KYBER = ITEMS.register("turquoise_kyber", () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> TYTHON_PORTAL_ITEM = ITEMS.register("tython_portal",
            () -> new TythonPortalItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MUSTAFAR_PORTAL_ITEM = ITEMS.register("mustafar_portal",
            () -> new MustafarPortalItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> NABOO_PORTAL_ITEM = ITEMS.register("naboo_portal",
            () -> new NabooPortalItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ILUM_PORTAL_ITEM = ITEMS.register("ilum_portal",
            () -> new IlumPortalItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> OSSUS_PORTAL_ITEM = ITEMS.register("ossus_portal",
            () -> new OssusPortalItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MALACHOR_PORTAL_ITEM = ITEMS.register("malachor_portal",
            () -> new MalachorPortalItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> KORRIBAN_PORTAL_ITEM = ITEMS.register("korriban_portal",
            () -> new KorribanPortalItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BOGAN_PORTAL_ITEM = ITEMS.register("bogan_portal",
            () -> new BoganPortalItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ASHLA_PORTAL_ITEM = ITEMS.register("ashla_portal",
            () -> new AshlaPortalItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DANTOOINE_PORTAL_ITEM = ITEMS.register("dantooine_portal",
            () -> new DantooinePortalItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> ACID_FORGED_PLATE = ITEMS.register("acid_forged_plate",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ACIDIC_VENOM_SAC = ITEMS.register("acidic_venom_sac",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILK_THREAD = ITEMS.register("silk_thread",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHITIN_FRAGMENTS = ITEMS.register("chitin_fragments",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LOST_HILT = ITEMS.register("lost_hilt",
            () -> new HiltItem("green", new Item.Properties()));
    public static final RegistryObject<Item> AEGIS_HILT = ITEMS.register("aegis_hilt",
            () -> new HiltItem("orange", new Item.Properties()));
    public static final RegistryObject<Item> APPRENTICE_HILT = ITEMS.register("apprentice_hilt",
            () -> new HiltItem("pink", new Item.Properties()));
    public static final RegistryObject<Item> CHOSEN_HILT = ITEMS.register("chosen_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final RegistryObject<Item> EMPEROR_HILT = ITEMS.register("emperor_hilt",
            () -> new HiltItem("red", new Item.Properties()));
    public static final RegistryObject<Item> FALLEN_HILT = ITEMS.register("fallen_hilt",
            () -> new HiltItem("red", new Item.Properties()));
    public static final RegistryObject<Item> GRACE_HILT = ITEMS.register("grace_hilt",
            () -> new HiltItem("red", new Item.Properties()));
    public static final RegistryObject<Item> GUARD_HILT = ITEMS.register("guard_hilt",
            () -> new HiltItem("red", new Item.Properties()));
    public static final RegistryObject<Item> HARMONY_HILT = ITEMS.register("harmony_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final RegistryObject<Item> LEGACY_HILT = ITEMS.register("legacy_hilt",
            () -> new HiltItem("green", new Item.Properties()));
    public static final RegistryObject<Item> PADAWAN_HILT = ITEMS.register("padawan_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final RegistryObject<Item> RESOLVE_HILT = ITEMS.register("resolve_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final RegistryObject<Item> SKUSTELL_HILT = ITEMS.register("skustell_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final RegistryObject<Item> TALON_HILT = ITEMS.register("talon_hilt",
            () -> new HiltItem("orange", new Item.Properties()));
    public static final RegistryObject<Item> VALOR_HILT = ITEMS.register("valor_hilt",
            () -> new HiltItem("purple", new Item.Properties()));
    public static final RegistryObject<Item> WISDOM_HILT = ITEMS.register("wisdom_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final RegistryObject<Item> NEGOTIATOR_HILT = ITEMS.register("negotiator_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final RegistryObject<Item> KNIGHTFALL_HILT = ITEMS.register("knightfall_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final RegistryObject<Item> BAROSHE_HILT = ITEMS.register("baroshe_hilt",
            () -> new HiltItem("blue", new Item.Properties()));
    public static final RegistryObject<Item> ACID_SPIDER_SPAWN_EGG = ITEMS.register("acid_spider_spawn_egg",
            () -> new ForgeSpawnEggItem(galaxyunderchaos.ACID_SPIDER, 0x53524b, 0xdac741, new Item.Properties()));
    public static final Map<String, RegistryObject<Item>> LIGHTSABERS = new HashMap<>();

    public static void registerLightsabers() {
        String[] bladeColors = {
                "red", "blue", "green", "yellow", "cyan",
                "white", "magenta", "purple", "pink",
                "lime_green", "turquoise", "orange", "blood_orange"
        };

        String[] hiltNames = {
                "apprentice", "chosen", "emperor", "legacy", "padawan",
                "resolve", "talon", "valor", "wisdom", "lost", "aegis", "grace", "guard", "harmony",
                "skustell", "fallen", "negotiator", "baroshe", "knightfall"
        };

        for (String color : bladeColors) {
            for (String hilt : hiltNames) {
                String id = color + "_" + hilt + "_lightsaber";
                LIGHTSABERS.put(id, ITEMS.register(
                        id,
                        () -> new LightsaberItem(color, new Item.Properties().stacksTo(1))
                ));
            }
        }
    }

    // #ENTITIES
    public static final RegistryObject<EntityType<AcidSpiderEntity>> ACID_SPIDER =
            ENTITY_TYPES.register("acid_spider", () -> EntityType.Builder.of(AcidSpiderEntity::new, MobCategory.MONSTER)
                    .sized(1.5f, 1.5f).build("acid_spider"));

    public galaxyunderchaos() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        ENTITY_TYPES.register(modEventBus);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ModBiomes.BIOMES.register(modEventBus);
        CreativeMenuTabs.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        registerLightsabers();
        ModSounds.register(modEventBus);
        ModDataComponentTypes.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(LightsaberBeltRenderer.class);
//        MinecraftForge.EVENT_BUS.register(ForcePowerOverlay.class);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        modEventBus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(HyperspaceOverlayRenderer.class);
        ModLootModifiers.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(LightsaberFormEventHandler.class);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
        LightsaberFormNetworking.registerPackets(event);
//        event.enqueueWork(GalaxyUnderChaosNetworking::registerPackets);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        EntityRenderers.register(galaxyunderchaos.ACID_SPIDER.get(), AcidSpiderRenderer::new);
        event.enqueueWork(() -> {
            ModItemProperties.addCustomItemProperties();
            Minecraft mc = Minecraft.getInstance();

            mc.getEntityRenderDispatcher()
                    .getSkinMap()
                    .values()
                    .forEach(renderer -> {
                        if (renderer instanceof PlayerRenderer pr) {
                            pr.addLayer(new LightsaberFirstPersonLayer(pr));
                        }
                    });

            galaxyunderchaos.LIGHTSABERS.values()
                    .forEach(reg -> ItemBlockRenderTypes.setRenderLayer(
                            Block.byItem(reg.get()),
                            RenderType.translucent()
                    ));
        });
        MinecraftForge.EVENT_BUS.addListener(ClientEventSubscriber::onRenderTick);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

}