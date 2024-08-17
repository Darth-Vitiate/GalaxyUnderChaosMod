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
                        output.accept(TYTHON_GRASS.get().asItem());
                        output.accept(TYTHON_DIRT.get().asItem());
                    }).build());

    public static final Supplier<CreativeModeTab> GALAXY_UNDER_CHAOS_ITEMS_TAB = CREATIVE_MODE_TABS.register("galaxy_under_chaos_items",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(SHUURA.get()))
                    .title(Component.translatable("creativetab.galaxyunderchaos.galaxy_under_chaos_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(SHUURA.get());
                        output.accept(JEDI_HOLOBOOK.get());
                        output.accept(ANCIENT_HOLOBOOK.get());
                        output.accept(SITH_HOLOBOOK.get());
                        output.accept(TYTHON_PORTAL_ITEM.get());
                        output.accept(NABOO_PORTAL_ITEM.get());

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
