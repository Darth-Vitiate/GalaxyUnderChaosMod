package server.galaxyunderchaos.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import server.galaxyunderchaos.galaxyunderchaos;

import java.util.function.Supplier;

import static server.galaxyunderchaos.galaxyunderchaos.*;

public class CreativeMenuTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, galaxyunderchaos.MODID);

    public static final java.util.function.Supplier<CreativeModeTab> GALAXY_UNDER_CHAOS_TAB = CREATIVE_MODE_TABS.register("galaxy_under_chaos",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(TEMPLE_STONE.get())) // Use ModBlocks.TEMPLE_STONE
                    .title(Component.translatable("creativetab.galaxyunderchaos.galaxy_under_chaos_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(TEMPLE_STONE.get().asItem());
                        output.accept(TEMPLE_STONE_HOLOBOOK.get().asItem());
                        output.accept(TEMPLE_STONE_PILLAR.get().asItem());
                        output.accept(TEMPLE_STONE_STAIRS.get().asItem());
                        output.accept(TEMPLE_STONE_SLAB.get().asItem());
                        output.accept(TEMPLE_STONE_WALL.get().asItem());
                        output.accept(DARK_TEMPLE_STONE.get().asItem());
                        output.accept(DARK_TEMPLE_STONE_HOLOBOOK.get().asItem());
                        output.accept(DARK_TEMPLE_STONE_PILLAR.get().asItem());
                        output.accept(DARK_TEMPLE_STONE_STAIRS.get().asItem());
                        output.accept(DARK_TEMPLE_STONE_SLAB.get().asItem());
                        output.accept(DARK_TEMPLE_STONE_WALL.get().asItem());
                        output.accept(ANCIENT_TEMPLE_STONE.get().asItem());
                        output.accept(ANCIENT_TEMPLE_STONE_HOLOBOOK.get().asItem());
                        output.accept(ANCIENT_TEMPLE_STONE_PILLAR.get().asItem());
                        output.accept(ANCIENT_TEMPLE_STONE_CRACKED.get().asItem());
                        output.accept(ANCIENT_TEMPLE_STONE_STAIRS.get().asItem());
                        output.accept(ANCIENT_TEMPLE_STONE_SLAB.get().asItem());
                        output.accept(ANCIENT_TEMPLE_STONE_WALL.get().asItem());
                        output.accept(PURPLE_CRYSTAL_ORE.get().asItem());
                        output.accept(MAGENTA_CRYSTAL_ORE.get().asItem());
                        output.accept(PINK_CRYSTAL_ORE.get().asItem());
                        output.accept(ORANGE_CRYSTAL_ORE.get().asItem());
                        output.accept(YELLOW_CRYSTAL_ORE.get().asItem());
                        output.accept(LIME_GREEN_CRYSTAL_ORE.get().asItem());
                        output.accept(GREEN_CRYSTAL_ORE.get().asItem());
                        output.accept(TURQUOISE_CRYSTAL_ORE.get().asItem());
                        output.accept(CYAN_CRYSTAL_ORE.get().asItem());
                        output.accept(BLUE_CRYSTAL_ORE.get().asItem());
                        output.accept(WHITE_CRYSTAL_ORE.get().asItem());
                        output.accept(MALACHITE_OBSIDIAN.get().asItem());
                        output.accept(CHROMIUM_ORE.get().asItem());
                        output.accept(CHROMIUM_DEEPSLATE_ORE.get().asItem());
                        output.accept(TITANIUM_ORE.get().asItem());
                        output.accept(TITANIUM_DEEPSLATE_ORE.get().asItem());
                        output.accept(BLEEDING_TABLE.get().asItem());
                        output.accept(LIGHTSABER_CRAFTING_TABLE.get().asItem());
                        output.accept(JEDI_GUARD_STATUE.get().asItem());
                        output.accept(SITH_GUARD_STATUE_ITEM.get().asItem());
//                        output.accept(PURPLE_CRYSTAL_BLOCK.get().asItem());
//                        output.accept(MAGENTA_CRYSTAL_BLOCK.get().asItem());
//                        output.accept(PINK_CRYSTAL_BLOCK.get().asItem());
//                        output.accept(RED_CRYSTAL_BLOCK.get().asItem());
//                        output.accept(BLOOD_ORANGE_CRYSTAL_BLOCK.get().asItem());
//                        output.accept(ORANGE_CRYSTAL_BLOCK.get().asItem());
//                        output.accept(YELLOW_CRYSTAL_BLOCK.get().asItem());
//                        output.accept(LIME_GREEN_CRYSTAL_BLOCK.get().asItem());
//                        output.accept(GREEN_CRYSTAL_BLOCK.get().asItem());
//                        output.accept(TURQUOISE_CRYSTAL_BLOCK.get().asItem());
//                        output.accept(CYAN_CRYSTAL_BLOCK.get().asItem());
//                        output.accept(BLUE_CRYSTAL_BLOCK.get().asItem());
//                        output.accept(WHITE_CRYSTAL_BLOCK.get().asItem());

                    }).build());

    public static final Supplier<CreativeModeTab> GALAXY_UNDER_CHAOS_ITEMS_TAB = CREATIVE_MODE_TABS.register("galaxy_under_chaos_items",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(SHUURA.get()))
                    .title(Component.translatable("creativetab.galaxyunderchaos.galaxy_under_chaos_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(CHROMIUM_INGOT.get());
                        output.accept(TITANIUM_INGOT.get());
                        output.accept(TITANIUM_CHROMIUM_INGOT.get());
//                        output.accept(REACTOR_ASSEMBLY.get());
                        output.accept(NAVIGATION_COMPUTER.get());
                        output.accept(PORTAL_ITEM.get());
                        output.accept(TYTHON_PORTAL_ITEM.get());
                        output.accept(ASHLA_PORTAL_ITEM.get());
                        output.accept(BOGAN_PORTAL_ITEM.get());
                        output.accept(NABOO_PORTAL_ITEM.get());
                        output.accept(ILUM_PORTAL_ITEM.get());
                        output.accept(MUSTAFAR_PORTAL_ITEM.get());
                        output.accept(OSSUS_PORTAL_ITEM.get());
                        output.accept(MALACHOR_PORTAL_ITEM.get());
                        output.accept(KORRIBAN_PORTAL_ITEM.get());
                        output.accept(DANTOOINE_PORTAL_ITEM.get());
                        output.accept(PURPLE_KYBER.get());
                        output.accept(MAGENTA_KYBER.get());
                        output.accept(PINK_KYBER.get());
                        output.accept(RED_KYBER.get());
                        output.accept(BLOOD_ORANGE_KYBER.get());
                        output.accept(ORANGE_KYBER.get());
                        output.accept(YELLOW_KYBER.get());
                        output.accept(LIME_GREEN_KYBER.get());
                        output.accept(GREEN_KYBER.get());
                        output.accept(TURQUOISE_KYBER.get());
                        output.accept(CYAN_KYBER.get());
                        output.accept(BLUE_KYBER.get());
                        output.accept(WHITE_KYBER.get());
                        output.accept(JEDI_HOLOBOOK.get());
                        output.accept(JEDI_HOLOCRON.get());
                        output.accept(ANCIENT_HOLOBOOK.get());
                        output.accept(SITH_HOLOBOOK.get());
//                        output.accept(SITH_HOLOCRON.get());
                        output.accept(ACID_SPIDER_SPAWN_EGG.get());
                        output.accept(ACIDIC_VENOM_SAC.get());
                        output.accept(SILK_THREAD.get());
                        output.accept(CHITIN_FRAGMENTS.get());
                        output.accept(ACID_FORGED_PLATE.get());

                        output.accept(SHUURA.get());



                    }).build());

    public static final Supplier<CreativeModeTab> GALAXY_UNDER_CHAOS_WEAPONS_TAB = CREATIVE_MODE_TABS.register("galaxy_under_chaos_weapons",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(AEGIS_HILT.get()))
                    .title(Component.translatable("creativetab.galaxyunderchaos.galaxy_under_chaos_weapons"))
                    .displayItems((parameters, output) -> {
//                        for (String color : new String[]{"red", "blue", "green", "yellow", "cyan", "white", "magenta", "purple", "pink", "lime_green", "turquoise", "orange", "blood_orange"}) {
//                            for (String hilt : new String[]{"apprentice", "chosen", "emperor", "legacy", "padawan", "resolve", "talon", "valor", "wisdom", "lost", "aegis", "grace", "guard", "harmony", "skustell", "fallen"}) {
//                                output.accept(galaxyunderchaos.LIGHTSABERS.get(color + "_" + hilt + "_lightsaber").get());
//                            }
//                        }
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("green_lost_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("orange_aegis_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("pink_apprentice_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("blue_chosen_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("red_emperor_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("red_fallen_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("red_grace_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("red_guard_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("blue_harmony_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("green_legacy_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("blue_padawan_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("blue_resolve_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("blue_skustell_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("orange_talon_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("purple_valor_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("blue_wisdom_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("blue_knightfall_lightsaber").get());                        output.accept(galaxyunderchaos.LIGHTSABERS.get("blue_padawan_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("blue_baroshe_lightsaber").get());
                        output.accept(galaxyunderchaos.LIGHTSABERS.get("blue_negotiator_lightsaber").get());
                        output.accept(LOST_HILT.get());
                        output.accept(AEGIS_HILT.get());
                        output.accept(APPRENTICE_HILT.get());
                        output.accept(CHOSEN_HILT.get());
                        output.accept(EMPEROR_HILT.get());
                        output.accept(FALLEN_HILT.get());
                        output.accept(GRACE_HILT.get());
                        output.accept(GUARD_HILT.get());
                        output.accept(HARMONY_HILT.get());
                        output.accept(LEGACY_HILT.get());
                        output.accept(PADAWAN_HILT.get());
                        output.accept(RESOLVE_HILT.get());
                        output.accept(SKUSTELL_HILT.get());
                        output.accept(TALON_HILT.get());
                        output.accept(VALOR_HILT.get());
                        output.accept(WISDOM_HILT.get());
                        output.accept(WISDOM_HILT.get());
                        output.accept(KNIGHTFALL_HILT.get());
                        output.accept(NEGOTIATOR_HILT.get());
                        output.accept(BAROSHE_HILT.get());

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
