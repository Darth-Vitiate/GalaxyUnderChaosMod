package server.galaxyunderchaos.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import server.galaxyunderchaos.data.ModDataComponentTypes;
import server.galaxyunderchaos.galaxyunderchaos;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        galaxyunderchaos.LIGHTSABERS.values().forEach(lightsaber -> {
            ItemProperties.register(lightsaber.get(),
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "active"),
                    (itemStack, clientLevel, livingEntity, i) ->
                            itemStack.getOrDefault(ModDataComponentTypes.ACTIVE.get(), false) ? 1.0F : 0.0F
            );
        });
    }
}