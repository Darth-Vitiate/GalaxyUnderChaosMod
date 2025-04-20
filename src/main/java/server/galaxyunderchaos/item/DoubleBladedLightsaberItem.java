// === DoubleBladedLightsaberItem.java ===
package server.galaxyunderchaos.item;

import client.renderer.DoubleBladedSaberRenderer;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;
import org.lwjgl.glfw.GLFW;
import server.galaxyunderchaos.galaxyunderchaos;

import java.util.Map;
import java.util.function.Consumer;

public class DoubleBladedLightsaberItem extends LightsaberItem {
    public DoubleBladedLightsaberItem(String combinedHiltName, Properties properties) {
        super(combinedHiltName, properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(this);
        if (id == null) return Component.literal("Unknown Saber");

        String path = id.getPath();
        if (path.endsWith("_double_lightsaber")) {
            path = path.replace("_double_lightsaber", "");
        }

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
        return input.length() > 0 ? input.substring(0, 1).toUpperCase() + input.substring(1) : input;
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
}
