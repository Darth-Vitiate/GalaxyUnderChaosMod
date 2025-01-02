package server.galaxyunderchaos.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import server.galaxyunderchaos.galaxyunderchaos;

import java.util.function.Supplier;

import static server.galaxyunderchaos.galaxyunderchaos.*;

public class CreativeMenuTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, galaxyunderchaos.MODID);

    public static final java.util.function.Supplier<CreativeModeTab> GALAXY_UNDER_CHAOS_TAB = CREATIVE_MODE_TABS.register("galaxy_under_chaos",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(FORCE_STONE.get())) // Use ModBlocks.FORCE_STONE
                    .title(Component.translatable("creativetab.galaxyunderchaos.galaxy_under_chaos_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(FORCE_STONE.get().asItem());
                        output.accept(FORCE_STONE_HOLOBOOK.get().asItem());
                        output.accept(FORCE_STONE_PILLAR.get().asItem());
                        output.accept(FORCE_STONE_STAIRS.get().asItem());
                        output.accept(FORCE_STONE_SLAB.get().asItem());
                        output.accept(DARK_FORCE_STONE.get().asItem());
                        output.accept(DARK_FORCE_STONE_HOLOBOOK.get().asItem());
                        output.accept(DARK_FORCE_STONE_PILLAR.get().asItem());
                        output.accept(DARK_FORCE_STONE_STAIRS.get().asItem());
                        output.accept(DARK_FORCE_STONE_SLAB.get().asItem());
                        output.accept(ANCIENT_FORCE_STONE.get().asItem());
                        output.accept(ANCIENT_FORCE_STONE_HOLOBOOK.get().asItem());
                        output.accept(ANCIENT_FORCE_STONE_PILLAR.get().asItem());
                        output.accept(ANCIENT_FORCE_STONE_CRACKED.get().asItem());
                        output.accept(ANCIENT_FORCE_STONE_STAIRS.get().asItem());
                        output.accept(ANCIENT_FORCE_STONE_SLAB.get().asItem());
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
                        output.accept(CHROMIUM_ORE.get().asItem());
                        output.accept(CHROMIUM_DEEPSLATE_ORE.get().asItem());
                        output.accept(TITANIUM_ORE.get().asItem());
                        output.accept(TITANIUM_DEEPSLATE_ORE.get().asItem());
                        output.accept(BLEEDING_TABLE.get().asItem());
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
                        output.accept(NABOO_PORTAL_ITEM.get());
                        output.accept(ILUM_PORTAL_ITEM.get());
                        output.accept(MUSTAFAR_PORTAL_ITEM.get());
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
                        output.accept(ANCIENT_HOLOBOOK.get());
                        output.accept(SITH_HOLOBOOK.get());
                        output.accept(SHUURA.get());



                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
