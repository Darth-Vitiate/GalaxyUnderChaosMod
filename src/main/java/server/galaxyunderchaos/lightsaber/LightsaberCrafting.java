package server.galaxyunderchaos.lightsaber;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import server.galaxyunderchaos.galaxyunderchaos;

public class LightsaberCrafting {
    public static ItemStack craftLightsaber(ItemStack hilt, ItemStack kyberCrystal) {
        if (hilt.isEmpty() || kyberCrystal.isEmpty()) {
            return ItemStack.EMPTY;
        }

        // Get the kyber crystal color
        ResourceLocation kyberRegistryName = BuiltInRegistries.ITEM.getKey(kyberCrystal.getItem());
        if (kyberRegistryName == null) {
            return ItemStack.EMPTY; // Invalid kyber crystal
        }

        String kyberName = kyberRegistryName.getPath();
        String bladeColor = BladeColorRegistry.getBladeColor(kyberName);

        // Get the hilt name
        ResourceLocation hiltRegistryName = BuiltInRegistries.ITEM.getKey(hilt.getItem());
        if (hiltRegistryName == null) {
            return ItemStack.EMPTY; // Invalid hilt
        }

        String hiltName = hiltRegistryName.getPath().replace("_hilt", ""); // Remove "_hilt" from ID

        // Construct the lightsaber ID
        String lightsaberId = bladeColor + "_" + hiltName + "_lightsaber";

        // Retrieve the pre-registered lightsaber
        if (!galaxyunderchaos.LIGHTSABERS.containsKey(lightsaberId)) {
            return ItemStack.EMPTY; // No lightsaber registered for this combination
        }

        return new ItemStack(galaxyunderchaos.LIGHTSABERS.get(lightsaberId).get());
    }


}