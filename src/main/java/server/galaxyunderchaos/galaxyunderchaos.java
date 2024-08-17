package server.galaxyunderchaos;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
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
import server.galaxyunderchaos.item.CreativeMenuTabs;
import server.galaxyunderchaos.item.NabooPortalItem;
import server.galaxyunderchaos.item.TythonPortalItem;
import server.galaxyunderchaos.worldgen.biome.ModBiomes;

@Mod(galaxyunderchaos.MODID)public class galaxyunderchaos {
    public static final String MODID="galaxyunderchaos";
    private static final Logger LOGGER= LogUtils.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Block> FORCE_STONE = BLOCKS.register("force_stone", ForceStone::new);
    public static final RegistryObject<Item> FORCE_STONE_ITEM = ITEMS.register("force_stone", () -> new BlockItem(FORCE_STONE.get(), new Item.Properties()));

    public static final RegistryObject<Block> FORCE_STONE_PILLAR = BLOCKS.register("force_stone_pillar", ForceStonePillar::new);
    public static final RegistryObject<Item> FORCE_STONE_PILLAR_ITEM = ITEMS.register("force_stone_pillar", () -> new BlockItem(FORCE_STONE_PILLAR.get(), new Item.Properties()));

    public static final RegistryObject<Block> FORCE_STONE_HOLOBOOK = BLOCKS.register("force_stone_holobook", ForceStoneHolobook::new);
    public static final RegistryObject<Item> FORCE_STONE_HOLOBOOK_ITEM = ITEMS.register("force_stone_holobook", () -> new BlockItem(FORCE_STONE_HOLOBOOK.get(), new Item.Properties()));

    public static final RegistryObject<Block> FORCE_STONE_STAIRS = BLOCKS.register("force_stone_stairs", () -> new ForceStoneStairs(FORCE_STONE.get().defaultBlockState()));
    public static final RegistryObject<Item> FORCE_STONE_STAIRS_ITEM = ITEMS.register("force_stone_stairs", () -> new BlockItem(FORCE_STONE_STAIRS.get(), new Item.Properties()));

    public static final RegistryObject<Block> FORCE_STONE_SLAB = BLOCKS.register("force_stone_slab", ForceStoneSlab::new);
    public static final RegistryObject<Item> FORCE_STONE_SLAB_ITEM = ITEMS.register("force_stone_slab", () -> new BlockItem(FORCE_STONE_SLAB.get(), new Item.Properties()));

    public static final RegistryObject<Block> DARK_FORCE_STONE = BLOCKS.register("dark_force_stone", DarkForceStone::new);
    public static final RegistryObject<Item> DARK_FORCE_STONE_ITEM = ITEMS.register("dark_force_stone", () -> new BlockItem(DARK_FORCE_STONE.get(), new Item.Properties()));

    public static final RegistryObject<Block> DARK_FORCE_STONE_PILLAR = BLOCKS.register("dark_force_stone_pillar", DarkForceStonePillar::new);
    public static final RegistryObject<Item> DARK_FORCE_STONE_PILLAR_ITEM = ITEMS.register("dark_force_stone_pillar", () -> new BlockItem(DARK_FORCE_STONE_PILLAR.get(), new Item.Properties()));

    public static final RegistryObject<Block> DARK_FORCE_STONE_HOLOBOOK = BLOCKS.register("dark_force_stone_holobook", DarkForceStoneHolobook::new);
    public static final RegistryObject<Item> DARK_FORCE_STONE_HOLOBOOK_ITEM = ITEMS.register("dark_force_stone_holobook", () -> new BlockItem(DARK_FORCE_STONE_HOLOBOOK.get(), new Item.Properties()));

    public static final RegistryObject<Block> DARK_FORCE_STONE_STAIRS = BLOCKS.register("dark_force_stone_stairs", () -> new DarkForceStoneStairs(DARK_FORCE_STONE.get().defaultBlockState()));
    public static final RegistryObject<Item> DARK_FORCE_STONE_STAIRS_ITEM = ITEMS.register("dark_force_stone_stairs", () -> new BlockItem(DARK_FORCE_STONE_STAIRS.get(), new Item.Properties()));

    public static final RegistryObject<Block> DARK_FORCE_STONE_SLAB = BLOCKS.register("dark_force_stone_slab", DarkForceStoneSlab::new);
    public static final RegistryObject<Item> DARK_FORCE_STONE_SLAB_ITEM = ITEMS.register("dark_force_stone_slab", () -> new BlockItem(DARK_FORCE_STONE_SLAB.get(), new Item.Properties()));

    public static final RegistryObject<Item> SHUURA = ITEMS.register("shuura", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(6).saturationModifier(2f).build())));
    public static final RegistryObject<Item> JEDI_HOLOBOOK = ITEMS.register("jedi_holobook", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANCIENT_HOLOBOOK = ITEMS.register("ancient_holobook", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SITH_HOLOBOOK = ITEMS.register("sith_holobook", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Block> ANCIENT_FORCE_STONE = BLOCKS.register("ancient_force_stone", AncientForceStone::new);
    public static final RegistryObject<Item> ANCIENT_FORCE_STONE_ITEM = ITEMS.register("ancient_force_stone", () -> new BlockItem(ANCIENT_FORCE_STONE.get(), new Item.Properties()));
    public static final RegistryObject<Block> ANCIENT_FORCE_STONE_CRACKED = BLOCKS.register("ancient_force_stone_cracked", AncientForceStoneCracked::new);
    public static final RegistryObject<Item> ANCIENT_FORCE_STONE_CRACKED_ITEM = ITEMS.register("ancient_force_stone_cracked", () -> new BlockItem(ANCIENT_FORCE_STONE_CRACKED.get(), new Item.Properties()));
    public static final RegistryObject<Block> ANCIENT_FORCE_STONE_PILLAR = BLOCKS.register("ancient_force_stone_pillar", AncientForceStonePillar::new);
    public static final RegistryObject<Item> ANCIENT_FORCE_STONE_PILLAR_ITEM = ITEMS.register("ancient_force_stone_pillar", () -> new BlockItem(ANCIENT_FORCE_STONE_PILLAR.get(), new Item.Properties()));
    public static final RegistryObject<Block> ANCIENT_FORCE_STONE_HOLOBOOK = BLOCKS.register("ancient_force_stone_holobook", AncientForceStoneHolobook::new);
    public static final RegistryObject<Item> ANCIENT_FORCE_STONE_HOLOBOOK_ITEM = ITEMS.register("ancient_force_stone_holobook", () -> new BlockItem(ANCIENT_FORCE_STONE_HOLOBOOK.get(), new Item.Properties()));
    public static final RegistryObject<Block> ANCIENT_FORCE_STONE_STAIRS = BLOCKS.register("ancient_force_stone_stairs", () -> new AncientForceStoneStairs(ANCIENT_FORCE_STONE.get().defaultBlockState()));
    public static final RegistryObject<Item> ANCIENT_FORCE_STONE_STAIRS_ITEM = ITEMS.register("ancient_force_stone_stairs", () -> new BlockItem(ANCIENT_FORCE_STONE_STAIRS.get(), new Item.Properties()));

    public static final RegistryObject<Block> ANCIENT_FORCE_STONE_SLAB = BLOCKS.register("ancient_force_stone_slab", AncientForceStoneSlab::new);
    public static final RegistryObject<Item> ANCIENT_FORCE_STONE_SLAB_ITEM = ITEMS.register("ancient_force_stone_slab", () -> new BlockItem(ANCIENT_FORCE_STONE_SLAB.get(), new Item.Properties()));

    public static final RegistryObject<Block> TYTHON_GRASS = BLOCKS.register("tython_grass", TythonGrass::new);
    public static final RegistryObject<Item> TYTHON_GRASS_ITEM = ITEMS.register("tython_grass", () -> new BlockItem(TYTHON_GRASS.get(), new Item.Properties()));

    public static final RegistryObject<Block> TYTHON_DIRT = BLOCKS.register("tython_dirt", TythonDirt::new);
    public static final RegistryObject<Item> TYTHON_DIRT_ITEM = ITEMS.register("tython_dirt", () -> new BlockItem(TYTHON_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> TYTHON_PORTAL_ITEM = ITEMS.register("tython_portal",
            () -> new TythonPortalItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> NABOO_PORTAL_ITEM = ITEMS.register("naboo_portal",
            () -> new NabooPortalItem(new Item.Properties().stacksTo(1)));

    public galaxyunderchaos() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ModBiomes.BIOMES.register(modEventBus);
        CreativeMenuTabs.register(modEventBus); // Register the creative tabs

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}