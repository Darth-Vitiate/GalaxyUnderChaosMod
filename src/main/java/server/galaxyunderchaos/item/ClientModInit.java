package server.galaxyunderchaos.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import server.galaxyunderchaos.galaxyunderchaos;

import java.util.Map;

public class ClientModInit {
    public static void registerItemProperties() {
        for (Map.Entry<String, RegistryObject<Item>> entry : galaxyunderchaos.DOUBLE_BLADED_LIGHTSABERS.entrySet()) {
            ItemProperties.register(entry.getValue().get(), ResourceLocation.parse("active"),
                    (stack, level, entity, seed) -> {
                        if (stack.getItem() instanceof LightsaberItem item) {
                            return item.isActive(stack) ? 1.0F : 0.0F;
                        }
                        return 0.0F;
                    });
        }
    }
}
