
package server.galaxyunderchaos.item;

import client.renderer.DoubleBladedSaberRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class RegisteredDoubleBladedLightsaberItem extends DoubleBladedLightsaberItem {
    public RegisteredDoubleBladedLightsaberItem(String combinedHiltName, Properties properties) {
        super(combinedHiltName, properties);
    }

    @Override
    public String getDescriptionId() {
        return "item.galaxyunderchaos.placeholder_double_saber";
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new DoubleBladedSaberRenderer();
            }
        });
    }

    @Override
    public Component getName(ItemStack stack) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(this);
        if (id == null) return Component.literal("Unknown Saber");

        String path = id.getPath().replace("_double_lightsaber", "");
        String[] halves = path.split("_and_");
        if (halves.length != 2) return Component.literal("Double-Bladed Lightsaber");

        String[] part1 = halves[0].split("_", 2);
        String[] part2 = halves[1].split("_", 2);
        if (part1.length != 2 || part2.length != 2) return Component.literal("Double-Bladed Lightsaber");

        String hilt1 = capitalize(part1[1]);
        String hilt2 = capitalize(part2[1]);

        return Component.literal(hilt1 + "-" + hilt2 + " Double-Bladed Lightsaber");
    }

    private String capitalize(String input) {
        return input.length() > 0
                ? input.substring(0, 1).toUpperCase() + input.substring(1)
                : input;
    }
}
