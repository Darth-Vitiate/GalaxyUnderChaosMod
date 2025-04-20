package server.galaxyunderchaos.lightsaber;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import server.galaxyunderchaos.galaxyunderchaos;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import server.galaxyunderchaos.galaxyunderchaos;

public class LightsaberCrafting {

    public static ItemStack craftLightsaber(ItemStack hilt, ItemStack kyberCrystal) {
        if (hilt.isEmpty() || kyberCrystal.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ResourceLocation kyberRegistryName = BuiltInRegistries.ITEM.getKey(kyberCrystal.getItem());
        if (kyberRegistryName == null) return ItemStack.EMPTY;
        String kyberName = kyberRegistryName.getPath();
        String bladeColor = BladeColorRegistry.getBladeColor(kyberName);

        ResourceLocation hiltRegistryName = BuiltInRegistries.ITEM.getKey(hilt.getItem());
        if (hiltRegistryName == null) return ItemStack.EMPTY;
        String hiltName = hiltRegistryName.getPath().replace("_hilt", "");

        String lightsaberId = bladeColor + "_" + hiltName + "_lightsaber";
        if (!galaxyunderchaos.LIGHTSABERS.containsKey(lightsaberId)) return ItemStack.EMPTY;

        return new ItemStack(galaxyunderchaos.LIGHTSABERS.get(lightsaberId).get());
    }

    public static ItemStack craftDoubleBladedLightsaber(ItemStack hilt1, ItemStack kyber1, ItemStack hilt2, ItemStack kyber2) {
        if (hilt1.isEmpty() || hilt2.isEmpty() || kyber1.isEmpty() || kyber2.isEmpty()) return ItemStack.EMPTY;

        String hiltName1 = getHiltName(hilt1);
        String hiltName2 = getHiltName(hilt2);
        String color1 = getBladeColorName(kyber1);
        String color2 = getBladeColorName(kyber2);

        if (hiltName1 == null || hiltName2 == null || color1 == null || color2 == null) return ItemStack.EMPTY;

        String id = color1 + "_" + hiltName1 + "_and_" + color2 + "_" + hiltName2 + "_double_lightsaber";
        if (!galaxyunderchaos.DOUBLE_BLADED_LIGHTSABERS.containsKey(id)) return ItemStack.EMPTY;

        return new ItemStack(galaxyunderchaos.DOUBLE_BLADED_LIGHTSABERS.get(id).get());
    }

    private static String getHiltName(ItemStack hilt) {
        ResourceLocation key = BuiltInRegistries.ITEM.getKey(hilt.getItem());
        return key != null ? key.getPath().replace("_hilt", "") : null;
    }

    private static String getBladeColorName(ItemStack kyber) {
        ResourceLocation key = BuiltInRegistries.ITEM.getKey(kyber.getItem());
        return key != null ? BladeColorRegistry.getBladeColor(key.getPath()) : null;
    }
    public static ItemStack craftDoubleFromLightsabers(ItemStack saber1, ItemStack saber2) {
        ResourceLocation id1 = BuiltInRegistries.ITEM.getKey(saber1.getItem());
        ResourceLocation id2 = BuiltInRegistries.ITEM.getKey(saber2.getItem());

        if (id1 == null || id2 == null) return ItemStack.EMPTY;

        // Extract color + hilt from item ID (e.g., "blue_apprentice_lightsaber")
        String[] parts1 = id1.getPath().replace("_lightsaber", "").split("_", 2);
        String[] parts2 = id2.getPath().replace("_lightsaber", "").split("_", 2);

        if (parts1.length != 2 || parts2.length != 2) return ItemStack.EMPTY;

        String color1 = parts1[0];
        String hilt1 = parts1[1];
        String color2 = parts2[0];
        String hilt2 = parts2[1];

        String comboId = color1 + "_" + hilt1 + "_and_" + color2 + "_" + hilt2 + "_double_lightsaber";

        if (!galaxyunderchaos.DOUBLE_BLADED_LIGHTSABERS.containsKey(comboId)) return ItemStack.EMPTY;

        return new ItemStack(galaxyunderchaos.DOUBLE_BLADED_LIGHTSABERS.get(comboId).get());
    }

}

